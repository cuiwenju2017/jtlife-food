package com.hz.coffeeshop.PLDroidShortVideo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.SensorManager;
import android.media.AudioFormat;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.base.commonlib.utils.StatusBarUtil;
import com.hz.coffeeshop.PLDroidShortVideo.utils.Config;
import com.hz.coffeeshop.PLDroidShortVideo.utils.GetPathFromUri;
import com.hz.coffeeshop.PLDroidShortVideo.utils.PermissionChecker;
import com.hz.coffeeshop.PLDroidShortVideo.utils.RecordSettings;
import com.hz.coffeeshop.PLDroidShortVideo.utils.ToastUtils;
import com.hz.coffeeshop.PLDroidShortVideo.view.CustomProgressDialog;
import com.hz.coffeeshop.PLDroidShortVideo.view.FocusIndicator;
import com.hz.coffeeshop.PLDroidShortVideo.view.FrameListView;
import com.hz.coffeeshop.PLDroidShortVideo.view.ImageSelectorPanel;
import com.hz.coffeeshop.PLDroidShortVideo.view.PaintSelectorPanel;
import com.hz.coffeeshop.PLDroidShortVideo.view.SectionProgressBar;
import com.hz.coffeeshop.PLDroidShortVideo.view.TextSelectorPanel;
import com.hz.coffeeshop.R;
import com.hz.coffeeshop.activity.CoffecMyPhotoActivity;
import com.qiniu.pili.droid.shortvideo.PLAudioEncodeSetting;
import com.qiniu.pili.droid.shortvideo.PLAuthenticationResultCallback;
import com.qiniu.pili.droid.shortvideo.PLBuiltinFilter;
import com.qiniu.pili.droid.shortvideo.PLCameraSetting;
import com.qiniu.pili.droid.shortvideo.PLCaptureFrameListener;
import com.qiniu.pili.droid.shortvideo.PLDraft;
import com.qiniu.pili.droid.shortvideo.PLDraftBox;
import com.qiniu.pili.droid.shortvideo.PLFaceBeautySetting;
import com.qiniu.pili.droid.shortvideo.PLFocusListener;
import com.qiniu.pili.droid.shortvideo.PLMicrophoneSetting;
import com.qiniu.pili.droid.shortvideo.PLRecordSetting;
import com.qiniu.pili.droid.shortvideo.PLRecordStateListener;
import com.qiniu.pili.droid.shortvideo.PLShortVideoEditor;
import com.qiniu.pili.droid.shortvideo.PLShortVideoEnv;
import com.qiniu.pili.droid.shortvideo.PLShortVideoRecorder;
import com.qiniu.pili.droid.shortvideo.PLVideoEditSetting;
import com.qiniu.pili.droid.shortvideo.PLVideoEncodeSetting;
import com.qiniu.pili.droid.shortvideo.PLVideoFrame;
import com.qiniu.pili.droid.shortvideo.PLVideoSaveListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import static com.hz.coffeeshop.PLDroidShortVideo.utils.RecordSettings.RECORD_SPEED_ARRAY;
import static com.hz.coffeeshop.PLDroidShortVideo.utils.RecordSettings.chooseCameraFacingId;

public class VideoRecordActivity extends Activity implements PLRecordStateListener, PLVideoSaveListener, PLFocusListener, View.OnClickListener {
    private static final String TAG = "VideoRecordActivity";

    public static final String PREVIEW_SIZE_RATIO = "PreviewSizeRatio";
    public static final String PREVIEW_SIZE_LEVEL = "PreviewSizeLevel";
    public static final String ENCODING_MODE = "EncodingMode";
    public static final String ENCODING_SIZE_LEVEL = "EncodingSizeLevel";
    public static final String ENCODING_BITRATE_LEVEL = "EncodingBitrateLevel";
    public static final String AUDIO_CHANNEL_NUM = "AudioChannelNum";
    public static final String DRAFT = "draft";

    /**
     * NOTICE: TUSDK needs extra cost
     */
    private static final boolean USE_TUSDK = true;
    private PLShortVideoRecorder mShortVideoRecorder;
    private SectionProgressBar mSectionProgressBar;
    private CustomProgressDialog mProcessingDialog;
    private View mRecordBtn;
    private View mDeleteBtn;
    private View mConcatBtn;
    private View mSwitchCameraBtn;
    private View mSwitchFlashBtn;
    private FocusIndicator mFocusIndicator;
    private long mLastRecordingPercentageViewUpdateTime = 0;
    private boolean mFlashEnabled;
    private boolean mIsEditVideo = false;
    private GestureDetector mGestureDetector;
    private PLCameraSetting mCameraSetting;
    private PLMicrophoneSetting mMicrophoneSetting;
    private PLRecordSetting mRecordSetting;
    private PLVideoEncodeSetting mVideoEncodeSetting;
    private PLAudioEncodeSetting mAudioEncodeSetting;
    private PLFaceBeautySetting mFaceBeautySetting;
    private int mFocusIndicatorX;
    private int mFocusIndicatorY;
    private double mRecordSpeed;
    private TextView mSpeedTextView, tv_recording;
    private Stack<Long> mDurationRecordStack = new Stack();
    private Stack<Double> mDurationVideoStack = new Stack();
    private OrientationEventListener mOrientationListener;
    private boolean mSectionBegan;
    private ImageView iv_speed, iv_my_photo;
    private LinearLayout ll_speed, ll_cancel;

    private Spinner mPreviewSizeRatioSpinner;
    private Spinner mPreviewSizeLevelSpinner;
    private Spinner mEncodingModeLevelSpinner;
    private Spinner mEncodingSizeLevelSpinner;
    private Spinner mEncodingBitrateLevelSpinner;
    private Spinner mAudioChannelNumSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_record);

        StatusBarUtil.setTranslucentStatus(this);//隐藏状态栏

        mSectionProgressBar = findViewById(R.id.record_progressbar);
        GLSurfaceView preview = findViewById(R.id.preview);
        mRecordBtn = findViewById(R.id.record);
        mDeleteBtn = findViewById(R.id.delete);
        mConcatBtn = findViewById(R.id.concat);
        mSwitchCameraBtn = findViewById(R.id.switch_camera);
        mSwitchFlashBtn = findViewById(R.id.switch_flash);
        mFocusIndicator = findViewById(R.id.focus_indicator);
        iv_speed = findViewById(R.id.iv_speed);
        ll_speed = findViewById(R.id.ll_speed);
        tv_recording = findViewById(R.id.tv_recording);
        iv_my_photo = findViewById(R.id.iv_my_photo);
        iv_speed.setOnClickListener(this);
        tv_recording.setOnClickListener(this);
        iv_my_photo.setOnClickListener(this);
        mProcessingDialog = new CustomProgressDialog(this);
        mPreviewSizeRatioSpinner = findViewById(R.id.PreviewSizeRatioSpinner);
        mPreviewSizeLevelSpinner = findViewById(R.id.PreviewSizeLevelSpinner);
        mEncodingModeLevelSpinner = findViewById(R.id.EncodingModeLevelSpinner);
        mEncodingSizeLevelSpinner = findViewById(R.id.EncodingSizeLevelSpinner);
        mEncodingBitrateLevelSpinner = findViewById(R.id.EncodingBitrateLevelSpinner);
        mAudioChannelNumSpinner = findViewById(R.id.AudioChannelNumSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.PREVIEW_SIZE_RATIO_TIPS_ARRAY);
        mPreviewSizeRatioSpinner.setAdapter(adapter);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.PREVIEW_SIZE_LEVEL_TIPS_ARRAY);
        mPreviewSizeLevelSpinner.setAdapter(adapter);
        mPreviewSizeLevelSpinner.setSelection(3);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.ENCODING_MODE_LEVEL_TIPS_ARRAY);
        mEncodingModeLevelSpinner.setAdapter(adapter);
        mEncodingModeLevelSpinner.setSelection(0);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.ENCODING_SIZE_LEVEL_TIPS_ARRAY);
        mEncodingSizeLevelSpinner.setAdapter(adapter);
        mEncodingSizeLevelSpinner.setSelection(7);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.ENCODING_BITRATE_LEVEL_TIPS_ARRAY);
        mEncodingBitrateLevelSpinner.setAdapter(adapter);
        mEncodingBitrateLevelSpinner.setSelection(2);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.AUDIO_CHANNEL_NUM_TIPS_ARRAY);
        mAudioChannelNumSpinner.setAdapter(adapter);
        mAudioChannelNumSpinner.setSelection(0);

        mProcessingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mShortVideoRecorder.cancelConcat();
            }
        });

        mShortVideoRecorder = new PLShortVideoRecorder();
        mShortVideoRecorder.setRecordStateListener(this);

        mRecordSpeed = RECORD_SPEED_ARRAY[2];
        mSpeedTextView = findViewById(R.id.normal_speed_text);

        String draftTag = getIntent().getStringExtra(DRAFT);
        if (draftTag == null) {
            int previewSizeRatioPos = getIntent().getIntExtra(PREVIEW_SIZE_RATIO, 0);
            int previewSizeLevelPos = getIntent().getIntExtra(PREVIEW_SIZE_LEVEL, 0);
            int encodingModePos = getIntent().getIntExtra(ENCODING_MODE, 0);
            int encodingSizeLevelPos = getIntent().getIntExtra(ENCODING_SIZE_LEVEL, 0);
            int encodingBitrateLevelPos = getIntent().getIntExtra(ENCODING_BITRATE_LEVEL, 0);
            int audioChannelNumPos = getIntent().getIntExtra(AUDIO_CHANNEL_NUM, 0);

            mCameraSetting = new PLCameraSetting();
            PLCameraSetting.CAMERA_FACING_ID facingId = chooseCameraFacingId();
            mCameraSetting.setCameraId(facingId);
            mCameraSetting.setCameraPreviewSizeRatio(RecordSettings.PREVIEW_SIZE_RATIO_ARRAY[previewSizeRatioPos]);
            mCameraSetting.setCameraPreviewSizeLevel(RecordSettings.PREVIEW_SIZE_LEVEL_ARRAY[previewSizeLevelPos]);

            mMicrophoneSetting = new PLMicrophoneSetting();
            mMicrophoneSetting.setChannelConfig(RecordSettings.AUDIO_CHANNEL_NUM_ARRAY[audioChannelNumPos] == 1 ?
                    AudioFormat.CHANNEL_IN_MONO : AudioFormat.CHANNEL_IN_STEREO);

            mVideoEncodeSetting = new PLVideoEncodeSetting(this);
            mVideoEncodeSetting.setEncodingSizeLevel(RecordSettings.ENCODING_SIZE_LEVEL_ARRAY[encodingSizeLevelPos]);
            mVideoEncodeSetting.setEncodingBitrate(RecordSettings.ENCODING_BITRATE_LEVEL_ARRAY[encodingBitrateLevelPos]);
            mVideoEncodeSetting.setHWCodecEnabled(encodingModePos == 0);
            mVideoEncodeSetting.setConstFrameRateEnabled(true);

            mAudioEncodeSetting = new PLAudioEncodeSetting();
            mAudioEncodeSetting.setHWCodecEnabled(encodingModePos == 0);
            mAudioEncodeSetting.setChannels(RecordSettings.AUDIO_CHANNEL_NUM_ARRAY[audioChannelNumPos]);

            mRecordSetting = new PLRecordSetting();
            mRecordSetting.setMaxRecordDuration(RecordSettings.DEFAULT_MAX_RECORD_DURATION);
            mRecordSetting.setRecordSpeedVariable(true);
            mRecordSetting.setVideoCacheDir(Config.VIDEO_STORAGE_DIR);
            mRecordSetting.setVideoFilepath(Config.RECORD_FILE_PATH);

            mFaceBeautySetting = new PLFaceBeautySetting(1.0f, 0.5f, 0.5f);

            mShortVideoRecorder.prepare(preview, mCameraSetting, mMicrophoneSetting, mVideoEncodeSetting,
                    mAudioEncodeSetting, USE_TUSDK ? null : mFaceBeautySetting, mRecordSetting);

            mSectionProgressBar.setFirstPointTime(RecordSettings.DEFAULT_MIN_RECORD_DURATION);
            onSectionCountChanged(0, 0);
        } else {
            PLDraft draft = PLDraftBox.getInstance(this).getDraftByTag(draftTag);
            if (draft == null) {
                ToastUtils.s(this, getString(R.string.toast_draft_recover_fail));
                finish();
            }

            mCameraSetting = draft.getCameraSetting();
            mMicrophoneSetting = draft.getMicrophoneSetting();
            mVideoEncodeSetting = draft.getVideoEncodeSetting();
            mAudioEncodeSetting = draft.getAudioEncodeSetting();
            mRecordSetting = draft.getRecordSetting();
            mFaceBeautySetting = draft.getFaceBeautySetting();

            if (mShortVideoRecorder.recoverFromDraft(preview, draft)) {
                long draftDuration = 0;
                for (int i = 0; i < draft.getSectionCount(); ++i) {
                    long currentDuration = draft.getSectionDuration(i);
                    draftDuration += draft.getSectionDuration(i);
                    onSectionIncreased(currentDuration, draftDuration, i + 1);
                    if (!mDurationRecordStack.isEmpty()) {
                        mDurationRecordStack.pop();
                    }
                }
                mSectionProgressBar.setFirstPointTime(draftDuration);
                ToastUtils.s(this, getString(R.string.toast_draft_recover_success));
            } else {
                onSectionCountChanged(0, 0);
                mSectionProgressBar.setFirstPointTime(RecordSettings.DEFAULT_MIN_RECORD_DURATION);
                ToastUtils.s(this, getString(R.string.toast_draft_recover_fail));
            }
        }
        mShortVideoRecorder.setRecordSpeed(mRecordSpeed);
        mSectionProgressBar.setProceedingSpeed(mRecordSpeed);
        mSectionProgressBar.setTotalTime(this, mRecordSetting.getMaxRecordDuration());

        mRecordBtn.setOnTouchListener(new View.OnTouchListener() {
            private long mSectionBeginTSMs;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    if (!mSectionBegan && mShortVideoRecorder.beginSection()) {
                        mSectionBegan = true;
                        mSectionBeginTSMs = System.currentTimeMillis();
                        mSectionProgressBar.setCurrentState(SectionProgressBar.State.START);
                        updateRecordingBtns(true);
                    } else {
                        ToastUtils.s(VideoRecordActivity.this, "无法开始视频段录制");
                    }
                } else if (action == MotionEvent.ACTION_UP) {
                    if (mSectionBegan) {
                        long sectionRecordDurationMs = System.currentTimeMillis() - mSectionBeginTSMs;
                        long totalRecordDurationMs = sectionRecordDurationMs + (mDurationRecordStack.isEmpty() ? 0 : mDurationRecordStack.peek().longValue());
                        double sectionVideoDurationMs = sectionRecordDurationMs / mRecordSpeed;
                        double totalVideoDurationMs = sectionVideoDurationMs + (mDurationVideoStack.isEmpty() ? 0 : mDurationVideoStack.peek().doubleValue());
                        mDurationRecordStack.push(new Long(totalRecordDurationMs));
                        mDurationVideoStack.push(new Double(totalVideoDurationMs));
                        if (mRecordSetting.IsRecordSpeedVariable()) {
                            Log.d(TAG, "SectionRecordDuration: " + sectionRecordDurationMs + "; sectionVideoDuration: " + sectionVideoDurationMs + "; totalVideoDurationMs: " + totalVideoDurationMs + "Section count: " + mDurationVideoStack.size());
                            mSectionProgressBar.addBreakPointTime((long) totalVideoDurationMs);
                        } else {
                            mSectionProgressBar.addBreakPointTime(totalRecordDurationMs);
                        }

                        mSectionProgressBar.setCurrentState(SectionProgressBar.State.PAUSE);
                        mShortVideoRecorder.endSection();
                        mSectionBegan = false;
                    }
                }

                return false;
            }
        });
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                mFocusIndicatorX = (int) e.getX() - mFocusIndicator.getWidth() / 2;
                mFocusIndicatorY = (int) e.getY() - mFocusIndicator.getHeight() / 2;
                mShortVideoRecorder.manualFocus(mFocusIndicator.getWidth(), mFocusIndicator.getHeight(), (int) e.getX(), (int) e.getY());
                return false;
            }
        });
        preview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

        mOrientationListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL) {
            @Override
            public void onOrientationChanged(int orientation) {
                int rotation = getScreenRotation(orientation);
                if (!mSectionProgressBar.isRecorded() && !mSectionBegan) {
                    mVideoEncodeSetting.setRotationInMetadata(rotation);
                }
            }
        };
        if (mOrientationListener.canDetectOrientation()) {
            mOrientationListener.enable();
        }
    }

    private int getScreenRotation(int orientation) {
        int screenRotation = 0;
        boolean isPortraitScreen = getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        if (orientation >= 315 || orientation < 45) {
            screenRotation = isPortraitScreen ? 0 : 90;
        } else if (orientation >= 45 && orientation < 135) {
            screenRotation = isPortraitScreen ? 90 : 180;
        } else if (orientation >= 135 && orientation < 225) {
            screenRotation = isPortraitScreen ? 180 : 270;
        } else if (orientation >= 225 && orientation < 315) {
            screenRotation = isPortraitScreen ? 270 : 0;
        }
        return screenRotation;
    }

    private void updateRecordingBtns(boolean isRecording) {
        mSwitchCameraBtn.setEnabled(!isRecording);
        mRecordBtn.setActivated(isRecording);
    }

    public void onScreenRotation(View v) {
        if (mDeleteBtn.isEnabled()) {
            ToastUtils.s(this, "已经开始拍摄，无法旋转屏幕。");
        } else {
            setRequestedOrientation(
                    getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ?
                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE :
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public void onCaptureFrame(View v) {
        mShortVideoRecorder.captureFrame(new PLCaptureFrameListener() {
            @Override
            public void onFrameCaptured(PLVideoFrame capturedFrame) {
                if (capturedFrame == null) {
                    Log.e(TAG, "capture frame failed");
                    return;
                }

                Log.i(TAG, "captured frame width: " + capturedFrame.getWidth() + " height: " + capturedFrame.getHeight() + " timestamp: " + capturedFrame.getTimestampMs());
                try {
                    FileOutputStream fos = new FileOutputStream(Config.CAPTURED_FRAME_FILE_PATH);
                    capturedFrame.toBitmap().compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.close();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.s(VideoRecordActivity.this, "截帧已保存到路径：" + Config.CAPTURED_FRAME_FILE_PATH);
                        }
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mRecordBtn.setEnabled(false);
        mShortVideoRecorder.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateRecordingBtns(false);
        mShortVideoRecorder.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mShortVideoRecorder.destroy();
        mOrientationListener.disable();
    }

    public void onClickDelete(View v) {
        if (!mShortVideoRecorder.deleteLastSection()) {
            ToastUtils.s(this, "回删视频段失败");
        }
    }

    public void onClickConcat(View v) {
        //mProcessingDialog.show();
        showChooseDialog();
    }

    /*public void onClickBrightness(View v) {
        boolean isVisible = mAdjustBrightnessSeekBar.getVisibility() == View.VISIBLE;
        mAdjustBrightnessSeekBar.setVisibility(isVisible ? View.GONE : View.VISIBLE);
    }*/

    public void onClickSwitchCamera(View v) {
        mShortVideoRecorder.switchCamera();
        mFocusIndicator.focusCancel();
    }

    public void onClickSwitchFlash(View v) {
        mFlashEnabled = !mFlashEnabled;
        mShortVideoRecorder.setFlashEnabled(mFlashEnabled);
        mSwitchFlashBtn.setActivated(mFlashEnabled);
    }

    public void onClickAddMixAudio(View v) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT < 19) {
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("audio/*");
        } else {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("audio/*");
        }
        startActivityForResult(Intent.createChooser(intent, "请选择混音文件："), 0);
    }

    public void onClickSaveToDraft(View v) {
        final EditText editText = new EditText(this);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this)
                .setView(editText)
                .setTitle(getString(R.string.dlg_save_draft_title))
                .setPositiveButton(getString(R.string.dlg_save_draft_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtils.s(VideoRecordActivity.this,
                                mShortVideoRecorder.saveToDraftBox(editText.getText().toString()) ?
                                        getString(R.string.toast_draft_save_success) : getString(R.string.toast_draft_save_fail));
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            String selectedFilepath = GetPathFromUri.getPath(this, data.getData());
            Log.i(TAG, "Select file: " + selectedFilepath);
            if (selectedFilepath != null && !"".equals(selectedFilepath)) {
                mShortVideoRecorder.setMusicFile(selectedFilepath);
            }
        }
    }

    @Override
    public void onReady() {
        mShortVideoRecorder.setFocusListener(this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSwitchFlashBtn.setVisibility(mShortVideoRecorder.isFlashSupport() ? View.VISIBLE : View.GONE);
                mFlashEnabled = false;
                mSwitchFlashBtn.setActivated(mFlashEnabled);
                mRecordBtn.setEnabled(true);
                refreshSeekBar();
                ToastUtils.s(VideoRecordActivity.this, "可以开始拍摄咯");
            }
        });
    }

    @Override
    public void onError(final int code) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.toastErrorCode(VideoRecordActivity.this, code);
            }
        });
    }

    @Override
    public void onDurationTooShort() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.s(VideoRecordActivity.this, "该视频段太短了");
            }
        });
    }

    @Override
    public void onRecordStarted() {
        Log.i(TAG, "record start time: " + System.currentTimeMillis());
    }

    @Override
    public void onRecordStopped() {
        Log.i(TAG, "record stop time: " + System.currentTimeMillis());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateRecordingBtns(false);
            }
        });
    }

    @Override
    public void onSectionRecording(long sectionDurationMs, long videoDurationMs, int sectionCount) {
        Log.d(TAG, "sectionDurationMs: " + sectionDurationMs + "; videoDurationMs: " + videoDurationMs + "; sectionCount: " + sectionCount);
        updateRecordingPercentageView(videoDurationMs);
    }

    @Override
    public void onSectionIncreased(long incDuration, long totalDuration, int sectionCount) {
        double videoSectionDuration = mDurationVideoStack.isEmpty() ? 0 : mDurationVideoStack.peek().doubleValue();
        if ((videoSectionDuration + incDuration / mRecordSpeed) >= mRecordSetting.getMaxRecordDuration()) {
            videoSectionDuration = mRecordSetting.getMaxRecordDuration();
        }
        Log.d(TAG, "videoSectionDuration: " + videoSectionDuration + "; incDuration: " + incDuration);
        onSectionCountChanged(sectionCount, (long) videoSectionDuration);
    }

    @Override
    public void onSectionDecreased(long decDuration, long totalDuration, int sectionCount) {
        mSectionProgressBar.removeLastBreakPoint();
        if (!mDurationVideoStack.isEmpty()) {
            mDurationVideoStack.pop();
        }
        if (!mDurationRecordStack.isEmpty()) {
            mDurationRecordStack.pop();
        }
        double currentDuration = mDurationVideoStack.isEmpty() ? 0 : mDurationVideoStack.peek().doubleValue();
        onSectionCountChanged(sectionCount, (long) currentDuration);
        updateRecordingPercentageView((long) currentDuration);
    }

    @Override
    public void onRecordCompleted() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.s(VideoRecordActivity.this, "已达到拍摄总时长");
            }
        });
    }

    @Override
    public void onProgressUpdate(final float percentage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProcessingDialog.setProgress((int) (100 * percentage));
            }
        });
    }

    @Override
    public void onSaveVideoFailed(final int errorCode) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProcessingDialog.dismiss();
                ToastUtils.s(VideoRecordActivity.this, "拼接视频段失败: " + errorCode);
            }
        });
    }

    @Override
    public void onSaveVideoCanceled() {
        mProcessingDialog.dismiss();
    }

    @Override
    public void onSaveVideoSuccess(final String filePath) {
        Log.i(TAG, "concat sections success filePath: " + filePath);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProcessingDialog.dismiss();
                int screenOrientation = (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE == getRequestedOrientation()) ? 0 : 1;
                if (mIsEditVideo) {
                    VideoEditActivity.start(VideoRecordActivity.this, filePath, screenOrientation);
                } else {
                    PlaybackActivity.start(VideoRecordActivity.this, filePath, screenOrientation);
                }
            }
        });
    }

    private void updateRecordingPercentageView(long currentDuration) {
        final int per = (int) (100 * currentDuration / mRecordSetting.getMaxRecordDuration());
        final long curTime = System.currentTimeMillis();
        if ((mLastRecordingPercentageViewUpdateTime != 0) && (curTime - mLastRecordingPercentageViewUpdateTime < 100)) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mLastRecordingPercentageViewUpdateTime = curTime;
            }
        });
    }

    private void refreshSeekBar() {
        final int max = mShortVideoRecorder.getMaxExposureCompensation();
        final int min = mShortVideoRecorder.getMinExposureCompensation();
        boolean brightnessAdjustAvailable = (max != 0 || min != 0);
        Log.e(TAG, "max/min exposure compensation: " + max + "/" + min + " brightness adjust available: " + brightnessAdjustAvailable);
    }

    private void onSectionCountChanged(final int count, final long totalTime) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDeleteBtn.setEnabled(count > 0);
                mConcatBtn.setEnabled(totalTime >= (RecordSettings.DEFAULT_MIN_RECORD_DURATION));
            }
        });
    }

    private void showChooseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.if_edit_video));
        builder.setPositiveButton(getString(R.string.dlg_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mIsEditVideo = true;
                mShortVideoRecorder.concatSections(VideoRecordActivity.this);
            }
        });
        builder.setNegativeButton(getString(R.string.dlg_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               /* mIsEditVideo = false;
                mShortVideoRecorder.concatSections(VideoRecordActivity.this);*/
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    public void onSpeedClicked(View view) {
        if (!mVideoEncodeSetting.IsConstFrameRateEnabled() || !mRecordSetting.IsRecordSpeedVariable()) {
            if (mSectionProgressBar.isRecorded()) {
                ToastUtils.s(this, "变帧率模式下，无法在拍摄中途修改拍摄倍数！");
                return;
            }
        }

        if (mSpeedTextView != null) {
            mSpeedTextView.setTextColor(getResources().getColor(R.color.speedTextNormal));
        }

        TextView textView = (TextView) view;
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        mSpeedTextView = textView;

        switch (view.getId()) {
            case R.id.super_slow_speed_text:
                mRecordSpeed = RECORD_SPEED_ARRAY[0];
                break;
            case R.id.slow_speed_text:
                mRecordSpeed = RECORD_SPEED_ARRAY[1];
                break;
            case R.id.normal_speed_text:
                mRecordSpeed = RECORD_SPEED_ARRAY[2];
                break;
            case R.id.fast_speed_text:
                mRecordSpeed = RECORD_SPEED_ARRAY[3];
                break;
            case R.id.super_fast_speed_text:
                mRecordSpeed = RECORD_SPEED_ARRAY[4];
                break;
        }

        mShortVideoRecorder.setRecordSpeed(mRecordSpeed);
        if (mRecordSetting.IsRecordSpeedVariable() && mVideoEncodeSetting.IsConstFrameRateEnabled()) {
            mSectionProgressBar.setProceedingSpeed(mRecordSpeed);
            mRecordSetting.setMaxRecordDuration(RecordSettings.DEFAULT_MAX_RECORD_DURATION);
            mSectionProgressBar.setFirstPointTime(RecordSettings.DEFAULT_MIN_RECORD_DURATION);
        } else {
            mRecordSetting.setMaxRecordDuration((long) (RecordSettings.DEFAULT_MAX_RECORD_DURATION * mRecordSpeed));
            mSectionProgressBar.setFirstPointTime((long) (RecordSettings.DEFAULT_MIN_RECORD_DURATION * mRecordSpeed));
        }

        mSectionProgressBar.setTotalTime(this, mRecordSetting.getMaxRecordDuration());
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onManualFocusStart(boolean result) {
        if (result) {
            Log.i(TAG, "manual focus begin success");
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFocusIndicator.getLayoutParams();
            lp.leftMargin = mFocusIndicatorX;
            lp.topMargin = mFocusIndicatorY;
            mFocusIndicator.setLayoutParams(lp);
            mFocusIndicator.focus();
        } else {
            mFocusIndicator.focusCancel();
            Log.i(TAG, "manual focus not supported");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onManualFocusStop(boolean result) {
        Log.i(TAG, "manual focus end result: " + result);
        if (result) {
            mFocusIndicator.focusSuccess();
        } else {
            mFocusIndicator.focusFail();
        }
    }

    @Override
    public void onManualFocusCancel() {
        Log.i(TAG, "manual focus canceled");
        mFocusIndicator.focusCancel();
    }

    @Override
    public void onAutoFocusStart() {
        Log.i(TAG, "auto focus start");
    }

    @Override
    public void onAutoFocusStop() {
        Log.i(TAG, "auto focus stop");
    }

    private boolean isShow = false;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_speed) {
            if (!isShow) {
                ll_speed.setVisibility(View.VISIBLE);
                isShow = true;
            } else {
                ll_speed.setVisibility(View.GONE);
                isShow = false;
            }
        } else if (i == R.id.tv_recording) {//录音
            if (isPermissionOK()) {
                jumpToAudioCaptureActivity();
            }
        } else if (i == R.id.iv_my_photo) {//我的相册
            startActivity(new Intent(this, CoffecMyPhotoActivity.class));
        }
    }

    public void jumpToAudioCaptureActivity() {
        Intent intent = new Intent(this, AudioRecordActivity.class);
        intent.putExtra(AudioRecordActivity.ENCODING_MODE, mEncodingModeLevelSpinner.getSelectedItemPosition());
        intent.putExtra(AudioRecordActivity.AUDIO_CHANNEL_NUM, mAudioChannelNumSpinner.getSelectedItemPosition());
        startActivity(intent);
    }

    private boolean isPermissionOK() {
        PermissionChecker checker = new PermissionChecker(this);
        boolean isPermissionOK = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checker.checkPermission();
        if (!isPermissionOK) {
            ToastUtils.s(this, "某些权限未被批准!!!");
        }
        return isPermissionOK;
    }

}

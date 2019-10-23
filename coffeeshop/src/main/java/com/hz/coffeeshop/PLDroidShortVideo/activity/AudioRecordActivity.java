package com.hz.coffeeshop.PLDroidShortVideo.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.media.AudioFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commonlib.utils.StatusBarUtil;
import com.hz.coffeeshop.PLDroidShortVideo.utils.Config;
import com.hz.coffeeshop.PLDroidShortVideo.utils.RecordSettings;
import com.hz.coffeeshop.PLDroidShortVideo.utils.ToastUtils;
import com.hz.coffeeshop.PLDroidShortVideo.view.CustomProgressDialog;
import com.hz.coffeeshop.PLDroidShortVideo.view.SectionProgressBar;
import com.hz.coffeeshop.R;
import com.qiniu.pili.droid.shortvideo.PLAudioEncodeSetting;
import com.qiniu.pili.droid.shortvideo.PLErrorCode;
import com.qiniu.pili.droid.shortvideo.PLMicrophoneSetting;
import com.qiniu.pili.droid.shortvideo.PLRecordSetting;
import com.qiniu.pili.droid.shortvideo.PLRecordStateListener;
import com.qiniu.pili.droid.shortvideo.PLShortAudioRecorder;
import com.qiniu.pili.droid.shortvideo.PLVideoSaveListener;

public class AudioRecordActivity extends Activity implements PLRecordStateListener, PLVideoSaveListener, View.OnClickListener {

    private static final String TAG = "AudioRecordActivity";
    public static final String ENCODING_MODE = "EncodingMode";
    public static final String AUDIO_CHANNEL_NUM = "AudioChannelNum";
    private PLShortAudioRecorder mShortAudioRecorder;
    private SectionProgressBar mSectionProgressBar;
    private CustomProgressDialog mProcessingDialog;
    private View mRecordBtn;
    private View mDeleteBtn;
    private View mConcatBtn;
    private String mRecordErrorMsg;
    private TextView tv_video;
    private ImageView iv;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_audio_record);
        StatusBarUtil.setTranslucentStatus(this);//隐藏状态栏
        int encodingModePos = getIntent().getIntExtra(ENCODING_MODE, 0);
        int audioChannelNumPos = getIntent().getIntExtra(AUDIO_CHANNEL_NUM, 0);

        mSectionProgressBar = (SectionProgressBar) findViewById(R.id.record_progressbar);
        mRecordBtn = findViewById(R.id.record);
        mDeleteBtn = findViewById(R.id.delete);
        mConcatBtn = findViewById(R.id.concat);
        tv_video = findViewById(R.id.tv_video);
        iv = findViewById(R.id.iv);
        tv_video.setOnClickListener(this);
        iv.setOnClickListener(this);
        initAnimator();
        mProcessingDialog = new CustomProgressDialog(this);
        mProcessingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mShortAudioRecorder.cancelConcat();
            }
        });

        mShortAudioRecorder = new PLShortAudioRecorder();
        mShortAudioRecorder.setRecordStateListener(this);

        PLMicrophoneSetting microphoneSetting = new PLMicrophoneSetting();
        microphoneSetting.setChannelConfig(RecordSettings.AUDIO_CHANNEL_NUM_ARRAY[audioChannelNumPos] == 1 ?
                AudioFormat.CHANNEL_IN_MONO : AudioFormat.CHANNEL_IN_STEREO);

        PLAudioEncodeSetting audioEncodeSetting = new PLAudioEncodeSetting();
        audioEncodeSetting.setHWCodecEnabled(encodingModePos == 0);
        audioEncodeSetting.setChannels(RecordSettings.AUDIO_CHANNEL_NUM_ARRAY[audioChannelNumPos]);

        PLRecordSetting recordSetting = new PLRecordSetting();
        recordSetting.setMaxRecordDuration(RecordSettings.DEFAULT_MAX_RECORD_DURATION);
        recordSetting.setVideoCacheDir(Config.VIDEO_STORAGE_DIR);
        recordSetting.setVideoFilepath(Config.AUDIO_RECORD_FILE_PATH);

        mShortAudioRecorder.prepare(this, microphoneSetting,
                audioEncodeSetting, recordSetting);

        mSectionProgressBar.setFirstPointTime(RecordSettings.DEFAULT_MIN_RECORD_DURATION);
        mSectionProgressBar.setTotalTime(this, recordSetting.getMaxRecordDuration());

        mRecordBtn.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    if (mShortAudioRecorder.beginSection()) {
                        updateRecordingBtns(true);
                        mAnimator.resume();//动画继续
                    } else {
                        ToastUtils.s(AudioRecordActivity.this, "无法开始视频段录制");
                    }
                } else if (action == MotionEvent.ACTION_UP) {
                    mShortAudioRecorder.endSection();
                    updateRecordingBtns(false);
                    mAnimator.pause();//动画暂停
                }
                return false;
            }
        });
        onSectionCountChanged(0, 0);
    }

    private ObjectAnimator mAnimator;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initAnimator() {
        mAnimator = ObjectAnimator.ofFloat(iv, "rotation", 0.0f, 360.0f);
        mAnimator.setDuration(3000);//设定转一圈的时间
        mAnimator.setRepeatCount(Animation.INFINITE);//设定无限循环
        mAnimator.setRepeatMode(ObjectAnimator.RESTART);// 循环模式
        mAnimator.setInterpolator(new LinearInterpolator());// 匀速
        mAnimator.start();//动画开始
        mAnimator.pause();//动画暂停
    }

    private void updateRecordingBtns(boolean isRecording) {
        mRecordBtn.setActivated(isRecording);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecordBtn.setEnabled(false);
        mShortAudioRecorder.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateRecordingBtns(false);
        mShortAudioRecorder.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mShortAudioRecorder.destroy();
    }

    public void onClickDelete(View v) {
        if (!mShortAudioRecorder.deleteLastSection()) {
            ToastUtils.s(this, "回删视频段失败");
        }
    }

    public void onClickConcat(View v) {//上传音频
        showChooseDialog();
    }

    private void showChooseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.upload));
        builder.setPositiveButton(getString(R.string.dlg_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton(getString(R.string.dlg_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    @Override
    public void onReady() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRecordBtn.setEnabled(true);
                ToastUtils.s(AudioRecordActivity.this, "可以开始录音咯");
            }
        });
    }

    @Override
    public void onError(int code) {
        if (code == PLErrorCode.ERROR_SETUP_MICROPHONE_FAILED) {
            mRecordErrorMsg = "麦克风配置错误";
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.s(AudioRecordActivity.this, mRecordErrorMsg);
            }
        });
    }

    @Override
    public void onDurationTooShort() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.s(AudioRecordActivity.this, "该视频段太短了");
            }
        });
    }

    @Override
    public void onRecordStarted() {
        Log.i(TAG, "record start time: " + System.currentTimeMillis());
        mSectionProgressBar.setCurrentState(SectionProgressBar.State.START);
    }

    @Override
    public void onRecordStopped() {
        Log.i(TAG, "record stop time: " + System.currentTimeMillis());
        mSectionProgressBar.setCurrentState(SectionProgressBar.State.PAUSE);
    }


    @Override
    public void onSectionRecording(long sectionDurationMs, long videoDurationMs, int sectionCount) {
        Log.d(TAG, "sectionDurationMs: " + sectionDurationMs + "; videoDurationMs: " + videoDurationMs + "; sectionCount: " + sectionCount);
    }

    @Override
    public void onSectionIncreased(long incDuration, long totalDuration, int sectionCount) {
        Log.i(TAG, "section increased incDuration: " + incDuration + " totalDuration: " + totalDuration + " sectionCount: " + sectionCount);
        onSectionCountChanged(sectionCount, totalDuration);
        mSectionProgressBar.addBreakPointTime(totalDuration);
    }

    @Override
    public void onSectionDecreased(long decDuration, long totalDuration, int sectionCount) {
        Log.i(TAG, "section decreased decDuration: " + decDuration + " totalDuration: " + totalDuration + " sectionCount: " + sectionCount);
        onSectionCountChanged(sectionCount, totalDuration);
        mSectionProgressBar.removeLastBreakPoint();
    }

    @Override
    public void onRecordCompleted() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.s(AudioRecordActivity.this, "已达到拍摄总时长");
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
                ToastUtils.s(AudioRecordActivity.this, "拼接视频段失败: " + errorCode);
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
                PlaybackActivity.start(AudioRecordActivity.this, filePath);
            }
        });
    }

    private void onSectionCountChanged(final int count, final long totalTime) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDeleteBtn.setEnabled(count > 0);
                mConcatBtn.setEnabled(totalTime >= RecordSettings.DEFAULT_MIN_RECORD_DURATION);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_video) {//视频
            finish();
        }
    }
}

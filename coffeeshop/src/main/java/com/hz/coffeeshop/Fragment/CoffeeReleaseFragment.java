package com.hz.coffeeshop.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.hz.coffeeshop.PLDroidShortVideo.activity.VideoRecordActivity;
import com.hz.coffeeshop.PLDroidShortVideo.utils.PermissionChecker;
import com.hz.coffeeshop.PLDroidShortVideo.utils.RecordSettings;
import com.hz.coffeeshop.PLDroidShortVideo.utils.ToastUtils;
import com.hz.coffeeshop.R;
import com.qiniu.pili.droid.shortvideo.PLAuthenticationResultCallback;
import com.qiniu.pili.droid.shortvideo.PLShortVideoEnv;

/**
 * 发布
 */
public class CoffeeReleaseFragment extends BaseFragment implements View.OnClickListener {

    private Button btn_issue;

    private Spinner mPreviewSizeRatioSpinner;
    private Spinner mPreviewSizeLevelSpinner;
    private Spinner mEncodingModeLevelSpinner;
    private Spinner mEncodingSizeLevelSpinner;
    private Spinner mEncodingBitrateLevelSpinner;
    private Spinner mAudioChannelNumSpinner;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_coffee_release;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        btn_issue = view.findViewById(R.id.btn_issue);
        btn_issue.setOnClickListener(this);

        mPreviewSizeRatioSpinner = view.findViewById(R.id.PreviewSizeRatioSpinner);
        mPreviewSizeLevelSpinner = view.findViewById(R.id.PreviewSizeLevelSpinner);
        mEncodingModeLevelSpinner = view.findViewById(R.id.EncodingModeLevelSpinner);
        mEncodingSizeLevelSpinner = view.findViewById(R.id.EncodingSizeLevelSpinner);
        mEncodingBitrateLevelSpinner = view.findViewById(R.id.EncodingBitrateLevelSpinner);
        mAudioChannelNumSpinner = view.findViewById(R.id.AudioChannelNumSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, RecordSettings.PREVIEW_SIZE_RATIO_TIPS_ARRAY);
        mPreviewSizeRatioSpinner.setAdapter(adapter);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, RecordSettings.PREVIEW_SIZE_LEVEL_TIPS_ARRAY);
        mPreviewSizeLevelSpinner.setAdapter(adapter);
        mPreviewSizeLevelSpinner.setSelection(3);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, RecordSettings.ENCODING_MODE_LEVEL_TIPS_ARRAY);
        mEncodingModeLevelSpinner.setAdapter(adapter);
        mEncodingModeLevelSpinner.setSelection(0);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, RecordSettings.ENCODING_SIZE_LEVEL_TIPS_ARRAY);
        mEncodingSizeLevelSpinner.setAdapter(adapter);
        mEncodingSizeLevelSpinner.setSelection(7);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, RecordSettings.ENCODING_BITRATE_LEVEL_TIPS_ARRAY);
        mEncodingBitrateLevelSpinner.setAdapter(adapter);
        mEncodingBitrateLevelSpinner.setSelection(2);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, RecordSettings.AUDIO_CHANNEL_NUM_TIPS_ARRAY);
        mAudioChannelNumSpinner.setAdapter(adapter);
        mAudioChannelNumSpinner.setSelection(0);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void fetchData() {

    }

    private boolean isPermissionOK() {
        PermissionChecker checker = new PermissionChecker(getActivity());
        boolean isPermissionOK = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checker.checkPermission();
        if (!isPermissionOK) {
            ToastUtils.s(getActivity(), "某些权限未被批准!!!");
        }
        return isPermissionOK;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_issue) {//发布
            if (isPermissionOK()) {
                jumpToCaptureActivity();
            }
        }
    }

    public void jumpToCaptureActivity() {
        Intent intent = new Intent(getActivity(), VideoRecordActivity.class);
        intent.putExtra(VideoRecordActivity.PREVIEW_SIZE_RATIO, mPreviewSizeRatioSpinner.getSelectedItemPosition());
        intent.putExtra(VideoRecordActivity.PREVIEW_SIZE_LEVEL, mPreviewSizeLevelSpinner.getSelectedItemPosition());
        intent.putExtra(VideoRecordActivity.ENCODING_MODE, mEncodingModeLevelSpinner.getSelectedItemPosition());
        intent.putExtra(VideoRecordActivity.ENCODING_SIZE_LEVEL, mEncodingSizeLevelSpinner.getSelectedItemPosition());
        intent.putExtra(VideoRecordActivity.ENCODING_BITRATE_LEVEL, mEncodingBitrateLevelSpinner.getSelectedItemPosition());
        intent.putExtra(VideoRecordActivity.AUDIO_CHANNEL_NUM, mAudioChannelNumSpinner.getSelectedItemPosition());
        startActivity(intent);
    }

}

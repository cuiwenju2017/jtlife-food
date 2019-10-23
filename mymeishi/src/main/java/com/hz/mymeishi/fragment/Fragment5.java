package com.hz.mymeishi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.bean.BaseData;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.observer.DataObserver;
import com.base.commonlib.view.LoadingDialog;
import com.hz.mymeishi.R;
import com.hz.mymeishi.View.PointImageView;
import com.hz.mymeishi.activity.CollectionActivity;
import com.hz.mymeishi.activity.GetAccountListActivity;
import com.hz.mymeishi.activity.ModifyPictureActivity;
import com.hz.mymeishi.activity.NewAddressActivity;
import com.hz.mymeishi.activity.OrderlistActivity;
import com.hz.mymeishi.activity.RefundActivity;
import com.hz.mymeishi.api.GitHubService;
import com.hz.mymeishi.model.GetKayue;
import com.hz.mymeishi.model.Getordernum;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Fragment5 extends BaseFragment implements View.OnClickListener{

    private LinearLayout Collection;
    private CircleImageView civ_head;
    private PointImageView daizhifuimg;
    private LoadingDialog loadingDialog;
    private PointImageView peisongimg;
    private PointImageView wanchengimg;
    private String type;
    private PointImageView tuikuan_img;
    private TextView kabaoyue;
    private TextView fragment_address;

    @Override
    protected int setLayoutId() {
        return R.layout.fm_layout5;
    }
    @Override
    protected void init(View view, Bundle savedInstanceState) {

        initView(view);
    }
    private void initView(View view) {
        Collection = (LinearLayout)view.findViewById(R.id.ll_goods_collect);
        civ_head =(CircleImageView) view.findViewById(R.id.civ_head);
        Collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),CollectionActivity.class);
                startActivity(intent);
            }
        });
        civ_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent picture = new Intent(getActivity(), ModifyPictureActivity.class);
                startActivity(picture);
            }
        });
        daizhifuimg = (PointImageView)view.findViewById(R.id.daizhifuimg);
        peisongimg = (PointImageView)view.findViewById(R.id.peisongimg);
        wanchengimg =(PointImageView) view.findViewById(R.id.wanchengimg);
        tuikuan_img = (PointImageView)view.findViewById(R.id.tuikuan_img);
        daizhifuimg.setOnClickListener(this);
        peisongimg.setOnClickListener(this);
        wanchengimg.setOnClickListener(this);
        tuikuan_img.setOnClickListener(this);
        kabaoyue = (TextView)view.findViewById(R.id.kabaoyue);
        kabaoyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent=new Intent();
                    intent.setClass(getActivity(),GetAccountListActivity.class);
                    startActivity(intent);
            }
        });
        fragment_address = (TextView)view.findViewById(R.id.fragment_address);
        fragment_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),NewAddressActivity.class);
                startActivity(intent );
            }
        });

    }

    @Override
    public void fetchData() {
        Log.d("ssss","5555---------*********/////////");
        loadingDialog = new LoadingDialog(getActivity());
        RxHttpUtils.createApi(GitHubService.class)
                .getordernum()
                .compose(Transformer.<BaseData<Getordernum>>switchSchedulers(loadingDialog))
                .subscribe(new DataObserver<Getordernum>() {
                    @Override
                    protected void onError(String errorMsg) {
                    }
                    @Override
                    protected void onSuccess(Getordernum data) {
                        int dzfnum=Integer.parseInt(data.getOrder1());
                        int peisong=Integer.parseInt(data.getOrder3());
                        int wancheng=Integer.parseInt(data.getOrder4());
                        daizhifuimg.setNum(dzfnum);
                        peisongimg.setNum(peisong);
                        wanchengimg.setNum(wancheng);
                    }
                });
        RxHttpUtils
                .createApi(GitHubService.class)
                .GetMemberAccount()
                .compose(Transformer.<GetKayue>switchSchedulers(loadingDialog))
                .subscribe(new Observer<GetKayue>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetKayue getKayue) {
                        String yue=Integer.toString(getKayue.getData().getBalance());
                        kabaoyue.setText(yue);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.daizhifuimg) {
            type = "1";
            Intent intent = new Intent();
            intent.setClass(getActivity(), OrderlistActivity.class);
            intent.putExtra("dfw", type);
            startActivity(intent);
//                ChangeActivity(OrderlistActivity.class,false);

        } else if (i == R.id.peisongimg) {
            type = "3";
            Intent peiintent = new Intent();
            peiintent.setClass(getActivity(), OrderlistActivity.class);        
            peiintent.putExtra("dfw", type);
            startActivity(peiintent);

        } else if (i == R.id.wanchengimg) {
            type = "4";
            Intent wanchengintent = new Intent();
            wanchengintent.setClass(getActivity(), OrderlistActivity.class);
            wanchengintent.putExtra("dfw", type);
            startActivity(wanchengintent);

        } else if (i == R.id.tuikuan_img) {
            Intent intenttuik = new Intent();
            intenttuik.setClass(getActivity(), RefundActivity.class);
            startActivity(intenttuik);
        }
    }
}

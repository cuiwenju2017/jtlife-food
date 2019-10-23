package commeishi.shanjing.mymeishi.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.ToastUtils;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.bumptech.glide.Glide;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.activity.BuyListActivity;
import commeishi.shanjing.mymeishi.bean.ShoppingCarDataBean;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车的adapter
 * 因为使用的是ExpandableListView，所以继承BaseExpandableListAdapter
 */
public class ShoppingCarAdapter extends BaseExpandableListAdapter {

    private List<ShoppingCarDataBean.DataBean> data;
    private Context context;
    private LinearLayout ll_select_all;
    private ImageView iv_select_all;
    private final Button btnOrder;
    private double total_price;
    private final TextView tvTotalPrice;
    private boolean isSelectAll = false;
    private LoadingDialog loadingDialog;
    private boolean isEdit = false;
    private int totalnum;

    public ShoppingCarAdapter(Context context, LinearLayout ll_select_all, ImageView iv_select_all,
                              List<ShoppingCarDataBean.DataBean> data,
                              Button btn_sumbit, TextView tv_price) {
        this.data = data;
        this.context = context;
        this.ll_select_all = ll_select_all;
        this.iv_select_all = iv_select_all;
        this.btnOrder = btn_sumbit;
        this.tvTotalPrice = tv_price;
    }

    @Override
    public int getGroupCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (data.get(groupPosition).getCartList() != null && data.get(groupPosition).getCartList().size() > 0) {
            return data.get(groupPosition).getCartList().size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getCartList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shopping_car_group, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        final ShoppingCarDataBean.DataBean datasBean = data.get(groupPosition);
        //店铺ID
        long store_id = datasBean.getShopId();
        //店铺名称
        String store_name = datasBean.getShopName();
        if (store_name != null) {
            groupViewHolder.tvStoreName.setText(store_name);
        } else {
            groupViewHolder.tvStoreName.setText("");
        }

        //店铺内的商品都选中的时候，店铺的也要选中
        for (int i = 0; i < datasBean.getCartList().size(); i++) {
            ShoppingCarDataBean.DataBean.CartListBean goodsBean = datasBean.getCartList().get(i);
            boolean isSelect = goodsBean.isSelect();
            if (isSelect) {
                datasBean.setSelect_shop(true);
            } else {
                datasBean.setSelect_shop(false);
                break;
            }
        }

        //因为set之后要重新get，所以这一块代码要放到一起执行
        //店铺是否在购物车中被选中
        final boolean isSelect_shop = datasBean.isSelect_shop();
        if (isSelect_shop) {
            groupViewHolder.ivSelect.setImageResource(R.drawable.select);
        } else {
            groupViewHolder.ivSelect.setImageResource(R.drawable.unselect);
        }

        //店铺选择框的点击事件
        groupViewHolder.ll.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datasBean.setSelect_shop(!isSelect_shop);

                List<ShoppingCarDataBean.DataBean.CartListBean> goods = datasBean.getCartList();
                for (int i = 0; i < goods.size(); i++) {
                    ShoppingCarDataBean.DataBean.CartListBean goodsBean = goods.get(i);
                    goodsBean.setSelect(!isSelect_shop);
                }
                notifyDataSetChanged();
            }
        });

        //当所有的选择框都是选中的时候，全选也要选中
        w:
        for (int i = 0; i < data.size(); i++) {
            List<ShoppingCarDataBean.DataBean.CartListBean> goods = data.get(i).getCartList();
            for (int y = 0; y < goods.size(); y++) {
                ShoppingCarDataBean.DataBean.CartListBean goodsBean = goods.get(y);
                boolean isSelect = goodsBean.isSelect();
                if (isSelect) {
                    isSelectAll = true;
                } else {
                    isSelectAll = false;
                    break w;//根据标记，跳出嵌套循环
                }
            }
        }
        if (isSelectAll) {
            iv_select_all.setBackgroundResource(R.drawable.select);
        } else {
            iv_select_all.setBackgroundResource(R.drawable.unselect);
        }

        //全选的点击事件
        ll_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelectAll = !isSelectAll;

                if (isSelectAll) {
                    for (int i = 0; i < data.size(); i++) {
                        List<ShoppingCarDataBean.DataBean.CartListBean> goods = data.get(i).getCartList();
                        for (int y = 0; y < goods.size(); y++) {
                            ShoppingCarDataBean.DataBean.CartListBean goodsBean = goods.get(y);
                            goodsBean.setSelect(true);
                        }
                    }
                } else {
                    for (int i = 0; i < data.size(); i++) {
                        List<ShoppingCarDataBean.DataBean.CartListBean> goods = data.get(i).getCartList();
                        for (int y = 0; y < goods.size(); y++) {
                            ShoppingCarDataBean.DataBean.CartListBean goodsBean = goods.get(y);
                            goodsBean.setSelect(false);
                        }
                    }
                }
                notifyDataSetChanged();
            }
        });

        //合计的计算
        totalnum = 0;
        total_price = 0.0;
        tvTotalPrice.setText("¥0.00");
        for (int i = 0; i < data.size(); i++) {
            List<ShoppingCarDataBean.DataBean.CartListBean> goods = data.get(i).getCartList();
            for (int y = 0; y < goods.size(); y++) {
                ShoppingCarDataBean.DataBean.CartListBean goodsBean = goods.get(y);
                boolean isSelect = goodsBean.isSelect();
                if (isSelect) {
                    int num = goodsBean.getNum();
                    totalnum +=num;
                    Log.d("aaqqcc1",num+"");
                    double price = goodsBean.getPriceSrc();

                    double v = Double.parseDouble("" + num);
                    double v1 = Double.parseDouble("" + price);

                    total_price = total_price + v * v1;

                    Log.d("saaaqq",total_price+"");

                    //让Double类型完整显示，不用科学计数法显示大写字母E
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    tvTotalPrice.setText("¥" + decimalFormat.format(total_price));
                }
            }
        }

        //去结算的点击事件
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建临时的List，用于存储被选中的商品
                List<ShoppingCarDataBean.DataBean.CartListBean> temp = new ArrayList<>();

                    for (int i = 0; i < data.size(); i++) {
                        List<ShoppingCarDataBean.DataBean.CartListBean> goods = data.get(i).getCartList();
                        for (int y = 0; y < goods.size(); y++) {
                            ShoppingCarDataBean.DataBean.CartListBean goodsBean = goods.get(y);
                            boolean isSelect = goodsBean.isSelect();
                            if (isSelect) {
                                temp.add(goodsBean);
                            }
                        }
                    }
                if (temp != null && temp.size() > 0) {
                    //如果有被选中的
                    String tonumber=Integer.toString(totalnum);
                    Intent intent = new Intent(context, BuyListActivity.class);
                    intent.putExtra("goodsbean", (Serializable) temp);
                    intent.putExtra("totalnum",tonumber);
                    intent.putExtra("totalprice",total_price+"");
                    context.startActivity(intent);

                }else {
                    ToastUtils.showToast("请选择要购买的商品");
                }


            }
        });

        return convertView;
    }

    static class GroupViewHolder {
        ImageView ivSelect;
        TextView tvStoreName;
        LinearLayout ll;

        public GroupViewHolder(View view) {
            ivSelect = view.findViewById(R.id.iv_select);
            tvStoreName = view.findViewById(R.id.tv_store_name);
            ll = view.findViewById(R.id.ll);
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shopping_car_child, null);

            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        final ShoppingCarDataBean.DataBean datasBean = data.get(groupPosition);
        //店铺ID
        final long store_id = datasBean.getShopId();
        //店铺名称
        final String store_name = datasBean.getShopName();

        final ShoppingCarDataBean.DataBean.CartListBean goodsBean = datasBean.getCartList().get(childPosition);
        //商品图片
        final String goods_image = goodsBean.getIcon();
        //商品ID
        final String goods_id = goodsBean.getGoodsId();
        //ID
        final String id = goodsBean.getId();
        //商品名称
        final String goods_name = goodsBean.getTitle();
        //商品价格
        double goods_price = goodsBean.getPriceSrc();
        //商品数量
        final int goods_num = goodsBean.getNum();
        //商品是否被选中
        final boolean isSelect = goodsBean.isSelect();
        //商品规格
        final String tv_pt = goodsBean.getProduceTitle();
        //产品ID
        final String produce_id = goodsBean.getProduceId();

        Glide.with(context)
                .load(goods_image)
                .into(childViewHolder.ivPhoto);

        if (tv_pt != null) {
            childViewHolder.tvPt.setText(tv_pt);
        } else {
            childViewHolder.tvPt.setText("");
        }

        if (goods_name != null) {
            childViewHolder.tvName.setText(goods_name);
        } else {
            childViewHolder.tvName.setText("");
        }
        if (goods_price != 0) {
            childViewHolder.tvPriceValue.setText("" + goods_price);
        } else {
            childViewHolder.tvPriceValue.setText("");
        }
        if (goods_num != 0) {
            childViewHolder.tvEditBuyNumber.setText("" + goods_num);
        } else {
            childViewHolder.tvEditBuyNumber.setText("");
        }

        //商品是否被选中
        if (isSelect) {
            childViewHolder.ivSelect.setImageResource(R.drawable.select);
        } else {
            childViewHolder.ivSelect.setImageResource(R.drawable.unselect);
        }

        //商品选择框的点击事件
        childViewHolder.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsBean.setSelect(!isSelect);
                if (!isSelect == false) {
                    datasBean.setSelect_shop(false);
                }
                notifyDataSetChanged();
            }
        });

        //加号的点击事件
        childViewHolder.ivEditAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //模拟加号操作
                int num = goodsBean.getNum();
                Integer integer = Integer.valueOf(num);
                integer++;
                goodsBean.setNum(integer + 0);
                notifyDataSetChanged();

                if (mChangeCountListener != null) {
                    mChangeCountListener.onChangeCount(goods_id);
                }
            }
        });
        //减号的点击事件
        childViewHolder.ivEditSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //模拟减号操作
                int num = goodsBean.getNum();
                Integer integer = Integer.valueOf(num);
                if (integer > 1) {
                    integer--;
                    goodsBean.setNum(integer + 0);

                    /**
                     * 实际开发中，通过回调请求后台接口实现数量的加减
                     */
                    if (mChangeCountListener != null) {
                        mChangeCountListener.onChangeCount(goods_id);
                    }
                } else {
                    ToastUtil.showShort(context, "商品不能再减少了");
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    static class ChildViewHolder {
        ImageView ivSelect, ivPhoto, ivEditSubtract, ivEditAdd;
        TextView tvName, tvPriceKey, tvPriceValue, tvEditBuyNumber, tvPt;

        ChildViewHolder(View view) {
            ivSelect = view.findViewById(R.id.iv_select);
            ivPhoto = view.findViewById(R.id.iv_photo);
            tvName = view.findViewById(R.id.tv_name);
            tvPriceKey = view.findViewById(R.id.tv_price_key);
            tvPriceValue = view.findViewById(R.id.tv_price_value);
            ivEditSubtract = view.findViewById(R.id.iv_edit_subtract);
            tvEditBuyNumber = view.findViewById(R.id.tv_edit_buy_number);
            ivEditAdd = view.findViewById(R.id.iv_edit_add);
            tvPt = view.findViewById(R.id.tv_pt);
        }
    }

    //-----------------------------------------------------------------------------------------------
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //删除的回调
    public interface OnDeleteListener {
        void onDelete();
    }

    public void setOnDeleteListener(OnDeleteListener listener) {
        mDeleteListener = listener;
    }

    private OnDeleteListener mDeleteListener;

    //修改商品数量的回调
    public interface OnChangeCountListener {
        void onChangeCount(String goods_id);
    }

    public void setOnChangeCountListener(OnChangeCountListener listener) {
        mChangeCountListener = listener;
    }

    private OnChangeCountListener mChangeCountListener;

}

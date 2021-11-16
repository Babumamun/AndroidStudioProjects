package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import Model.ShopBean;
import com.example.nsufood.R;
import com.example.nsufood.ShopDetailActivity;

import java.util.List;


public class ShopAdapter extends BaseAdapter {
    private Context context;
    private List<ShopBean> sl;

    public ShopAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ShopBean> sl) {
        this.sl = sl;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return sl == null ? 0 : sl.size();
    }

    @Override
    public Object getItem(int position) {
        return sl == null ? null : sl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.my_custom_view_layout, null);
            vh.shopName = convertView.findViewById(R.id.tv_shop_name);
            vh.shopDesc = convertView.findViewById(R.id.tv_shop_desc);
            vh.saleNum = convertView.findViewById(R.id.tv_sale_num);
            vh.shopImg = convertView.findViewById(R.id.iv_shop_img);
            vh.distributionCost=convertView.findViewById(R.id.tv_db_cost);
            vh.offerPrice=convertView.findViewById(R.id.tv_offer_price);
     //       vh.shopNotice=convertView.findViewById(R.id.tv_shop_notice);
            vh.time=convertView.findViewById(R.id.tv_time);
           // vh.welfare=convertView.findViewById(R.id.tv_welfare);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }


        final ShopBean bean = (ShopBean) getItem(position);
        if (bean != null) {
            vh.shopName.setText(bean.getShopName());
            vh.shopDesc.setText(bean.getShopDesc());
            vh.saleNum.setText("Sales Num: " + bean.getSaleNum());
           // vh.welfare.setText(bean.getWelfare());
            vh.time.setText("Time:"+bean.getTime());
           // vh.shopNotice.setText(bean.getShopNotice());
            vh.offerPrice.setText("offerPrice: "+bean.getOfferPrice());
            vh.distributionCost.setText("distributionCost: "+bean.getDistributionCost());

            //vh.saleNum.setText("Sales Num:"+bean.getSaleNum());
            Glide.with(context)
                    .load(bean.getShopPic())
                    .error(R.mipmap.ic_launcher)
                    .into(vh.shopImg);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShopDetailActivity.class);
                intent.putExtra("shop",bean);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
//            Glide.with(context)
//                    .load(bean.getShopPic())
//                    .error(R.mipmap.ic_launcher)
//                    .into(vh.shopImg);


//           // vh.shopImg.setImageResource(Model.getPic());
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context, "Position" + position, Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        }
//        return convertView;
//    }

        private class ViewHolder {
            public TextView shopName;
            public TextView shopDesc;
            public ImageView shopImg;
            public TextView saleNum;
            public TextView offerPrice;
            public TextView distributionCost;
           // public TextView shopNotice;
            public TextView time;
            //public TextView welfare;

        }
    }
//    convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(context, ShopDetailActivity.class);
//               intent.putExtra("shop", bean);
//                context.startActivity(intent);
//
//            }
//
//            });
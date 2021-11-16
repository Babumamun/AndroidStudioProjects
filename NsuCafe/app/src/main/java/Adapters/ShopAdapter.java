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
import com.example.nsucafe.R;
import com.example.nsucafe.ShopDetailActivity;

import java.util.List;

import Model.ShopBean;


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
                    inflate(R.layout.asia_food_row_item, null);
            vh.tv_shop_name = convertView.findViewById(R.id.tv_shop_name);
            vh.tv_sale_num = convertView.findViewById(R.id.tv_sale_num);
            vh.iv_shop_img = convertView.findViewById(R.id.iv_shop_img);
            vh.tv_offer_price=convertView.findViewById(R.id.tv_offer_price);
            vh.tv_welfare=convertView.findViewById(R.id.tv_welfare);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }


        final ShopBean bean = (ShopBean) getItem(position);
        if (bean != null) {
            vh.tv_shop_name.setText(bean.getShopName());
//            vh.shopDesc.setText(bean.getShopDesc());
           vh.tv_sale_num.setText("Sales Num: " + bean.getSaleNum());
            vh.tv_welfare.setText(bean.getWelfare());
//            vh.time.setText("Time:"+bean.getTime());
//            vh.shopNotice.setText(bean.getShopNotice());
            vh.tv_offer_price.setText("offerPrice: "+bean.getOfferPrice());
//            vh.distributionCost.setText("distributionCost: "+bean.getDistributionCost());

            //vh.saleNum.setText("Sales Num:"+bean.getSaleNum());
            Glide.with(context)
                    .load(bean.getShopPic())
                    .error(R.mipmap.ic_launcher)
                    .into(vh.iv_shop_img);
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
            public TextView tv_shop_name;
            public TextView tv_welfare;
            public ImageView iv_shop_img;
            public TextView tv_offer_price;
            public TextView tv_sale_num;

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


// <com.google.android.material.floatingactionbutton.FloatingActionButton
//                android:id="@+id/floatingActionButton2"
//                android:layout_width="wrap_content"
//                android:layout_height="wrap_content"
//                android:clickable="true"
//                app:backgroundTint="#FF6813"
//                app:layout_anchor="@id/bottomAppBar2"
//                app:srcCompat="@drawable/ic_location" />
//        </androidx.coordinatorlayout.widget.CoordinatorLayout>
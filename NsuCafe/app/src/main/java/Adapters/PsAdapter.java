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

public class PsAdapter extends BaseAdapter{
     private Context context;
    private List<ShopBean> sl;

    public PsAdapter(Context context) {
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
                    inflate(R.layout.popular_food_row_item, null);
            vh.tv_shop_name = convertView.findViewById(R.id.name);
            vh.tv_sale_num = convertView.findViewById(R.id.price);
            vh.iv_shop_img = convertView.findViewById(R.id.food_image);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }


        final ShopBean bean = (ShopBean) getItem(position);
        if (bean != null) {
            vh.tv_shop_name.setText(bean.getShopName());
            vh.tv_sale_num.setText("Sales Num: " + bean.getSaleNum());

            Glide.with(context)
                    .load(bean.getShopPic())
                    .error(R.mipmap.ic_launcher)
                    .into(vh.iv_shop_img);
        }

        return convertView;
    }

        private class ViewHolder {
            public TextView tv_shop_name;
            public ImageView iv_shop_img;
            public TextView tv_sale_num;


        }
    }


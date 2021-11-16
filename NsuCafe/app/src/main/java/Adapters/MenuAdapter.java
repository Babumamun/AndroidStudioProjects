package Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nsucafe.FoodActivity;
import com.example.nsucafe.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Model.FoodBean;

public class MenuAdapter extends BaseAdapter {
    private Context context;
    private List<FoodBean> beans = new ArrayList<>();
    private OnSelectListener onSelectListener;

    public MenuAdapter(Context context, OnSelectListener onSelectListener) {
        this.context = context;
        this.onSelectListener=onSelectListener;

    }

    public void setData(List<FoodBean> beans) {
        this.beans = beans;
        // this.beans.clear();
        // this.beans.addAll(beans);
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        //return beans.size();
        return beans == null ? 0 : beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.menu_items, null);
            vh = new ViewHolder();
            vh.tv_food_name = convertView.findViewById(R.id.tv_food_name);
            vh.tv_taste = convertView.findViewById(R.id.tv_taste);
            vh.tv_price = convertView.findViewById(R.id.tv_price);
            vh.tv_sale_num = convertView.findViewById(R.id.tv_sale_num);
            vh.food_img = convertView.findViewById(R.id.food_img);
            vh.iv_add_card = convertView.findViewById(R.id.iv_add_card);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final FoodBean foodBean = beans.get(position);

        if (foodBean != null) {
            vh.tv_food_name.setText(foodBean.getFoodName());
            vh.tv_taste.setText(foodBean.getTaste());
            vh.tv_sale_num.setText("Sales Num: " + foodBean.getSaleNum());
            vh.tv_price.setText("¥" + foodBean.getPrice());

            Glide.with(context)
                    .load(foodBean.getFoodPic())
                    .error(R.mipmap.ic_launcher)
                    .into(vh.food_img);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (foodBean == null) return;
                Intent intent = new Intent(context, FoodActivity.class);
                intent.putExtra("food", foodBean);
                context.startActivity(intent);
            }
        });
        vh.iv_add_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectListener.onSelectAddCard(position);
            }
        });


        return convertView;
    }

    class ViewHolder {
        public TextView tv_food_name, tv_taste, tv_price, tv_sale_num;
        public ImageView food_img;
        public ImageView iv_add_card;
    }

    public interface OnSelectListener {
        void onSelectAddCard(int position);
    }
}

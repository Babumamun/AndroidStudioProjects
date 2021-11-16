package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nsucafe.R;

import java.util.ArrayList;
import java.util.List;

import Model.FoodBean;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private List<FoodBean> fbl ;
    private OnCountListener onCountListener;

    public CartAdapter(Context context, OnCountListener onCountListener){
        this.context=context;
        this.onCountListener=onCountListener;
    }
    public void setData(List<FoodBean> fbl){
        this.fbl=fbl;
        notifyDataSetChanged();

    }
    @Override
    public int getCount() {
        return fbl == null ? 0 :fbl.size();
    }

    @Override
    public Object getItem(int position) {
        return fbl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.cart_item, null);

            vh.tv_food_name = convertView.findViewById(R.id.tv_food_name);
            vh.tv_count = convertView.findViewById(R.id.tv_count);
            vh.tv_price = convertView.findViewById(R.id.tv_price);
            vh.iv_add = convertView.findViewById(R.id.im_add);
            vh.iv_minus = convertView.findViewById(R.id.iv_minus);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();

        }

        final FoodBean foodBean = (FoodBean) getItem(position);

        if (foodBean != null) {
            vh.tv_food_name.setText(foodBean.getFoodName());
            vh.tv_count.setText(""+foodBean.getCount());
            vh.tv_price.setText("¥" + foodBean.getPrice());

        }
        final ViewHolder finalVh = vh;
        vh.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newCount = Integer.parseInt(finalVh.tv_count.getText().toString())+1;
                foodBean.setCount(newCount);
                notifyDataSetChanged();
                onCountListener.onChange();
            }
        });
        final ViewHolder finalVh1 = vh;
        vh.iv_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int currentCount= Integer.parseInt(finalVh1.tv_count.getText().toString());
               if (currentCount>0){
                   int newCount=currentCount-1;
                   foodBean.setCount(newCount);
                   notifyDataSetChanged();
               }
               onCountListener.onChange();
            }
        });

        return convertView;
    }
    class ViewHolder {
        public TextView tv_food_name, tv_count, tv_price;
        public ImageView iv_add,iv_minus;

    }
    public interface OnCountListener {
        public void onChange();
    }
}

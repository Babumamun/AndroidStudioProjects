package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nsucafe.R;

import java.util.ArrayList;
import java.util.List;

import Model.FoodBean;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<FoodBean> carFoodList = new ArrayList<>();

    public OrderAdapter(Context context ) {
        this.context = context;
    }

    public void setData(List<FoodBean> carFoodList) {
        this.carFoodList=carFoodList;
        //this.carFoodList.clear();
        //this.carFoodList.addAll(carFoodList);
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return carFoodList.size();
    }

    @Override
    public Object getItem(int position) {
        return carFoodList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        {
            ViewHolder vh = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).
                        inflate(R.layout.order_item, null);
                vh = new ViewHolder();
                vh.tv_food_name = convertView.findViewById(R.id.tv_food_name);
                vh.tv_count = convertView.findViewById(R.id.tv_count);
                vh.tv_money = convertView.findViewById(R.id.tv_money);
                vh.iv_food_pic = convertView.findViewById(R.id.iv_food_pic);

                convertView.setTag(vh);
            } else {
                vh = (ViewHolder)convertView.getTag();
            }

            final FoodBean foodBean = carFoodList.get(position);
            if (foodBean != null) {
                vh.tv_food_name.setText(foodBean.getFoodName());
                vh.tv_count.setText("" + foodBean.getCount());
                vh.tv_money.setText("¥" + foodBean.getPrice());
                Glide.with(context)
                        .load(foodBean.getFoodPic())
                        .error(R.mipmap.ic_launcher)
                        .into(vh.iv_food_pic);
            }

            return convertView;
        }


    }

    class ViewHolder {
        public TextView tv_food_name, tv_count, tv_money;
        public ImageView iv_food_pic;
    }

}
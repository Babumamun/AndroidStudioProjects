package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nsucafe.R;


import java.util.List;

import Model.ShopBean;

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.postViewHolder> {
    private Context context;
    private List<ShopBean> sl;

    public FoodRecyclerAdapter(Context context, List<ShopBean> sl) {
        this.context = context;
        this.sl = sl;
    }

    @NonNull
    @Override
    public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.popular_food_row_item,parent,false);
        return new postViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull postViewHolder holder, int position) {
        ShopBean bean= sl.get(position);
        holder.name.setText(bean.getShopName());
        holder.price.setText(""+bean.getOfferPrice());
       Glide.with(context).load(bean.getShopPic())
               .error(R.mipmap.ic_launcher)
               .into(holder.food_image);

    }

    @Override
    public int getItemCount() {
        return sl.size();
    }

    public class postViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public ImageView food_image;
        public TextView price;
        public postViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            food_image=itemView.findViewById(R.id.food_image);

        }
    }



}
//    private Context context;
//    private List<ShopBean> sl;
//
//    public FoodRecyclerAdapter(Context context,  List<ShopBean> sl){
//        this.context=context;
//        this.sl= sl;
//
//    }
//    @NonNull
//    @Override
//    public FoodRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater= LayoutInflater.from(context);
//        View view=inflater.inflate(R.layout.popular_food_row_item,parent,false);
////          View view= inflater.inflate(R.layout.popular_food_row_item,parent,false);
//        return newFoodRecyclerAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull FoodRecyclerAdapter.ViewHolder holder, int position) {
//        holder.name.setText(sl.get(position).getShopName());
//        holder.price.setText(sl.get(position).getOfferPrice());
//
//
//        //Picasso.get().load(sl.get(position).getShopPic()).into(holder.iv_shop_img);
//       // Glide.with(this).load(sl.)
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class FoodRecyclerAdapter extends RecyclerView.ViewHolder{
//
//        public TextView name;
//        public ImageView food_image;
//        public TextView price;
//
//
//        public FoodRecyclerAdapter(@NonNull View itemView) {
//            super(itemView);
//            name=itemView.findViewById(R.id.name);
//            price=itemView.findViewById(R.id.price);
//            food_image=itemView.findViewById(R.id.food_image);
//
//
//        }
//    }
//}
//    private Context context;
//    private List<ShopBean> sl;
//
//    public FoodRecyclerAdapter(Context context) {
//        this.context = context;
//    }
//
//    public  void setData(List<ShopBean> sl) {
//        this.sl = sl;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getCount() {
//        return sl == null ? 0 : sl.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return sl == null ? null : sl.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//       ViewHolder vh;
//        if (convertView == null) {
//            vh = new ViewHolder();
//            convertView = LayoutInflater.from(context).
//                    inflate(R.layout.asia_food_row_item, null);
//            vh.tv_shop_name = convertView.findViewById(R.id.tv_shop_name);
//            vh.tv_sale_num = convertView.findViewById(R.id.tv_sale_num);
//            vh.iv_shop_img = convertView.findViewById(R.id.iv_shop_img);
//            vh.tv_welfare=convertView.findViewById(R.id.tv_welfare);
//            vh.tv_offer_price=convertView.findViewById(R.id.tv_offer_price);
//            convertView.setTag(vh);
//        } else {
//            vh = (ViewHolder) convertView.getTag();
//        }
//
//
//        final ShopBean bean = (ShopBean) getItem(position);
//        if (bean != null) {
//            vh.tv_shop_name.setText(bean.getShopName());
//            vh.tv_sale_num.setText("Sales Num: " + bean.getSaleNum());
//            vh.tv_welfare.setText(bean.getWelfare());
//            vh.tv_offer_price.setText("offerPrice: "+bean.getOfferPrice());
//
//            Glide.with(context)
//                    .load(bean.getShopPic())
//                    .error(R.mipmap.ic_launcher)
//                    .into(vh.iv_shop_img);
//        }
//
//        return convertView;
//    }
//    private class ViewHolder {
//        public TextView tv_shop_name;
//        public TextView tv_welfare;
//        public ImageView iv_shop_img;
//        public TextView tv_offer_price;
//        public TextView tv_sale_num;


//    }
//}

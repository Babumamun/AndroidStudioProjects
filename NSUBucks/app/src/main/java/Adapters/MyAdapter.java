package Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nsubucks.Drink;
import com.example.nsubucks.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Drink> drinkList;
//private int resourceId;

    public MyAdapter(Context context, int layout, ArrayList<Drink> drinkList) {
        this.context = context;
        this.layout = layout;
        this.drinkList = drinkList;
    }

//public MyAdapter(Context context,List<Drink>drinkList){
//this.context=context;
//this.drinkList=drinkList;
//}

    @Override
    public int getCount() {
        return this.drinkList.size();
    }

    @Override
    public Object getItem(int position) {
        return drinkList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        ImageView image_view;
        TextView tv_name, tv_dsc, tv_price;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.tv_name=(TextView)row.findViewById(R.id.tv_name);
            holder.tv_dsc=(TextView)row.findViewById(R.id.tv_des);
            holder.tv_price=(TextView)row.findViewById(R.id.tv_price);
            holder.image_view=(ImageView)row.findViewById(R.id.image_view);
            row.setTag(holder);
        }
        else {
            holder=(ViewHolder)row.getTag();
        }
        Drink drink= drinkList.get(position);
        holder.tv_name.setText(drink.getName());
        holder.tv_dsc.setText(drink.getInformation());
        holder.tv_price.setText(drink.getPrice());
        byte[] drinkImage=drink.getImgId();
        Bitmap bitmap=BitmapFactory.decodeByteArray(drinkImage,0,drinkImage.length);
        holder.image_view.setImageBitmap(bitmap);
        return row;



        // public View getView(int position, View convertView, ViewGroup parent)
//        LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//        convertView=inflater.inflate(R.layout.my_custom_view,null);
//        TextView tv_name=(convertView).findViewById(R.id.tv_name);
//        TextView tv_price=(convertView).findViewById(R.id.tv_price);
//        TextView tv_dsc=(convertView).findViewById(R.id.tv_des);
//        ImageView image_View=(convertView).findViewById(R.id.imag_view);
//        Drink drink= drinkList.get(position);
//        tv_name.setText(drink.getName());
//        tv_price.setText(drink.getPrice());
//        tv_dsc.setText(drink.getInformation());
//        byte [] image =drink.getImgId();
//        Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
//        image_View.setImageBitmap(bitmap);
//


    }
}
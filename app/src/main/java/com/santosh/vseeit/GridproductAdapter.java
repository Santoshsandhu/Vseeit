package com.santosh.vseeit;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class GridproductAdapter extends BaseAdapter {

    List<HorizontalProductModel> horizontalProductModelList;

    public GridproductAdapter(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }

    @Override
    public int getCount() {
        return horizontalProductModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
       View view;
       if (convertView == null){
           view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,null);
           view.setElevation(0);
           view.setBackgroundColor(Color.parseColor("#ffffff"));

           view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   Intent prod = new Intent(parent.getContext(),ProductActivity.class);
                   prod.putExtra("PRODUCT_ID",horizontalProductModelList.get(position).getProductID());
                   parent.getContext().startActivity(prod);
               }
           });

           ImageView productImage = view.findViewById(R.id.h_s_product_img);
           TextView producttitle = view.findViewById(R.id.h_s_product_title);
           TextView productDesc = view.findViewById(R.id.h_s_product_dis);
           TextView proprice = view.findViewById(R.id.h_s_product_price);

           Glide.with(parent.getContext()).load(horizontalProductModelList.get(position).getProductimg()).apply(new RequestOptions().placeholder(R.drawable.img)).into(productImage);
           producttitle.setText(horizontalProductModelList.get(position).getProductitle());
           productDesc.setText(horizontalProductModelList.get(position).getProductdis());
           proprice.setText("\u20b9 "+horizontalProductModelList.get(position).getProductprice()+"/-");
       }else{
        view = convertView;
       }
       return view;
    }
}

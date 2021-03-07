package com.santosh.vseeit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    private List<CartitemModel> cartitemModelList;

    public CartAdapter(List<CartitemModel> cartitemModelList) {
        this.cartitemModelList = cartitemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartitemModelList.get(position).getType()) {
            case 0:
                return CartitemModel.CART_ITEM;
            case 1:
                return CartitemModel.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case CartitemModel.CART_ITEM:
                View cartitemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
                return new cartItemViewholder(cartitemView);
            case CartitemModel.TOTAL_AMOUNT:
                View cartAmount = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_ttl_amt_lyt, parent, false);
                return new cartTotalAmountviewholder(cartAmount);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return cartitemModelList.size();
    }

    class cartItemViewholder extends RecyclerView.ViewHolder {
        private ImageView proimg;
        private ImageView freecouponicon;
        private TextView protitle;
        private TextView freecoupon;
        private TextView productprice;
        private TextView cuttedprice;
        private TextView offrapplied;
        private TextView coupnapplied;
        private TextView proqtty;


        public cartItemViewholder(@NonNull View itemView) {
            super(itemView);
            proimg = itemView.findViewById(R.id.pro_img);
            productprice = itemView.findViewById(R.id.pro_price);
            protitle = itemView.findViewById(R.id.pro_title);
            proqtty = itemView.findViewById(R.id.pro_quantity);
            freecouponicon = itemView.findViewById(R.id.free_coupon);
            freecoupon = itemView.findViewById(R.id.tv_coupon);
            cuttedprice = itemView.findViewById(R.id.cutted_price);
            offrapplied = itemView.findViewById(R.id.offers_spl);
            coupnapplied = itemView.findViewById(R.id.couponapplied);


        }

        private void setItemDetails(int resource, String title, int freecouponNo, String productpriceText, String cuttedpriceText, int offrappliedNo) {
            proimg.setImageResource(resource);
            protitle.setText(title);
            if (freecouponNo > 0) {
                freecouponicon.setVisibility(View.VISIBLE);
                freecoupon.setVisibility(View.VISIBLE);
                    if (freecouponNo == 1) {
                        freecoupon.setText("Free" + freecouponNo + "Coupon");
                    }   else {
                        freecoupon.setText("Free" + freecouponNo + "Coupon");

                    }
            }else {
                freecouponicon.setVisibility(View.INVISIBLE);
                freecoupon.setVisibility(View.INVISIBLE);
            }
                productprice.setText(productpriceText);
                cuttedprice.setText(cuttedpriceText);
                if (offrappliedNo > 0){

                }
            }
        }

        class cartTotalAmountviewholder extends RecyclerView.ViewHolder {
            public cartTotalAmountviewholder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
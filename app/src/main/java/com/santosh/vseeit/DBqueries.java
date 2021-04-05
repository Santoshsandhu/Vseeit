package com.santosh.vseeit;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DBqueries {


    public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static  List<Category_model> category_modelList = new ArrayList<>();
    public static  List<HomePagemodel> homePagemodelList = new ArrayList<>();

    public static void loadcategories(final CategoryAdapter categoryAdapter, final Context context){


        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                category_modelList.add(new Category_model(documentSnapshot.get("icon").toString(), documentSnapshot.get("categoryName").toString()));
                            }
                            categoryAdapter.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    public static void loadFragmentData(final HomePageAdapter adapter, final Context context){
        firebaseFirestore.collection("CATEGORIES")
                .document("HOME")
                .collection("TOP_DEALS")
                .orderBy("index")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                if ((long)documentSnapshot.get("view_type") == 0) {
                                    List<SliderModel> sliderModelList = new ArrayList<>();
                                    long no_of_banners = (long)documentSnapshot.get("no_of_banners");
                                    for (long s = 1; s < no_of_banners + 1; s++) {
                                        sliderModelList.add(new SliderModel(documentSnapshot.get("banner_" + s).toString(),
                                                documentSnapshot.get("banner_" + s + "_bg").toString()));
                                    }
                                    homePagemodelList.add(new HomePagemodel(0, sliderModelList));
                                } else if ((long) documentSnapshot.get("view_type") == 1) {
                                    homePagemodelList.add(new HomePagemodel(1, documentSnapshot.get("strip_ad_banner").toString(),
                                            documentSnapshot.get("background").toString()));
                                } else if ((long) documentSnapshot.get("view_type") == 2) {
                                    List<HorizontalProductModel> horizontalProductModelList = new ArrayList<>();
                                    long no_of_products = (long)documentSnapshot.get("no_of_products");
                                    for (long s = 1; s < no_of_products + 1; s++) {
                                        horizontalProductModelList.add(new HorizontalProductModel(
                                                documentSnapshot.get("product_ID_"+s).toString(),
                                                documentSnapshot.get("product_img_"+s).toString(),
                                                documentSnapshot.get("product_title_"+s).toString(),
                                                documentSnapshot.get("product_sub_"+s).toString(),
                                                documentSnapshot.get("product_price_"+s).toString()));
                                    }
                                    homePagemodelList.add(new HomePagemodel(2,documentSnapshot.get("layout_title").toString(),documentSnapshot.get("layout_bg").toString(),horizontalProductModelList));

                                } else if ((long) documentSnapshot.get("view_type") == 3) {

                                    List<HorizontalProductModel> GridLayoutModellist = new ArrayList<>();
                                    long no_of_products = (long)documentSnapshot.get("no_of_products");
                                    for (long s = 1; s < no_of_products + 1; s++) {
                                        GridLayoutModellist .add(new HorizontalProductModel(
                                                documentSnapshot.get("product_ID_"+s).toString(),
                                                documentSnapshot.get("product_img_"+s).toString(),
                                                documentSnapshot.get("product_title_"+s).toString(),
                                                documentSnapshot.get("product_sub_"+s).toString(),
                                                documentSnapshot.get("product_price_"+s).toString()));
                                    }
                                    homePagemodelList.add(new HomePagemodel(3,documentSnapshot.get("layout_title").toString(),documentSnapshot.get("layout_bg").toString(),GridLayoutModellist));


                                }
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
package com.example.cartproject.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cartproject.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepo {

    private MutableLiveData<List<Product>> mutableProductList;

    public LiveData<List<Product>> getProducts() {
        if (mutableProductList == null) {
            mutableProductList = new MutableLiveData<>();
            loadProducts();
        }
        return mutableProductList;
    }

    private void loadProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(UUID.randomUUID().toString(), "M02 21", 129, true, "https://th.bing.com/th/id/R.89cd6fa19760e2e0c4febbf29ca5c7b6?rik=HJ8TALXTg%2brxAA&pid=ImgRaw&r=0" ));
        productList.add(new Product(UUID.randomUUID().toString(), "S01 Air", 79, true, "https://th.bing.com/th/id/R.6820c16a8e571d0f95016f62c4eddc49?rik=G1gOjqT4ekHGvQ&riu=http%3a%2f%2f2.bp.blogspot.com%2f-ryn6d1Oz_zQ%2fUfS4AMJ3VVI%2fAAAAAAAAFMo%2fYIEVEJRMDNg%2fs1600%2fcool%2btee%2bshirt%2bdesigns.jpg&ehk=x7%2fyv7MMBJgrhmCfa3egRfIFC0WPJuEe4K4H%2fb97n70%3d&risl=&pid=ImgRaw&r=0"));
        productList.add(new Product(UUID.randomUUID().toString(), "Q03 Pro", 99, true, "https://th.bing.com/th/id/OIP.Sf6WUdSfaT2bJaDcHKTwvwHaH7?pid=ImgDet&w=4200&h=4500&rs=1"));
        productList.add(new Product(UUID.randomUUID().toString(), "Ali 11", 69, false, "https://d2dytk4tvgwhb4.cloudfront.net/zxo4i78w/products/5e3980d154ead00001bc5f99/black-gold/0/regular.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "SMA Pro", 99, true, "https://m.media-amazon.com/images/I/71JbYla7skL._AC_UX569_.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "KM02 11 Pro Max", 1099, true, "https://th.bing.com/th/id/OIP.5GozB5Rg1SrwL3dHUrtm9AHaHa?pid=ImgDet&rs=1"));
        productList.add(new Product(UUID.randomUUID().toString(), "WA SE", 39, true, "https://th.bing.com/th/id/OIP.3Oe8ai3ojTw7zZdvnbjYDgHaHa?pid=ImgDet&rs=1"));
        productList.add(new Product(UUID.randomUUID().toString(), "MQY Air", 99, true, "https://35qdclpzuub3qxumdcz2kndo-wpengine.netdna-ssl.com/wp-content/uploads/2015/12/earth-Camp-Design-2016-By-In-Pursuit.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "MOE 13", 129, true, "https://th.bing.com/th/id/OIP.Fhu3VSsWNmR4fwaNrsJongHaHa?pid=ImgDet&rs=1"));
        productList.add(new Product(UUID.randomUUID().toString(), "MAW 16", 239, true, "https://www.fmicassets.com/Damroot/ZoomJpg/10001/9190112506_mdl_dtl_001_nr.jpg"));
        mutableProductList.setValue(productList);
    }
}

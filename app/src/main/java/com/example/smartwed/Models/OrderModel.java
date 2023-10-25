package com.example.smartwed.Models;

import android.graphics.Bitmap;

public class OrderModel {
   Bitmap orderImage;
    String BookMarqueeName,ordermenu;
    String orderprice;
    String ordernumber;
 static String orderdate;
   String ordertime;

    public String getTxtpending() {
        return txtpending;
    }

    public void setTxtpending(String txtpending) {
        this.txtpending = txtpending;
    }

    String txtpending;

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomerphone() {
        return customerphone;
    }

    public void setCustomerphone(String customerphone) {
        this.customerphone = customerphone;
    }

    String customername,customerphone;

    public OrderModel(String ordermenu) {
        this.ordermenu = ordermenu;
    }

    public String getOrdermenu() {
        return ordermenu;
    }

    public void setOrdermenu(String ordermenu) {
        this.ordermenu = ordermenu;
    }

    public OrderModel() {
        this.orderImage = orderImage;
        BookMarqueeName = BookMarqueeName;
        this.orderprice = orderprice;
        this.ordernumber = ordernumber;
    }


    public OrderModel(String orderdate, String ordertime) {
        this.orderdate = orderdate;
        this.ordertime = ordertime;
    }

    public static String getOrderdate() {
        return orderdate;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public Bitmap getOrderImage() {
        return orderImage;
    }

    public String getBookMarqueeName() {
        return BookMarqueeName;
    }

    public String getOrderprice() {
        return orderprice;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrderImage(Bitmap orderImage) {
        this.orderImage = orderImage;
    }

    public void setBookMarqueeName(String bookMarqueeName) {
        BookMarqueeName = bookMarqueeName;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }
}

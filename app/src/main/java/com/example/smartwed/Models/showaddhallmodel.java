package com.example.smartwed.Models;

import android.graphics.Bitmap;

public class showaddhallmodel {
    public String getAddhallno() {
        return addhallno;
    }

    public void setAddhallno(String addhallno) {
        this.addhallno = addhallno;
    }

    public Bitmap getAddimage() {
        return addimage;
    }

    public void setAddimage(Bitmap addimage) {
        this.addimage = addimage;
    }

    public String getAdddescription() {
        return adddescription;
    }

    public void setAdddescription(String adddescription) {
        this.adddescription = adddescription;
    }

    public String getAddprice() {
        return addprice;
    }

    public void setAddprice(String addprice) {
        this.addprice = addprice;
    }

    public String getAddname() {
        return addname;
    }

    public void setAddname(String addname) {
        this.addname = addname;
    }

    String addhallno;
    Bitmap addimage;
    String adddescription;
    String addprice, addname;
}
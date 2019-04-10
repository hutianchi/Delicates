package com.htc.delicates.Model;

import java.util.Date;

public class Coupon {
    public int count;
    public Date date;
    public String details;

    public Coupon(int count, Date date, String details) {
        this.count = count;
        this.date = date;
        this.details = details;
    }
}

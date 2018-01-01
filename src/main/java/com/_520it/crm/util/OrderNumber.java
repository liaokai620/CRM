package com._520it.crm.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNumber {

    private OrderNumber() {
    }

    private static OrderNumber instance = new OrderNumber();

    private int number = 1;

    public static OrderNumber getInstance(){
        return instance;
    }

    public String getNumber(){
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(d);
        while(date.length() < 10){
            date = date + "0";
        }
        if(number > 999){
            number = 1;
        }
        date = date + number;
        number ++;
        return date;
    }

}

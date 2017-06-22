package com.myjava;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Created by tzh on 3/2/17.
 */
public class week {
    @Test
    public void test(){
        Date now = new Date();

        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
        System.out.println(simpleDateformat.format(now));

        simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        System.out.println(simpleDateformat.format(now));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK)); // the day of the week in numerical format

    }

    @Test
    public void test2(){
        String month = "08";
        String day = "05";
        String year = "2015";
        String input_date = day + "/" + month + "/" + year;
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            DateFormat format2 = new SimpleDateFormat("EEEE");
            String final_day = format2.format(format1.parse(input_date));
            System.out.println(final_day.toUpperCase());
        }
        catch(Exception e){}
    }
}

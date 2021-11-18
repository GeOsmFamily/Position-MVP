package com.sogefi.position.utils;


import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {

    public static String BASEURL = "http://10.0.2.2:8000/";
    public static String NOMINATIMURL = "https://nominatim.openstreetmap.org/";
    public static String API_KEY = "dEeeqWdIr5AaXAKFREAG5Pu33QkR25uOASgFxIkxFDz2wkp13BSP5xGSQGcARf1M";

    public static SimpleDateFormat df =
            new SimpleDateFormat("dd-MM-yyyy HH:mm:ssZZ", Locale.ENGLISH);


}
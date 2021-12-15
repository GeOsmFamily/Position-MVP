package com.sogefi.position.utils;


import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {

    public static String BASEURL = "https://services.position.cm/"; //10.0.2.2
    public static String IMAGEURL = "https://services.position.cm";
    public static String NOMINATIMURL = "https://nominatim.openstreetmap.org/";
    public static String API_KEY = "ZblqALRIQjtlCqqBEL8DJgi4JnkYzvi4vbcib0n99o1PwbHZlbAna1o20AK9o8UW";

    public static SimpleDateFormat df =
            new SimpleDateFormat("dd-MM-yyyy HH:mm:ssZZ", Locale.ENGLISH);


}
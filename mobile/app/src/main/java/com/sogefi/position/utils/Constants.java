package com.sogefi.position.utils;


import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {

    public static String BASEURL = "https://services.position.cm/"; //10.0.2.2
    public static String IMAGEURL = "https://services.position.cm";
    public static String NOMINATIMURL = "https://nominatim.openstreetmap.org/";
    public static String API_KEY = "I33fao5883gMdAMysm56QqRVR4OIm74qy8wd5g5RtciuLuBU37uKCjRJ1qFPqbIr";

    public static SimpleDateFormat df =
            new SimpleDateFormat("dd-MM-yyyy HH:mm:ssZZ", Locale.ENGLISH);


}
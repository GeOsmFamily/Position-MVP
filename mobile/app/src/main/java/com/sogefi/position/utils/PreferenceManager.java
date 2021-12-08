package com.sogefi.position.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static final String PREF_NAME = "welcome";
    private static final String FIRST = "no";
    private static final String LON = "lon";
    private static final String LAT = "lat";
    private static final String NAMEORI = "nameori";
    private static final String NAMEDEST = "namedest";
    private static final String TYPE = "type";

    private static final String LONORI = "lonori";
    private static final String LATORI = "latori";

    private static final String LONDEST = "londest";
    private static final String LATDEST = "latdest";

    private static final String STYLE = "style";

    private static final String TOKEN = "token";

    private static final String EMAIL = "email";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String ID = "id";

    private static final String ROLEID = "roleid";
    private static final String ZONEID = "zoneid";

    private static final String PROFILEIMAGE = "profileimage";

    private static final String ACTIVE = "active";


    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    // shared pref mode
    int PRIVATE_MODE = 0;

    public PreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void firstConnect(String a) {
        editor.putString(FIRST, a);
        editor.apply();
    }


    public String getConnect() {
        return pref.getString(FIRST, "no");
    }

    public String getLon() {
        return pref.getString(LON, "lon");
    }

    public void setLon(String lon) {
        editor.putString(LON, lon);
        editor.apply();
    }

    public String getLat() {
        return pref.getString(LAT, "lat");
    }

    public void setLat(String lat) {
        editor.putString(LAT, lat);
        editor.apply();
    }

    public String getName() {
        return pref.getString(NAME, "name");
    }

    public void setName(String name) {
        editor.putString(NAME, name);
        editor.apply();
    }

    public String getNameORI() {
        return pref.getString(NAMEORI, "nameori");
    }

    public void setNameORI(String nameOri) {
        editor.putString(NAMEORI, nameOri);
        editor.apply();
    }

    public String getNameDest() {
        return pref.getString(NAMEDEST, "namedest");
    }

    public void setNameDest(String nameDest) {
        editor.putString(NAMEDEST, nameDest);
        editor.apply();
    }

    public String getType() {
        return pref.getString(TYPE, "type");
    }

    public void setType(String type) {
        editor.putString(TYPE, type);
        editor.apply();
    }

    public String getLonori() {
        return pref.getString(LONORI, "lonori");
    }

    public void setLonori(String lonori) {
        editor.putString(LONORI, lonori);
        editor.apply();
    }

    public String getLatori() {
        return pref.getString(LATORI, "latori");
    }

    public void setLatori(String latori) {
        editor.putString(LATORI, latori);
        editor.apply();
    }

    public String getLondest() {
        return pref.getString(LONDEST, "londest");
    }

    public void setLondest(String londest) {
        editor.putString(LONDEST, londest);
        editor.apply();
    }

    public String getLatdest() {
        return pref.getString(LATDEST, "latdest");
    }

    public void setLatdest(String latdest) {
        editor.putString(LATDEST, latdest);
        editor.apply();
    }


    public String getStyle() {
        return pref.getString(STYLE, "style");
    }

    public void setStyle(String style) {
        editor.putString(STYLE, style);
        editor.apply();
    }

    public String getToken() {
        return pref.getString(TOKEN, "token");
    }

    public void setToken(String token) {
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public String getEmail() {
        return pref.getString(EMAIL, "email");
    }

    public void setEmail(String email) {
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public String getPhone() {
        return pref.getString(PHONE, "phone");
    }

    public void setPhone(String phone) {
        editor.putString(PHONE, phone);
        editor.apply();
    }

    public String getId() {
        return pref.getString(ID, "id");
    }

    public void setId(String id) {
        editor.putString(ID, id);
        editor.apply();
    }

    public String getRoleid() {
        return pref.getString(ROLEID, "roleid");
    }

    public void setRoleid(String roleid) {
        editor.putString(ROLEID, roleid);
        editor.apply();
    }


    public String getZoneid() {
        return pref.getString(ZONEID, "zoneid");
    }

    public void setZoneid(String zoneid) {
        editor.putString(ZONEID, zoneid);
        editor.apply();
    }

    public String getProfileimage() {
        return pref.getString(PROFILEIMAGE, "profileimage");
    }

    public void setProfileimage(String profileimage) {
        editor.putString(PROFILEIMAGE, profileimage);
        editor.apply();
    }

    public String getActive() {
        return pref.getString(ACTIVE, "active");
    }

    public void setActive(String active) {
        editor.putString(ACTIVE, active);
        editor.apply();
    }

}


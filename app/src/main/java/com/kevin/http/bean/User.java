package com.kevin.http.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * - Created by: maitao.
 * <br>
 * -       Date: 17-9-13.
 */

public class User {

    /**
     * id : 5
     * login : yuyongzhi
     * firstname : 永志
     * lastname : 于
     * mail : yongzhi.yu@maitao.com
     * created_on : 2017-05-17T05:18:33Z
     * last_login_on : 2017-09-13T18:22:37+08:00
     * api_key : eea84d0dee10d2da31d92569773eab09b474ac55
     * status : 1
     */

    @SerializedName("id")
    public int id;
    @SerializedName("login")
    public String login;
    @SerializedName("firstname")
    public String firstname;
    @SerializedName("lastname")
    public String lastname;
    @SerializedName("mail")
    public String mail;
    @SerializedName("created_on")
    public String createdOn;
    @SerializedName("last_login_on")
    public String lastLoginOn;
    @SerializedName("api_key")
    public String apiKey;
    @SerializedName("status")
    public int status;

    public static User objectFromData(String str) {
        User obj = null;
        try {
            obj = new Gson().fromJson(str, User.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (obj == null) {
            obj = new User();
        }
        return obj;
    }

    public static User objectFromData(String str, String key) {
        User obj = null;
        try {
            JSONObject jsonObject = new JSONObject(str);
            obj = new Gson().fromJson(jsonObject.getString(key), User.class);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        if (obj == null) {
            obj = new User();
        }
        return obj;
    }

    public static List<User> arrayFromData(String str) {
        List<User> list = null;
        try {
            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();
            list = new Gson().fromJson(str, listType);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    public static List<User> arrayFromData(String str, String key) {
        List<User> list = null;
        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();
            list = new Gson().fromJson(jsonObject.getString(key), listType);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }
}

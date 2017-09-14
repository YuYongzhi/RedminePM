package com.kevin.realm;

import com.kevin.util.StringUtil;

import io.realm.Realm;

/**
 * <p>
 * - Created by: yongzhi.
 * <br>
 * -       Date: 17-9-13.
 */

public class Properties {

    public static int save(String key, String value) {
        if (StringUtil.isNull(key)) {
            return -1;
        }
        Property property = new Property(key, value);
        Realm realm = null;
        try {

            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(property);
            realm.commitTransaction();
            return 0;
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public static int save(String key, Boolean b) {
        return save(key, String.valueOf(b));
    }

    public static int save(String key, Long l) {
        return save(key, String.valueOf(l));
    }

    public static int save(String key, int i) {
        return save(key, String.valueOf(i));
    }

    public static String get(String key) {
        return get(key, "");
    }

    public static String get(String key, String defValue) {
        if (StringUtil.isNull(key)) {
            return defValue;
        }
        Realm realm = null;
        try {

            realm = Realm.getDefaultInstance();
            Property property = realm.where(Property.class)
                    .equalTo("key", key)
                    .findFirst();
            if (property == null) {
                return defValue;
            }
            return property.value;
        }
        catch (Exception e) {
            e.printStackTrace();
            return defValue;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public static Boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static Boolean getBoolean(String key, boolean defValue) {
        String s = get(key, String.valueOf(defValue));
        return Boolean.parseBoolean(s);
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int defValue) {
        String s = get(key, String.valueOf(defValue));
        return Integer.parseInt(s);
    }

    public static long getLong(String key) {
        return getLong(key, 0L);
    }

    public static long getLong(String key, long defValue) {
        String s = get(key, String.valueOf(defValue));
        return Long.parseLong(s);
    }

}

package com.kevin.realm;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;

/**
 * <p>
 * - Created by: yongzhi.
 * <br>
 * -       Date: 17-9-13.
 */

class Property implements RealmModel {

    @PrimaryKey
    public String key;
    public String value;

    public Property(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

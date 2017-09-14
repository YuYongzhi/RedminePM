package com.kevin.util;

/**
 * <p>
 * - Created by: yongzhi.
 * <br>
 * -       Date: 17-9-13.
 */

public class StringUtil {

    public static boolean isNull(CharSequence sequence) {
        return sequence == null
                || sequence.length() == 0;
    }

    public static boolean notNull(CharSequence sequence) {
        return !isNull(sequence);
    }

}

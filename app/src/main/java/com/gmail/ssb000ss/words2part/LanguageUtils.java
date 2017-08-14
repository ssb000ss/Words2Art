package com.gmail.ssb000ss.words2part;


import android.content.Context;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LanguageUtils {

    public static String[] getLanguage(Context context) {
        return context.getResources().getStringArray(R.array.lang);
    }

    public static String[] getLanguageCode(Context context) {
        return context.getResources().getStringArray(R.array.lang_code);
    }

    public static Map<String, String> getMap(Context context) {
        HashMap<String, String> map = new HashMap<>();
        String[] language = getLanguage(context);
        String[] lang_code = getLanguageCode(context);
        for (int i = 0; i < language.length; i++) {
            map.put(language[i], lang_code[i]);
        }
        return map;
    }
}


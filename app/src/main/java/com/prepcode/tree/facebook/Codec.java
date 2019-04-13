package com.prepcode.tree.facebook;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Codec {

    private Map<String, String> map;

    String alphaNumeric = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    Random rand = new Random();

    private String key = getRandom();

    public String getRandom() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(alphaNumeric.charAt(rand.nextInt(62)));
        }
        return sb.toString();
    }

    public Codec() {
        map = new HashMap<>();
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (longUrl == null) return null;
        while (map.containsKey(key)) {
            key = getRandom();
        }
        map.put(key, longUrl);
        return "http://tinyurl.com/" + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if (shortUrl == null) return null;
        return map.get(shortUrl.replace("http://tinyurl.com/", ""));
    }


}

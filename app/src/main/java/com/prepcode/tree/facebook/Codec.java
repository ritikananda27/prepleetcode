package com.prepcode.tree.facebook;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Codec {

    // Correct soln. Commented to practice the question again

  /*  private Map<String, String> map;

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
*/

    String alphaNumeric = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Map<String, String> map = new HashMap<>();
    private Random random = new Random();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (longUrl == null) return null;
        String key = getRandomKey();
        while (map.containsKey(key)) {
            key = getRandomKey();
        }

        map.put(key, longUrl);
        return "http://tinyurl.com/" + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if (shortUrl == null) return null;
        String key = shortUrl.replace("http://tinyurl.com/", "");
        if (map.containsKey(key)) return map.get(key);
        else return null;

    }


    private String getRandomKey() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(alphaNumeric.charAt(random.nextInt(62)));
        }

        return sb.toString();
    }


}

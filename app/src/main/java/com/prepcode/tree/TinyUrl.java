package com.prepcode.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TinyUrl {
    //https://leetcode.com/problems/design-tinyurl
    //http://tinyurl.com/4e9iAk

    private String doubleColen = "://";
    private Random random = new Random();

    private Map<String, String> protocolMap = new HashMap();
    private Map<String, String> domainMap = new HashMap();
    private Map<String, String> segmentMap = new HashMap<>();

    public String encode(String longUrl) {

        if (!longUrl.contains(doubleColen)) {
            return null;
        }
        String protocol = longUrl.substring(0, longUrl.indexOf(doubleColen));
        String ranDomProtocol = Integer.toString(Math.abs(random.nextInt()));
        protocolMap.put(ranDomProtocol, protocol);

        longUrl = longUrl.replace(protocol + doubleColen, "");


        String domain = longUrl.substring(0, longUrl.indexOf('/'));
        String randomDomain = Integer.toString(Math.abs(random.nextInt()));
        domainMap.put(randomDomain, domain);

        longUrl = longUrl.replace(domain + "/", "");

        if (longUrl.length() == 0) return ranDomProtocol + doubleColen + randomDomain + "/";

        String randomSegment = Integer.toString(Math.abs(random.nextInt()));
        segmentMap.put(randomSegment, longUrl);

        return ranDomProtocol + doubleColen + randomDomain + "/" + randomSegment;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if (shortUrl.isEmpty() || !shortUrl.contains(doubleColen)) {
            return null;
        }

        String shortProtocol = shortUrl.substring(0, shortUrl.indexOf(doubleColen));
        String originalProtocol = protocolMap.get(shortProtocol);

        shortUrl = shortUrl.replace(shortProtocol + doubleColen, "");

        String shortDomain = shortUrl.substring(0, shortUrl.indexOf('/'));
        String realDomain = domainMap.get(shortDomain);

        shortUrl = shortUrl.replace(shortDomain + '/', "");

        if (shortUrl.length() == 0) return originalProtocol + doubleColen + realDomain + "/";

        String segment = segmentMap.get(shortUrl);

        return originalProtocol + doubleColen + realDomain + "/" + segment;
    }
}

package com.prepcode.tree;

public class TiralJava {

    public String reverseWords(String s) {
        if (s.isEmpty()) return null;
        StringBuilder sb = new StringBuilder();
        String[] strArr = s.split(" ");
        for (int i = strArr.length - 1; i > 0; i--) {
            String st = strArr[i];
            st.trim();
            if (st != null && st.length() > 0) {
                sb.append(st + " ");
            }

        }

        return sb.toString();

    }

}

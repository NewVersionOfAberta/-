package com.natali.utils;

public class UriUtils {
    public static String tryGetRelativeUri(String uri, String relativeTo) {
        int index = uri.indexOf(relativeTo);
        if (index == -1) {
            return null;
        }
        int startIndex = index + relativeTo.length() + 1;
        if (startIndex >= uri.length()) {
            return null;
        }
        return uri.substring(startIndex);
    }
}

package xyz.zerxoi.util;

import javax.servlet.http.Cookie;

public class CookieUtils {
    public static Cookie findCookie(String name, Cookie[] cookies) {
        if (name == null || cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }
}

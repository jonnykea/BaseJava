package com.urise.webapp.util;

    public class CheckInNull {
        public static boolean isEmpty(String str) {
            return str == null || str.trim().length() == 0;
        }
    }
package ua.util;
import java.time.*;
class ValidationHelper{
    static String requireNonBlank(String v,String f){if(v==null||v.trim().isEmpty())throw new IllegalArgumentException(f);return v.trim();}
    static <T>T requireNonNull(T v,String f){if(v==null)throw new IllegalArgumentException(f);return v;}
    static int requireInRange(int v,int a,int b,String f){if(v<a||v>b)throw new IllegalArgumentException(f);return v;}
}

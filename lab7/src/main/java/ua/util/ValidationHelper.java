package ua.util;
public final class ValidationHelper{
    private ValidationHelper(){}
    public static String requireNonBlank(String v,String f){
        if(v==null||v.trim().isEmpty()) throw new IllegalArgumentException(f+" must not be blank");
        return v.trim();
    }
    public static <T> T requireNonNull(T v,String f){
        if(v==null) throw new IllegalArgumentException(f+" must not be null");
        return v;
    }
    public static int requireInRange(int v,int a,int b,String f){
        if(v<a||v>b) throw new IllegalArgumentException(f+" must be between "+a+" and "+b);
        return v;
    }
}

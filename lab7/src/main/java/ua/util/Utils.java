package ua.util;
import java.time.*;import java.time.format.*;
public final class Utils{
    private static final DateTimeFormatter F=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private Utils(){}
    public static String formatFullName(String f,String l){return f+" "+l;}
    public static String formatDateTime(LocalDateTime dt){return dt.format(F);}
}

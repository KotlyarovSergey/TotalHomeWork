import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {
    public static Date parseDate(String str){
        // yyyy-mm-dd

        if(str.length() != 10)
            return null;

        String[] parts = str.split("-");
        if(parts.length != 3)
            return null;

        if(parts[0].length() != 4 || parts[1].length() != 2 || parts[2].length() != 2)
            return null;

        int year = 0;
        int month = 0;
        int day = 0;

        try{
            year = Integer.parseInt(parts[0]);
            month = Integer.parseInt(parts[1]);
            day = Integer.parseInt(parts[2]);
        }
        catch (NumberFormatException exception){
            return null;
        }
        
        
        Calendar calend = new GregorianCalendar(year, month-1, day);
        calend.setLenient(false);
        Date result = null;
        try{
            result = calend.getTime();
            
        }catch(IllegalArgumentException exception){
            return null;
        }

        Calendar now = GregorianCalendar.getInstance();
        if (calend.after(now))
            return null;

        return result;
    }
}

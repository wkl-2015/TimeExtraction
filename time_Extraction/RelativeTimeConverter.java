package time_Extraction;

import java.util.Calendar;
import java.util.Date;

public class RelativeTimeConverter {
    Calendar referenceTime;
    
    public RelativeTimeConverter(Calendar referenceTime){
        this.referenceTime = referenceTime;
    }
    
    public Calendar convert(String relativeTime){
        Calendar result = Calendar.getInstance();
        result.setTimeInMillis(referenceTime.getTimeInMillis());
        if(relativeTime.contains("today")){
            return result;
        }
        if(relativeTime.contains("yesterday")){
            result.add(Calendar.DATE, -1);
            return result;
        }
        if(relativeTime.contains("tomorrow")){
            result.add(Calendar.DATE, 1);
            return result;
        }
        if(relativeTime.contains("next month") || relativeTime.contains("a month later")){
            result.add(Calendar.MONTH, 1);
            return result;
        }
        if(relativeTime.contains("last month") || relativeTime.contains("a month ago")){
            result.add(Calendar.MONTH, -1);
            return result;
        }
        if(relativeTime.contains("next year")){
            result.add(Calendar.YEAR, 1);
            return result;
        }
        if(relativeTime.contains("last year") || relativeTime.contains("past year")
                || relativeTime.contains("year ago") || relativeTime.contains("year earlier")){
            result.add(Calendar.YEAR, -1);
            return result;
        }
        if(relativeTime.contains("years ago") || relativeTime.contains("years earlier")){
            int year = Integer.parseInt(relativeTime.replaceAll("[^0-9]", ""));
            result.add(Calendar.YEAR, year*-1);
            return result;
        }
        if(relativeTime.contains("years later")){
            int year = Integer.parseInt(relativeTime.replaceAll("[^0-9]", ""));
            result.add(Calendar.YEAR, year);
            return result;
        }
        if(relativeTime.contains("months ago") || relativeTime.contains("months earlier")){
            int year = Integer.parseInt(relativeTime.replaceAll("[^0-9]", ""));
            result.add(Calendar.MONTH, year*-1);
            return result;
        }
        if(relativeTime.contains("months later")){
            int year = Integer.parseInt(relativeTime.replaceAll("[^0-9]", ""));
            result.add(Calendar.MONTH, year);
            return result;
        }
        if(relativeTime.contains("this morning")){
            result.set(Calendar.HOUR, 8);
            result.set(Calendar.MINUTE, 0);
            result.set(Calendar.AM_PM, Calendar.AM);
            return result;
        }

      
        System.out.println("Can't convert: " + relativeTime);
       
        return null;
    }
}

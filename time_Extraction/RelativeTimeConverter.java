package time_Extraction;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class RelativeTimeConverter {
    Calendar referenceTime;
    List<String> relativeRegexs;
    
    public RelativeTimeConverter(Calendar referenceTime, 
            List<String> relativeRegexs){
        this.referenceTime = referenceTime;
        this.relativeRegexs = relativeRegexs;
    }
    
    public Calendar convert(String relativeTime, String regex){
        Calendar result = Calendar.getInstance();
        result.setTimeInMillis(referenceTime.getTimeInMillis());
        String[] substrings = null;
        int delta = 0;
        switch (regex) {
        case "\\b\\d{1,2} in the (morning|evening)":
            substrings = relativeTime.split(" ");
            delta = Integer.parseInt(substrings[0]);
            result.set(Calendar.HOUR, delta);
            result.set(Calendar.MINUTE, 0);
            if (substrings[3].equals("evening")) {
                result.set(Calendar.AM_PM, Calendar.PM);
            }
            return result;
        case "\\blast (spring|summer|autumn|fall|winter)":
            result.set(Calendar.DATE, 1);
            result.add(Calendar.YEAR, -1);
            switch (relativeTime.split(" ")[1]) {
            case "spring":
                result.set(Calendar.MONTH, 3);
                break;
            case "summer":
                result.set(Calendar.MONTH, 6);
                break;
            case "fall":
            case "autumn":
                result.set(Calendar.MONTH, 9);
            case "winter":
                result.set(Calendar.MONTH, 0);
            default:
                break;
            }
            return result;
        case "spring|summer|fall|autumn|winter":
            result.set(Calendar.DATE, 1);
            switch (relativeTime) {
            case "spring":
                result.set(Calendar.MONTH, 3);
                break;
            case "summer":
                result.set(Calendar.MONTH, 6);
                break;
            case "fall":
            case "autumn":
                result.set(Calendar.MONTH, 9);
            case "winter":
                result.set(Calendar.MONTH, 0);
            default:
                break;
            }
            return result;
        case "\\bthis second\\b":
            return result;
        case "\\blast night\\b":
            result.add(Calendar.DATE, -1);
            result.set(Calendar.HOUR, 9);
            result.set(Calendar.AM_PM, Calendar.PM);
            return result;
        case "\\b\\d{1,2} month(s?) before now\\b":
            delta = Integer.parseInt(relativeTime.substring(0, 2).trim());
            result.set(Calendar.DATE, 1);
            result.add(Calendar.MONTH, -1 * delta);
            return result;
        case "\\b\\d{1,2} (hour|day|week|month|year)(s?) ((before now)|ago|earlier)\\b":
            substrings = relativeTime.split(" ");
            delta = Integer.parseInt(substrings[0]) * -1;
            switch (substrings[1]) {
            case "hour":
            case "hours":
                result.add(Calendar.HOUR, delta);
                return result;
            case "day":
            case "days":
                result.add(Calendar.DATE, delta);
                return result;
            case "week":
            case "weeks":
                result.add(Calendar.DATE, delta * 7);
                return result;
            case "month":
            case "months":
                result.add(Calendar.MONTH, delta);
                return result;
            case "year":
            case "years":
                result.add(Calendar.YEAR, delta);
                return result;
            default:
                break;
            }
        case "\\b\\d{1,2} (hour|day|week|month|year)(s?) ((from now)|hence|later)\\b":
            substrings = relativeTime.split(" ");
            delta = Integer.parseInt(substrings[0]);
            switch (substrings[1]) {
            case "hour":
            case "hours":
                result.add(Calendar.HOUR, delta);
                return result;
            case "day":
            case "days":
                result.add(Calendar.DATE, delta);
                return result;
            case "week":
            case "weeks":
                result.add(Calendar.DATE, delta * 7);
                return result;
            case "month":
            case "months":
                result.add(Calendar.MONTH, delta);
                return result;
            case "year":
            case "years":
                result.add(Calendar.YEAR, delta);
                return result;
            default:
                break;
            }
        case "\\bin \\d{1,2} (hour|day|week|month|year)(s?)\\b":
            substrings = relativeTime.split(" ");
            delta = Integer.parseInt(substrings[1]);
            switch (substrings[2]) {
            case "hour":
            case "hours":
                result.add(Calendar.HOUR, delta);
                return result;
            case "day":
            case "days":
                result.add(Calendar.DATE, delta);
                return result;
            case "week":
            case "weeks":
                result.add(Calendar.DATE, delta * 7);
                return result;
            case "month":
            case "months":
                result.add(Calendar.MONTH, delta);
                return result;
            case "year":
            case "years":
                result.add(Calendar.YEAR, delta);
                return result;
            default:
                break;
            }
        default:
            break;
        }

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
            result.set(Calendar.DATE, 1);
            return result;
        }
        if(relativeTime.contains("last month") || relativeTime.contains("a month ago")){
            result.add(Calendar.MONTH, -1);
            result.set(Calendar.DATE, 1);
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
            result.set(Calendar.HOUR, 9);
            result.set(Calendar.MINUTE, 0);
            result.set(Calendar.AM_PM, Calendar.AM);
            return result;
        }

      
        System.out.println("- Unconverted: " + relativeTime);
       
        return null;
    }

    private int hash(String regex) {
        int hash = 7;
        for (int i = 0; i < regex.length(); i++) {
            hash = hash * 31 + regex.charAt(i);
        }
        return hash;
    }
}

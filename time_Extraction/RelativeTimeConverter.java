package time_Extraction;

import java.util.Calendar;
import java.util.Date;

public class RelativeTimeConverter {
    Calendar referenceTime;
    
    public RelativeTimeConverter(Calendar referenceTime){
        this.referenceTime = referenceTime;
    }
    
    public Calendar convert(String relativeTime){
        Calendar result = null;
        if(relativeTime.contains("today")){
            result = referenceTime;
        }
        if(relativeTime.contains("yesterday")){
            
        }
        
        
        
        
        
        
        
        
        
        if(result != null){
            System.out.println("Date: '" + result.toString() + "' convertered from: " + relativeTime);
        }
        else{
            System.out.println("Can't concert this relative time: " + relativeTime);
        }
        return result;
    }
}

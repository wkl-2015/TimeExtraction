package time_Extraction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateBundle {
    private TYPE timeType;
    private String rawValue;
    private Calendar calendar;
    
    public DateBundle(String rawValue, TYPE timeType){
        this.rawValue = rawValue;
        this.timeType = timeType;
    }
    
    public void setCalendar(Calendar calendar){
        this.calendar = calendar;
    }
    
    public String getRawValue(){
        return this.rawValue;
    }
    
    public Calendar getCalendar(){
        return this.calendar;
    }
    
    public TYPE getType(){
        return this.timeType;
    }
}

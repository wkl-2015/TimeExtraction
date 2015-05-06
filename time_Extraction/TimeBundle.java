package time_Extraction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeBundle {
    private TYPE timeType;
    private String rawValue;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    
    public TimeBundle(String rawValue, TYPE timeType){
        this.rawValue = rawValue;
        this.timeType = timeType;
    }
    
    public void setCalendar(Calendar calendar){
        this.calendar = calendar;
    }
    
    public void setDateFormat(SimpleDateFormat format){
        this.dateFormat = format;
    }
    
    public SimpleDateFormat getDateFormat(){
        return this.dateFormat;
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

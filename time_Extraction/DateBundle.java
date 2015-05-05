package time_Extraction;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateBundle {
    private TYPE timeType;
    private String rawValue;
    private Date date;
    
    public DateBundle(String rawValue, TYPE timeType){
        this.rawValue = rawValue;
        this.timeType = timeType;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    public String getRawValue(){
        return this.rawValue;
    }
    
    public Date getDate(){
        return this.date;
    }
}

package time_Extraction;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateBundle {
    private String rawValue;
    private SimpleDateFormat matchedFormat;
    private Date date;
    
    public DateBundle(String rawValue){
        this.rawValue = rawValue;
    }
    
    public void setDateFormat(SimpleDateFormat matchedFormat){
        this.matchedFormat = matchedFormat;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    public String getRawValue(){
        return this.rawValue;
    }
    
    public SimpleDateFormat getDateFormat(){
        return this.matchedFormat;
    }
    
    public Date getDate(){
        return this.date;
    }
}

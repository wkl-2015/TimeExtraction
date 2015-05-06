package time_Extraction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Formatter {
    private List<SimpleDateFormat> formatters = new ArrayList<SimpleDateFormat>();
    
    public Formatter(String fileName){
        List<String> formatterStr = IO.readDateFormat(fileName);
        for(String format: formatterStr){
            formatters.add(new SimpleDateFormat(format));
        }
    }
    
    public Calendar format(TimeBundle timeBundle){
        Calendar resultDate = Calendar.getInstance();
        for(SimpleDateFormat formatter: formatters){
            resultDate = parse(formatter, timeBundle.getRawValue());
            if(resultDate != null){
                timeBundle.setDateFormat(formatter);
                return resultDate;
            }
        }
        System.out.println("Can't parse this absolute time: " + timeBundle.getRawValue());
        return null;
    }
    
    private Calendar parse(SimpleDateFormat sdf, String rawStr){
        Calendar myCal = Calendar.getInstance();
        try{
            myCal.setTime(sdf.parse(rawStr));
        } catch(ParseException e){
            myCal = null;
        }
        return myCal;
    }
}

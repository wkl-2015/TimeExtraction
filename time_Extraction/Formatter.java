package time_Extraction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    
    public Date format(String rawDate){
        Date resultDate = new Date();
        for(SimpleDateFormat formatter: formatters){
            resultDate = parse(formatter, rawDate);
            if(resultDate != null){
                return resultDate;
            }
        }
        System.out.println("Date parse failed: " + rawDate);
        return null;
    }
    
    private Date parse(SimpleDateFormat sdf, String rawStr){
        Date date = new Date();
        try{
            date = sdf.parse(rawStr);
        } catch(ParseException e){
            date = null;
        }
        return date;
    }
}

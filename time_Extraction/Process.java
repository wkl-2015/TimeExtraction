package time_Extraction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Process {
    private List<MatchedTime> matchedTimes = new ArrayList<MatchedTime>();
    private List<DateBundle> dateBundles = new ArrayList<DateBundle>();
    private Formatter formatter;
    private RelativeTimeConverter converter;
    private Calendar referenceTime;
    
    public Process(String formattersFileName, Calendar referenceTime){
        this.formatter = new Formatter(formattersFileName);
        this.referenceTime = referenceTime;
        this.converter = new RelativeTimeConverter(this.referenceTime);
    }
    
    public void extractTime(String articleFileName){
        TimeExtract extractor = new TimeExtract();
        matchedTimes = extractor.extractText(articleFileName, 20);
    }
    
    public void createDateBundles(){
        for(MatchedTime time: matchedTimes){
            dateBundles.add(new DateBundle(time.rawString, time.timeType)); 
        }
    }
    
    public void formatDate(){
        for(DateBundle dateBundle: dateBundles){
            if(dateBundle.getType() == TYPE.ABSOLUTE){
                Calendar calendar = formatter.format(dateBundle.getRawValue());
                dateBundle.setCalendar(calendar);
            }
            else{
                Calendar date = converter.convert(dateBundle.getRawValue());
                dateBundle.setCalendar(date);
            }
        }
    }
    
    public void writeResult(){
        for(DateBundle dateBundle: dateBundles){
            IO.writeNormalizedTime(dateBundle.getCalendar());
        }
    }
}

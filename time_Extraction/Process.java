package time_Extraction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Process {
    private List<MatchedTime> matchedTimes = new ArrayList<MatchedTime>();
    private List<TimeBundle> timeBundles = new ArrayList<TimeBundle>();
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
            timeBundles.add(new TimeBundle(time.rawString, time.timeType)); 
        }
    }
    
    public void formatDate(){
        for(TimeBundle timeBundle: timeBundles){
            if(timeBundle.getType() == TYPE.ABSOLUTE){
                Calendar calendar = formatter.format(timeBundle.getRawValue());
                timeBundle.setCalendar(calendar);
            }
            else{
                Calendar date = converter.convert(timeBundle.getRawValue());
                timeBundle.setCalendar(date);
            }
        }
    }
    
    public void writeResult(){
        for(TimeBundle timeBundle: timeBundles){
            IO.writeNormalizedTime(timeBundle.getCalendar());
        }
    }
}

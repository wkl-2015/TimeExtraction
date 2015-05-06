package time_Extraction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Process {
    private List<MatchedTime> matchedTimes;
    private List<TimeBundle> timeBundles;
    private Formatter formatter;
    private RelativeTimeConverter converter;
    private Calendar referenceTime;
    
    public Process(String formattersFileName, Calendar referenceTime){
        matchedTimes = new ArrayList<MatchedTime>();
        timeBundles = new ArrayList<TimeBundle>();
        this.formatter = new Formatter(formattersFileName);
        this.referenceTime = referenceTime;
        this.converter = new RelativeTimeConverter(this.referenceTime);
    }
    
    public void extractTimeFromFile(String articleFileName){
        TimeExtract extractor = new TimeExtract();
        matchedTimes = extractor.extractFile(articleFileName, 20);
    }
    
    public void extractTimeFromInput(String input){
        TimeExtract extractor = new TimeExtract();
        matchedTimes = extractor.extractInput(input);
    }
    
    public void createDateBundles(){
        for(MatchedTime time: matchedTimes){
            timeBundles.add(new TimeBundle(time.rawString, time.timeType)); 
        }
    }
    
    public void formatDate(){
        for(TimeBundle timeBundle: timeBundles){
            if(timeBundle.getType() == TYPE.ABSOLUTE){
                Calendar calendar = formatter.format(timeBundle);
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
            IO.writeNormalizedTime(timeBundle);
        }
    }
    
    public List<TimeBundle> getTimeBundles(){
        return this.timeBundles;
    }
    
    public void clear(){
        matchedTimes = new ArrayList<MatchedTime>();
        timeBundles = new ArrayList<TimeBundle>();
    }
}

package time_Extraction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Process {
    private List<MatchedTime> matchedTimes;
    private List<TimeBundle> timeBundles;
    private List<String> relativeRegexs;
    private Formatter formatter;
    private RelativeTimeConverter converter;
    private Calendar referenceTime;
    private TimeExtract extractor;
    
    public Process(String formattersFileName, Calendar referenceTime){
        matchedTimes = new ArrayList<MatchedTime>();
        timeBundles = new ArrayList<TimeBundle>();
        this.formatter = new Formatter(formattersFileName, referenceTime);
        this.referenceTime = referenceTime;
        this.extractor = new TimeExtract();
        this.converter = new RelativeTimeConverter(this.referenceTime,
            this.extractor.getRelativeRegexList());
    }
    
    public void extractTimeFromFile(String articleFileName){
        matchedTimes = extractor.extractFile(articleFileName, 20);
    }
    
    public void extractTimeFromInput(String input){
        matchedTimes = extractor.extractInput(input);
    }
    
    public void createDateBundles(){
        for(MatchedTime time: matchedTimes){
            timeBundles.add(new TimeBundle(time.rawString, time.timeType, 
                time.regex)); 
        }
    }
    
    public void formatDate(){
        for(TimeBundle timeBundle: timeBundles){
            if(timeBundle.getType() == TYPE.ABSOLUTE){
                Calendar calendar = formatter.format(timeBundle);
                timeBundle.setCalendar(calendar);
            }
            else if(timeBundle.getType() == TYPE.RELATIVE){
                Calendar date = converter.convert(timeBundle.getRawValue(), 
                    timeBundle.getRegex());
                timeBundle.setCalendar(date);
            }
            else{}
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
    
    public List<MatchedTime> getExtractedTimes(){
        return this.matchedTimes;
    }
}

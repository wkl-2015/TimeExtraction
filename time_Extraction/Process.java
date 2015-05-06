package time_Extraction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Process {
    private List<MatchedTime> matchedTimes = new ArrayList<MatchedTime>();
    private List<DateBundle> dateBundles = new ArrayList<DateBundle>();
    private Formatter formatter;
    private RelativeTimeConverter converter;
    
    public Process(String formattersFileName){
        formatter = new Formatter(formattersFileName);
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
                Date date = formatter.format(dateBundle.getRawValue());
                dateBundle.setDate(date);
            }
            else{
                Date date = converter.convert(dateBundle.getRawValue());
                dateBundle.setDate(date);
            }
        }
    }
    
    public void writeResult(){
        for(DateBundle dateBundle: dateBundles){
            IO.writeNormalizedTime(dateBundle.getDate());
        }
    }
}

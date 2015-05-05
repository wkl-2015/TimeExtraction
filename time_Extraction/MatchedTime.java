package time_Extraction;

public class MatchedTime {
    public TYPE timeType;
    public String regex;
    public String rawString;
    
    public MatchedTime(TYPE timeType, String regex, String rawString){
        this.timeType = timeType;
        this.regex = regex;
        this.rawString = rawString;
    }
}

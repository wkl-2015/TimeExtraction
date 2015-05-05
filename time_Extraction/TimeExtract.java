package time_Extraction;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TimeExtract {
    private List<String> absoluteRegexs;
    private List<String> relativeRegexs;

    public TimeExtract() {
        absoluteRegexs = IO.readRegex("./TimeRegexAbsolute.txt");
        relativeRegexs = IO.readRegex("./TimeRegexRelative.txt");
    }
    
    public List<MatchedTime> extractText(String text) {
        List<MatchedTime> result = new ArrayList<MatchedTime>();
        for (String regex : absoluteRegexs) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                MatchedTime matchedTime = new MatchedTime(TYPE.ABSOLUTE,
                    regex, matcher.group());
                result.add(matchedTime);
            }
        }
        for (String regex : relativeRegexs) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                MatchedTime matchedTime = new MatchedTime(TYPE.RELATIVE,
                    regex, matcher.group());
                result.add(matchedTime);
            }
        }
        return result;
    }
}

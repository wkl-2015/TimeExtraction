package time_Extraction;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TimeExtract {
    private List<String> absoluteRegexs;
    private List<String> relativeRegexs;

    public TimeExtract() {
        absoluteRegexs = IO
                .readRegex("./time_Extraction/TimeRegexAbsolute.txt");
        relativeRegexs = IO
                .readRegex("./time_Extraction/TimeRegexRelative.txt");
    }

    public List<MatchedTime> extractFile(String fileName, int linesNum) {
        List<String> texts = IO.readArticle(fileName, linesNum);
        List<MatchedTime> result = new ArrayList<MatchedTime>();
        for (String text : texts) {
            result.addAll(extractInput(text));
        }
        return result;
    }

    public List<MatchedTime> extractInput(String input) {
        input = input.toLowerCase();
        List<MatchedTime> result = new ArrayList<MatchedTime>();
        for (String regex : absoluteRegexs) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                String rawString = matcher.group();
                MatchedTime matchedTime = 
                    new MatchedTime(TYPE.ABSOLUTE, regex, rawString);
                result.add(matchedTime);
                input.replace(rawString, 
                    fixedLengthString("*", rawString.length()));
            }
        }
        for (String regex : relativeRegexs) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                String rawString = matcher.group();
                MatchedTime matchedTime = 
                    new MatchedTime(TYPE.RELATIVE, regex, rawString);
                result.add(matchedTime);
                input.replace(rawString, 
                    fixedLengthString("*", rawString.length()));
            }
        }
        return result;
    }

    private String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }
}

package time_Extraction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class main {
    final static String formatterFileName = "./time_Extraction/formatters.txt";
    final static String testFileName = "./time_Extraction/training.txt";
    // 05-06-2015 00:00 AM
    static Calendar referenceTime = Calendar.getInstance();
    static Process processer;

    public static void main(String[] args) {
        setUp();
        try (BufferedReader br = new BufferedReader(
                new FileReader(testFileName))) {
            int i = 0;
            for (String line; (line = br.readLine()) != null;) {
                i++;
                if (line.length() > 1 && line.substring(0, 2).equals("//")) {
                    continue;
                }
                String[] pair = line.split(" == ");
                if (pair.length != 2) {
                    System.out.println("Error test line " + i + " : " + line);
                    continue;
                }
                assertInput(processer, pair[0], pair[1]);
                processer.clear();
            }
        } catch (IOException e) {
            System.out.println("Can not open training file: " + testFileName);
            e.printStackTrace();
        }
    }

    private static void assertInput(Process processer, String input,
            String expectedOutput) {
        processer.extractTimeFromInput(input);
        if (processer.getExtractedTimes().size() == 0) {
            System.out.println("- Unmatched: " + input);
            return;
        }
        processer.createDateBundles();
        processer.formatDate();
        List<TimeBundle> timeBundles = processer.getTimeBundles();
        Calendar myCal = timeBundles.get(0).getCalendar();
        if (myCal == null) {
            // System.out.println("- Can't get Calendar: " + input);
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy KK:mm a");
        dateFormat.setTimeZone(myCal.getTimeZone());
        String normalizedString = dateFormat.format(myCal.getTime());
        if (!normalizedString.equals(expectedOutput)) {
            System.out.println("- Incorrect: " + input + " -> "
                    + timeBundles.get(0).getRawValue() + " -> "
                    + normalizedString + " != " + expectedOutput);
            if (timeBundles.get(0).getDateFormat() != null) {
                System.out.println(timeBundles.get(0).getDateFormat()
                        .toPattern());
            } else {
                System.out.println("no matched format");
            }
        } else {
           // System.out.println("+ Correct: " + input + " -> "
            //        + normalizedString + " == " + expectedOutput);
        }
    }

    private static void setUp() {
        referenceTime.set(Calendar.YEAR, 2015);
        referenceTime.set(Calendar.MONTH, 4);
        referenceTime.set(Calendar.DAY_OF_MONTH, 6);
        referenceTime.set(Calendar.HOUR, 0);
        referenceTime.set(Calendar.AM_PM, Calendar.AM);
        referenceTime.set(Calendar.MINUTE, 0);
        processer = new Process(formatterFileName, referenceTime);
    }
}
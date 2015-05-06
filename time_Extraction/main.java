package time_Extraction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class main{
    final static String formatterFileName = "./time_Extraction/formatters.txt";
    final static String articleFileName = "./time_Extraction/training.txt";
    final static Calendar referenceTime = Calendar.getInstance();
    
    public static void main(String[] args){
        Process processer = new Process(formatterFileName, referenceTime);
        processer.extractTimeFromFile(articleFileName);
        processer.createDateBundles();
        processer.formatDate();
        processer.writeResult();
       
    }
}
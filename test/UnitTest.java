package test;

import static org.junit.Assert.*;
import time_Extraction.Process;
import time_Extraction.TimeBundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UnitTest {
    Process processer;
    Calendar referenceTime;
    String formatsfileName;
    
    @Before
    public void setup(){
        formatsfileName = "./time_Extraction/formatters.txt";
        this.referenceTime = Calendar.getInstance();
        processer = new Process(formatsfileName, referenceTime);
    }
    
    @Test
    public void test1() {
        processInput("5/27/1979", "05-27-1979");
    }
    
    @Test
    public void test2() {
        processInput("jan 9, 2007", "01-09-2007");
    }
    
    public void processInput(String input, String expectedOutput){
        processer.extractTimeFromInput(input);
        if(processer.getExtractedTimes().size() == 0){
            System.out.println("Not matched: " + input);
        }
        processer.createDateBundles();
        processer.formatDate();
        List<TimeBundle> timeBundles = processer.getTimeBundles();
        Calendar myCal = timeBundles.get(0).getCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        dateFormat.setTimeZone(myCal.getTimeZone());
        String normalizedString = dateFormat.format(myCal.getTime());
        processer.clear();
        assertEquals(expectedOutput, normalizedString);
    }
    
}

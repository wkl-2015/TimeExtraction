package time_Extraction;

import java.util.List;

public class main{
    final static String formatterFileName = "./time_Extraction/formatters.txt";
    final static String articleFileName = "./time_Extraction/training.txt";
    public static void main(String[] args){
        Process processer = new Process(formatterFileName);
        processer.extractTime(articleFileName);
        processer.createDateBundles();
        processer.formatDate();
        processer.writeResult();
    }
}
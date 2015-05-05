package time_Extraction;

import java.util.List;

public class main{
    public static void main(String[] args){
        test();
    }

    public static void test() {
        TimeExtract extractor = new TimeExtract();
        List<MatchedTime> times = extractor.extractText("training.txt", 20);
        for (MatchedTime time : times) {
            System.out.println(time);
        }
    }
}
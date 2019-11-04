import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class App {

    public static void main(String[] args) throws IOException {
        Scrapper scrapper = new Scrapper();
//        var urlList = new ArrayList<String>();

        var start = new Date();
        scrapper.getAllATags("https://improving.com");

        System.out.println("Duration: " + (new Date().getTime() - start.getTime()) / 1000 + "s");

    }


}

import java.io.IOException;
import java.util.ArrayList;


public class App {

    public static void main(String[] args) throws IOException {
        Scrapper scrapper = new Scrapper();
        var urlList = new ArrayList<String>();

        var start = System.nanoTime();
        scrapper.getAllATags("https://improving.com", urlList);

        System.out.println("Duration: " + (System.nanoTime() - start) / 100000000 + "s");

    }


}

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scrapper {
    private List<String> urlList = new ArrayList<>();
    private int fourHundredFourCount = 0;

    public void getAllATags(String url) throws IOException {
        //connect to url and get all A tags
        Document doc = Jsoup.connect(url).get();
        Elements allATags = doc.select("a");

        for (Element aTag : allATags) {
            //For each tag on page, get url
            String fullUrl = aTag.absUrl("href");

            //only improving urls W/O "#" that are NOT in the list already
            if (fullUrl.startsWith("https://improving.com") && !fullUrl.contains("#") && !urlList.contains(fullUrl)) {
                try {
                    urlList.add(fullUrl);
                    System.out.println("unique link found: " + fullUrl);
                    System.out.println("current webpage: " + url);
                    System.out.println();
                    //
                    getAllATags(fullUrl);


                } catch (UnsupportedMimeTypeException pdfException) {
                    System.out.println("pdf found");
                    System.out.println();
                    urlList.add(fullUrl);

                } catch (HttpStatusException e) {
                    System.out.println("404 error");
                    System.out.println();
                    fourHundredFourCount++;
                }
            }

        }
        System.out.println("Num of Unique Links: " + urlList.size());
        System.out.println("Num of 404 errors:" + fourHundredFourCount);
    }
}





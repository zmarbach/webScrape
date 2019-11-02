import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class Scrapper {
    public void getAllATags(String url, List<String> urlList) throws IOException {

        //only connect if haven't already visited link
//        if (!urlList.contains(url)) {

            //connect to url and get all A tags
            Connection connection = Jsoup.connect(url);
            Document doc = connection.get();
            Elements allATags = doc.select("a");


            for (Element aTag : allATags) {
                //For each tag on page, get url
                System.out.println(aTag.attr("href"));
                String linkValue = aTag.attr("href");

                //only improving urls W/O "#" that are NOT in the list already
                if (linkValue.startsWith("/") && !linkValue.contains("#") && !urlList.contains(linkValue)) {
                    try {
                        urlList.add(linkValue);
                        System.out.println("unique link found: " + linkValue);
                        System.out.println("current webpage: " + url);
                        System.out.println("size of list: " + urlList.size());
                        System.out.println();
                        //
                        getAllATags("https://improving.com" + linkValue, urlList);


                    } catch (UnsupportedMimeTypeException pdfException) {
                        System.out.println("pdf found");
                        urlList.add(linkValue);
                    } catch (HttpStatusException e){ //im not getting any of these...
                        System.out.println("404 error");
                        urlList.add(linkValue);
                    }
                }
            }
//        }
        System.out.println("Num of Unique Links: " + urlList.size());
    }
}





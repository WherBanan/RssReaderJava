import java.io.*;
import java.net.*;


public class RssReader {
    static String url = new String("https://www.unica.fi/modules/MenuRss/MenuRss/CurrentWeek?costNumber=1970&language=fi");


    public static void main(String[] args) {
        System.out.println(readRSS(url));
    }
    
    public static String readRSS(String url) {
        try {
            URL RSSurl = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(RSSurl.openStream(), "UTF-8"));
            String item = "";
            String line;
            String tempLines = "";
            Boolean x = false;
            while((line = in.readLine())!=null) {
                if (line.contains("<title>")) {
                    int startPos = line.indexOf("<title>");
                    String temp = line.substring(startPos);
                    temp = temp.replace("<title>", "");
                    int endPos = temp.indexOf("</title>");
                    temp = temp.substring(0, endPos);
                    item += temp +"\n";
                }
                if (x != false) {
                    tempLines += line +"\n";;
                }
                if (line.contains("<description>")) {
                    x = true;
                }
                if (line.contains("</description>")) {
                    tempLines += line;
                    x = false;
                    tempLines = tempLines.replace("<description>", "");
                    tempLines = tempLines.replace("&lt;br&gt;", "");
                    tempLines = tempLines.replace("</description>", "");
                    item += tempLines +"\n";
                }
            
            }    
            in.close();
            return item;
        } catch (MalformedURLException urle) {
            System.out.println("Malformed url" + urle);
        } catch (IOException ioe) {
            System.out.println("Error" + ioe);
        }
        String error = "Error";
        return error;


        
    }
    
}

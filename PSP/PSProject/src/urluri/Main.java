package urluri;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {

        URL url = null;
        URI uri = null;
        try {
            url = new URL("https://www.google.com");
            uri = new URI("https://www.google.com");
            System.out.println(url.getPort() + " " + uri.getPort() + "\n" + url.getHost() + " " + uri.getHost()
            + "\n" + url.getPath() + " " + uri.getPath() + "\n" + url.getQuery() + " " + uri.getQuery()
            + "\n" + url.getContent());

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

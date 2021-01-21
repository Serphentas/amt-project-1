package stackoverflow;

import javax.annotation.Resource;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectionAPI {
    public void post(String type, String userId, String URL, String key) {
        try {

            URL url = new URL(URL);
            String jsonFormat = "{" +
                "\"properties\": {}," +
                "\"timestamp\": \"%s\"," +
                "\"type\": \"%s\"," +
                "\"userId\": \"%s\"" +
            " }";
            String timestamp = java.time.Clock.systemUTC().instant().toString();

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .POST(HttpRequest.BodyPublishers.ofString(String.format(jsonFormat,timestamp, type, userId)))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

            // postConnection.setRequestProperty("x-api-key", key);
            // postConnection.setRequestProperty("Content-Type", "application/json");
        }
        catch (ProtocolException e) {
            e.printStackTrace();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getUser(String userId, String URL, String key) {
        try {
            URL url = new URL(URL + userId);

            HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
            getConnection.setRequestMethod("GET");
            getConnection.setRequestProperty("x-api-key", key);
            getConnection.setRequestProperty("Content-Type", "application/json");
            getConnection.setDoOutput(true);

            Object ob =  getConnection.getContent();
            System.out.println(ob);
            return ob;
        }
        catch (ProtocolException e) {
            e.printStackTrace();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getTop10(String URL, String key) {
        try {
            URL url = new URL(URL + "top10bypoint");

            HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
            getConnection.setRequestMethod("GET");
            getConnection.setRequestProperty("x-api-key", key);
            getConnection.setRequestProperty("Content-Type", "application/json");
            getConnection.setDoOutput(true);

            Object ob = getConnection.getContent();
            System.out.println(ob);
            return ob;
        }
        catch (ProtocolException e) {
            e.printStackTrace();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

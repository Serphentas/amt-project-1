package stackoverflow;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ConnectionAPI {
    public void post(String type, String userId, String URL, String key) {
        try {
            String jsonFormat = "{\n" +
                "\"properties\": {},\n" +
                "\"timestamp\": \"%s\",\n" +
                "\"type\": \"%s\",\n" +
                "\"userId\": \"%s\"\n" +
            "}";
            String timestamp = java.time.Clock.systemUTC().instant().toString();
            System.out.println(String.format(jsonFormat,timestamp, type, userId));

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .setHeader("X-API-KEY", key)
                    .setHeader("Content-Type", "application/json")
                    .setHeader("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(String.format(jsonFormat,timestamp, type, userId)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUser(String userId, String URL, String key) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .setHeader("x-api-key", key)
                    .setHeader("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
            return response.body();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTop10(String URL, String key) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .setHeader("x-api-key", key)
                    .setHeader("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
            return response.body();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package classes;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DiscordPoster {

    public static void main(String[] args) throws IOException, InterruptedException {

        String requestBody = "{ \"content\" : \"test\"}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://discord.com/api/webhooks/851852923902492752/Q_voNGYe0RVFBnb2o4LKJm5KYHnbWaWY6fPE3G5_DUVdWRNm0IyZ1hdUNEnKtWAVH9IL"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
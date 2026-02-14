package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // 1. Get the API Key from the environment variable
        String token = System.getenv("OPENAI_API_KEY");

        // 2. Create the JSON body for the request
        // We select the model "gpt-4o-mini" and send a user message.
        String requestBody = """
                {
                    "model": "gpt-4o-mini",
                    "messages": [
                        {
                            "role": "user",
                            "content": "Name one Bollywood comedy movie"
                        }
                    ]
                }
                """;

        // 3. Build the HTTP Request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // 4. Create the Client and send the request
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // 5. Print the response
        System.out.println(response.body());
    }
}
package org.example;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class MainOpenAISDK {
    public static void main(String[] args) {
        // Check if API key is set
        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.trim().isEmpty()) {
            System.err.println("Error: OPENAI_API_KEY environment variable is not set!");
            System.err.println("Please set your OpenAI API key as an environment variable.");
            System.exit(1);
        }
        
        // 1. Create the OpenAI Client using the API Key from environment variables
        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .build();

        // 2. Define the parameters (Model and Message)
        // The video suggests using "gpt-4o" (referred to as "photo" in the transcript due to auto-captioning)
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .model(ChatModel.GPT_4O)
                .addUserMessage("Name one Bollywood comedy movie")
                .build();

        // 3. Send the request and get the response
        ChatCompletion response = client.chat().completions().create(params);

        // 4. Print the response
        // The instructor prints the whole response object to see the structure
        System.out.println(response);
    }
}
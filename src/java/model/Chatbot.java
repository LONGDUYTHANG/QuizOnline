/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.lilittlecat.chatgpt.offical.ChatGPT;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author Phuong Anh
 */
public class Chatbot {

    private static final String API_KEY = ""; 
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static void main(String[] args) {
        String userMessage = "bác hồ là ai";
        String response = sendMessageToChatGPT(userMessage);
        System.out.println("ChatGPT: "+ response);
              
    }

    private static String sendMessageToChatGPT(String message) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setDoOutput(true);

            JSONObject jsonInput = new JSONObject();
            jsonInput.put("model", "gpt-3.5-turbo");
            jsonInput.put("messages", new JSONObject[]{
                new JSONObject().put("role", "user").put("content", message)
            });

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            return new JSONObject(response.toString()).getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");

        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while calling ChatGPT API.";
        }
    }
}

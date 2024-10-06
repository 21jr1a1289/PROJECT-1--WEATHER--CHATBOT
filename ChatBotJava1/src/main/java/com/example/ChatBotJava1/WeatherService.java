package com.example.ChatBotJava1;



import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject; // Ensure you have the JSON library

public class WeatherService {

    // Replace with your actual OpenWeatherMap API key
    private static final String API_KEY = "7d2def3a7129b33a35f241b3b98f7ea8"; 
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public String getWeather(String city) {
        // Construct the URL for the API call
        String url = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric"; // Adding units parameter for Celsius
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = client.execute(request);
            String jsonResponse = EntityUtils.toString(response.getEntity());

            // Process the JSON response to extract needed data
            JSONObject jsonObject = new JSONObject(jsonResponse);
            if (jsonObject.has("main")) {
                JSONObject main = jsonObject.getJSONObject("main");
                double temperature = main.getDouble("temp");
                return "The current temperature in " + city + " is " + temperature + "°C.";
            } else {
                return "City not found or invalid response.";
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
            return "Error fetching weather data.";
        }
    }
}

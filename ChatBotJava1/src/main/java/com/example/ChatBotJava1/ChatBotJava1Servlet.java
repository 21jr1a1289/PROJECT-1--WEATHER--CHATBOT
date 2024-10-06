package com.example.ChatBotJava1;
 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; // Ensure you have this import
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

// Use the @WebServlet annotation to map the servlet
@WebServlet("/ChatBotJava1Servlet")
public class ChatBotJava1Servlet extends HttpServlet implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L; // Serial version UID
    private WeatherService weatherService;
    
    // Map to store country to city mapping
    private static final Map<String, String> countryToCityMap = new HashMap<>();

    static {
        // Adding countries and their default cities
    	countryToCityMap.put("UK", "London");
        countryToCityMap.put("Japan", "Tokyo");
        countryToCityMap.put("USA", "Washington");
        countryToCityMap.put("India", "New Delhi");
        countryToCityMap.put("France", "Paris");
        countryToCityMap.put("Germany", "Berlin");
        countryToCityMap.put("Canada", "Ottawa");
        countryToCityMap.put("Australia", "Canberra");
        countryToCityMap.put("Brazil", "Brasília");
        countryToCityMap.put("China", "Beijing");
        countryToCityMap.put("South Korea", "Seoul");
        countryToCityMap.put("Italy", "Rome");
        countryToCityMap.put("Mexico", "Mexico City");
        countryToCityMap.put("South Africa", "Pretoria");
        countryToCityMap.put("Russia", "Moscow");
        countryToCityMap.put("Spain", "Madrid");
        countryToCityMap.put("Saudi Arabia", "Riyadh");
        countryToCityMap.put("Turkey", "Ankara");
        countryToCityMap.put("Netherlands", "Amsterdam");
        countryToCityMap.put("Sweden", "Stockholm");
        countryToCityMap.put("Argentina", "Buenos Aires");
        countryToCityMap.put("Switzerland", "Bern");
        countryToCityMap.put("Egypt", "Cairo");
        countryToCityMap.put("Greece", "Athens");
        countryToCityMap.put("Nigeria", "Abuja");
        countryToCityMap.put("Israel", "Jerusalem");
        countryToCityMap.put("Thailand", "Bangkok");
        countryToCityMap.put("Singapore", "Singapore");
        countryToCityMap.put("New Zealand", "Wellington");
        countryToCityMap.put("Pakistan", "Islamabad");
        countryToCityMap.put("Norway", "Oslo");
        countryToCityMap.put("Portugal", "Lisbon");
        countryToCityMap.put("Vietnam", "Hanoi");
        countryToCityMap.put("Ukraine", "Kyiv");
        countryToCityMap.put("Indonesia", "Jakarta");
        countryToCityMap.put("Belgium", "Brussels");
        countryToCityMap.put("Philippines", "Manila");
        countryToCityMap.put("Austria", "Vienna");
        countryToCityMap.put("Bangladesh", "Dhaka");
        countryToCityMap.put("Finland", "Helsinki");
        countryToCityMap.put("Denmark", "Copenhagen");
        countryToCityMap.put("Poland", "Warsaw");
        countryToCityMap.put("Malaysia", "Kuala Lumpur");
        countryToCityMap.put("Chile", "Santiago");
        countryToCityMap.put("Colombia", "Bogotá");
        countryToCityMap.put("Peru", "Lima");
        // Add more countries and their default cities as needed
        // Add more countries as needed
    }

    @Override
    public void init() throws ServletException {
        weatherService = new WeatherService(); // Initialize WeatherService
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userMessage = request.getParameter("message").toLowerCase();
        String botResponse;

        // Handling multiple intents
        if (userMessage.contains("weather")) {
            String country = extractCountry(userMessage);
            String city = countryToCityMap.getOrDefault(country, "London"); // Use default city if country is not found
            botResponse = weatherService.getWeather(city);
        } else if (userMessage.contains("hello") || userMessage.contains("hi")) {
            botResponse = "Hello! How can I assist you today?";
        } else if (userMessage.contains("bye")) {
            botResponse = "Goodbye! Have a great day!";
        } else {
            botResponse = getChatBotResponse(userMessage);
        }

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(botResponse);
        out.flush();
    }

    private String extractCountry(String message) {
        for (String country : countryToCityMap.keySet()) {
            if (message.contains(country.toLowerCase())) {
                return country; // Return country if found
            }
        }
        return ""; // Return empty if no country found
    }

    private String getChatBotResponse(String message) {
        return "You said: " + message; // Simple echo for now
    }
}

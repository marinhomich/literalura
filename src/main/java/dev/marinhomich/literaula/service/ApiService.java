package dev.marinhomich.literaula.service;


import dev.marinhomich.literaula.entry.EntryPoint;
import dev.marinhomich.literaula.utils.ScreenClear;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    public String getResponseBody (String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            ScreenClear.clear();
            System.out.println("Não foi possível estabelecer a conexão.");
            EntryPoint.setUserInput("");
        }

        String responseBody = response != null ? response.body() : null;
        return responseBody;
    }
}
package com.aisite.springemini.controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatbotController {

    private String keyGemini = System.getenv("GEMINI_KEY"); //"AIzaSyAY90uaQnIn_cNsSXFt-ttuBdJ1fE9orOQ";

    public void teste(){
        System.out.println(keyGemini);
    }

    public String chamaGemini(String prompt){

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key="+ keyGemini))
            .POST(BodyPublishers.ofString("{\"contents\": [{\"parts\":[{\"text\": \"" + prompt + "\"}]}]}"))
            .build();
        
        try{
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode saida = mapper.readTree(response.body());
            String texto = saida.get("candidates").get(0).get("content").get("parts").get(0).get("text").asText();
            // System.out.println(saida);
            //System.out.println(texto);
            return texto;

        }catch(IOException e){
            System.err.println("ERRO: " + e);
            return "erro";
        }catch(InterruptedException e){
            System.err.println("ERRO: " + e);
            return "erro";
        }
    } 
}

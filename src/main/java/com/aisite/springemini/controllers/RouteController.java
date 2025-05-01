package com.aisite.springemini.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {
    @GetMapping("/")
    public String index(Model model){

        ChatbotController bot = new ChatbotController();
        String resposta = "erro?";
        resposta = bot.chamaGemini("Quanto vocês sabe sobre lol?");
        //bot.teste();
        model.addAttribute("resposta", resposta);

        return "index";
    }
}

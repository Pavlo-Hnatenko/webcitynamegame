package com.example.mvc_demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AppController {

    private static final String START_CITY = "Kharkiv";
    private static final String FAREWELL_MESSAGE = "Thank you for playing!:)";
    private static final String[] CITIES_VALUES = new String[]{
            "Ankara", "Budapest", "Canberra", "Dhaka", "Edinburgh", "Freetown", "Gdansk", "Honiara", "Islamabad",
            "Juba", "Kyiv", "London", "Montevideo", "Niamey", "Oslo", "Podgorica", "Quito", "Roseau", "Santiago",
            "Thimphu", "Ulaanbaatar", "Vatican", "Warsaw", "Xining", "Yerevan", "Zanzibar"
    };
    private static final Set<String> CITIES = new HashSet<>(Arrays.asList(CITIES_VALUES));

    @GetMapping(value = "/begin")
    public String beginController(Model model) {
        model.addAttribute("startcity", START_CITY);
        return "begin";
    }

    @GetMapping(value = "/next")
    public String nextController(@RequestParam(name = "inputcity") String inputCity, Model model) {

        if (inputCity.regionMatches(true, 0, START_CITY, START_CITY.length() - 1, 1)
                && !inputCity.equalsIgnoreCase(START_CITY)) {
            model.addAttribute("inputcity", inputCity + ". It is correct answer!");

            for (String city : CITIES) {
                if (city.regionMatches(true, 0, inputCity, inputCity.length() - 1, 1)
                        && !city.equalsIgnoreCase(inputCity)) {
                    model.addAttribute("systemanswer", "And my answer is: " + city);
                    break;
                } else {
                    model.addAttribute("systemanswer",
                            "Sorry, I don't have any suitable city in my memory for the answer. You a winner!");
                }
            }
        } else {
            model.addAttribute("inputcity", inputCity + " is incorrect answer!");
        }

        return "next";
    }

    @GetMapping(value = "/end")
    public void endController(Model model) {
        model.addAttribute("farewellmessage", FAREWELL_MESSAGE);
    }

}


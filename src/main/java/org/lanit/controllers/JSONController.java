package org.lanit.controllers;

import org.lanit.modelsJson.*;
import org.lanit.validate.CheckSnils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Controller
public class JSONController {

    @PostMapping(value = "/snils")
    public Object addTicker(@RequestBody String requestBodyData) throws IOException {

        try {
            // Converting JSON to classes
            ObjectMapper objectMapper = new ObjectMapper();
            SnilsRequest snilsRequest = objectMapper.readValue(requestBodyData, SnilsRequest.class);

            try {

                // Parsing JSON
                String snils = snilsRequest.getSnils();

                CheckSnils checkSnils = new CheckSnils();
                try {
                    // Calling method to check snils
                    checkSnils.checkSnils(snilsRequest.getNumbers(), snilsRequest.getCheckSumm());
                    if (!checkSnils.getIsValid()) {
                        return ResponseEntity.status(400).header("content-type", "application/json").body("{" +
                                "  \"message\": \"Error: uncorrected snils\"," +
                                "  \"snils\": \"" + snilsRequest.getSnils() + "\"" +
                                "}");
                    } else {
                        //if summary is correct, return success
                        return ResponseEntity.ok().header("Content-Type", "application/json").body(String.format("{\"message\":\"success\", \"snils\" : \"%s\"}", snilsRequest.getSnils()));
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    //if snils isn't correct, return error
                    return ResponseEntity.status(400).header("content-type", "application/json").body("{" +
                            "  \"message\": \"Error: uncorrected snils\"," +
                            "  \"snils\": \"" + snilsRequest.getSnils() + "\"" +
                            "}");
                }
            } catch (Exception e) {
                System.out.println(e);

                return ResponseEntity.status(400).header("content-type", "application/json").body("{" +
                        "  \"message\": \"Error: uncorrected snils\"," +
                        "  \"snils\": \"" + snilsRequest.getSnils() + "\"" +
                        "}");
            }
        } catch (Exception e) {
            System.out.println(e);
            //Getting rid of unnecessary symbols
            String replacedSnils = requestBodyData.replace("\r\n ", "");
            replacedSnils = replacedSnils.replace("\r\n", "");

            return ResponseEntity.status(400).header("content-type", "application/json").body("{" +
                    "  \"message\": \"Error: uncorrected json\"," +
                    "  \"request\": " +  replacedSnils  +
                    "}");
        }


    }
}

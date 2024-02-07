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

                SnilsRequest snilsInstance = new SnilsRequest(snils);

                CheckSnils checkSnils = new CheckSnils();
                try {
                    checkSnils.checkSnils(snilsInstance.getNumbers(), snilsInstance.getCheckSumm());
                    System.out.println(checkSnils.getIsValid());
                } catch (Exception e) {
                    System.out.println(e);
                    return ResponseEntity.status(400).header("content-type", "application/json").body("{" +
                            "  \"message\": \"Error: uncorrected snils\"," +
                            "  \"snils\": \"" + snilsInstance.getSnils() + "\"" +
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
            String replacedSnils = requestBodyData.replace("\r\n\s", "");
            replacedSnils = replacedSnils.replace("\r\n", "");
            replacedSnils = replacedSnils.replace("\"", "\\\"");

            return ResponseEntity.status(400).header("content-type", "application/json").body("{" +
                    "  \"message\": \"Error: uncorrected snils\"," +
                    "  \"request\": \"" +  replacedSnils  + "\"" +
                    "}");
        }

        return ResponseEntity.ok().header("Content-Type", "application/json").body(String.format("{\"message\":\" JSON\", \"request:\" : \"%s\"}", requestBodyData));
    }
}

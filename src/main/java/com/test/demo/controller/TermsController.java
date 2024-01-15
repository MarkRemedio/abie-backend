package com.test.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("test")
public class TermsController {

    @PostMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> checkWords(@RequestBody String textToCheck) {
        final List<String> flaggedWords = Arrays.asList("near", "baked bread", "call of the city", "moss covered rock",
                "bake", "it", "adventure", "renewed purpose", "distant");

        List<String> flagWordsFound = new ArrayList<>();
        for(String wordPhrase : flaggedWords) {
            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(wordPhrase) + "\\b", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(textToCheck);

            if (matcher.find()) {
                flagWordsFound.add(wordPhrase);
            }
        }

        if(flagWordsFound.size() > 0) {
            return new ResponseEntity<>(
                    "Flagged Words Found: " + Arrays.toString(flagWordsFound.toArray()),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                "Sentence is clear",
                HttpStatus.OK);
    }
}

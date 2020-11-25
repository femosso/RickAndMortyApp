package com.example.rickandmorty.utils;

import com.example.rickandmorty.entities.Character;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static RequestBuilder buildGetRequest(String uri) {
        return MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
    }

    public static RequestBuilder buildPutRequest(String uri, String inputJson) {
        return MockMvcRequestBuilders
                .put(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);
    }

    public static RequestBuilder buildDeleteRequest(String uri) {
        return MockMvcRequestBuilders
                .delete(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
    }

    public static String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    public static List<Character> buildRandomCharacterList(int size) {
        List<Character> characterList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            characterList.add(generateRandomCharacter(i));
        }
        return characterList;
    }

    public static Character generateRandomCharacter(int id) {
        return new Character(
                id,                     /* id */
                randomAlphaNumeric(20), /* name */
                randomAlphaNumeric(10), /* gender */
                randomAlphaNumeric(50), /* image */
                randomAlphaNumeric(10), /* species */
                randomAlphaNumeric(7),  /* status */
                true                    /* editable */
        );
    }

    private static String randomAlphaNumeric(int count) {
        final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}

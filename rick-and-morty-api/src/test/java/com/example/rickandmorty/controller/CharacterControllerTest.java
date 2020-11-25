package com.example.rickandmorty.controller;

import com.example.rickandmorty.entities.Character;
import com.example.rickandmorty.payload.CharacterDto;
import com.example.rickandmorty.service.CharacterService;
import com.example.rickandmorty.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CharacterController.class)
@ActiveProfiles("test")
class CharacterControllerTest {

    private final String GET_CHARACTERS_URI = "/characters";
    private final String PUT_CHARACTERS_URI = "/characters";
    private final String DELETE_CHARACTERS_URI = "/characters/{characterId}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private CharacterService characterService;

    private List<CharacterDto> characterDtoList;
    private CharacterDto characterDto;

    @BeforeEach
    void init() {
        characterDtoList = mapList(TestUtils.buildRandomCharacterList(10), CharacterDto.class);
        Character randomCharacter = TestUtils.generateRandomCharacter(0);
        characterDto = modelMapper.map(randomCharacter, CharacterDto.class);

        when(characterService.getCharacters()).thenReturn(characterDtoList);
        when(characterService.save(randomCharacter)).thenReturn(true);
        when(characterService.delete(randomCharacter.getId())).thenReturn(true);
    }

    @Test
    void getCharacters_valid() throws Exception {
        RequestBuilder request = TestUtils.buildGetRequest(GET_CHARACTERS_URI);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtils.mapToJson(characterDtoList)))
                .andReturn();
    }

    @Test
    void addCharacters_valid() throws Exception {
        RequestBuilder request = TestUtils.buildPutRequest(PUT_CHARACTERS_URI, TestUtils.mapToJson(characterDto));
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void addCharacters_invalid() throws Exception {
        when(characterService.save(any())).thenReturn(false);

        RequestBuilder request = TestUtils.buildPutRequest(PUT_CHARACTERS_URI, TestUtils.mapToJson(characterDto));
        mockMvc.perform(request)
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    @Test
    void removeCharacters_valid() throws Exception {
        String deleteUri = DELETE_CHARACTERS_URI.replace("{characterId}", String.valueOf(characterDto.getId()));
        RequestBuilder request = TestUtils.buildDeleteRequest(deleteUri);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void removeCharacters_invalid() throws Exception {
        when(characterService.delete(any())).thenReturn(false);

        String deleteUri = DELETE_CHARACTERS_URI.replace("{characterId}", String.valueOf(characterDto.getId()));
        RequestBuilder request = TestUtils.buildDeleteRequest(deleteUri);
        mockMvc.perform(request)
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}

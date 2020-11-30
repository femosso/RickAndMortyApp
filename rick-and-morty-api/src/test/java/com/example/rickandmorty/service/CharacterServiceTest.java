package com.example.rickandmorty.service;


import com.example.rickandmorty.entities.Character;
import com.example.rickandmorty.payload.CharacterDto;
import com.example.rickandmorty.payload.CharacterListDto;
import com.example.rickandmorty.repository.CharacterRepository;
import com.example.rickandmorty.utils.TestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@EnableAutoConfiguration
@ActiveProfiles("test")
class CharacterServiceTest {

    @Mock
    private CharacterRepository characterRepository;

    @Mock
    private ModelMapper modelMapper;

    private CharacterService characterService;

    private static MockWebServer mockWebServer;

    private List<CharacterDto> characterDtoList;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void init() {
        characterDtoList = TestUtils.mapList(modelMapper, TestUtils.buildRandomCharacterList(10), CharacterDto.class);

        List<Character> characterListFromDb = TestUtils.buildRandomCharacterList(10);

        Character[] charactersArrayFromDb = new Character[characterListFromDb.size()];
        charactersArrayFromDb = characterListFromDb.toArray(charactersArrayFromDb);

        Iterable<Character> charactersIterableFromDb = Arrays.asList(charactersArrayFromDb);

        WebClient webClient = WebClient.builder().baseUrl(mockWebServer.url("/").toString()).build();
        characterService = new CharacterService(characterRepository, webClient, modelMapper);
        when(characterRepository.findAll()).thenReturn(charactersIterableFromDb);
    }

    @Test
    void getCharacters_valid() throws JsonProcessingException, InterruptedException {
        CharacterListDto characterListDto = new CharacterListDto();
        characterListDto.setResults(characterDtoList);
        mockWebServer.enqueue(TestUtils.createMockResponse(characterListDto, HttpStatus.OK));

        List<CharacterDto> characterDtoList = characterService.getCharacters();

        RecordedRequest request = mockWebServer.takeRequest();
        Assertions.assertEquals(HttpMethod.GET.toString(), request.getMethod());
        Assertions.assertEquals("/character", request.getPath());
        Assertions.assertEquals(20, characterDtoList.size());
    }
}

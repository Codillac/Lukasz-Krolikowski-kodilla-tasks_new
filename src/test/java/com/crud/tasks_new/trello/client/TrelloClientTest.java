package com.crud.tasks_new.trello.client;

import com.crud.tasks_new.domain.TrelloBoardDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    @InjectMocks
    TrelloClient trelloClient;

    @Mock
    RestTemplate restTemplate;

    @Mock
    TrelloBoardDto trelloBoardDto;

    @Before
    public void setUrl() {
        try {
            Field field = trelloClient.getClass().getDeclaredField("trelloApiEndpoint");
            field.setAccessible(true);
            field.set(trelloClient, "http://test.com");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void getTrelloBoardsTestWithNoBoards() {
        //Given
        when(restTemplate.getForObject(trelloClient.buildUrl(), TrelloBoardDto[].class)).thenReturn(null);

        //When
        List<TrelloBoardDto> resultListOfBoards = trelloClient.getTrelloBoards();

        //Then
        Assert.assertNotNull(resultListOfBoards);
    }

    @Test
    public void getTrelloBoardsTestWithSomeBoards() {
        //Given
        TrelloBoardDto[] sampleBoardDto = new TrelloBoardDto[3];
        when(restTemplate.getForObject(trelloClient.buildUrl(), TrelloBoardDto[].class)).thenReturn(sampleBoardDto);

        //When
        List<TrelloBoardDto> resultListOfBoards = trelloClient.getTrelloBoards();

        //Then
        Assert.assertEquals(3,resultListOfBoards.size());
    }

}
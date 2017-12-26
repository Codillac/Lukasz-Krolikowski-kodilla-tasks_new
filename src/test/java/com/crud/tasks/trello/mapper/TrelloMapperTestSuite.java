package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMaper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    TrelloMaper trelloMaper;

    @Test
    public void testMapToBoards() {
        //When
        List<TrelloBoard> trelloBoards = trelloMaper.mapToBards(prepareTestDataForBoardMethods());

        //Then
        Assert.assertEquals(2, trelloBoards.size());
        Assert.assertEquals("5", trelloBoards.get(1).getId());
        Assert.assertEquals("in progress", trelloBoards.get(0).getLists().get(1).getName());
        Assert.assertEquals(true, trelloBoards.get(0).getLists().get(2).isClosed());
    }

    @Test
    public void testMapToBardsDto() {
        //When
        testMapToBoards();
        List<TrelloBoardDto> boardDtos = trelloMaper.mapToBoardsDto(trelloMaper.mapToBards(prepareTestDataForBoardMethods()));

        //Then
        Assert.assertEquals(2, boardDtos.size());
        Assert.assertEquals("5", boardDtos.get(1).getId());
        Assert.assertEquals("in progress", boardDtos.get(0).getLists().get(1).getName());
        Assert.assertEquals(true, boardDtos.get(0).getLists().get(2).isClosed());
    }

    @Test
    public void testMapToList() {
        //When
        List<TrelloList> trelloLists = trelloMaper.mapToList(prepareTestDataForListMethods());

        //Then
        Assert.assertEquals(3, trelloLists.size());
    }

    @Test
    public void testMapToListDto() {
        //When
        List<TrelloListDto> trelloListDtos = trelloMaper.mapToListDto(trelloMaper.mapToList(prepareTestDataForListMethods()));

        //Then
        testMapToList();
        Assert.assertEquals(3, trelloListDtos.size());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("To be happy", "Test happiness", "top", "1");

        //When
        TrelloCard resultTrelloCard = trelloMaper.mapToCard(cardDto);

        //Then
        Assert.assertEquals("To be happy", resultTrelloCard.getName());
        Assert.assertEquals("Test happiness", resultTrelloCard.getDesccription());
        Assert.assertEquals("top", resultTrelloCard.getPos());
        Assert.assertEquals("1", resultTrelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("To be happy", "Test happiness", "top", "1");

        //When
        TrelloCardDto resultTrelloCardDto = trelloMaper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals("To be happy", resultTrelloCardDto.getName());
        Assert.assertEquals("Test happiness", resultTrelloCardDto.getDescription());
        Assert.assertEquals("top", resultTrelloCardDto.getPos());
        Assert.assertEquals("1", resultTrelloCardDto.getListId());
    }

    private List<TrelloListDto> prepareTestDataForListMethods() {
        List<TrelloListDto> listOfLists = new ArrayList<>();
        listOfLists.add(new TrelloListDto("1", "to do", false));
        listOfLists.add(new TrelloListDto("2", "in progress", false));
        listOfLists.add(new TrelloListDto("3", "done", true));
        return listOfLists;
    }

    private List<TrelloBoardDto> prepareTestDataForBoardMethods() {
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        List<TrelloListDto> trelloListDtos1 = prepareTestDataForListMethods();
        List<TrelloListDto> trelloListDtos2 = new ArrayList<>();
        trelloListDtos2.add(new TrelloListDto("1", "dreams", false));
        trelloListDtos2.add(new TrelloListDto("2", "plans", false));
        trelloListDtos2.add(new TrelloListDto("3", "actions", false));
        TrelloBoardDto boardDto1 = new TrelloBoardDto("Test board1", "4", trelloListDtos1);
        TrelloBoardDto boardDto2 = new TrelloBoardDto("Test board2", "5",trelloListDtos2);
        trelloBoardDtoList.add(boardDto1);
        trelloBoardDtoList.add(boardDto2);
        return trelloBoardDtoList;
    }
}

package com.it.code.LottoLogger.LottoLogger;

import com.it.code.LottoLogger.LottoLogger.controller.LottoController;
import com.it.code.LottoLogger.LottoLogger.entity.LottoPlay;
import com.it.code.LottoLogger.LottoLogger.repository.LottoPlayRepository;
import com.it.code.LottoLogger.LottoLogger.service.LotLogServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class LottoLoggerApplicationTests {

    @Mock
    private LottoPlay lottoPlay;

    @Mock
    private LottoPlayRepository lotRepos;

    @Mock
    private LotLogServiceImpl service;

    @InjectMocks
    private LottoController lottoController;

    private LottoPlay lotPl = new LottoPlay();
    private Set<Integer> set = new HashSet<Integer>();
    private Integer[] result = {3, 4, 55, 12, 21};
    private List<LottoPlay> lottoList = new ArrayList<>();

    @Test
    void contextLoads() {
    }

    @Test
    public void testAddSeq() {

        Mockito.when(service.addMegaPowerSequence("MegaBall", set, 4, 24)).thenReturn(lottoPlay);

    }

    @Test
    public void testAddLottoPlay() {

        Mockito.when(service.addLottoPlay(lotPl)).thenReturn(lotPl);

    }

    @Test
    public void testGetPow() {

        Mockito.when(service.generatePowerNumber()).thenReturn(result);
        ResponseEntity<?> response = lottoController.getPowerSeq();
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void testGetMeg() {

        Mockito.when(service.generateMegaNumber()).thenReturn(result);
        ResponseEntity<?> response = lottoController.getMegaSeq();
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void testGetListOfAllSeq() {
        List<LottoPlay> lotPl = new ArrayList<LottoPlay>();
        lotPl.add(lottoPlay);
        Mockito.when(service.getLottoPlayList()).thenReturn(lotPl);
        ResponseEntity<?> response = lottoController.listMegaPow();
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetListOfAllPowerBallsLottoPlay() {

        Mockito.when(service.getPowerBalls("PowerBall")).thenReturn(lottoList);
        ResponseEntity<?> response = lottoController.getPowerPlays();
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetListOfAllMegaBallsLottoPlay() {

        Mockito.when(service.getMegaBalls("MegaBall")).thenReturn(lottoList);
        ResponseEntity<?> response = lottoController.getMegaPlays();
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

}

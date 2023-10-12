package com.it.code.LottoLogger.LottoLogger.controller;

import com.it.code.LottoLogger.LottoLogger.service.LotLogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;
@Slf4j
@Controller
public class LottoController {

    LotLogServiceImpl lotLogService;

    public LottoController(LotLogServiceImpl lotService) {

        this.lotLogService=lotService;
    }

    @GetMapping("/mega")
    public ResponseEntity<?> getMegaSeq(){

        log.info("+===> {}",HttpStatus.CREATED);
        return new ResponseEntity<>(lotLogService.generateMegaBalls(), HttpStatus.CREATED);
    }

    @GetMapping("/power")
    public ResponseEntity<?> getPowerSeq(){

        log.info("+===> {}",HttpStatus.CREATED);
        return new ResponseEntity<>(lotLogService.generatePowerBalls(), HttpStatus.CREATED);
    }
    @GetMapping("/lot_play")
    public ResponseEntity<?> listMega(){

        log.info("+===> {}",HttpStatus.OK);
        return new ResponseEntity<>(lotLogService.getLottoPlayList(), HttpStatus.OK);
    }


}

package com.it.code.LottoLogger.LottoLogger.controller;

import com.it.code.LottoLogger.LottoLogger.service.LotLogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.it.code.LottoLogger.LottoLogger.constants.Constant.MEGA_BALL;
import static com.it.code.LottoLogger.LottoLogger.constants.Constant.POWER_BALL;

@Slf4j
@Controller
public class LottoController {

    LotLogServiceImpl lotLogService;

    public LottoController(LotLogServiceImpl lotService) {

        this.lotLogService = lotService;
    }

    @GetMapping("/mega")
    public ResponseEntity<?> getMegaSeq() {

        log.info("+===> {}", HttpStatus.CREATED);
        return new ResponseEntity<>(lotLogService.generateMegaNumber(), HttpStatus.CREATED);
    }

    @GetMapping("/gold")
    public ResponseEntity<?> getMegaGold() {

        log.info("+===> {}", HttpStatus.CREATED);
        return new ResponseEntity<>(lotLogService.generateGoldenBall(), HttpStatus.CREATED);
    }


    @GetMapping("/mega-plays")
    public ResponseEntity<?> getMegaPlays() {

        log.info("+===> {}", HttpStatus.OK);
        return new ResponseEntity<>(lotLogService.getMegaBalls(MEGA_BALL), HttpStatus.OK);
    }

    @GetMapping("/power")
    public ResponseEntity<?> getPowerSeq() {

        log.info("+===> {}", HttpStatus.CREATED);
        return new ResponseEntity<>(lotLogService.generatePowerNumber(), HttpStatus.CREATED);
    }

    @GetMapping("/red")
    public ResponseEntity<?> getRedBall() {

        log.info("+===> {}", HttpStatus.CREATED);
        return new ResponseEntity<>(lotLogService.generateRedBall(), HttpStatus.CREATED);
    }

    @GetMapping("/power-plays")
    public ResponseEntity<?> getPowerPlays() {

        log.info("+===> {}", HttpStatus.OK);
        return new ResponseEntity<>(lotLogService.getPowerBalls(POWER_BALL), HttpStatus.OK);
    }

    @GetMapping("/lot_play")
    public ResponseEntity<?> listMegaPow() {

        log.info("+===> {}", HttpStatus.OK);
        return new ResponseEntity<>(lotLogService.getLottoPlayList(), HttpStatus.OK);
    }


}

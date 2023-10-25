package com.it.code.LottoLogger.LottoLogger.service;

import com.it.code.LottoLogger.LottoLogger.entity.LottoPlay;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface LotLogService {
    public List<LottoPlay> getLottoPlay(String lottoName);

    public LottoPlay addLottoPlay(LottoPlay lottoPlay);

    public List<LottoPlay> getLottoPlayList();

    public List<LottoPlay> getMegaBalls(String gameName);

    public List<LottoPlay> getPowerBalls(String gameName);

    public Integer[] generateMegaNumber();

    public Integer[] generatePowerNumber();

    public int generatePowerBalls();

    public int generateMegaBalls();

    public int generateGoldenBall();

    public int generateRedBall();

    public LottoPlay addMegaPowerSequence(String gameName, Set<Integer> megaPowSeq, int generatedBall, int lastBall);


}

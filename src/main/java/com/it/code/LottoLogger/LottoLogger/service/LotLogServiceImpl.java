package com.it.code.LottoLogger.LottoLogger.service;

import com.it.code.LottoLogger.LottoLogger.entity.LottoPlay;
import com.it.code.LottoLogger.LottoLogger.repository.LottoPlayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import static com.it.code.LottoLogger.LottoLogger.constants.Constant.*;

@Slf4j
@Service
public class LotLogServiceImpl implements LotLogService {
    private Random random = new Random();
    private LottoPlayRepository lottoPlayRepository;

    @Override
    public LottoPlay addLottoPlay(LottoPlay lottoPlay) {
        log.info("Adding Lotto Play --+++========>{}", "lottoPlay");
        return lottoPlayRepository.save(lottoPlay);
    }

    @Override
    public LottoPlay getLottoPlay(String lottoName) {
        return lottoPlayRepository.findLottoPlayByGameName(lottoName);
    }

    /**
     * @return
     */
    @Override
    public List<LottoPlay> getLottoPlayList() {
        return lottoPlayRepository.findAll();
    }

    @Override
    public Set<Integer> getMegaBalls(String gameName) {
        return getLottoPlay(gameName).getMegaBallSequence();
    }

    @Override
    public Set<Integer> getPowerBalls(String gameName) {
        return getLottoPlay(gameName).getPowerBallSequence();
    }

    @Override
    public Set<Integer> generateMegaNumber() {
        Set<Integer> megaSet = new HashSet<>();
        return addMegaPowerSequence(MEGA_BALL, megaSet, generateMegaBalls(), generateGoldenBall()).getMegaBallSequence();
    }

    @Override
    public Set<Integer> generatePowerNumber() {
        Set<Integer> powerSet = new HashSet<>();
        return addMegaPowerSequence(POWER_BALL, powerSet, generatePowerBalls(), generateRedBall()).getPowerBallSequence();
    }


    /**
     * This code will generate a random integer in the range [0, MAX_POWER_DIGIT+BOUND_INCREMENT),
     * which means that the result will be >= 0 and less than
     * (MAX_POWER_DIGIT+BOUND_INCREMENT).
     *
     * @return
     */
    @Override
    public int generatePowerBalls() {

        return random.nextInt(MAX_POWER_DIGIT + BOUND_INCREMENT)+ BOUND_INCREMENT;
    }

    /**
     * This code will generate a random integer in the range [0, MAX_MEGA_DIGIT+BOUND_INCREMENT),
     * which means that the result will be >= 0 and less than
     * (MAX_MEGA_DIGIT+BOUND_INCREMENT).
     *
     * @return
     */
    @Override
    public int generateMegaBalls() {
        return random.nextInt(MAX_MEGA_DIGIT + BOUND_INCREMENT)+ BOUND_INCREMENT;
    }

    /**
     * This code will generate a random integer in the range [0, MAX_GOLDEN_DIGIT+BOUND_INCREMENT),
     * which means that the result will be >= 0 and less than
     * (MAX_GOLDEN_DIGIT+BOUND_INCREMENT).
     *
     * @return
     */
    @Override
    public int generateGoldenBall() {
        return random.nextInt(MAX_GOLDEN_DIGIT + BOUND_INCREMENT)+ BOUND_INCREMENT;
    }

    /**
     * This code will generate a random integer in the range [0, MAX_RED_DIGIT+BOUND_INCREMENT),
     * which means that the result will be >= 0 and less than
     * (MAX_RED_DIGIT+BOUND_INCREMENT).
     *
     * @return
     */
    @Override
    public int generateRedBall() {
        return random.nextInt(MAX_RED_DIGIT + BOUND_INCREMENT)+ BOUND_INCREMENT;
    }

    /**
     * Add sequence number
     *
     * @param gameName      String name of the game
     * @param megaPowSeq    Set<Integer>
     * @param generatedBall INTEGER
     * @param lastBall      INTEGER
     * @return LottoPlay
     */
    @Override
    public LottoPlay addMegaPowerSequence(String gameName, Set<Integer> megaPowSeq, int generatedBall, int lastBall) {

        int numbOfDig = 6;
        int ballNumb = generatedBall;
        LottoPlay lottoPlay = new LottoPlay();





        switch (gameName) {

            case MEGA_BALL: {

                lottoPlay.setGameName(gameName);

                lottoPlay.setGoldMegaBall(lastBall);
                log.info("lotto ================================================================="+lottoPlay.getGoldMegaBall());

                                lottoPlay.setWhiteMegaBalls(generateMegaBalls());

                                megaPowSeq.add(generateMegaBalls());
                                megaPowSeq.add(generateMegaBalls());
                                megaPowSeq.add(generateMegaBalls());
                                megaPowSeq.add(generateMegaBalls());
                                megaPowSeq.add(generateMegaBalls());





                lottoPlay.setMegaBallSequence(megaPowSeq);
                //addLottoPlay(lottoPlay);

                break;
            }
            case POWER_BALL: {
                lottoPlay.setGameName(gameName);
                lottoPlay.setPowerBallSequence(megaPowSeq);

                lottoPlay.setRedPowerBall(lastBall);
                log.info("lotto ================================================================="+lottoPlay);
//                IntStream.range(START_AT_0, numbOfDig).forEach(i -> {
//
//                if ((megaPowSeq.size() == 0 || megaPowSeq.size() < numbOfDig) && !megaPowSeq.contains(ballNumb)) {
//
//                    lottoPlay.setWhitePowerBalls(generatePowerBalls());
//
//                    megaPowSeq.add(lottoPlay.getWhitePowerBalls());
//                    megaPowSeq.add(lottoPlay.getWhitePowerBalls());
//                    megaPowSeq.add(lottoPlay.getWhitePowerBalls());
//                    megaPowSeq.add(lottoPlay.getWhitePowerBalls());
//                    megaPowSeq.add(lottoPlay.getWhitePowerBalls());
//
//
//                } else {
//                    addMegaPowerSequence(gameName, megaPowSeq, generatedBall, lastBall);
//                }
//                        }
//                );

               // addLottoPlay(lottoPlay);

                break;
            }

            default: {

                log.info("Unknown game name {}", gameName);
            }
        }


        return lottoPlay;

    }
}

package com.it.code.LottoLogger.LottoLogger.service;

import com.it.code.LottoLogger.LottoLogger.entity.LottoPlay;
import com.it.code.LottoLogger.LottoLogger.repository.LottoPlayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.it.code.LottoLogger.LottoLogger.constants.Constant.*;

@Slf4j
@Service
public class LotLogServiceImpl implements LotLogService {
    private Random random = new Random();
    private LottoPlayRepository lottoPlayRepository;

    @Autowired
    public LotLogServiceImpl(LottoPlayRepository lottoPlyRep) {
        this.lottoPlayRepository = lottoPlyRep;

    }

    @Override
    public LottoPlay addLottoPlay(LottoPlay lottoPlay) {
        // lottoPlayRepository.deleteAll();
        deleteJunkData();
        log.info("Adding Lotto Play ---+====>{}", "lottoPlay");
        return lottoPlayRepository.save(lottoPlay);
    }

    @Override
    public List<LottoPlay> getLottoPlay(String lottoName) {

        return lottoPlayRepository.findAllByGameName(lottoName);
    }

    /**
     * Retrieve a list of all available LottoPlay instances
     *
     * @return
     */
    @Override
    public List<LottoPlay> getLottoPlayList() {

        return lottoPlayRepository.findAll();
    }

    /**
     * Retrieve MegaBalls of all available LottoPlay instances
     *
     * @param gameName
     * @return
     */
    @Override
    public List<LottoPlay> getMegaBalls(String gameName) {


        return getLottoPlay(gameName);
    }

    /**
     * Retrieve PowerBalls of all available LottoPlay instances
     *
     * @param gameName
     * @return
     */
    @Override
    public List<LottoPlay> getPowerBalls(String gameName) {

        return getLottoPlay(gameName);
    }

    public void deleteJunkData() {

        if (getPowerBalls(POWER_BALL).size() == MAX_SEQUENCE_COUNT ||
                getMegaBalls(MEGA_BALL).size() == MAX_SEQUENCE_COUNT) {
            log.info("Deleting Sequence Numbers...");
            lottoPlayRepository.deleteAll();
        }
    }

    /**
     * Will generate MegaNumber when invoked
     *
     * @return
     */
    @Override
    public Integer[] generateMegaNumber() {
        Set<Integer> megaSet = new HashSet<>();
        return addMegaPowerSequence(MEGA_BALL, megaSet, generateMegaBalls(), generateGoldenBall()).getMegaBallSequence();
    }

    /**
     * Will generate PowerNumber when invoked
     *
     * @return
     */
    @Override
    public Integer[] generatePowerNumber() {
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

        return random.nextInt(MAX_POWER_DIGIT) + BOUND_INCREMENT;
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
        return random.nextInt(MAX_MEGA_DIGIT) + BOUND_INCREMENT;
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
        return random.nextInt(MAX_GOLDEN_DIGIT) + BOUND_INCREMENT;
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
        return random.nextInt(MAX_RED_DIGIT) + BOUND_INCREMENT;
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

        LottoPlay lottoPlay = new LottoPlay();
        try {

            switch (gameName) {

                case MEGA_BALL: {

                    lottoPlay.setGameName(gameName);

                    lottoPlay.setGoldMegaBall(lastBall);

                    lottoPlay.setMegaBallSequence(addBallsValues(megaPowSeq, lastBall));

                    log.info("=-->" + lottoPlay.getGoldMegaBall().toString());

                    checkStatus(megaPowSeq, lottoPlay);
                    addLottoPlay(lottoPlay);

                    break;
                }
                case POWER_BALL: {
                    lottoPlay.setGameName(gameName);


                    lottoPlay.setRedPowerBall(lastBall);
                    lottoPlay.setPowerBallSequence(addBallsValues(megaPowSeq, lastBall));
                    log.info("=->" + megaPowSeq.size());

                    checkStatus(megaPowSeq, lottoPlay);

                    addLottoPlay(lottoPlay);

                    break;
                }

                default: {

                    log.info("Unknown game name {}", gameName);
                }
            }

            return lottoPlay;

        } catch (Exception e) {

            log.error("Error on:: addMegaPowerSequence =>{}", e.getLocalizedMessage());

            return lottoPlay;
        }


    }


    /**
     * check if size is 5 and set complete true
     *
     * @param megaPowSeq
     * @param lottoPlay
     */
    private void checkStatus(Set<Integer> megaPowSeq, LottoPlay lottoPlay) {

        try {
            if (megaPowSeq.size() == 5) {

                lottoPlay.setSize(megaPowSeq.size());
                lottoPlay.setIsComplete(Boolean.TRUE);

            } else {

                lottoPlay.setSize(megaPowSeq.size());
                lottoPlay.setIsComplete(Boolean.FALSE);
            }
        } catch (Exception e) {
            log.error("Error checking Status =>{}", e.getLocalizedMessage());
        }


    }

    /**
     * A set into an array, it will also add values to a set eliminating duplicates
     *
     * @param megaPowSeq
     * @return
     */
    private Integer[] addBallsValues(Set<Integer> megaPowSeq, int lastBall) {
        try {
            for (int i = 0; i < 5; i++) {
                megaPowSeq.add(generateMegaBalls());
            }

            Integer[] seqArray = megaPowSeq.toArray(new Integer[megaPowSeq.size()]);
            Arrays.sort(seqArray);

            // Add lastBall to the end of the array
            Integer[] resultArray = new Integer[seqArray.length + 1];
            System.arraycopy(seqArray, 0, resultArray, 0, seqArray.length);
            resultArray[resultArray.length - 1] = lastBall;
            log.info("resultArray: last - {}", lastBall);
            return resultArray;
        } catch (Exception e) {
            log.error("Error on:: addBallsValues=>{}", e.getMessage());
            return null;
        }
    }
}

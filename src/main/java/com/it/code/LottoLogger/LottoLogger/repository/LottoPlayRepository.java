package com.it.code.LottoLogger.LottoLogger.repository;

import com.it.code.LottoLogger.LottoLogger.entity.LottoPlay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LottoPlayRepository extends JpaRepository<LottoPlay,Long> {
    LottoPlay findLottoPlayByGameName(String gameName);

}

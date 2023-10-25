package com.it.code.LottoLogger.LottoLogger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LottoPlay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)

    private Long id;

    private String gameName;
    private Integer size;
    private Boolean isComplete;

    private Integer goldMegaBall;
    private Integer[] megaBallSequence;


    private Integer redPowerBall;

    private Integer[] powerBallSequence;


}

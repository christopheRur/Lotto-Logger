package com.it.code.LottoLogger.LottoLogger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    private Integer whiteMegaBalls;
    private Integer goldMegaBall;
    private Set<Integer> megaBallSequence;

    private Integer whitePowerBalls;
    private Integer redPowerBall;
    private Set<Integer> powerBallSequence;


}

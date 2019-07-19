package org.ajou.realcoding.massagemachine.massagemachine.domain;

import lombok.Data;

@Data
public class MassageMode {
    private String wantMode;
    private String bodyPart;
    private String power;
    private int time;
}
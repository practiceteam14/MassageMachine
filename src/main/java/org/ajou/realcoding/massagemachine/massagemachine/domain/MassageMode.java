package org.ajou.realcoding.massagemachine.massagemachine.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class MassageMode {
    private String wantMode;
    private String bodyPart;
    private String power;
    private int time;

    @Builder
    public MassageMode(String wantModeName, String bodyPart, String power, int time) {
        this.wantMode = wantModeName;
        this.bodyPart = bodyPart;
        this.power = power;
        this.time = time;
    }
}
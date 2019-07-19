package org.ajou.realcoding.massagemachine.massagemachine.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
public class MassageMode {
    private String wantMode;
    private String bodyPart;
    private String power;
    private int time;
}
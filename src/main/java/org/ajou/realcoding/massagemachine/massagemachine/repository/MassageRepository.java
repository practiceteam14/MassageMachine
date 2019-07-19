package org.ajou.realcoding.massagemachine.massagemachine.repository;

import org.ajou.realcoding.massagemachine.massagemachine.domain.MassageMode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

public class MassageRepository {
    List<MassageMode> modes;

    @Autowired
    Random random;

    public void insertModeRandomlyBodyPartAndPower(String wantModeName) {
        String bodyPart[] = {"목", "어깨", "팔", "다리", "발"};
        String power[] = {"강", "중", "약"};
        int time = random.nextInt(10);
        MassageMode massageMode = new MassageMode(wantModeName, bodyPart[random.nextInt(bodyPart.length-1)],
                power[random.nextInt(power.length-1)], time);
        modes.add(massageMode);
    }

    public List<MassageMode> findAll() {
        return modes;
    }

    public MassageMode findMassageModeByModeName(String wantModeName) {
        for (MassageMode massageMode : modes) {
            if(massageMode.getWantMode().equals(wantModeName)) return massageMode;
        }
        return null;
    }
}

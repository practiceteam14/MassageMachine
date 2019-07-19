package org.ajou.realcoding.massagemachine.massagemachine.service;

import org.ajou.realcoding.massagemachine.massagemachine.domain.MassageMode;
import org.ajou.realcoding.massagemachine.massagemachine.repository.MassageRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MassageService {
    @Autowired
    private MassageRepository massageRepository;

    public void addMassageModeByModeName(String wantModeName) {
        massageRepository.insertModeRandomlyBodyPartAndPower(wantModeName);
    }

    public MassageMode selectMassageModeByModeName(String wantModeName) {
        for (int i = 0; i<massageRepository.findAll().size(); i++) {
            log.info("마사지 모드 : {}", massageRepository.findAll().get(i));
        }
        return massageRepository.findMassageModeByModeName(wantModeName);
    }
}

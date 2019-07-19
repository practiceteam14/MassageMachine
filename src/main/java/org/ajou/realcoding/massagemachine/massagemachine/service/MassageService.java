package org.ajou.realcoding.massagemachine.massagemachine.service;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.massagemachine.massagemachine.domain.MassageMode;
import org.ajou.realcoding.massagemachine.massagemachine.repository.MassageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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

    public void startMassage(String wantModeName) {
        MassageMode massageMode = selectMassageModeByModeName(wantModeName);
        for (int i = massageMode.getTime(); i>0; i++) {
            log.info("마사지 부위 {}, 마사지 세기 : {}, 마사지 시간 {}", massageMode.getBodyPart(), massageMode.getPower(), i);
        }
        log.info("마사지가 끝났습니다.");
    }
}

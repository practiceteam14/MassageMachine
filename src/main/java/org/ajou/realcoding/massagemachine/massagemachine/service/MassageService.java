package org.ajou.realcoding.massagemachine.massagemachine.service;

import org.ajou.realcoding.massagemachine.massagemachine.repository.MassageRepository;

public class MassageService {
    private MassageRepository massageRepository;

    public void addMassageModeByModeName(String wantModeName) {
        massageRepository.insertModeRandomlyBodyParyAndPower(wantModeName);
    }
}

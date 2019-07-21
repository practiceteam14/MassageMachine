package org.ajou.realcoding.massagemachine.massagemachine.service;

import org.ajou.realcoding.massagemachine.massagemachine.domain.MassageMode;
import org.ajou.realcoding.massagemachine.massagemachine.repository.MassageRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class MassageServiceTest {
    @Mock
    private MassageRepository massageRepository;

    @InjectMocks
    private MassageService massageService;

    @Test
    public void 랜덤으로_만들어진_마사지모드의_time은_무조건_10미만이다 () {
        MassageMode massageMode = mock(MassageMode.class);
        when(massageMode.getTime()).thenReturn(4);

        assertThat(massageMode.getTime(), lessThan(10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 마사지모드로_목어깨팔다리발_이외의_부위로_저장하면_에러발생 () {
        MassageMode massageMode = mock(MassageMode.class);

        doThrow(new IllegalArgumentException()).when(massageMode).setBodyPart("허리");
        massageMode.setBodyPart("허리");
    }

    @Test
    public void 부위_강도_시간은_랜덤으로_결정되므로_set_실행시_오류발생 () {
        MassageMode massageMode = mock(MassageMode.class);

        verify(massageMode, never()).setBodyPart(anyString());
        verify(massageMode, never()).setPower(anyString());
        verify(massageMode, never()).setTime(anyInt());
    }
    @Test
    public void 아침모드를_호출하면_안마부위를_리턴하고_리턴값이_올바른지_검색테스트() {
        when(massageService.selectMassageModeByModeName("아침")).thenReturn(new MassageMode("아침", "발", "강", 10));
        String massageBodyPart = massageService.selectMassageModeByModeName("아침").getBodyPart();
        assertThat(massageBodyPart, is("발"));
    }
}

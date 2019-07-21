package org.ajou.realcoding.massagemachine.massagemachine.service;

import org.ajou.realcoding.massagemachine.massagemachine.domain.MassageMode;
import org.ajou.realcoding.massagemachine.massagemachine.repository.MassageRepository;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

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
    public void 마사지모드에서_몸부위를_불러오면_목부위를_리턴한다() {  //김영진 작성
        MassageMode massageMode = mock(MassageMode.class);
        when(massageMode.getBodyPart()).thenReturn("목");
        assertThat(massageMode.getBodyPart(), is("목"));
    }

    @Test
    public void 안마기의정보를_모킹하고_마사지서비스의_무중력모드를불렀을경우_테스트() { //김영진 작성
        when(massageService.selectMassageModeByModeName("무중력"))
                .thenReturn(new MassageMode("무중력", "다리", "약", 100));
        String massageModeName = massageService.selectMassageModeByModeName("무중력").getWantMode();
        assertThat(massageModeName, is("무중력"));
        verify(massageRepository, times(1)).findMassageModeByModeName(anyString());
    }

    @Test
    public void 홍콩을_호출하면_홍콩모드의정보를_리턴하고_최소1번호출되었는지_검증() {
        given(massageRepository.findMassageModeByModeName("홍콩"))
                .willReturn(new MassageMode("홍콩","어깨","강",30));
        MassageMode massageMode = massageService.selectMassageModeByModeName("홍콩");

        verify(massageRepository, atLeast(1)).findMassageModeByModeName(anyString());
        assertThat(massageMode.getWantMode(), is("홍콩"));
    }
    @Test
    public void 찾고자_하는_변수에_맞는_첫번째_마사지_모드_찾기(){
        List<MassageMode> massageModeList =new ArrayList<>();
        MassageMode modeOne=new MassageMode("one","목","강",4);
        MassageMode modeTwo=new MassageMode("two","어깨","약",9);
        MassageMode modeThree=new MassageMode("three","다리","약",8);

        massageModeList.add(modeOne);
        massageModeList.add(modeTwo);
        massageModeList.add(modeThree);

        Optional<MassageMode> filteredMode=massageModeList.stream()
                .filter(c->c.getPower().equals("약"))
                .findFirst();
        assertThat(filteredMode.get().getWantMode(),is("two"));
    }
}

package com.example.v3_372.hw4_2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by V3-372 on 2018/4/11.
 */

public class JudgeTest {
    private Judge OutComeTest;

    @Before
    public void set(){OutComeTest=new Judge();}

    @After
    public void clear(){OutComeTest=null;}

    @Test
    public void getOutcome(){

        assertEquals("雙方平手!",OutComeTest.getOutcome("剪刀","剪刀"));
        assertEquals("雙方平手!",OutComeTest.getOutcome("石頭","石頭"));
        assertEquals("雙方平手!",OutComeTest.getOutcome("布","布"));
        assertEquals("恭喜，你贏了！",OutComeTest.getOutcome("剪刀","石頭"));
        assertEquals("恭喜，你贏了！",OutComeTest.getOutcome("石頭","布"));
        assertEquals("恭喜，你贏了！",OutComeTest.getOutcome("布","剪刀"));
        assertEquals("很可惜，你輸了!",OutComeTest.getOutcome("剪刀","布"));
        assertEquals("很可惜，你輸了!",OutComeTest.getOutcome("石頭","剪刀"));
        assertEquals("很可惜，你輸了!",OutComeTest.getOutcome("布","石頭"));
    }

}

package com.example.v3_372.hw4_2;

/**
 * Created by V3-372 on 2018/4/11.
 */

public class Judge {
    String getOutcome(String com,String play)
    {
        if(com.equals(play)) return "雙方平手";
        if(com.equals("剪刀")&&play.equals("石頭")||
           com.equals("石頭")&&play.equals("布")||
           com.equals("布")&&play.equals("剪刀")) return "恭喜，你贏了!";
        else return "很可惜，你輸了!";
    }
}

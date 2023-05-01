package com.example.javafxpractice;

public class Stone {
    //石のマーク
    String mark;
    //石の総数
    int total;
    //一度にとれる石の数
    int takeStone;

    public Stone(String mark, int total, int takeStone) {
        this.mark = mark;
        this.total = total;
        this.takeStone = takeStone;
    }

}

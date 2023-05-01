package com.example.javafxpractice;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StoneGame implements App {
    @FXML
    Label name;
    Player[] players;
    int totalNumberOfStones;
    int numberOfStonesLeft;
    int playerIndex;
    Stone mark;

    public StoneGame(Player[] players, int playerIndex, Stone mark) {
        this.players = players;
        this.mark = mark;
        //this.numberOfStonesLeft = totalNumberOfStones;
        this.numberOfStonesLeft = mark.total;
        this.playerIndex = playerIndex;
    }

    //初期情報出力
    private void output() {
        System.out.println("石の総数：" + mark.total);
        System.out.println("1度に取れる石の数：" + mark.takeStone);
        System.out.print("参加プレイヤー");
        for (Player player : players) {
            System.out.print(player.name + " ");
        }
        System.out.println();
        System.out.println("石の記号；" + mark.mark);
        System.out.println("------------------------------");
    }

    private boolean judgeEnd() {
        if (numberOfStonesLeft >= 1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean next(int num) {

        try {
            if (checkInput(num)) {
                return next(num);
            } else {
                numberOfStonesLeft -= num;
                return judgeEnd();
            }
        } catch (InputMismatchException e) {
            System.out.println("数値を入力してください");
            return next(num);
        }
    }

    private boolean checkInput(int num) {
        if (num < 1 || num > mark.takeStone) {
            System.out.println("入力値が間違っています(1~" + mark.takeStone + "まで入力可能です)");
            return true;
        } else {
            return false;
        }
    }

    public void start() {
        System.out.println("------------------------------");
        System.out.println("石取りゲームを開始します。");

        output();

        //while(totalNumberOfStones < 0) {
//            System.out.println(players[playerIndex].name + "の番");
//            System.out.println("石の数を入力してください。（1~" + mark.takeStone + "まで入力可能です。）");
//            if (next()) {
//                break;
//            }
            String stone = "";
            for (int i = 1; i <= numberOfStonesLeft; i++) {
                stone += mark.mark;
            }
            System.out.println("残り：" + numberOfStonesLeft + "個");
            System.out.println(stone);
            System.out.println("------------------------------");
            playerIndex++;
            playerIndex = playerIndex % players.length;
        //}
        int loser = playerIndex;
        int winner = playerIndex-1;
        if (winner == -1) {
            winner = players.length - 1;
        }

        //勝者プレイヤーのカウントアップ
        players[winner].countUpWins();

        System.out.println("勝者：" + players[winner].name);
        System.out.println("敗者：" + players[loser].name);

    }

//    public void startGame() {
//        while(true) {
//            this.name.setText(players[playerIndex].name + "の番");
//            break;
//        }
//    }
}

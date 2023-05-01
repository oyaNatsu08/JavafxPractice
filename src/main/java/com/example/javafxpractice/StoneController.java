package com.example.javafxpractice;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StoneController {
    @FXML
    private Label nameFXML;
    @FXML
    private Text totalFXML;
    @FXML
    private Text takeStoneFXML;
    @FXML
    private Text playersFXML;
    @FXML
    private Text markFXML;
    @FXML
    private Text leftNumberFXML;
    @FXML
    private TextField numFXML;
    @FXML
    private Label winFXML;
    @FXML
    private Label loseFXML;
    @FXML
    private Label errorFXML;

    StoneGame stoneGame;
    Player[] players;
    Stone mark;
    @FXML
    private void initialize() {
        //石取りゲームのメインメソッド処理
        //参加プレイヤーオブジェクト
        players = new Player[] {
                new Player("Noa"),
                new Player("Neko"),
                new Player("Inu")
        };
        //石取りゲームマークオブジェクト
        mark = new Stone("☆", 50, 5);
        //石取りゲームメイン処理オブジェクト
        stoneGame = new StoneGame(players, 0, mark);
        //stoneGame.start();

        this.totalFXML.setText(String.valueOf(mark.total));
        this.takeStoneFXML.setText(String.valueOf(mark.takeStone));
        String playerText = "";
        for (Player player : players) {
            playerText += player.name + " ";
        }
        this.playersFXML.setText(playerText);
        this.markFXML.setText(mark.mark);
        this.nameFXML.setText(players[0].name + "の番");
        this.leftNumberFXML.setText("残り" + mark.total + "個\n" + mark.mark.repeat(mark.total));
    }

    @FXML
    public void takeStone() {
        //入力した値を受け取る
        int num = Integer.parseInt(this.numFXML.getText());
        if (num > mark.takeStone) {
            this.errorFXML.setText("入力値が違います。1~" + mark.takeStone + "までしか入力できません。");
            return;
        } else {
            this.errorFXML.setText("");
        }
        //残りから引く
        mark.total -= num;
        stoneGame.playerIndex++;
        stoneGame.playerIndex = stoneGame.playerIndex % players.length;
        this.nameFXML.setText(players[stoneGame.playerIndex].name + "の番");
        this.leftNumberFXML.setText("残り" + mark.total + "個\n" + mark.mark.repeat(mark.total));
        if (mark.total <= 0) {
            int loser = stoneGame.playerIndex - 1;
            int winner = stoneGame.playerIndex - 2;
            System.out.println(loser);
            if (winner <= -1) {
                winner = players.length - 2 + stoneGame.playerIndex;
            }
            if (loser <= -1) {
                loser = players.length - 1;
            }
            this.winFXML.setText("勝者：" + players[winner].name);
            this.loseFXML.setText("敗者：" + players[loser].name);
        }

    }
}

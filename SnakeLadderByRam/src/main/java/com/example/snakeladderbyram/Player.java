package com.example.snakeladderbyram;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Player {
    //create Circile class for dice move
   private Circle coin;
   //variable for count exact position of coin
   private int currentPosition ;
   // name of player
    private String name ;
    // initialize the board class for put the coin on board

   private static Board gameBoard = new Board();
   // constructor of Player class have value
    public Player(int tileSize , Color coinColor , String playerName){
        coin = new Circle(tileSize/4);
        coin.setFill(coinColor);
        // fix the position for initial at 1;
       currentPosition = 0; // we  can set currentPosition 0 as well out of board
        movePlayer(1);
        name = playerName;

    }
    // move method of move the coin on the board
    public void movePlayer(int diceValue){
        //check for currentPosition will  not go beyond 100
        if(currentPosition+diceValue<=100){
            //currentPosition increase
            currentPosition    += diceValue;

            TranslateTransition secondMove = null , firstMove = translateAnimation(diceValue);

//            translateAnimation(diceValue);

           // get new position if new position is not equal to current position we get from new array
            int newPosition = gameBoard.getNewPosition(currentPosition);
            if(newPosition != currentPosition && currentPosition != -1){
                currentPosition = newPosition;
//                translateAnimation(6);
               secondMove = translateAnimation(6);
            }

            if(secondMove==null){
                firstMove.play();
            } else {
                SequentialTransition sequentialTransition = new SequentialTransition(
                        firstMove , new PauseTransition(Duration.millis(400)) , secondMove );
                sequentialTransition.play();
            }

        }

//        int x  = gameBoard.getXCoordinate(currentPosition);
//        int y = gameBoard.getYCoordinate(currentPosition);
//        coin.setTranslateX(x);
//        coin.setTranslateY(y);


    }
    // use for animation while move coin
    public TranslateTransition translateAnimation(int diceValue){
        TranslateTransition animate = new TranslateTransition(Duration.millis(150*diceValue) , coin);
        animate.setToX(gameBoard.getXCoordinate(currentPosition));
        animate.setToY(gameBoard.getYCoordinate(currentPosition));
        animate.setAutoReverse(false);
//       return animate;
        return animate;

    }

    // when restart the game after win put coin on initial stage i.e. 1;

 public void startingPosition(){
        currentPosition = 0;
        movePlayer(1);
 }
   //check winning condition
    boolean isWinner(){
        if(currentPosition==100)
            return true;
        return false;
    }

    //getter method for coin as variable is pvt
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
 //getter method for name as variable is pvt
    public String getName() {
        return name;
    }
}

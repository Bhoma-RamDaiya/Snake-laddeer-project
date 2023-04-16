package com.example.snakeladderbyram;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.io.IOException;


public class SnakeLadder extends Application {
//    initialize public varriable to set the size of board
    public static final int tileSize = 40 ,  width = 10 , height = 10 ;
//    initialize the height of button  i.e static varriable
    public static final int buttonLine = height*tileSize+70;
    //    initialize the height of Label info display  i.e static varriable
    public static final int infoLine = height*tileSize+40;
//    initialize object of class;
    private Dice  dice = new Dice();
//   create object of the player class for two player
    private Player playerOne , playerTwo;
//    initialize the boolean variable for condition check
    private boolean gameStarted = false , playerOneturn = true , playerTwoTurn = false;

//create constructor for pane class
    private Pane createContent(){
        //object creation of pane class
        Pane root = new Pane();
        // size of board
        root.setPrefSize(width*tileSize , height*tileSize+100);
        for (int i = 0; i <height ; i++) {
            for (int j = 0; j < width; j++) {

//        initialize instanse of Tile class
                Tile tile  =  new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);

//  add the tile to the pane ;
                root.getChildren().add(tile);

            }
        }
//   initialize Image class to provide the path of image
        Image img = new Image("C:\\Users\\91887\\Desktop\\SnakeLadderByRam\\src\\main\\Snake&Ladder.png");
// for visibility of img create the instance of ImageView class
        ImageView board = new ImageView();
//        set the height and width of image according to board
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);
//        buttons for gui
        Button Player1 = new Button("Player One");
        Button Player2 = new Button("Player Two");
        Button StartButton = new Button("Game Start");
//        fix the height and width of buttons
        Player1.setTranslateY(buttonLine);
        Player1.setTranslateX(20);
        Player1.setDisable(true);

        Player2.setTranslateY(buttonLine);
        Player2.setTranslateX(300);
        Player2.setDisable(true);

        StartButton.setTranslateY(buttonLine);
        StartButton.setTranslateX(160);

//        info display
        Label Player1label = new Label("");

        Label Player2label = new Label("");

        Label Startlabel = new Label("Start The Game");

        Player1label.setTranslateY(infoLine);
        Player1label.setTranslateX(20);

        Player2label.setTranslateY(infoLine);
        Player2label.setTranslateX(300);

        Startlabel.setTranslateY(infoLine);
        Startlabel.setTranslateX(160);

 // initialize variable of Player class
        playerOne = new Player(tileSize , Color.BLACK , "Ram Dahiya");
        playerTwo = new Player(tileSize-4 , Color.AQUA , "Kishan");

//        moving player 1
        Player1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneturn){
                        int diceValue = dice.getRolledDiceValue();
                        Startlabel.setText(" Dice Value " + diceValue);
                        playerOne.movePlayer(diceValue);


                        //Winning Condition Check for Player one

                        if(playerOne.isWinner()){
                            //make the all condition false if player is won
                            playerOneturn = false ;
                            Player1.setDisable(true);
                            Player1label.setText("");

                            playerTwoTurn = false ;
                            Player2.setDisable(true);

                            Startlabel.setText(" Congratulations "+ playerOne.getName());
                            //restart game
                            StartButton.setDisable(false);
                            StartButton.setText("Restart");
                            gameStarted = false;
                        } else {

                            playerOneturn = false ;
                            Player1.setDisable(true);
                            Player1label.setText("");
// make player two turn true , enable button of player2
                            playerTwoTurn = true ;
                            Player2.setDisable(false);
                            Player2label.setText(" Your Turn "+ playerTwo.getName());
                        }

                    }
                }

            }
        });


//        moving player 2
        Player2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){

                        int diceValue = dice.getRolledDiceValue();
                        Startlabel.setText(" Dice Value " + diceValue);
                        playerTwo.movePlayer(diceValue);

                       // wining condition
                        if(playerTwo.isWinner()){
                            //as same as player one condition for player two
                            Startlabel.setText(" Congratulations " +playerTwo.getName());
                            playerTwoTurn = false ;
                            Player2.setDisable(true);
                            Player2label.setText("");

                            playerOneturn = false;
                            Player1.setDisable(true);
                            Player1label.setText("");
                            //for game restart
                            StartButton.setDisable(false);
                            StartButton.setText("Restart");
                            gameStarted = false;
                        } else {
                            playerTwoTurn = false ;
                            Player2.setDisable(true);
                            Player2label.setText("");

                            playerOneturn = true ;
                            Player1.setDisable(false);
                            Player1label.setText(" Your Turn "+ playerOne.getName());
                        }



                    }
                }
            }
        });


        StartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true ;
                StartButton.setDisable(true);
                Startlabel.setText("Game Started");
                playerOneturn = true ;
                Player1label.setText("Your Turn " + playerOne.getName());
                Player1.setDisable(false);
                playerOne.startingPosition();
                playerTwoTurn = false;
                Player2.setDisable(true);
                Player2label.setText("");
                playerTwo.startingPosition();

            }
        });
//        add the img board & button &Label to Pane class (root)
        root.getChildren().addAll(board , Player1 ,Player2 , StartButton ,Player1label, Startlabel ,Player2label ,
        playerOne.getCoin() , playerTwo.getCoin()  );
        return root ;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
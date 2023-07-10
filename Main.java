package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.util.Random;
import javafx.animation.PauseTransition;


/**
 * The Main class is the starting point for a Battleship game application.
 * It handles the user interface and game initialization, and it maintains a reference
 * to the game boards for both the player and the AI.
 */
public class Main extends Application {

    private int boardSize = 6; // Default board size
    static String player="player";
    static String AI="AI";
    GameBoard playerBoard = new GameBoard(boardSize,player="gg");
    GameBoard computerBoard = new GameBoard(boardSize,AI);
    ProgressBar playerProgressBar= new ProgressBar();
    ProgressBar computerProgressBar= new ProgressBar();
     BorderPane root = new BorderPane();  
     int x,y;
     
    
     /**
      * The start method is the main entry point for all JavaFX applications.
      *
      * @param primaryStage the primary stage for this application, onto which
      * the application scene can be set.
      */
    @Override
    public void start(Stage primaryStage) {
        try {
             
           
            playerProgressBar.setPrefHeight(20); // Set preferred height
          
            computerProgressBar.setPrefHeight(20); // Set preferred height
            VBox playerBox = new VBox(playerBoard.getGridPane(), playerProgressBar);
            playerBox.setStyle("-fx-background-color: #0000FF"); // Hex color for blue
            root.setLeft(playerBox);
            VBox computerBox = new VBox(computerBoard.getGridPane(), computerProgressBar);
            computerBox.setStyle("-fx-background-color: #0000FF"); // Hex color for blue
            root.setRight(computerBox);
           

       
          
            primaryStage.setTitle("Yaser Alsami & Farid Jarkas"); // Set the window title

            
    
            MiddlePanel middlePanel = new MiddlePanel();

            // Add a listener to the dimension selection
            middlePanel.getDimensionSelection().valueProperty().addListener((observable, oldValue, newValue) -> {
                int newBoardSize = 2 * newValue;
                playerBoard.setBoardSize(newBoardSize);
                computerBoard.setBoardSize(newBoardSize);
                boardSize = newBoardSize;
            });
            
            
            
            


            root.setCenter(middlePanel.getVBox());
            
            Button randomButton = new Button("Random");
            Label rand = new Label("Feeling Lazey? click this button to genrate a random design for you boat");
            randomButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
               	 
                	  playerBoard.placeShipRandomly("player");
                  	
                     
                      
                   
                }
            });
            
          
           Button playButton = new Button ("AI");
           Label ai = new Label("click AI button to have the AI make shot");
           playButton.setOnAction(new EventHandler<ActionEvent>() {
        	   public void handle(ActionEvent event) { 
        		// Assuming `playerBoard` is the player's GameBoard and `computerBoard` is the AI's GameBoard
        		    Random random = new Random ();
        		   
        		   x=  random.nextInt(boardSize);
        	        y=  random.nextInt(boardSize);
        		  
        		  
        		   playerBoard.hitShip(x, y, "player");;
           }
        });
           HBox randButton = new HBox(rand,randomButton, playButton,ai );
           root.setBottom(randButton);
           
   
            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            
            
            // Create a new Stage for the splash screen
            Stage splashScreen = new Stage();
            splashScreen.initStyle(StageStyle.UNDECORATED); 

            // Create a VBox to hold the splash screen content
            VBox splashLayout = new VBox();
            splashLayout.setAlignment(Pos.CENTER);

            // Add an ImageView with your splash image
            Image splashImage = new Image("C:\\CST8221\\Assig22\\src\\application\\game_about.jpg"); // Replace with the path to your image
            ImageView splashImageView = new ImageView(splashImage);
            splashLayout.getChildren().add(splashImageView);
            splashImageView.setFitWidth(800);
            splashImageView.setFitHeight(600);

            // Add a ProgressBar
            ProgressBar loadProgress = new ProgressBar();
            loadProgress.setProgress(-1.0f); // Indeterminate progress
            splashLayout.getChildren().add(loadProgress);

            // Show the splash screen
            Scene splashScene = new Scene(splashLayout);
            splashScreen.setScene(splashScene);
            splashScreen.show();

            // Use a PauseTransition to delay the main game window
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished(event -> {
                splashScreen.close(); // Close the splash screen

              
            });
        delay.play();
            
         // Load the image
            Image topImage = new Image("C:\\CST8221\\Assig22\\src\\application\\game_about.jpg");
            

            // Create the ImageView
            ImageView topImageView = new ImageView(topImage);
            topImageView.setFitWidth(800);
            topImageView.setFitHeight(200);

            // Add the ImageView to the top of the BorderPane
            root.setTop(topImageView);
         // Create a VBox
            VBox topBox = new VBox();

            // Add the MenuBar and the ImageView to the VBox
            topBox.getChildren().addAll(middlePanel.getMenuBar(), topImageView);

            // Set the VBox as the top node of the BorderPane
            root.setTop(topBox);

        System.out.println(root);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * The resetGame method resets the game state by reinitializing game components
     * and clearing both player and computer game boards.
     */
     void resetGame() {
        if (root != null) {
            // remove old game components
            root.getChildren().removeAll(playerBoard.getGridPane(), computerBoard.getGridPane());

            // re-initialize game components
            playerBoard = new GameBoard(boardSize,"player");
            computerBoard = new GameBoard(boardSize,"AI");
            playerProgressBar.setProgress(0);
            computerProgressBar.setProgress(0);
            if (playerBoard.getGridPane() != null && playerProgressBar != null && computerBoard.getGridPane() != null && computerProgressBar != null) {
                root.setLeft(new VBox(playerBoard.getGridPane(), playerProgressBar));
                root.setRight(new VBox(computerBoard.getGridPane(), computerProgressBar));
            } else {
                System.out.println("One or more elements are null, cannot reset game.");
            }
        } else {
            System.out.println("Root is null, cannot reset game.");
        }
        playerBoard.clearBoard();
        computerBoard.clearBoard();
    }


     /**
      * The main method is the entry point for all Java applications.
      *
      * @param args the command-line arguments.
      */
    public static void main(String[] args) {
        launch(args);
    }
}

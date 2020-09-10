import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;
import java.io.*;
import javafx.scene.media.*;
///////////////////////////////////////////////////////////////////////////////
//                
// Title:            (GuiTetris.java)
// Semester:         (CS8B) Fall 2018
//
// Author:           (Matthew Roth)
// Email:            (mrroth@ucsd.edu)
// CS Login:         (cs8bfds)
// Lecturer's Name:  (Prof. Paul Cao; TA's - Grace Chen, Alberto, Cheng, Emily,
//                     Godwin, Henry, Hensen, Hilda, Lavanya, Nishil, Sneha)
// 
// Class Desc:        The GuiTetris is a graphical user interface that will 
//                    interface with the Tetris class and the piece class
//                    to simulate a game on screen. 
//                    
///////////////////////////////////////////////////////////////////////////////

/**
 * The GuiTetris is a graphical user interface that will 
 * interface with the Tetris class and the piece class
 * to simulate a game on screen. 
 **/
public class GuiTetris extends Application {

  private static final int PADDING = 10;
  private static final int TILE_GAP = 2;
  private Tetris tetris;
  private GridPane pane;
  private MyKeyHandler myKeyHandler;
  //create an array of rectangles for the grid 
  private  Rectangle[][] grid = new Rectangle[24][10];
  //create an array of rectangles for the piece 
  private Rectangle[][] piece;
  //create a label for the title of game
  private Text title;
  //create a label for the cleared lines
  private Text lines;
  //deep cop char array for grid
  char[][] temp_grid;

  //int for magic numbers. most of these numbers have no
  //explanation except to provide input for values, and 
  //we will call them "MAGIC" numbers by default 
  private final int MAGIC_NUM20 = 20;
  private final int GRID_HEIGHT = 24;
  private final int GRID_WIDE = 10;
  private final int MAGIC_NUM9 = 9;
  private final int MAGIC_NUM4 = 4;
  private final int MAGIC_NUM2 = 2;
  private final int MAGIC_NUM6 = 6;
  private final int SQUARE = 25;

  @Override
  public void start(Stage primaryStage) {
    this.tetris = new Tetris();

    // Comment out if needed
    //startMusic();

    //create the gridpane that will be a container for the gui elements
    pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(PADDING,PADDING,PADDING,PADDING));
    pane.setStyle("-fx-background-color: rgb(255,255,255)");
    pane.setHgap(TILE_GAP); 
    pane.setVgap(TILE_GAP);

    Button button = new Button("Restart");

    //set the title to Tetris
    title = new Text("Tetris");
    title.setFont(Font.font ("Verdana", MAGIC_NUM20));
    title.setFill(Color.BLUE);
    //set the lines cleared to zero 
    lines = new Text("" + tetris.linesCleared);
    lines.setFont(Font.font ("Verdana", MAGIC_NUM20));
    lines.setFill(Color.GREEN);
    //add the clearlines int value to the pane
    pane.add(lines,MAGIC_NUM9,0,MAGIC_NUM2,MAGIC_NUM4);
    //add the title of the game 
    pane.add(title,MAGIC_NUM2,0,MAGIC_NUM6,MAGIC_NUM4); 

    //deep copy the grid
    temp_grid = new char[tetris.grid.length][tetris.grid[0].length];
    for (int row=0; row<tetris.grid.length; row++)
      for (int col=0; col<tetris.grid[0].length; col++)
        temp_grid[row][col] = tetris.grid[row][col];

    //put the active piece in the temp grid
    for (int row=0; row<tetris.activePiece.tiles.length; row++)
      for (int col=0; col<tetris.activePiece.tiles[0].length; col++)
        if (tetris.activePiece.tiles[row][col] == 1)
          temp_grid[row+tetris.activePiece.rowOffset]
            [col+tetris.activePiece.colOffset] = 
            tetris.activePiece.shape;


    //create the initial empty grid
    for(int i = 0; i<GRID_HEIGHT; i++){
      for(int j = 0; j<GRID_WIDE; j++){
        if(i < MAGIC_NUM4){
          grid[i][j] = new Rectangle(SQUARE,SQUARE,Color.WHITE);
        }else
          grid[i][j] = new Rectangle(SQUARE,SQUARE, Color.GRAY);
        int newRow = i + MAGIC_NUM6;
        pane.add(grid[i][j], j, newRow);

      }
    }

    //add the active piece to the grid pane 
    for(int k = 0; k < tetris.nextPiece.tiles.length; k++){
      for(int l = 0; l < tetris.nextPiece.tiles.length;l++){
        if (tetris.nextPiece.tiles[k][l] == 0)
          grid[k][l].setFill(Color.WHITE);
        else{
          grid[k][l+MAGIC_NUM6].setFill(getPieceColor(tetris.nextPiece));     
        }
      }
    }   
    //update the grid with the new colored squares 
    for(int i = 0; i<MAGIC_NUM20; i++){
      for(int j = 0; j<GRID_WIDE; j++){
        grid[i+MAGIC_NUM4][j].setFill(getPieceColor(temp_grid[i][j]));
      }
    }

    //create the scene to hold the gridpane 
    Scene scene = new Scene(pane);
    primaryStage.setTitle("Tetris");
    primaryStage.setScene(scene);
    primaryStage.show();

    //create a KeyHandler to implement the handler to 
    //take input from the keyboard
    myKeyHandler = new MyKeyHandler();
    scene.setOnKeyPressed(myKeyHandler);
    MoveDownWorker worker = new MoveDownWorker();
    worker.start();

  }

  /**
   * private helper method to return a
   * color for each shape. 
   * @param piece the shape 
   * @return the Color
   * */
  private Color getPieceColor(Piece piece){
    if(piece.shape == 'I')
      return Color.BLUE;
    else if(piece.shape == 'J')
      return Color.RED;
    else if(piece.shape == 'L')
      return Color.GREEN;
    else if(piece.shape == 'O')
      return Color.BROWN;
    else if(piece.shape == 'S')
      return Color.ORANGE;
    else if(piece.shape == 'Z')
      return Color.PURPLE;
    else if(piece.shape == 'T')
      return Color.YELLOW;
    else
      return Color.WHITE;
  }

  /**
   * private helper method to return a
   * color for each shape. 
   * @param char the shape 
   * @return the Color
   * */
  private Color getPieceColor(char shape){
    if(shape == 'I')
      return Color.BLUE;
    else if(shape == 'J')
      return Color.RED;
    else if(shape == 'L')
      return Color.GREEN;
    else if(shape == 'O')
      return Color.BROWN;
    else if(shape == 'S')
      return Color.ORANGE;
    else if(shape == 'Z')
      return Color.PURPLE;
    else if(shape == 'T')
      return Color.YELLOW;
    else
      return Color.GRAY;
  }

  /**
   * private helper method to update 
   * the grid with the movement of the
   * activepiece and the consolidated
   * pieces
   * */
  private void updateGame(){
    /*deep copy the grid*/
    temp_grid = new char[tetris.grid.length][tetris.grid[0].length];
    for (int row=0; row<tetris.grid.length; row++)
      for (int col=0; col<tetris.grid[0].length; col++)
        temp_grid[row][col] = tetris.grid[row][col];

    /*put the active piece in the temp grid*/
    for (int row=0; row<tetris.activePiece.tiles.length; row++)
      for (int col=0; col<tetris.activePiece.tiles[0].length; col++)
        if (tetris.activePiece.tiles[row][col] == 1)
          temp_grid[row+tetris.activePiece.rowOffset]
            [col+tetris.activePiece.colOffset] = 
            tetris.activePiece.shape;

    //set the active piece location to a cleared whitespace before 
    //adding the new nextpiece 
    for(int k = 0; k < MAGIC_NUM4; k++){
      for(int l = 0; l <GRID_WIDE ;l++){
        grid[k][l].setFill(Color.WHITE);
      }
    }


    //set the active piece in the grid
    for(int k = 0; k < tetris.nextPiece.tiles.length; k++){
      for(int l = 0; l < tetris.nextPiece.tiles.length;l++){
        grid[k][l+MAGIC_NUM6].setFill(Color.WHITE);

        if (tetris.nextPiece.tiles[k][l] == 0)
          grid[k][l].setFill(Color.WHITE);
        else{
          grid[k][l+MAGIC_NUM6].setFill(getPieceColor(tetris.nextPiece));     
        }
      }
    }   
    //update the grid with new colored squares 
    for(int i = 0; i<MAGIC_NUM20; i++){
      for(int j = 0; j<GRID_WIDE; j++){
        grid[i+MAGIC_NUM4][j].setFill(getPieceColor(temp_grid[i][j]));
      }
    }
  }

  /**
   * private inner class to implement the 
   * Event Handler for Key Strokes
   * */
  private class MyKeyHandler implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent e) {

      //use case switch to determine the user input on the keyboard
      //and execute the correct implementation 
      switch( e.getCode() ){
        case UP:
          tetris.rotate();
          break;
        case DOWN:
          tetris.move(Direction.DOWN);
          break;
        case LEFT:
          tetris.move(Direction.LEFT);
          break;
        case RIGHT:
          tetris.move(Direction.RIGHT);
          break;
        case SPACE:
          tetris.drop();
          break;
        case O:
          try{
            tetris.outputToFile();
          }catch (IOException a) {
            System.err.println("SOS please someone help me");
          }
          break;
        default:
          System.out.println("Invalid Input");
          break;

      }

      //update the clear lines 
      lines.setText("" + tetris.linesCleared);
      //update the grid and continue if not game over
      if(!tetris.isGameover)
        updateGame();  
      //if gae over then set the title to game over   
      if(tetris.isGameover)
        title.setText("Game Over!");          
    }
  }

  /* ---------------- DO NOT EDIT BELOW THIS LINE ---------------- */

  /**
   * private class GuiTetris.MoveDownWorker
   * a thread that simulates a downwards keypress every some interval
   * @author Junshen (Kevin) Chen
   */
  private class MoveDownWorker extends Thread{

    private static final int DROP_INTERVAL = 500; // millisecond
    private int move_down_timer = DROP_INTERVAL; 

    /**
     * method run
     * called when the thread begins, decrements the timer every iteration
     * of a loop, reset the timer and sends a keydown when timer hits 0
     */
    @Override
    public void run(){

      // run forever until returned
      while (true) {
        // stop the thread if the game is over
        if (tetris.isGameover) return; 

        // wait 1ms per iteration
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          break;
        }

        move_down_timer -= 1;
        if (move_down_timer <= 0 ) {

          // simulate one keydown by calling the 
          // handler.handle()
          myKeyHandler.handle(
              new KeyEvent(null, "", "", KeyCode.DOWN, 
                false, false, false, false)
              );

          move_down_timer = DROP_INTERVAL;
        }
      }
    }
  } // end of private class MoveDownWorker

  /**
   * Cheng Shen Nov. 11th 2018
   * This method plays the background music
   */
  private void startMusic(){
    try{
      System.out.println("Playing Music~~~");
      File mp3 = new File("./Tetris.mp3");
      Media bgm = new Media(mp3.toURI().toString());
      MediaPlayer bgmPlayer = new MediaPlayer(bgm);
      bgmPlayer.setCycleCount(bgmPlayer.INDEFINITE);
      bgmPlayer.play();
    }catch (Exception e){
      System.out.println("Exception playing music");
    }
  }

}

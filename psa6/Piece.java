///////////////////////////////////////////////////////////////////////////////
//                
// Title:            (Piece.java)
// Files:            (...)
// Semester:         (CS8B) Fall 2018
//
// Author:           (Matthew Roth)
// Email:            (mrroth@ucsd.edu)
// CS Login:         (cs8bfds)
// Lecturer's Name:  (Prof. Paul Cao; TA's - Grace Chen, Alberto, Cheng, Emily,
//                     Godwin, Henry, Hensen, Hilda, Lavanya, Nishil, Sneha)
// 
// Class Desc:        The Piece class will interact directly with the 
//                    Tetris.java class and the Direction.java class in
//                    order to perform operations and methods to initiate
//                    and play the game Tetris. The class Piece will have three
//                    different constructors to initialize data in the instance
//                    variables and arrays to create a 'Piece' object for the 
//                    tetris game. The methods rotate(), rotateClockwise(), and
//                    rotateCounterClockwise() will all perform a rotation
//                    on the piece in question. movePiece() method will 
//                    increment Column and Row holding variables that will
//                    shift the piece in the game board or the grid. 
//
//
///////////////////////////////////////////////////////////////////////////////

import java.util.*;

/**
 *
 * The Piece class will interact with the Tetris class, in a game of tetris
 * that will generate a Piece object, initialized with a shape, location, and 
 * game status.
 * @author Matthew Roth 
 * */

public class Piece {

  /**
   * the demo method for the piece class will test a new object 
   * created for Piece with initialized constructor, and default constructor
   *
   * */
  public static void main(String[] args){
    //create a Piece object with no param, using default constructor,generate 
    //random piece
    Piece piece1 = new Piece();
    //print the tiles array to show that the piece was properly initialized
    System.out.println(Arrays.deepToString(piece1.tiles)
        .replace("], ", "]\n"));
    //print the shape of the piece object 
    System.out.println(piece1.shape);
    //print the row offset of this piece
    System.out.println(piece1.rowOffset);
    //print the column offset of thi piece
    System.out.println(piece1.colOffset);
    //rotate the piece 
    piece1.rotate();
    //print the new roatate piece tile array 
    System.out.println(Arrays.deepToString(piece1.tiles)
        .replace("], ", "]\n"));

    //create a Piece object initialzed with a char 'O'
    Piece piece2 = new Piece('I');
    //print the tiles array to show that the piece was properly initialized
    System.out.println(Arrays.deepToString(piece2.tiles)
        .replace("], ", "]\n"));
    //print the shape of the piece object 
    System.out.println(piece2.shape);
    //print the row offset of this piece
    System.out.println(piece2.rowOffset);
    //print the column offset of this piece
    System.out.println(piece2.colOffset);
    //rotate the piece 
    piece2.rotate();
    //print the new roatate piece tile array 
    System.out.println(Arrays.deepToString(piece2.tiles)
        .replace("], ", "]\n"));
    // create a Piece object initialized with another Piece object to copy 
    //create a Piece object with no param, using default constructor,generate 
    //random piece
    Piece piece3 = new Piece(piece2);
    //print the tiles array to show that the piece was properly initialized
    System.out.println(Arrays.deepToString(piece3.tiles)
        .replace("], ", "]\n"));
    //print the shape of the piece object 
    System.out.println(piece3.shape);
    //print the row offset of this piece
    System.out.println(piece3.rowOffset);
    //print the column offset of thi piece
    System.out.println(piece3.colOffset);
    //rotate the piece 
    piece3.rotate();
    //print the new roatate piece tile array 
    System.out.println(Arrays.deepToString(piece3.tiles)
        .replace("], ", "]\n"));

    piece3.rotate();
    System.out.println(Arrays.deepToString(piece3.tiles)
        .replace("], ", "]\n"));



  }


  // all possible char representation of a piece
  public static final char[] possibleShapes = 
  {'O', 'I', 'S', 'Z', 'J', 'L', 'T'}; 

  // initial state of all possible shapes, notice that this array's 
  // content shares index with the possibleShapes array 
  public static final int[][][] initialTiles = {
    {{1,1},
      {1,1}}, // O

    {{0,0,0,0},
      {1,1,1,1},
      {0,0,0,0},
      {0,0,0,0}}, // I

    {{0,0,0},
      {0,1,1},
      {1,1,0}}, // S

    {{0,0,0},
      {1,1,0},
      {0,1,1}}, // Z

    {{0,0,0},
      {1,1,1},
      {0,0,1}}, // J

    {{0,0,0},
      {1,1,1},
      {1,0,0}}, // L

    {{0,0,0},
      {1,1,1},
      {0,1,0}} // T
  };  

  // random object used to generate a random shape
  public static Random random = new Random(); 

  // char representation of shape of the piece, I, J, L, O, S, Z, T
  public char shape;

  // the position of the upper-left corner of the tiles array 
  // relative to the Tetris grid
  public int rowOffset;
  public int colOffset;

  // used to determine 2-state-rotations for shapes S, Z, I
  // set to true to indicate the next call to rotate() should
  // rotate clockwise
  public boolean rotateClockwiseNext = false;

  // an array marking where the visible tiles are
  // a 1 indicates there is a visible tile in that position
  // a 0 indicates there is no visible tile in that position
  public int[][] tiles;

  /**
   * the Piece constructor is a default constructor for Piece
   * object that will generate a random shape char, and initialize
   * all relevant data based on that randomly generated char
   **/
  public Piece(){
    //the initial location on the grid for col offset 
    final int SHAPE_O_START = 4;
    final int OTHER_SHAPE_START = 3;
    // generate new random shape char 
    int shapeIndex = random.nextInt(possibleShapes.length);
    shape = possibleShapes[shapeIndex];
    int length = initialTiles[shapeIndex].length;
    tiles = new int[length][length]; 
    //iterate through the array of initialTiles and set each tile to the
    //corespondant tile from the initialzed shape in the tiles array
    for(int i = 0; i<initialTiles[shapeIndex].length;i++)
      for(int j=0; j<initialTiles[shapeIndex].length;j++){
        tiles[i][j] = initialTiles[shapeIndex][i][j];  
      }
    //set the offset for row and column depending on each shape 
    if(shape == 'O'){
      rowOffset = 0;
      colOffset = SHAPE_O_START;
    }else{ 
      rowOffset = -1;
      colOffset = OTHER_SHAPE_START;
    }
  }
  /**
   * the Piece constructor will create an object for the Piece
   * class taking parameters for a char and initializing the shape
   * based on the char and set the tiles associated with the char 
   * in the argument 
   * */
  public Piece(char shape) {

    //set the shape char to the instance variable shape 
    this.shape = shape;
    //create an index holder value for indexing the correct element from the
    //possibleShapes and initialTiles array 
    int shapeIndex = 0;
    //find the corect index for the shape 
    for(int i = 0; i<possibleShapes.length; i++)
    {
      if(possibleShapes[i] == shape)
        shapeIndex = i;
    }
    //create the new tile array with the correct shape contained 
    //ne each element 
    int length = initialTiles[shapeIndex].length;
    tiles = new int[length][length]; 
    for(int i = 0; i<initialTiles[shapeIndex].length;i++)
      for(int j=0; j<initialTiles[shapeIndex].length;j++){
        tiles[i][j] = initialTiles[shapeIndex][i][j];  
      }
    //if the shape is "O" the offset is unique
    if(shape == 'O'){
      rowOffset = 0;
      colOffset = 4;
      //all other shapes will take the standard offset 
    }else{ 
      rowOffset = -1;
      colOffset = 3;
    }
  }
  /**
   * the Piece object constructor will take another Piece
   * object as a paramter, and copy that Piece object to make
   * a new copy of the Piece object.
   * @param other the piece to copy
   **/
  public Piece(Piece other){

    //set the instance variable shape, to the parameter shape
    //from the incoming object 
    shape = other.shape;
    int shapeIndex = 0;

    for(int i = 0; i<possibleShapes.length; i++)
    {
      //find the index corespondant	    
      if(possibleShapes[i] == shape)
        shapeIndex = i;
    }
    //set the tiles array to contain a tile from the inititalTiles
    //array and based on the shape implicated 
    int length = initialTiles[shapeIndex].length;
    tiles = new int[length][length]; 
    for(int i = 0; i<initialTiles[shapeIndex].length;i++)
      for(int j=0; j<initialTiles[shapeIndex].length;j++){
        tiles[i][j] = other.tiles[i][j];
        //tiles[i][j] = initialTiles[shapeIndex][i][j];  
      }
    //set the row and col offset to be updated from the copy Piece object
    rowOffset = other.rowOffset;
    colOffset = other.colOffset;
    //update the roatateClockwiseNext variable to hold the same value as the 
    //paramter piece object 
    this.rotateClockwiseNext = other.rotateClockwiseNext;

  }
  /**
   * the rotate method is a roation either counterclockwise
   * or clockwise based on the specific piece in question
   * */

  public void rotate(){
    //determine what the char shape is and rotate if its clockwise if its 
    //one of the O,T,L,J shapes 
    if(shape == 'O' || shape == 'T' || shape == 'L' || shape == 'J'){
      rotateClockwise();
      //all other shapes roatate counterclockwise first 
    }else{
      if(!rotateClockwiseNext){
        rotateCounterClockwise();
        rotateClockwiseNext = true;
      }else{
        rotateClockwise();
        rotateClockwiseNext = false; 
      }
    }
  }
  /**
   * the rotateClockwise method will 
   * rotate all the array elements in a clockwise
   * like direction, changing the overall orientation
   * of the tile on the game grid
   *
   **/

  public void rotateClockwise() {
    final int HALF_LENGTH = 2;	  
    //make sure ther is no null or empty references 
    if(tiles == null)
      return;

    if(tiles.length != tiles[0].length)
      return;

    //PART 1: rotate along the diagonal of the matrix
    //by swaping each element diagonally
    int length = tiles.length;
    for(int i = 0; i < length; i++){
      for(int j = 0; j < length-1-i; j++){
        int temp = tiles[i][j];
        tiles[i][j] = tiles[length-1-j][length-1-i];
        tiles[length-1-j][length-1-i] = temp;
      }
    }
    //PART II: the similar method below for CounterClockwise
    //complete rotation by reflecting the matrix about the middle row
    int length2 = tiles.length;
    for(int i = 0; i < length2/HALF_LENGTH; i++){
      for(int j = 0;j < length2; j++){
        int temp = tiles[i][j];
        tiles[i][j] = tiles[length2-1-i][j];
        tiles[length2-1-i][j] = temp;
      }
    }
  }
  /**
   * the rotateCounterClockwise method will 
   * rotate all the array elements in a counter clockwise
   * like direction, changing the overall orientation
   * of the tile on the game grid
   *
   **/
  public void rotateCounterClockwise () {
    //half the length of tiles array 	  
    final int HALF_LENGTH = 2;	   
    //if array is null return 
    if(tiles == null)
      return;
    // return if length of array mismatch error 
    if(tiles.length != tiles[0].length)
      return;

    // PART I: iterate through the array and swap elements
    //accordingly until the correct orientation has been 
    //acheived (transpose the matrix grid)
    for(int i = 0; i < tiles.length; i++){
      for(int j = i+1; j < tiles.length; j++){
        int temp = tiles[i][j];
        tiles[i][j] = tiles[j][i];
        tiles[j][i] = temp;
      }
    }
    //PART II: 
    //reflect the matrix about the middle row to complete rotation fullrotation
    int length2 = tiles.length;
    for(int i = 0; i < length2/HALF_LENGTH; i++){
      for(int j = 0;j < length2; j++){
        int temp = tiles[i][j];
        tiles[i][j] = tiles[length2-1-i][j];
        tiles[length2-1-i][j] = temp;
      }
    }
  }

  /**
   * the method movePiece will take a Direction object as
   * an argument, and shift the tile piece on the game grid
   * according to the input in direction paramter
   * @param direction the direction peice will move on grid
   * */
  public void movePiece(Direction direction) {
    if(direction == Direction.DOWN)
      rowOffset++;
    else if(direction == Direction.LEFT)
      colOffset--;
    else if(direction == Direction.RIGHT)
      colOffset++; 
  }


}

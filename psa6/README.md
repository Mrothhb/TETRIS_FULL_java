#Extra Credit 0

#Program Description
The game of Tetris is a fun game that people of all ages can enjoy. It is descr
ibed as having a piece in the shape of a character initiated at the top of a 
game
board, and the piece will continualy fall towards the bottom of the board until
it reaches the bottom, or comes into contact with another piece that has 
already
reached the bottom as well.  An enitre row of pieces can be cleared once a row 
is filled with pieces. This is acheived by mathching the pieces on the board 
correctly to leave no empty space between them like a puzzle. You can rotate 
pieces around by pressing 'w', and move them side to side and down with 'd','a'
's', but you cannot move upwards once a piece has started falling. If you want 
to hold a peice for later use, press 'z' but this can only be done once per rou
nd. To save the current game, press 'o' and the current game state will be 
saved to file. TO quit the game immediately press 'q'. 
The idea of the game is to fit pieces correctly so that lines can be cleared 
from the bottom of the board, and pieces dont build up to the top of the board.
Once the entire board has been filled, or no new pieces can be generated 
becuase there is an old piece sitting underneath it, then the game is over.

#Short Repsonse
Unix/Linux:
1. mkdir -p forDir/barDir
2. rm *.java
3. ls -lr

JavaFx
1. Yes. the Gridpane is a container that will allow for a nested gridpane 
2. This inner class allows for the Gui to interface with KeyEvents directly 
from within the class.
3. //Lambda expressions, shorter Anon inner Class
	foo.setOnAction(e-> {
		System.out.println(" text ");
		} );
4. the scene is a container for the gridpane 

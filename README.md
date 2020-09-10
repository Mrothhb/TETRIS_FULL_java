#Program Description

The game of Tetris is a fun game that people of all ages can enjoy. It is described as having a piece in the shape of a character initiated at the top of a game
board, and the piece will continualy fall towards the bottom of the board until it reaches the bottom, or comes into contact with another piece that has already reached the bottom as well.  An enitre row of pieces can be cleared once a row is filled with pieces. This is acheived by mathching the pieces on the board correctly to leave no empty space between them like a puzzle. You can rotate pieces around by pressing 'w', and move them side to side and down with 'd','a', 's', but you cannot move upwards once a piece has started falling. If you want to hold a peice for later use, press 'z' but this can only be done once per round. To save the current game, press 'o' and the current game state will be saved to file. TO quit the game immediately press 'q'. 
The idea of the game is to fit pieces correctly so that lines can be cleared from the bottom of the board, and pieces dont build up to the top of the board. Once the entire board has been filled, or no new pieces can be generated becuase there is an old piece sitting underneath it, then the game is over.
#Testin 

1. testing was performed in a slightly more unconventional way for this class since the program is based on an active play game. The most effective tests were done by attempting to make moves in the game play that would caus different types of errors. OutofBounds Exception errors would be one example on the game board grid, or trying to move up with a piece, or trying to rotate a piece when it was at the top of a game board to make it go out of bounds etc. these are just some of the examples of the testing cases used. 
2. The best way to know of the tests are actually providing accurate feedback is to implement them in realtime during the game play, and to also have various pintline methods throughout the code sequence to make sure are the correct variables and method calls are being stored and executed correctly. 
3. this was described above already. but to reiterate, the in game play was an excellent way to determine if the game was operating correctly. also having printline methods to display what is being stored in variables and what methods are being called when, is helpful. 

#VIM/Linux
VIM:
1. type == line#G ex.  " 42G" in command mode or 42gg  
2. to undo press "u" in command mode 
3. type the "/" or "?" followed by the word in command mode
4. in the terminal type vim folowed by the name of all the files with spaces in between each file name 
5. :n

LINUX
1. find . -type f -path "./src/*/*" -name "*.class" -exec rm -f {} \;
2. cp -r /thirdup/secondup/firstup/current .
3. shows the manual standard input 
4. grep pattern files 
5. tail file

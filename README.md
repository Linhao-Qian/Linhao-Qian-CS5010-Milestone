# Linhao-Qian-CS5010-Milestone

# 
JAR File: ./res/Milestone.jar

How to use: java -jar Milestone.jar ./Leo's_world.txt 4 view

Tips:
- The first argument is the input file location.
- The second argument is the maximum number of turns allowed. You can set the second argument larger if you want to play a longer game.
- The third argument is the game mode. If you input "view", the game will be view-based. If you input "text", the game will be text-based.

# 
How to play a text-based game:

After you enter the game through my jar file, you will see the world's information on the top, including the world's name, size, and the information of spaces and items. In the last few lines, the controller will indicate that you can input 4 different commands: displaySpaceInformation, addComputerPlayer, addHumanPlayer and generateMap.

You can check a specified space by input displaySpaceInformation. The controller will instruct you to enter the space name, and the space information will be displayed to you after that.

You can generate a world map by input generateMap. The controller will generate a PNG file in the current directory. The PNG file's name depends on the name of the world, i.e., Leo's World.png.

You can add a computer-controlled player by input addComputerPlayer. The controller will instruct you to enter the player name and the initial space name. After the player is successfully created, the controller will provide clues about that.

You can add a human-controlled player by input addHumanPlayer. The controller will instruct you to enter the player name and the initial space name. After the player is successfully created, the controller will provide clues about that.

After you add the first player, there will be two more commands for you to choose: displayPlayerInformation, nextTurn

You can check a specified player by input displayPlayerInformation. The controller will instruct you to enter the player name, and the player information will be displayed to you after that.

You can make the game enter the next turn by input nextTurn. After you input that, the game will enter the player insight. Some hint information will be displayed to the current player and there will be five command options for the current player to choose: movePlayer, pickUpItem, lookAround, movePet, makeAnAttempt. If the current player is controlled by human, you need to input the command and corresponding arguments manually, otherwise the computer will execute one of the five commands automatically. After execution of any of the five commands, the next player in the player list will take the turn, the target character will move to his next destination automatically, and the pet will move to its next space according to the depth-first traversal algorithm.

When movePlayer is inputed, the controller will instruct you to enter the name of the space where you want the current player to move. After the player is successfully moved to the specified space, the controller will provide clues about that.

When pickUpItem is inputed, the controller will instruct you to enter the name of the item which you want the current player to carry. After the player successfully carries the item, the controller will provide clues about that.

When lookAround is inputed, the controller will display the information of the space where the current player stays, including:
- Where the current player were (the name of the space), who was in the room with the current player (the other players, the pet and the target character), what items were laying around the space, as well as information about neighboring spaces
- When it comes to the neighboring spaces, the current player can identify what space it was as well as what characters and items were in the neighboring space. However, any space that is occupied by the pet cannot be seen by its neighbors.

When movePet is inputed, the controller will instruct you to enter the name of the space where you want the pet be moved to. After the pet is successfully moved to the specified space, the controller will provide clues about that.

When makeAnAttempt is inputed, the controller will instruct you to enter the name of the item which you want the current player to use. If the current player does not have any items, please input "pokeEyes" which does 1 point of damage to the target character. The controller will provide clues about whether the attack is successful. Note that if an attack is seen by another player (human or computer), it is automatically stopped and no damage is done.

Each time one of the above five commands is executed successfully, there may be three different prompts:
- If a player successfully kills the target character, they win the game.
- If The maximum number of turns is reached, the target character escapes and runs away to live another day and nobody wins.
- Otherwise, the controller will exit the player insight, and ask you to input the initial 6 commands again: displaySpaceInformation, addComputerPlayer, addHumanPlayer, generateMap, displayPlayerInformation and nextTurn.

Please enjoy your game in Leo's world! (You can check my world map in ./res/Leo's World.png)

# 
How to play a view-based game:

After you enter the game through my jar file, you will see an about screen. Press the "Enter Game" button, then choose an option from the game menu.

If you choose a new world, a File Chooser will pop up. Please choose the world specification file which you want to play. Or if you choose the current world, you will enter the game interface directly.

After you enter the game interface, Please add players firstly. If there is no players in the game, you cannot start it.

After you add at least one player, you can start the game. Please remember following the game's instructions while playing.

Wish you a pleasant game experience!

# 
Game assumptions:
- The view part consists of many JPanel components.
- Only after at least one player has been successfully created can you start the game.
- You can play the game either in text mode or in view mode.

# 
Game limitations:
- The UI of the game is rough and primitive, not beautiful enough.
- In text mode, some prompt information lacks appropriate separators, which is not convenient for players to read game text.

# 
Game development citations:
- https://www.javatpoint.com/java-jpanel

# 
Preliminary design changes comparing to Milestone 3:
- Add a getSpace method to the model.
- Add the View part to the game.
- Add the view-related constructor and methods to the controller.
- Add a MockView class to the design, which makes it possible to test controller isolating from the view.

To get more details, please check my Preliminary Design PDF file in the res directory.

# 
Text-based example run 1: Display target character escape

Input file: ./res/Leo's_world.txt

Input commands: ./res/example_run_target_character_escape.txt (line 54 - 76)

Output file: ./res/example_run_target_character_escape.txt (line 80 - 621)

Please use these commands to validate these functionalities of my project:
- the target character's pet effect on the visibility of a space from neighboring spaces
- the player moving the target character's pet
- the pet wandering according to the depth-first traversal algorithm
- the target character escaping with his life and the game ending

# 
Text-based example run 2: Display computer player win

Input file: ./res/Leo's_world.txt

Input commands: ./res/example_run_computer_player_win.txt (line 54 - 57)

Output file: ./res/example_run_computer_player_win.txt (line 61 - 279)

Please use these commands to validate these functionalities of my project:
- a computer-controlled player making an attempt on the target character's life
- a computer-controlled player winning the game by killing the target character

# 
Text-based example run 3: Display human player win

Input file: ./res/Leo's_world.txt

Input commands: ./res/example_run_human_player_win.txt (line 54 - 59)

Output file: ./res/example_run_human_player_win.txt (line 63 - 291)

Please use these commands to validate these functionalities of my project:
- a human-player making an attempt on the target character's life
- a human-player winning the game by killing the target character

# 
View-based game demonstration:

I will show you how to play the view-based game during the code walk.
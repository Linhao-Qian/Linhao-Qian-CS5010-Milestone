# Linhao-Qian-CS5010-Milestone

Part2: Synchronous Controller
# 
JAR File: ./res/Milestone2.jar

How to use: java -jar Milestone2.jar ./Leo's_world.txt 4

Tips:
The first argument is the input file location, the second argument is the maximum number of turns allowed.
You can set the second argument larger if you want to play a longer game.
# 
How to play:

After you enter the game through my jar file, you will see the world's information on the top, including the world's name, size, and the information of spaces and items. In the last few lines, the controller will indicate that you can input 4 different commands: displaySpaceInformation, addComputerPlayer, addHumanPlayer and generateMap.

You can check a specified space by input displaySpaceInformation. The controller will instruct you to enter the space name, and the space information will be displayed to you after that.

You can generate a world map by input generateMap. The controller will generate a PNG file in the current directory. The PNG file's name depends on the name of the world, i.e., Leo's World.png.

You can add a computer-controlled player by input addComputerPlayer. The controller will instruct you to enter the player name and the initial space name. After the player is successfully created, the controller will provide clues about that.

You can add a human-controlled player by input addHumanPlayer. The controller will instruct you to enter the player name and the initial space name. After the player is successfully created, the controller will provide clues about that.

After you add the first player, there will be two more commands for you to choose: displayPlayerInformation, nextTurn

You can check a specified player by input displayPlayerInformation. The controller will instruct you to enter the player name, and the player information will be displayed to you after that.

You can make the game enter the next turn by input nextTurn. After you input that, the game will enter the player insight and there will be three command options for the current player to choose: movePlayer, pickUpItem, lookAround. If the current player is controlled by human, you need to input the command and corresponding arguments manually, otherwise the computer will execute one of the three commands automatically. After execution of any of the three commands, the next player in the player list will take the turn, and the target character will move to his next destination automatically.

When movePlayer is inputed, the controller will instruct you to enter the name of the space where you want the current player to move. After the player is successfully moved to the specified space, the controller will provide clues about that.

When pickUpItem is inputed, the controller will instruct you to enter the name of the item which you want the current player to carry. After the player successfully carries the item, the controller will provide clues about that.

When lookAround is inputed, the controller will display the information of the space where the current player stays, including the neighboring information.

Each time one of the above three commands is executed successfully, there may be two different prompts. If the number of turns reaches the maximum number of turns allowed, the game will end. Otherwise, the controller will exit the player insight, and ask you to input the initial 6 commands again: displaySpaceInformation, addComputerPlayer, addHumanPlayer, generateMap, displayPlayerInformation and nextTurn.

Please enjoy your game in Leo's world!
# 
Example run: Display functionalities of my project

Input file: ./res/Leo's_world.txt

Output file: ./res/example_run.txt

World map: ./res/Leo's World.png
# 
The commands I input in my example run, in order, are:

addComputerPlayer\n

Leon\n

Dining Hall\n

addHumanPlayer\n

Leo\n

Billiard Room\n

nextTurn\n

nextTurn\n

movePlayer\n

Armory\n

nextTurn\n

displayPlayerInformation\n

Leo\n

displaySpaceInformation\n

Dining Hall\n

generateMap\n

nextTurn\n

pickUpItem\n

Tight Hat\n

Please use these commands to validate the functionalities of my project. Thank you!
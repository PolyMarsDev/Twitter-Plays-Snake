# Twitter Plays Snake
 
Twitter Plays Snake is a Twitter bot written with [Twitter4J](https://github.com/Twitter4J/Twitter4J) that allows users to play a collaborative game of [Snake](https://en.wikipedia.org/wiki/Snake_(video_game_genre)) using likes and retweets.
## Screenshots
![](https://media.discordapp.net/attachments/729726327544086679/793944106086301726/ezgif.com-gif-maker_3.gif)

## Features
#### Special modes
Special snake modes change up the gameplay and have a 25% chance of activating after each move.
-  ``😈🟪🟪🟣`` Devil snake moves in the opposite direction of the chosen move. 
-  ``🤢🟩🟩🟢`` Glutted snake dies when eating an apple.
-  ``😡🟥🟥🔴`` Charged snake continues to move until reaching an obstacle.

Special fruit change up the gameplay and can spawn after earning a point.
- ``🍏🍏`` Green apples spawn in doubles, teleporting the snake to its pair when collected.

#### Serialization
Games are loaded and saved between program runs if ``SAVEPATH`` is specified in ``Bot.java``.
#### Debug mode
Setting ``DEBUG`` to ``true`` in ``Bot.java`` allows you to play the game solo with console commands for debugging purposes.

## Usage
### Public host
Twitter Plays Snake can be found [here](https://twitter.com/snakegamebot).
### Self-hosting
#### Note: Feel free to use the code for educational reference, but do not rehost it in its entirety.
Replace the constants in ``Bot.java`` with your bot's username, save directory, and respective keys/tokens from the [Twitter Developer Portal](https://developer.twitter.com/en/dashboard). Ensure your IDE has Maven support (ex: [IntelliJ IDEA](https://www.jetbrains.com/idea/)), and execute ``mvn clean install`` in the project directory.


## Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

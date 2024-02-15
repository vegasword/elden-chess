/*
    TODO(a.perche):
    -> Reask failed player actions
    -> Better output
    -> Moar debug
 */

package org.codingfactory;

import java.util.*;

public class Game {
    private static Random random = new Random();
    public static SafeInput input = new SafeInput();
    private static Scoreboard scoreboard = new Scoreboard();

    private boolean gameboard[][];
    private int currentPlayer, playersCount, dimX, dimY;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Player> loosers = new ArrayList<Player>();

    /**
     * Display the game's main menu
     * @return Returns the choice
     */
    public int displayMainMenu() {
        input.clearScreen();
        return input.nextIntRange(
            "# DESTRUCT CHESS #\n" +
            " 1: Play\n" +
            " 2: Rules\n" +
            " 3: Scoreboards\n" +
            " 4: Quit\n",
            1, 4
        );
    }

    /**
     * Display the scoreboard.
     */
    public void displayScoreboard() {
        if (scoreboard.isEmpty()) {
           System.out.println("There are not yet scores in the scoreboard !");
           input.pause();
           return;
        }

        input.clearScreen();
        int choice = input.nextIntRange(
            "# SCOREBOARD #\n" +
            " 1: Top 10\n" +
            " 2: Top 10 best\n" +
            " 3: Top 10 worst\n" +
            " 4: Back to main menu\n",
            1, 4
        );
        switch (choice) {
            case 1: scoreboard.displayScores();
            case 2: scoreboard.displayScoresAsc();
            case 3: scoreboard.displayScoresDesc();
            case 4: return;
        }
    }

    /**
     * Initialize the game.
     */
    public void init() {
        playersCount = input.nextIntRange("How many players are you ? ", 2, 4);

        int minDim = playersCount * 3, maxDim = playersCount * 10;
        dimX = input.nextIntRange(
            "Please enter the grid width (from "+ minDim + " to " + maxDim + ") : ",
            minDim, maxDim
        );
        dimY = input.nextIntRange(
            "Please enter the grid height (from "+ minDim + " to " + maxDim + ") : ",
            minDim, maxDim
        );
        gameboard = new boolean[dimY][dimX];

        for (int i = 0; i < playersCount; i++) {
            String pseudo = input.nextString("Please enter player n°" + (i+1) + " pseudo : ");
            int x = dimX / 2;
            int y = (dimY / 2) - (playersCount / 2) + i;
            players.add(new Player(pseudo, x, y));
            gameboard[y][x] = true;
        }

        this.currentPlayer = random.nextInt(playersCount);
    }

    /**
     * Display the game's rules.
     */
    public void displayRules() {
        System.out.println("" +
            "In this game, players take turns moving their pawn one square \n" +
            "either vertically or horizontally and then destroy a square on \n" +
            "the board. The last player able to make a move wins. A player \n" +
            "unable to move during their turn is declared the loser.\n" +
            "Winning a game earns a player 5 points, while losing deducts 2 points.");
        input.pause();
    }

    /**
     * Check if there is a player at the specified coordinates and returns it, else returns null.
     */
    private Player isPlayerThere(int x, int y) {
        for (Player player : players) {
            if (player.x == x && player.y == y) {
                return player;
            }
        }
        return null;
    }

    /**
     * Print differents ASCII characters representing the gameboard according
     * to the tile state and the players positions.
     */
    private void displayGameboard() {
        for (int y = 0; y < dimY; y++){
            for (int x = 0; x < dimX; x++) {
                Player player = isPlayerThere(x, y);
                if (player != null && !loosers.contains(player)) {
                    System.out.print(player.pseudo.charAt(0) + " ");
                } else {
                    System.out.print(gameboard[y][x] ? "X " : "█ ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Clear the screen and display the gameboard.
     */
    private void refreshDisplay() {
        input.clearScreen();
        displayGameboard();
    }

    /**
     * Ask the player to move in a specific direction.
     */
    private void movePlayer() {
        Player player = players.get(currentPlayer);

        while (true) {
            String direction = input.nextStringExpect(
                "Choose your direction " + player.pseudo + " !",
                List.of("UP", "DOWN", "LEFT", "RIGHT")
            );

            switch (direction) {
                case "UP": {
                    if (player.y-1 >= 0 && !gameboard[player.y-1][player.x]) {
                        gameboard[player.y][player.x] = false;
                        gameboard[--player.y][player.x] = true;
                        return;
                    }
                } break;

                case "DOWN": {
                    if (player.y+1 < dimY && !gameboard[player.y+1][player.x]) {
                        gameboard[player.y][player.x] = false;
                        gameboard[++player.y][player.x] = true;
                        return;
                    }
                } break;

                case "LEFT": {
                    if (player.x-1 >= 0 && !gameboard[player.y][player.x-1]) {
                        gameboard[player.y][player.x] = false;
                        gameboard[player.y][--player.x] = true;
                        return;
                    }
                } break;

                case "RIGHT": {
                    if (player.x+1 < dimX && !gameboard[player.y][player.x+1]) {
                        gameboard[player.y][player.x] = false;
                        gameboard[player.y][++player.x] = true;
                        return;
                    }
                } break;
            }
            System.out.println("You can't move there !");
        }
    }

    /**
     * Ask the current player to destroy a tile on the gameboard.
     */
    private void destroyTile() {
        System.out.println("Time to destroy a tile !\nPlease specify the X and Y coordinate :");
        int x = input.nextIntRange("X = ", 1, dimX);
        int y = input.nextIntRange("Y = ", 1, dimY);
        x--; y--;

        while (isPlayerThere(x, y) != null || gameboard[y][x]) {
            System.out.println("You can't destroy this tile !");
            x = input.nextIntRange("X = ", 1, dimX);
            y = input.nextIntRange("Y = ", 1, dimY);
            x--; y--;
        }

        // Place the tile
        gameboard[y][x] = true;

        // Check if any blocked player and replace it  by a tile
        for (Player p : players)
        {
            boolean up = p.y-1 >= 0 && gameboard[p.y-1][p.x];
            boolean down = p.y+1 < dimY && gameboard[p.y+1][p.x];
            boolean left = p.x-1 >= 0 && gameboard[p.y][p.x-1];
            boolean right = p.x+1 < dimX && gameboard[p.y][p.x+1];

            if (!loosers.contains(p) && up && down && left && right) {
                loosers.add(p);
                gameboard[p.y][p.x] = false;
            }
        }
    }

    /**
     * Update the game's states.
     */
    public void update() {
        // Game loop
        while (loosers.size() < playersCount - 1) {
            refreshDisplay();
            movePlayer();

            refreshDisplay();
            destroyTile();

            currentPlayer++;
            if (currentPlayer == playersCount) currentPlayer = 0;
        }

        // Winner annoucement
        ArrayList<Player> tmp = (ArrayList<Player>) players.clone();
        tmp.removeAll(loosers);
        Player winner = tmp.getFirst();
        System.out.println(players.get(0).pseudo + " win the game !");
        input.pause();

        // Scoreboard update
        scoreboard.updateScore(winner.pseudo, true);
        for (Player looser : loosers) scoreboard.updateScore(looser.pseudo, false);
    }
}

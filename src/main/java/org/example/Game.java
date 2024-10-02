package org.example;

import javax.print.attribute.HashDocAttributeSet;
import java.util.*;

public class Game {
    String player1;
    String player1Input;
    String player2Input;
    int player1Score = 0;
    int player2Score = 0;
    int result;
    int game = 1;
    int round = 1;
    String[] moves = {"Rock", "Paper", "Scissors"};
    Set<ArrayList<String>> winningCombinations = new HashSet<>();
    Scanner input = new Scanner(System.in);
    public void playGame (User currentUser){
        player1 = currentUser.getName();

        addWinningCombinations();

        System.out.println("REMEMBER! MAKE NO SPELLING MISTAKES!");

        while(round <= 3) {

            showCurrentGameInfo();

            player1Input = move("player1");
            player2Input = move("player2");

            String[] currentRoundMoves = {player1Input, player2Input};

            String winner = findRoundWinner(currentRoundMoves);

            updateScore(winner);

            announceRoundWinner(winner);

            result = player1Score - player2Score;

            round++;
        }

        checkForWinner(currentUser, result);
    }
    private void addWinningCombinations () {
        ArrayList<String> combination1 = new ArrayList<>(Arrays.asList("Rock", "Scissors"));
        ArrayList<String> combination2 = new ArrayList<>(Arrays.asList("Paper", "Rock"));
        ArrayList<String> combination3 = new ArrayList<>(Arrays.asList("Scissors", "Paper"));
        winningCombinations.add(combination1);
        winningCombinations.add(combination2);
        winningCombinations.add(combination3);
    }
    private void showCurrentGameInfo () {
        System.out.println(
            "--------------------------"+
            "\n< Game #" + game + " > " + " << Round "+ round + " >>" +
            "\n--------------------------" +
            "\n| You [" + player1Score + "] | [" + player2Score + "] Computer |" +
            "\n--------------------------"
        );
    }
    private String move (String player) {
        if (player == "player1") {
            System.out.println("¿Rock? ¿Paper? ¿Scissors?");
            return input.next();
        } else {
            Random random = new Random();
            return moves[random.nextInt(moves.length)];
        }
    }
    private String findRoundWinner (String[] currentRoundMoves){
        if (winningCombinations.contains(Arrays.asList(currentRoundMoves))) {
            return "player1";
        } else if (player1Input.equals(player2Input)) {
            return "null";
        } else {
            return "player2";
        }
    }
    private void updateScore (String winner) {
        switch(winner) {
            case "player1":
                player1Score++;
                break;
            case "player2":
                player2Score++;
                break;
        }
    }
    private void announceRoundWinner (String roundWinner) {
        switch(roundWinner) {
            case "player1":
                System.out.println("\n" + player1Input.toUpperCase() + " beats " + player2Input.toUpperCase() +" *** Point for " + player1 + ". ***" + "\n");
                break;
            case "player2":
                System.out.println("\n" + player1Input.toUpperCase() + " beats " + player2Input.toUpperCase() +" *** Point for the computer. ***" + "\n");
                break;
            default:
                System.out.println("\n" + player1Input + " IS " + player2Input +" *** TIE ***" + "\n");
        }

    }
    private void checkForWinner (User currentUser, int result) {
        if(result>0) {
            System.out.println("Condragulations "+ player1 +  "!!! YOU are the WINNER, baby!");
            resetGame();
        } else if (result<0) {
            System.out.println(player1 + ", I'm sorry my dear but it's not your time.");
            System.out.println("Sashay away...");
            resetGame();
        } else {
            System.out.println("NO WINNER! You must try again.");
            resetGame();
            game++;
            playGame(currentUser);
        }
    }
    private void resetGame () {
        round = 1;
        player1Score = 0;
        player2Score = 0;
    }
}

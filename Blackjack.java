import java.util.Scanner ;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
/**
 *<h2> BlackJack </h2>
 *<p><Problem Statement:</p>  <i>Create a blackjack game incorporating techniques learned over the fall semester</i>
 *<br></br>
 * <p>Algorithm</p>
 *  <p>In <code>BlackJack</code>:</p>
 *      <ol>
 * <li>Prompt user for name and amount of money at stake</li>
 * <li>Prompt user for the amount of money they would like to bet</li>
 * <li>Create a stack of cards including all 52 playing cards</li>
 * <li>Have the user draw one card, while simultaneously showing the dealer's first card</li>
 * <li>Allow the user to draw another card as much as desired until they reach a number over 21</li>
 * <li>After the user declines to hit or busts, allow the dealer to have their turn</li>
 * <li>Have the dealer draw cards until bust or their hand reaches a value over 17</li>
 * <li>Check whether the dealer or player is closer to 21</li>
 * <li>Display the winner and log the last round into a text file</li>
 * <li>Allow the user to input the next bet</li>
 * </ol>
 * 
 * 
 * <br></br> variables used in <code>Main</code>:
 * <p>
 * <ul>
 * <li>String playerName, sTotalMoney, playerHand, dealerHand, hand</li>
 * <li>double totalMoney, playerBet,</li>
 * <li>int playerValue, dealerValue, nextMove, playerScore, dealerScore, playerValue</li>
 * <li>String[] suit, values</li>
 * <li>char value</li>
 * </ul>
 * </p>
 * 
 * <br></br> variables used in <code>Dealer</code>:
 * <p>
 * <ul>
 * <li>String dealerHand, dealerName, allDealerCards</li>
 * <li>int dealerValue</li>
 * </ul>
 * </p>
 * 
 * <br></br> variables used in <code>Player1</code>:
 * <p>
 * <ul>
 * <li>String playerHand, playerName, allPlayerCards</li>
 * <li>int playerValue</li>
 * </ul>
 * </p>
 * 
 * <br></br> variables used in <code>Player</code>:
 * <p>
 * <ul>
 * <li>String playerHand, playerName</li>
 * <li>int playerValue</li>
 * </ul>
 * </p>
 * 
 * <br></br> variables used in <code>createFile</code>:
 * <p>
 * <ul>
 * <li>String playerName</li>
 * <li>double moneyChange</li>
 * </ul>
 * </p>
 *   
 *@author L.J. Balanza
 *@version Final Module                  
 *<p>Final Project</p>
 */
public class Blackjack
{
    public static void main(String args[]){  
        //Invoke newStack() method to create stack of cards
        ArrayList<String> cardStack = newStack(); 
        //Prompt user for their name, money available, and money bet
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your name? ");
        String playerName = scanner.nextLine();
        //Windows GUI to input total money available
        String sTotalMoney = JOptionPane.showInputDialog("Enter total money available");
        double totalMoney = Double.parseDouble(sTotalMoney);
        JOptionPane.showMessageDialog(null, "The bet inputted is: " + totalMoney, "Bet Input", JOptionPane.PLAIN_MESSAGE);
        System.out.println("Welcome to Black Jack, " + playerName + "! You have been given " + totalMoney + " to play, how much would you like to bet? (-1) to end");
        
        double playerBet = scanner.nextDouble();
        totalMoney = totalMoney - playerBet;
        System.out.println("Betting money left: " + totalMoney);
        String playerHand = "";
        String dealerHand = "";
        int playerValue = 0;
        int dealerValue = 0;
        int nextMove = 0;
        //Loops until player either runs out of money or bets nothing
        while (playerBet > 0 && totalMoney > -1){
            //Have the dealer and player draw a card, removing the card after they draw it
            Player1 player1 = new Player1(playerHand, playerValue, playerName);
            Dealer dealer = new Dealer(dealerHand,dealerValue,"dealer");
            playerHand = randomCard(cardStack);
            System.out.println("\n" + playerName +" has drawn " + playerHand + " from the deck\n");
            playerValue = getValue(playerHand,player1.getPlayerValue());
            cardStack.remove(playerHand);
            player1.addValue(playerValue);
            //Display player values
            System.out.println(playerName + "'s hand: " + player1.deckHistory(playerHand));
            System.out.println(playerName + "'s hand value = " + player1.getPlayerValue() + "\n");

            dealerHand = randomCard(cardStack);
            dealerValue = getValue(dealerHand,dealer.getDealerValue());
            cardStack.remove(dealerHand);
            //Display dealer values
            System.out.println("Dealer hand: " + dealer.deckHistory(dealerHand));
            dealer.addValue(dealerValue);
            System.out.println("Dealer hand value = " + dealer.getDealerValue() + "\n");
            //Prompt user to input 0 or 1 to hit or stay respectively, 2 as a sentinel
            while (nextMove == 0 && player1.getPlayerValue() <= 21){
                System.out.println("Hit or stay? (0/1), 2 to leave: ");
                nextMove = scanner.nextInt();
                if (nextMove == 2){
                    System.exit(0);
                }
                //Statement runs if user hits
                if (nextMove == 0){
                    playerHand = randomCard(cardStack);
                    playerValue = getValue(playerHand,player1.getPlayerValue());
                    player1.addValue(playerValue);
                    cardStack.remove(playerHand);
                    System.out.println(playerName + "'s hand: " + player1.deckHistory(playerHand));
                    System.out.println(playerName + "'s hand value = " + player1.getPlayerValue());
                }
            }
            //Statement runs if user hand values over 21
            if (player1.getPlayerValue() > 21){
                System.out.println("\nUh oh, busted! \n");
                nextMove = 0;
            }
            //Statement runs if user stays or busts
            {
                System.out.println(playerName + "'s hand: " + player1.deckHistory(playerHand));
                System.out.println(playerName + "'s hand value = " + player1.getPlayerValue());
                System.out.println("\nIt is now the opponent's turn. \n");
                nextMove = 0;
            }
            //Loop to allow dealer to play
            while (dealer.getDealerValue() < 17){
                //Loop keeps running until dealer hand reaches a value of 17
                dealerHand = randomCard(cardStack);
                dealerValue = getValue(dealerHand,dealer.getDealerValue());
                dealer.addValue(dealerValue);
                cardStack.remove(dealerHand);
                System.out.println("Dealer hand: " + dealer.deckHistory(dealerHand));
                System.out.println("Dealer hand value = " + dealer.getDealerValue());
            }
            //Basis to tell dealer when to stay
            while (dealer.getDealerValue() >= 17){
                System.out.println("Dealer hand: " + dealer.deckHistory(""));
                System.out.println("Dealer hand value = " + dealer.getDealerValue());
                break;
            }
            //Statement runs if dealer hand value is over 21
            if (dealer.getDealerValue() > 21){
                System.out.println("The dealer is bust! \n");
            }
            //Calculate the score and determine the winner of the round
            int playerScore = 21 - player1.getPlayerValue();
            int dealerScore = 21 - dealer.getDealerValue();
            if (playerScore < 0){
                playerScore = 21;
            }
            if (dealerScore < 0){
                dealerScore = 21;
            }
            //Creates new print writer
            createFile x = new createFile();
            x.openFile();
            //Winning conditions, if player's score is larger than dealers, then the dealer wins
            if (playerScore > dealerScore){
                System.out.println("Dealer wins! \n");
                System.out.println("You lost " + playerBet + " dollars \n");
                System.out.println("Total betting money: " + totalMoney);
                x.addRound("dealer", -playerBet);
            }
            //If the dealer's score is larger than the player's, the player wins 2* back what they bet
            if (dealerScore > playerScore ){
                System.out.println(playerName + " wins! \n");
                totalMoney += playerBet*2;
                System.out.println("You've won " + playerBet + " dollars! \n");
                System.out.println("Total betting money: " + totalMoney);
                x.addRound(playerName, playerBet);
            }
            //If the player and dealer have same score, refund the betting amount
            if (dealerScore == playerScore){
                System.out.println("You're all losers! push! \n");
                totalMoney += playerBet;
                System.out.println(playerBet + " dollars has been refunded to your account\n");
                System.out.println("Total betting money: " + totalMoney);
                x.addRound(playerName + " tied with dealer", 0);
            }
            //Close print writer
            x.closeFile();
            //Prompt user for next bet or sentinel
            if (totalMoney > 0){
                System.out.println("Enter next bet. (0 to leave)");
                playerBet = scanner.nextDouble();
                totalMoney -= playerBet;
            }   
        }
        
    }
    /**
     * getValue takes first character of the String of the card hand, returns an integer corresponding to the card value
     * if a player draws an ace, use the value of the player's hand as a conditional
     * @param String hand
     * @param playerValue
     * @return int value of hand
     */
    public static int getValue(String hand, int playerValue){
        //Takes the first character of the card String and evaluates it
        char value = hand.charAt(0);
        if (value == 'A'){
            //Uses the player value as a conditional to return high or low ace
            Scanner scanner = new Scanner(System.in);
            if (playerValue < 11){
                return 11;
            }
            if (playerValue >= 11){
                return 1;
            }
        }
        //Returns integer corresponding to card String
        if (value == '2'){
            return 2;
        }
        if (value == '3'){
            return 3;
        }
        if (value == '4'){
            return 4;
        }
        if (value == '5'){
            return 5;
        }
        if (value == '6'){
            return 6;
        }
        if (value == '7'){
            return 7;
        }
        if (value == '8'){
            return 8;
        }
        if (value == '9'){
            return 9;
        }
        if (value == 'J'){
            return 10;
        }
        if (value == 'Q'){
            return 10;
        }
        if (value == 'K'){
            return 10;
        }
        return 10;
    }
    /**
     * newStack() creates a fresh stack of 52 playing cards
     * @return ArrayList of 52 Strings (cards)
     */
    public static ArrayList<String> newStack(){
        //CDHS represents clubs, diamonds, hearts, spades
        String[] suit = "C,D,H,S".split(",");
        String[] values = "A,2,3,4,5,6,7,8,9,10,J,Q,K".split(",");
        ArrayList<String> cardStack = new ArrayList<String>();
        //One loop sets all values to correspond to one suit
        for(String s:suit){
            for (String v:values){
                cardStack.add(v+s);
            }
        }
        return cardStack;
    }
    /**
     * randomCard(List) takes a random element from the array and returns it
     * @return String object from list
     */
    public static String randomCard(List<String> list){
        //Uses Random class provided by Java
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}


public class Player1 extends Player
{
    private String playerHand, playerName;
    private int playerValue = 0;
    private String allPlayerCards = "";
    /**
     * Constructor method for Player1
     * @param String playerHand
     * @param int playerValue
     * @param String playerName
     */
    public Player1(String playerHand, int playerValue, String playerName){
        //Inheritance, supplies parameters to parent class-Player
        super(playerHand, playerValue, playerName);
        this.playerHand = playerHand;
        this.playerName = playerName;
        this.playerValue = playerValue;
    }
    /**
     * Getter method for playerHand
     * @return String playerHand
     */
    public String getPlayerHand(){
        return playerHand;
    }
    /**
     * Getter method for playerName
     * @return String playerName
     */
    public String getPlayerName(){
        return playerName;
    }
    /**
     * Getter method for playerValue
     * @return int playerValue
     */
    public int getPlayerValue(){
        return playerValue;
    }
    /**
     * Setter method for playerHand
     * @param String playerHand
     */
    public void setPlayerHand(String playerHand){
        this.playerHand = playerHand;
    }
    /**
     * Setter method for playerHand
     * @param String playerName
     */
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }
    /**
     * Setter method for playerHand
     * @param int playerValue
     */
    public void setPlayerValue(int playerValue){
        this.playerValue = playerValue;
    }
    /**
     * Displays all cards in a player's hand
     * @param String playerHand
     */
    public String deckHistory(String playerHand){
        //Takes the string of cards in a player's hand and adds another card to it
        allPlayerCards = allPlayerCards + " " + playerHand;
        return allPlayerCards;
    }
    /**
     * Adds a card value to the total value
     * @param int value
     */
    public void addValue(int value){
        playerValue += value;
    }
    /**
     * Displays all player cards and corresponding value
     * @param String playerHand
     * @param String playerName
     * @param int playerValue
     * @return String displaying name, hand, and value of hand
     */
    public String displayValue(String playerHand, int playerValue, String playerName){
        return playerName +" has a hand of " + allPlayerCards + " valued at " + playerValue;
    }
}

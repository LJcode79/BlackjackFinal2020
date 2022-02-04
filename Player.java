public class Player
{
    private String playerHand;
    private int playerValue;
    private String playerName = "";
    /**
     * Constructor for Player class
     * @param String playerHand
     * @param int playerValue
     * @param String playerName
     */
    public Player(String playerHand, int playerValue, String playerName){
        this.playerHand = playerHand;
        this.playerName = playerName;
        this.playerValue = playerValue;
    }
    /**
     * Displays the player's hand and the value of the player's hand
     * @param String playerHand
     * @param int playerValue
     * @return String displaying playerHand and the corresponding playerValue
     */
    public String displayValue(String playerHand, int playerValue){
        return "Player has a hand of " + playerHand + " valued at " + playerValue;
    }
    }

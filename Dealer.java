public class Dealer extends Player
{
    private String dealerHand, dealerName = "dealer";
    private int dealerValue = 0;
    private String allDealerCards = "";
    /**
     * Constructor method for Dealer
     * @param String dealerHand
     * @param String dealerName
     * @param int dealerValue
     */
    public Dealer(String dealerHand, int dealerValue,String dealerName){
        //Inheritance, supplies parameters to parent class-Player
        super(dealerHand, dealerValue, dealerName);
        this.dealerHand = dealerHand;
        this.dealerValue = dealerValue;
        this.dealerName = "dealer";
    }
    /**
     * dealerHand getter
     * @return String dealerHand
     */
    public String getDealerHand(){
        return dealerHand;
    }
    /**
     * dealerName getter
     * @return String dealerName
     */
    public String getDealerName(){
        return dealerName;
    }
    /**
     * dealerHand getter
     * @return int dealerValue
     */
    public int getDealerValue(){
        return dealerValue;
    }
    /**
     * dealerHand setter
     * @param String dealerHand
     */
    public void setDealerHand(String dealerHand){
        this.dealerHand = dealerHand;
    }
    /**
     * dealerName setter
     * @param String dealerName
     */
    public void setDealerName(String dealerName){
        this.dealerName = dealerName;
    }
    /**
     * dealerValue setter
     * @param String dealerValue
     */
    public void setDealerValue(int dealerValue){
        this.dealerValue = dealerValue;
    }
    /**
     * Shows strings of card in a dealer's hand
     * @param String dealerHand
     * @return String allDealerCards
     */
    public String deckHistory(String dealerHand){
        //Takes all current strings and adds the string in the parameter to it
        allDealerCards = allDealerCards + " " + dealerHand;
        return allDealerCards;
    }
    /**
     * addValue(int value) adds the value of a card to the total dealer's value
     * @param int Value
     */
    public void addValue(int value){
        //Takes the total dealer value and adds the value in the parameter to it
        dealerValue += value;
    }
    /**
     * Displays the current hand of the dealer along with it's value
     * @param String dealerHand
     * @param int dealerValue
     * @param String dealerName
     * @return String showing dealer's hand and value
     */
    public String displayValue(String dealerHand, int dealerValue, String dealerName){
        return dealerName +" has a hand of " + allDealerCards + " valued at " + dealerValue;
    }
}
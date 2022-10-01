//Suits:    0 - Spades  1 - Clubs  2 - Hearts  3- Diamonds

public class card {
    public int suit;
    public int value;
    public int totalValue; // Respresents the suit and value, has a range of 2 to 54, because it is nice to represent 2 of spades as 2, not 0
    public card(int suit, int value){
        this.suit = suit;
        this.value = value;
        this.totalValue=suit*13 + value;
    }
    public card(int totalValue){
        this.totalValue=totalValue;
        this.value=(totalValue-2)%13+2;
        this.suit=(totalValue-value)/13;
    }
    public String englishValue(){
        String output = "";
        switch (this.value){
            case 2:
                output+="Two ";
                break;
            case 3:
                output+="Three ";
                break;
            case 4:
                output+="Four ";
                break;
            case 5:
                output+="Five ";
                break;
            case 6:
                output+="Six ";
                break;
            case 7:
                output+="Seven ";
                break;
            case 8:
                output+="Eight ";
                break;
            case 9:
                output+="Nine ";
                break;
            case 10:
                output+="Ten ";
                break;
            case 11:
                output+="Jack ";
                break;
            case 12:
                output+="Queen ";
                break;
            case 13:
                output+="King ";
                break;
            case 14:
                output+="Ace ";
                break;
        }
        output += "of ";
        switch (this.suit){
            case 0:
                output+="Spades";
                break;
            case 1:
                output+="Clubs";
                break;
            case 2:
                output+="Diamonds";
                break;
            case 3:
                output+="Hearts";
                break;
        }
        return output;
    }
}

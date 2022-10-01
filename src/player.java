public class player {
    public int playerTotal=0; //Used for chanceOfWin class, keeps track of how many possible hands the player has won
    public card[] hand = new card[2];
    public int handScore = -1;
    public card[] bestHand;

    public player(card[] hand){
        this.hand=hand;
    }

    public player(){

    }

    public void set(int handScore,card[] bestHand){
        this.handScore = handScore;
        this.bestHand = bestHand;
    }
}

import com.sun.corba.se.impl.legacy.connection.USLPort;

import java.util.Random;

public class deck {
    public card[] deck = new card[52];
    public deck(){
        reset();
    }

    public void reset(){
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j < 15; j++) {
                deck[(j-2)+i*13] = new card(i,j);
            }
        }
    }

    public void shuffle(){

        Random rand = new Random();
        for (int i = 0; i < 52; i++) {
            int randomIndexToSwap = rand.nextInt(deck.length);
            card temp = deck[randomIndexToSwap];
            deck[randomIndexToSwap] = deck[i];
            deck[i] = temp;
        }
    }


//    public card getCard(int index){
//        return out;
//    }
}

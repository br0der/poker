import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class chanceOfWinSlow {
    public static void main(String[] args) {
        Date dateStart = new Date();
        long start = dateStart.getTime();
//        Scanner scan = new Scanner(System.in);
        poker poker = new poker();
//        deck deck = new deck();
        card[] hand = new card[4]; //0-1 is player 1       2-3 is player 2
        hand[0]=new card(0, 14);
        hand[1]=new card(1, 10);
        hand[2]=new card(2, 7);
        hand[3]=new card(3, 2);

        player p1 = new player(new card[]{hand[0], hand[1]});
        player p2 = new player(new card[]{hand[2], hand[3]});

        // nCr = n(n - 1)(n - 2) . . . (n - r + 1)/r! = n! / r!(n - r)! Where n is the number of card in the deck and r is the # of spots we need filled
        int numOfCards = 44;
        int numOfSpaces = 1;
        int length = 5;
        int increment = 52;
        int ties = 0;
        long total = 0;
        //Optimization
        //long numOfPossibleHands = poker.fac(numOfCards).divide(poker.fac(numOfSpaces).multiply(poker.fac(numOfCards-numOfSpaces))).longValue();
        long iterations = poker.pow(increment,length);

        here: for (int i = 0; i < poker.pow(increment,length); i++) {
//            System.out.println(i);
            card[] arr = new card[length + hand.length];
            for (int j = 0; j < length; j++) {
                int pow = (int) poker.pow(increment, length - j - 1);
                int totalVal = ((i - (i % pow)) / pow) % increment; //Shut up I know what im doing
                int value = totalVal % 13;
                int suit = (totalVal - value) / 13;
                arr[j] = new card(suit, value + 2);
            }


            System.arraycopy(hand, 0, arr, length, hand.length);

            for (int j = 0; j < arr.length - 1; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (arr[j].totalValue == arr[k].totalValue) {
                        continue here;
                    }
                }
            }
            poker.cardAnalysis(p1, arr);
            poker.cardAnalysis(p2, arr);
            total++;

            // 205476480


            if (p1.handScore < p2.handScore) {
                p1.playerTotal++;
            } else if (p1.handScore > p2.handScore) {
                p2.playerTotal++;
            } else {
                int result = poker.whoWins(p1, p2);
                if (result == 1) {
                    p1.playerTotal++;
                } else if (result == 2) {
                    p2.playerTotal++;
                } else {
                    ties++;
                }
            }
        }
        System.out.println("Player 1 won " + (double) p1.playerTotal/total*100 + "% of the time and Player 2 won " + (double) p2.playerTotal/total*100 + "% of the time and they split the pot " + (double) ties/total*100 + "% of the time total:" + total);
        Date dateEnd = new Date();
        long end = dateEnd.getTime();
        System.out.println("This took " + (end-start) + " milliseconds or " + ((end-start)/1000) + " seconds");
    }
}

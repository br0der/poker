import java.util.Date;

public class chanceOfWin {
    public static void main(String[] args) {
//        double avgTime = 0;
//        int length = 10;
//        for (int i = 0; i < length; i++) {
            Date dateStart = new Date();
            long start = dateStart.getTime();
//        Scanner scan = new Scanner(System.in);
            poker poker = new poker();
//        deck deck = new deck();
            card[] hand = new card[4]; //0-1 is player 1       2-3 is player 2
            hand[0]=new card(0, 12);
            hand[1]=new card(3, 10);
            hand[2]=new card(2, 8);
            hand[3]=new card(3, 9);

            double[] results = poker.chanceOfWin(hand[0],hand[1],hand[2],hand[3]);
            double total = results[0]+results[1]+results[2];
            System.out.println("Player 1 won " + results[1]/total*100 + "% of the time and Player 2 won " + (double) results[2]/total*100 + "% of the time and they split the pot " + (double) results[0]/total*100 + "% of the time total:" + total);
            Date dateEnd = new Date();
            long end = dateEnd.getTime();
            System.out.println("This took " + (end-start) + " milliseconds or " + ((end-start)/1000) + " seconds");
            System.out.println(poker.getCounter()); //A counter used to debug in the poker class
            //System.out.println(poker.printNum());
//            avgTime+=(end-start);
//        }
//        avgTime/=length;
//        System.out.println(avgTime);
    }
}

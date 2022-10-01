import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        poker poker = new poker();

        Date dateStart = new Date();
        long start = dateStart.getTime();

        deck mainDeck = new deck();

        int[] results =  new int[10];
        Arrays.fill(results, 0);

        System.out.println("How many iterations?");
        int iterations = scan.nextInt();

        for (int iteration = 0; iteration < iterations; iteration++) {
            mainDeck.shuffle();
            int nextCardIndex = 0;

            player player = new player();
            for (int i = 0; i < 2; i++) {
                player.hand[i] = mainDeck.deck[nextCardIndex];
                nextCardIndex++;
            }
            card[] house = new card[5];
            for (int i = 0; i < 5; i++) {
                house[i] = mainDeck.deck[nextCardIndex];
                nextCardIndex++;
            }

            poker.cardAnalysisEfficient(player,house);
            results[player.handScore]++;
        }
        Date dateEnd = new Date();
        long end = dateEnd.getTime();
        System.out.println("Royal flush %: " +      (results[0] / ((double)iterations/100) ) + ", Real probability is: 0.0032%");
        System.out.println("Straight flush %: " +   (results[1] / ((double)iterations/100) ) + ", Real probability is: 0.0279%");
        System.out.println("Four of a kind %: " +   (results[2] / ((double)iterations/100) ) + ", Real probability is: 0.168%");
        System.out.println("Full house %: " +       (results[3] / ((double)iterations/100) ) + ", Real probability is: 2.60%");
        System.out.println("Flush %: " +            (results[4] / ((double)iterations/100) ) + ", Real probability is: 3.03%");
        System.out.println("Straight %: " +         (results[5] / ((double)iterations/100) ) + ", Real probability is: 4.62%");
        System.out.println("Three of a kind %: " +  (results[6] / ((double)iterations/100) ) + ", Real probability is: 4.83%");
        System.out.println("Two pair %: " +         (results[7] / ((double)iterations/100) ) + ", Real probability is: 23.5%");
        System.out.println("Pair %: " +             (results[8] / ((double)iterations/100) ) + ", Real probability is: 43.8%");
        System.out.println("High card %: " +        (results[9] / ((double)iterations/100) ) + ", Real probability is: 17.4%");
        System.out.println("This took " + (end-start) + " milliseconds or " + ((end-start)/1000) + " seconds");
        System.out.println(poker.printNum());
    }
}

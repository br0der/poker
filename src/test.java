import java.util.*;

public class test {
    public static void main(String[] args) {
        poker Poker = new poker();
        card[] test = new card[7];
        test[0] = new card(0,12);
        test[1] = new card(0,10);
        test[2] = new card(0,13);
        test[3] = new card(1,14);
        test[4] = new card(0,11);
        test[5] = new card(0,14);
        test[6] = new card(2,9);
        int[] arr = {5,3,6,2,1};
        Poker.heapSort(arr);
        System.out.println(Arrays.toString(arr));
        //suitSort(test);
        //test=numSort(test);
        player testP = new player(new card[]{test[0], test[1]});
        System.out.println(Poker.print(Poker.cardAnalysisEfficient(testP, new card[]{test[2], test[3], test[4], test[5], test[6]})) + testP.handScore);

    }
    public static int isStraightFlush(card[] hand){
        int streak = 1;
        for (int i = 1; i < 7; i++) {

            if(hand[i-1].value-1==hand[i].value&&hand[i-1].suit==hand[i].suit){
                streak++;
                //Finding the 14 5 4 3 2 Straight
                if(streak==4&&hand[i].value==2){
                    //Finding an Ace in the first 3 spots
                    for (int j = 0; j < 3; j++) {
                        if(hand[j].value==14&&hand[j].suit==hand[i].suit){
                            return 1;
                        }
                    }
                }
            }

            else{
                if(i>3){
                    return 0;
                }
                streak=1;
            }

            if(streak>=5){
                if(hand[i].value==10){
                    return 2;
                }

                return 1;
            }

        }
        return 0;
    }
    public static int findPairs(card[] hand){
        int[] values = new int[13];
        for (int i = 0; i < hand.length; i++) {
            values[hand[i].value-2]++;
        }
        int triples = 0;
        int doubles = 0;
        for (int i = 0; i < values.length; i++) {
            int num = values[i];
            if(num==4){
                return 5;       //Four of a kind
            }
            else if(num==3){
                triples++;
            }
            else if(num==2){
                doubles++;
            }
        }
        if(triples>0&&doubles>0||triples==2){
            return 4;           //Full House
        }
        if(triples>0){
            return 3;           //Three of a kind
        }
        if(doubles>1){
            return 2;           //Two Pair
        }
        if(doubles==1){
            return 1;           //Pair
        }
        return 0;               //Muck
    }
    public static boolean isStraight(card[] hand){
        int streak = 1;
        for (int i = 1; i < 7; i++) {
            if(hand[i-1].value-1==hand[i].value){
                streak++;
                if(streak==4&&hand[i].value==2&&hand[0].value==14){
                    return true;
                }
            }
            else if(hand[i-1].value!=hand[i].value){
                if(i>3){
                    return false;
                }
                streak=1;
            }
            if(streak>=5){
                return true;
            }
        }
        return false;
    }

    public static card[] numSort(card[] array){
        for (int i = 0; i < array.length; i++) {
            card max = array[i];
            int maxId = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j].value > max.value) {
                    max = array[j];
                    maxId = j;
                }
            }

            card temp = array[i];
            array[i] = max;
            array[maxId] = temp;
        }
        return array;
    }
    public static card[] suitSort(card[] array){
        int n = array.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (array[j].suit > array[j+1].suit)
                {
                    // swap arr[j+1] and arr[j]
                    card temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
        return array;
    }
}

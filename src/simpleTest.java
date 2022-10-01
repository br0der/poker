import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

import javax.xml.soap.SOAPPart;

public class simpleTest {
    public static void main(String[] args) {
        int[] deck = {0,1,2,3,4,5,6,7,8,9};
        int length = 2;
        int increment = 52;
        card[] hand = new card[4];
        hand[0]=new card(0, 2);
        hand[1]=new card(1, 2);
        hand[2]=new card(2, 2);
        hand[3]=new card(3, 2);
        here: for (int i = 0; i < Math.pow(increment,length); i++) {
            card[] arr = new card[length];

            for (int j = 0; j < length; j++) {
                int totalVal = (  (i- (i % (int) Math.pow(increment,length-j-1) ) ) / (int) Math.pow(increment,length-j-1)  ) % increment;
                int value = totalVal%13;
                int suit = (totalVal-value)/13;
                arr[j]= new card(suit, value+2); //Shut up I know what im doing
            }

            card[] avoid = new card[length+hand.length];

            System.arraycopy(arr, 0, avoid, 0, length);
            System.arraycopy(hand, 0, avoid, length, hand.length);

            for (int j = 0; j < avoid.length-1; j++) {
                for (int k = j + 1; k < avoid.length; k++) {
                    if (avoid[j].value == avoid[k].value&&avoid[j].suit==avoid[k].suit) {
                        continue here;
                    }
                }
            }

            for (int j = 0; j < length; j++) {
                System.out.print(arr[j].englishValue()+", ");
            }
            System.out.println();
        }
    }
}

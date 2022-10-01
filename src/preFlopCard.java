import java.util.Date;
import java.util.Objects;

public class preFlopCard {
    public static void main(String[] args) {
        Date dateStart = new Date();
        long start = dateStart.getTime();
        poker poker = new poker();
        card c1 = new card(1,3);
        card c2 = new card(2,3);
        card c3 = null;
        card c4 = null;
        double total=0;
        double[] totalresults = new double[3];

        //All possible
        for(int a = 2; a<54; a++){
            if(a==c1.totalValue||a==c2.totalValue){
                continue;
            }
            c3=new card(a);

            for(int b = a+1; b<54; b++){
                if(b==c1.totalValue||b==c2.totalValue){
                    continue;
                }
                c4=new card(b);
//                System.out.println(c3.englishValue() + c4.englishValue());
                double[] results = poker.chanceOfWin(c1,c2,c3,c4);
                for (int i = 0; i < 3; i++) {
                    totalresults[i]+=results[i];
                }
                total+=(results[0]+results[1]+results[2]);
            }
        }

        //Random
//        for (int iteration = 0; iteration < 100; iteration++) {
//            boolean next = true;
//            while(next) {
//                int a = (int) (Math.random() * 52 + 2);
//                if (!(a == c1.totalValue || a == c2.totalValue)) {
//                    next = true;
//                    c3=new card(a);
//                }
//                else{
//                    next=false;
//                }
//            }
//            next=true;
//            while(next){
//                int b = (int) (Math.random() * 52 + 2);
//                if(!(b==c1.totalValue||b==c2.totalValue||b== Objects.requireNonNull(c3).totalValue)){ //idk why i need Objects.requireNonNull
//                    next=true;
//                    c4=new card(b);
//                }
//                else{
//                    next=false;
//                }
//            }
//            if(c3==null||c4==null){
//                System.out.println("?????");
//            }
//            double[] results = poker.chanceOfWin(c1,c2,c3,c4);
//            for (int i = 0; i < 3; i++) {
//                totalresults[i]+=results[i];
//            }
//            total+=(results[0]+results[1]+results[2]);
//            System.out.println("Player 1 won " + totalresults[1]/total*100 + "% of the time");
//        }
        System.out.println("Player 1 won " + totalresults[1]/total*100 + "% of the time");
        System.out.println();
        Date dateEnd = new Date();
        long end = dateEnd.getTime();
        System.out.println("This took " + (end-start) + " milliseconds or " + ((end-start)/1000) + " seconds");
    }
}

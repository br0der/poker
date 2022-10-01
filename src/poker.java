import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

public class poker {
    private int counter;
    public card[] findPairs(card[] hand){
        int[] values = new int[15];
        for (int i = 0; i < hand.length; i++) {
            values[hand[i].value]++;
        }
        int[] tripleVal = new int[2];
        int[] doubleVal = new int[2];
        for (int i = 2; i < values.length; i++) {
            int num = values[i];

            if(num==4){             //Four of a kind
                card[] output = new card[2];
                for (int j = 0; j < 7; j++) {
                    if(hand[j].value==i){
                        output[0]=hand[j];
                    }

                    else if(output[1]==null||hand[j].value>output[1].value){
                        output[1]=hand[j];
                    }
                }
                return output;
            }

            else if(num==3){
                if(tripleVal[0]==0){
                    tripleVal[0]=i;
                }
                else if(i>tripleVal[0]){
                    tripleVal[1]=tripleVal[0];
                    tripleVal[0]=i;
                }
                else{
                    tripleVal[1]=i;
                }

            }
            else if(num==2){
                if(doubleVal[0]==0){
                    doubleVal[0]=i;
                }
                else if(doubleVal[1]==0||i>doubleVal[1]){
                    doubleVal[1]=i;
                }
                if(doubleVal[0]<doubleVal[1]){
                    int temp = doubleVal[0];
                    doubleVal[0]=doubleVal[1];
                    doubleVal[1]=temp;
                }
            }
        }
        if(tripleVal[0]!=0&&doubleVal[0]!=0){ //Full House
            card[] output = new card[3];
            for (int i = 0; i < 7; i++) {
                if(output[0]==null&&hand[i].value==tripleVal[0]){
                    output[0]=hand[i];
                }
                else if(output[1]==null&&hand[i].value==doubleVal[0]){
                    output[1]=hand[i];
                }
            }
            return output;
        }
        if(tripleVal[1]!=0){                 //Also Full House but checks if there are 2 sets of three of a kinds
            card[] output = new card[3];
            for (int i = 0; i < 7; i++) {
                if(output[0]==null&&hand[i].value==tripleVal[0]){
                    output[0]=hand[i];
                }
                else if(output[1]==null&&hand[i].value==tripleVal[1]){
                    output[1]=hand[i];
                }
            }
            return output;
        }

        if(tripleVal[0]!=0){            //Three of a kind
            card[] output = new card[4];
            for (int i = 0; i < 7; i++) {
                if(hand[i].value==tripleVal[0]){
                    output[0]=hand[i];
                }
                else if(output[1]==null){
                    output[1]=hand[i];
                }
                else if(output[1].value<hand[i].value){
                    output[2]=output[1];
                    output[1]=hand[i];
                }
                else if(output[2]==null||output[2].value<hand[i].value){
                    output[2]=hand[i];
                }
            }
            return output;
        }
        if(doubleVal[1]!=0){            //Two Pair
            card[] output = new card[5];
            for (int i = 0; i < 7; i++) {
                if(output[0]==null&&hand[i].value==doubleVal[0]){
                    output[0]=hand[i];
                }
                else if(output[1]==null&&hand[i].value==doubleVal[1]){
                    output[1]=hand[i];
                }
                else if((hand[i].value!=doubleVal[0]&&hand[i].value!=doubleVal[1])&&(output[2]==null||hand[i].value>output[2].value) ){
                    output[2]=hand[i];
                }
            }
            return output;
        }
        if(doubleVal[0]!=0){           //Pair
            card[] output = new card[6];
            card[] leftover = new card[5];
            for (int i = 0; i < 7; i++) {
                if(hand[i].value==doubleVal[0]){
                    output[0]=hand[i];
                }
                else{
                    for (int j = 0; j < 5; j++) {
                        if(leftover[j]==null){
                            leftover[j]=hand[i];
                            break;
                        }
                    }
                }
            }
            heapSortValue(leftover);
            System.arraycopy(leftover,0,output,1,3);
            return output;
        }
        return new card[1];               //Muck
    }

    public int[] pairsGetArray(card[] hand){
        int[] values = new int[15];
        for (int i = 0; i < hand.length; i++) {
            values[hand[i].value]++;
        }
        return values;
    }

    public card[] pairsFourOfAKind(card[] hand, int[] values){
        card[] output = new card[2];
        for (int i = 2; i < values.length; i++) {
            for (int j = 0; j < 7; j++) {
                if (hand[j].value == i) {
                    output[0] = hand[j];
                } else if (output[1] == null || hand[j].value > output[1].value) {
                    output[1] = hand[j];
                }
            }
        }
        return output;
    }

    public card[] pairsFindTwos(card[] hand, int[] values){
        int[] doubleIndexes = {-1,-1,-1};
        for (int i = 0; i < values.length; i++) {
            if(values[i]==2) {
                if (doubleIndexes[0] == -1) {
                    doubleIndexes[0] = i;
                } else if (doubleIndexes[1] == -1) {
                    doubleIndexes[1] = i;
                }
                else{
                    doubleIndexes[2] = i;
                }
            }
        }
        heapSort(doubleIndexes);
        if(doubleIndexes[1]!=-1){

        }
        if(doubleIndexes[0]!=-1){

        }
        return new card[1];
    }

    public BigInteger fac(int num1){
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= num1; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

    public card[] isFlush(card[] hand){
        card[] handFinal = hand; // Just so numSorting a couple lines down does not mess with the original array
        int[] suits = new int[4];
        for (int i = 0; i < handFinal.length; i++) {
            suits[handFinal[i].suit]++;
        }
        for (int i = 0; i < suits.length; i++) {
            if(suits[i]>=5){
                card[] output = new card[5];
                heapSortValue(handFinal);
                int nextIndex = 0;
                for (int j = 0; j < 7; j++) {
                    if(nextIndex==5){
                        break;
                    }
                    if(handFinal[j].suit==i){
                        output[nextIndex]=handFinal[j];
                        nextIndex++;
                    }
                }
                return output;
            }
        }
        return null;
    }

    public card[] isStraight(card[] hand){
        int streak = 1;
        card[] output = new card[1];
        for (int i = 1; i < 7; i++) {
            if(hand[i-1].value-1==hand[i].value){
                streak++;
                if(streak==4&&hand[i].value==2&&hand[0].value==14){
                    output[0]=hand[i];
                    return output;
                }
            }
            else if(hand[i-1].value!=hand[i].value){
                if(i>3){            // Would be 2 if it weren't for the 5 4 3 2 14 (Ace) Straight
                    return null;
                }
                streak=1;
            }
            if(streak>=5){
                output[0]=hand[i];
                return output;
            }
        }
        return null;
    }

    public card[] isStraightFlush(card[] hand){
        int streak = 1;
        for (int i = 1; i < 7; i++) {

            if(hand[i-1].value-1==hand[i].value&&hand[i-1].suit==hand[i].suit){
                streak++;
                //Finding the 14 5 4 3 2 Straight
                if(streak==4&&hand[i].value==2){
                    //Finding an Ace in the first 3 spots
                    for (int j = 0; j < 3; j++) {
                        if(hand[j].value==14&&hand[j].suit==hand[i].suit){
                            return new card[]{hand[i]};
                        }
                    }
                }
            }

            else{
                if(i>3){
                    return null;
                }
                streak=1;
            }

            if(streak>=5){
                return new card[]{hand[i]};
            }
        }
        return null;
    }

    public card[] numSort(card[] array){
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

    public void heapSort(int arr[])
    {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(int arr[], int n, int i)
    {
        int smallest = i; // Initialize smallest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is smaller than root
        if (l < n && arr[l] < arr[smallest])
            smallest = l;

        // If right child is smaller than largest so far
        if (r < n && arr[r] < arr[smallest])
            smallest = r;

        // If smallest is not root
        if (smallest != i) {
            int swap = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, smallest);
        }
    }

    static void heapifyValue(card[] array, int length, int i) {
        int leftChild = 2*i+1;
        int rightChild = 2*i+2;
        int smallest = i;

        // if the left child is larger than parent
        if (leftChild < length && array[leftChild].value < array[smallest].value) {
            smallest = leftChild;
        }

        // if the right child is larger than parent
        if (rightChild < length && array[rightChild].value < array[smallest].value) {
            smallest = rightChild;
        }

        // if a swap needs to occur
        if (smallest != i) {
            card temp = array[i];
            array[i] = array[smallest];
            array[smallest] = temp;
            heapifyValue(array, length, smallest);
        }
    }

    public void heapSortValue(card[] array) {
        if (array.length == 0) return;

        // Building the heap
        int length = array.length;
        // we're going from the first non-leaf to the root
        for (int i = length / 2-1; i >= 0; i--)
            heapifyValue(array, length, i);

        for (int i = length-1; i >= 0; i--) {
            card temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapifyValue(array, i, 0);
        }
        counter++;
    }

    public card[] valueSort(card[] array){
        for (int i = 0; i < array.length; i++) {
            card max = array[i];
            int maxId = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j].totalValue > max.totalValue) {
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

    public long pow(int num1, int num2){
        int total = 1;
        for (int i = 0; i < num2; i++) {
            total*=num1;
        }
        return total;
    }

    static void heapifySuit(card[] array, int length, int i) {
        int leftChild = 2*i+1;
        int rightChild = 2*i+2;
        int largest = i;

        // if the left child is larger than parent
        if (leftChild < length && array[leftChild].suit > array[largest].suit) {
            largest = leftChild;
        }

        // if the right child is larger than parent
        if (rightChild < length && array[rightChild].suit > array[largest].suit) {
            largest = rightChild;
        }

        // if a swap needs to occur
        if (largest != i) {
            card temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            heapifySuit(array, length, largest);
        }
    }

    public void heapSortSuit(card[] array) {
        if (array.length == 0) return;

        // Building the heap
        int length = array.length;
        // we're going from the first non-leaf to the root
        for (int i = length / 2-1; i >= 0; i--)
            heapifySuit(array, length, i);

        for (int i = length-1; i >= 0; i--) {
            card temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapifySuit(array, i, 0);
        }
    }

    public card[] suitSort(card[] array){
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

    public String print(card[] array){
        String out = "";
        for (int i = 0; i < array.length; i++) {
            if(array[i]==null){
                break;
            }
            out+=array[i].englishValue()+", ";
        }
        return out;
    }

    public long getCounter(){
        return counter;
    }

    public int whoWins(player p1, player p2){
        for (int i = 0; i < p1.bestHand.length; i++) {
            if(p1.bestHand[i]==null||p2.bestHand[i]==null){
                break;
            }
            if(p1.bestHand[i].value>p2.bestHand[i].value){
                return 1;
            }
            else if(p1.bestHand[i].value<p2.bestHand[i].value){
                return 2;
            }
        }
        return 0;
    }

    public void cardAnalysis(player player, card[] house){
        //Setup
        card[] fullHand = new card[7];
        for (int i = 0; i < 7; i++) {
            if (i < 5) {
                fullHand[i] = house[i];
            } else {
                fullHand[i] = player.hand[i - 5];
            }
        }
        heapSortValue(fullHand);
        heapSortSuit(fullHand);

        //Finding value
        card[] straightFlush_royalFlush = isStraightFlush(fullHand);
        if(straightFlush_royalFlush!=null&&straightFlush_royalFlush[0].value==10){
            player.set(0,straightFlush_royalFlush);
        }
        else if(straightFlush_royalFlush!=null){
            player.set(1,straightFlush_royalFlush);
        }
        else {
            heapSortValue(fullHand);
            card[] pairs = findPairs(fullHand);
            if (pairs.length==2) {
                player.set(2,pairs);
            } else if (pairs.length==3) {
                player.set(3,pairs);
            } else {
                card[] flush = isFlush(fullHand);
                if (flush!=null) {
                    player.set(4,flush);
                } else {
                    card[] straight = isStraight(fullHand);
                    if (straight!=null) {
                        player.set(5,straight);
                    } else if (pairs.length==4) {
                        player.set(6,pairs);
                    } else if (pairs.length==5) {
                        player.set(7,pairs);
                    } else if (pairs.length==6) {
                        player.set(8,pairs);
                    } else {
                        player.set(9, Arrays.copyOfRange(fullHand, 0, 5));
                    }
                }
            }
        }
    }
    public card[] cardAnalysisEfficient(player player, card[] house) {
        //Setup
        card[] fullHand = new card[7];
        for (int i = 0; i < 7; i++) {
            if (i < 5) {
                fullHand[i] = house[i];
            } else {
                fullHand[i] = player.hand[i - 5];
            }
        }


        //Finding value
        card[] pairs = findPairs(fullHand);
        if(pairs.length==6){
            player.set(8,pairs);
        }
        if(pairs.length==5){
            player.set(7,pairs);
        }
        if(pairs.length==4){
            player.set(6,pairs);
        }
        heapSortValue(fullHand);

        card[] straight = isStraight(fullHand);
        if(straight!=null){
            player.set(5,straight);
        }
        card[] flush = isFlush(fullHand);
        if (flush != null) {
            if(player.handScore==5){
                fullHand=suitSort(fullHand);
                card[] straightFlush_royalFlush = isStraightFlush(fullHand);
                if(straightFlush_royalFlush!=null){
                    if(straightFlush_royalFlush[0].value==10){
                        player.set(0,straightFlush_royalFlush);
                        return player.bestHand;
                    }
                    player.set(1,straightFlush_royalFlush);
                    return player.bestHand;
                }
            }
            player.set(4,flush);
        }
        if(pairs.length==3){
            player.set(3,pairs);
            return player.bestHand;
        }
        if(pairs.length==2){
            player.set(2,pairs);
            return player.bestHand;
        }
        if(player.handScore==-1){
            player.set(9, Arrays.copyOfRange(fullHand, 0, 5));
        }
        return player.bestHand;
    }

    public double[] chanceOfWin(card c1, card c2, card c3, card c4){
        Date dateStart = new Date();
        long start = dateStart.getTime();
        card[] hand = new card[4]; //0-1 is player 1       2-3 is player 2
        hand[0]=c1;
        hand[1]=c2;
        hand[2]=c3;
        hand[3]=c4;
        player p1 = new player(new card[]{hand[0], hand[1]});
        player p2 = new player(new card[]{hand[2], hand[3]});
        int length = 5;
        long total = 0;
        long ties = 0;
        card[] arr = new card[length + hand.length]; //I override A LOT of things in here
        System.arraycopy(hand, 0, arr, length, hand.length);
        a: for (int a = 2; a < 54; a++) { //Takes around 310 ms
            for (int i = 0; i < 4; i++) {
                if(a==hand[i].totalValue){
                    continue a;
                }
            }
            arr[0]=new card(a);
            b: for (int b = a+1; b < 54; b++) {
                for (int i = 0; i < 4; i++) {
                    if(b==hand[i].totalValue){
                        continue b;
                    }
                }
                arr[1]=new card(b);
                c: for (int c = b+1; c < 54; c++) {
                    for (int i = 0; i < 4; i++) {
                        if(c==hand[i].totalValue){
                            continue c;
                        }
                    }
                    arr[2]=new card(c);
                    d: for (int d = c+1; d < 54; d++) {
                        for (int i = 0; i < 4; i++) {
                            if(d==hand[i].totalValue){
                                continue d;
                            }
                        }
                        arr[3]=new card(d);
                        e: for (int e = d+1; e < 54; e++) {
                            for (int i = 0; i < 4; i++) {
                                if(e==hand[i].totalValue){
                                    continue e;
                                }
                            }
                            arr[4]=new card(e); //takes around 330 ms

                            cardAnalysisEfficient(p1,arr);
                            cardAnalysisEfficient(p2,arr);
                            total++;

                            // 205476480

                            if(p1.handScore<p2.handScore){
                                p1.playerTotal++;
                            }
                            else if(p1.handScore>p2.handScore){
                                p2.playerTotal++;
                            }
                            else{
                                int result = whoWins(p1,p2);
                                if(result==1){
                                    p1.playerTotal++;
                                }
                                else if(result==2){
                                    p2.playerTotal++;
                                }
                                else {
                                    ties++;
                                }
                            }
                            p1.handScore=-1;
                            p2.handScore=-1;
                        }
                    }
                }
            }
        }
//        System.out.println("Player 1 won " + (double) p1.playerTotal/total*100 + "% of the time and Player 2 won " + (double) p2.playerTotal/total*100 + "% of the time and they split the pot " + (double) ties/total*100 + "% of the time total:" + total);
//        Date dateEnd = new Date();
//        long end = dateEnd.getTime();
//        System.out.println("This took " + (end-start) + " milliseconds or " + ((end-start)/1000) + " seconds");
        return new double[] {ties, p1.playerTotal, p2.playerTotal};
    }
}
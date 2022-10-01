public class possibleCompQuestion {
    public static void main(String[] args) {
        int[] deck = {0,1,2,3,4,5,6,7,8,9};
        int length = 3;
        int increment = 10;
        int suitIncrement = 4;
        here: for (int i = 0; i < Math.pow(increment,length); i++) {
            int[] arr = new int[length];
            for (int j = 0; j < length; j++) {
                arr[j]= ((i-(i%(int)Math.pow(increment,length-j-1)))/(int)Math.pow(increment,length-j-1))%increment; //Shut up I know what im doing
            }


            for (int j = 0; j < length-1; j++)
                for (int k = j+1; k < length; k++)
                    if(arr[j]==arr[k]&&arr[j]==arr[k]) continue here;
            for (int j = 0; j < length; j++) {
                System.out.print(arr[j]+", ");
            }
            System.out.println();
        }

    }
}

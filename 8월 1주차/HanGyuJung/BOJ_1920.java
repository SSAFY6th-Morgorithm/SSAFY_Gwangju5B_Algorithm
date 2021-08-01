import java.util.Arrays;
import java.util.Scanner;

public class main1920 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int arr[] = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }
        Arrays.sort(arr);
        int M = scan.nextInt();
        for (int i = 0; i < M; i++) {
            int check = Arrays.binarySearch(arr, scan.nextInt());
            if (check < 0) System.out.println("0");
            else System.out.println("1");

        }
    }
}


   // private static void search(int num, int[] arr){




        //규정 - 메모리초과
//        if(arr.length==0){
//            System.out.println("0");
//            return;
//        }
//        int half = arr.length/2;
//
//        if(arr[half]<num){
//            int[] new_arr=Arrays.copyOfRange(arr, half+1, arr.length);
//            search(num, new_arr);
//        }
//        else if(arr[half]>num){
//            int[] new_arr= Arrays.copyOfRange(arr,0,half);
//            search(num,new_arr);
//        }
//        else if(arr[half]==num){
//            System.out.println("1");
//            return;
//        }


//    }
//}

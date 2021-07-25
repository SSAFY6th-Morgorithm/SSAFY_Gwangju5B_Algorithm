import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

         //퀵소트 알고리즘
//     public static void main(String[] args)  {

//         Scanner scan=new Scanner(System.in);
//         int N=scan.nextInt();
//         int[] arr = new int[N];

//         for(int i=0;i<N;i++){
//             arr[i]=scan.nextInt();
//         }
//      
//         quicksort(arr,0,arr.length-1);


//         for(int data:arr){
//             System.out.println(data);
//         }
//     }
//     public static void quicksort(int[] data, int l, int r){
//         int left = l;
//         int right = r;
//         int pivot = data[(l+r)/2];

//         do{
//             while(data[left] < pivot) left++;
//             while(data[right] > pivot) right--;
//             if(left <= right){
//                 int temp = data[left];
//                 data[left] = data[right];
//                 data[right] = temp;
//                 left++;
//                 right--;
//             }
//         }while (left <= right);

//         if(l < right) quicksort(data, l, right);
//         if(r > left) quicksort(data, left, r);
//     }

}


                  //계수(Counting) 알고리즘
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        boolean[] arr = new boolean[2000001];

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            arr[Integer.parseInt(br.readLine()) + 1000000] = true;
        }

        for(int i = 0; i < arr.length; i++) {
            if(arr[i]) {
                sb.append((i - 1000000)).append('\n');
            }
        }
        System.out.print(sb);
    }





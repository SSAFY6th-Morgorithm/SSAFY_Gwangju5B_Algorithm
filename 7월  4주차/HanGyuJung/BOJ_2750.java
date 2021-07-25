import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {

       //내장 라이브러리 정렬 함수 사용
//         Scanner scan=new Scanner(System.in);
//         int N = scan.nextInt();
//         int[] arr=new int[N];
//         for(int n=0;n<N;n++){
//             arr[n]=scan.nextInt();
//         }
//         Arrays.sort(arr);
//         for(int data:arr){
//             System.out.println(data);
//         }
       
        //삽입 정렬 구현
        
        Scanner scan=new Scanner(System.in);
        int N=scan.nextInt();
        int[] arr = new int[N];

        for(int i=0;i<N;i++){
            arr[i]=scan.nextInt();
        }
        //삽입정렬;
        for(int i = 1; i < N; i++) {
            int target = arr[i];

            int j = i - 1;

            while(j >= 0 && target < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = target;
        }

        for(int data:arr){
            System.out.println(data);
        }
    }
}


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {

        Scanner scan=new Scanner(System.in);
        int N = scan.nextInt();
        int[] arr=new int[N];
        for(int n=0;n<N;n++){
            arr[n]=scan.nextInt();
        }
        Arrays.sort(arr);
        for(int data:arr){
            System.out.println(data);
        }


    }
}

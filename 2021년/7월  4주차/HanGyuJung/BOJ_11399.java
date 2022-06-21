import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scan =new Scanner(System.in);
        int N = scan.nextInt();
        int[] arr = new int[N];
        int wait = 0;
        int sum=0;
        for(int i=0;i<N;i++){
            arr[i]=scan.nextInt();
        }
        Arrays.sort(arr);
        for(int i=0;i<N;i++){
            wait=wait+arr[i];
            sum= sum+wait;
        }
        System.out.println(sum);
    }
}

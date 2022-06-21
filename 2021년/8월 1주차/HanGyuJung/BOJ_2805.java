import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class main2805 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int arr[] = new int[N];
        int want = scan.nextInt();
        for (int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }
        Arrays.sort(arr);
        int end=arr[arr.length-1];
        int start=0;
        int answer=0;
        while (end>=start){
            long sum=0;
            int mid = (end+start)/2;

            for(int i=0;i<arr.length;i++){
                if(arr[i]>mid)
                sum+=arr[i]-mid;
            }
            if(want<=sum){
                start = mid+1;
                answer=mid;
            }
            else{
                end=mid-1;
            }
        }
        System.out.println(answer);
    }
}

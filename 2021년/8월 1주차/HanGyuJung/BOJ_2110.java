import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class main1920 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int c = scan.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = scan.nextInt();
        }

        Arrays.sort(array);

        int left = 1;
        int right = array[n - 1] - array[0];
        int d = 0;
        int ans = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int start = array[0];
            int cnt = 1;

            for (int i = 1; i < n; i++) {
                d = array[i] - start;
                if (mid <= d) {
                    ++cnt;
                    start = array[i];
                }
            }

            if (cnt >= c) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(ans);
    }
}


package Sort;

import java.util.Arrays;
import java.util.Scanner;

public class bj_1427 {

	public static void main(String[] args) {
		// 소트인사이드(삽입정렬-내림차순)

		Scanner scan = new Scanner(System.in);
		int target = 0;

		String x = scan.next();
		int[] div = new int[x.length()];

		for (int i = 0; i < x.length(); i++) {
			div[i] = x.charAt(i) - '0';
		}

		for (int j = 1; j < div.length; j++) {
			target = div[j];
			int k = j - 1;
			while (k >= 0 && target > div[k]) {
				div[k + 1] = div[k];
				k--;
			}
			div[k + 1] = target;
		}

		for (int i = 0; i < div.length; i++) {
			System.out.print(div[i]);
		}
	}

}

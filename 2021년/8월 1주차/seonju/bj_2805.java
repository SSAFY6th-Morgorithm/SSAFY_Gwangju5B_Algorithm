package BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class bj_2805 {
	// 나무의 길이 중 제일 큰 나무의 중간값부터 계산하면서 이진탐색을 이용해 높이의 최대값을 계산했다.
		public static void main(String[] args) {
			// 나무 자르기(이진탐색)
			Scanner scan = new Scanner(System.in);

			int tree = scan.nextInt(); // N(나무 수)
			int need = scan.nextInt(); // M(상근이가 필요한 나무 미터)
			int[] treeh = new int[tree];

			for (int i = 0; i < tree; i++) {
				treeh[i] = scan.nextInt();
			}

			Arrays.sort(treeh);

			int low = 0;
			int high = treeh[tree-1];
			int mid = 0;

			while (low <= high) {
				long sum = 0;			// M의 최대 길이가 20억이기 때문에 합계로 int 자료형은 부족함.
				mid = (low + high) / 2;
				for (int i = 0; i < tree; i++) {
					if(treeh[i]>mid) {
						sum += treeh[i]-mid;
					}
				}
				if(sum>=need) {
					low = mid+1;
				}else if(sum<need){
					high = mid-1;
				}
			}
			System.out.println(high);
		}
}

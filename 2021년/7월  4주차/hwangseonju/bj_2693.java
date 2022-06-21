package Sort;

import java.util.Arrays;
import java.util.Scanner;

public class bj_2693 {

	public static void main(String[] args) {
		// N번째 큰 값 출력하기(선택 정렬)

		int tc = 0; // 테스트 케이스
		int[] num = new int[10];
		int size = num.length;

		Scanner scan = new Scanner(System.in);
		tc = scan.nextInt();

		// 테스트 케이스만큼 돌리기
		for (int i = 0; i < tc; i++) {
			// 값 배열에 넣기
			for (int n = 0; n < size; n++) {
				num[n] = scan.nextInt();
			}

			for (int j = 0; j < size - 1; j++) {
				int min_index = j;
				for (int z = j + 1; z < size; z++) {
					if (num[z] < num[min_index]) {
						min_index = z;
					}
				}
				swap(num, min_index, j);
			}
			System.out.println(num[7]);
		}
	}

	public static void swap(int[] num, int min_index, int j) {
		int temp = num[min_index];
		num[min_index] = num[j];
		num[j] = temp;
	}

}

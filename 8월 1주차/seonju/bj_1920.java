package BinarySearch;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class bj_1920 {

	public static void main(String[] args) {
		// 수 찾기(이진탐색)
		Scanner scan = new Scanner(System.in);

		int A = scan.nextInt();
		int[] numA = new int[A]; // numA배열(2번째줄 입력)
		for (int i = 0; i < A; i++) {
			numA[i] = scan.nextInt();
		}

		int B = scan.nextInt();
		int[] numB = new int[B]; // numB배열(4번째줄 입력)
		for (int i = 0; i < B; i++) {
			numB[i] = scan.nextInt();
		}

		Arrays.sort(numA); // 오름차순으로 numA배열 정렬하기

		for (int i = 0; i < B; i++) { // numB배열 값 개수만큼 탐색하기

			int mid = 0; // numA배열의 중앙 인덱스값
			int low = 0; // numA배열에서 탐색할 첫번째 인덱스값
			int high = A - 1; // numA배열에서 탐색할 마지막 인덱스값

			while (true) {
				mid = (low + high) / 2;
				if (numB[i] == numA[mid]) {
					System.out.println("1");
					break;
				} else if (numB[i] > numA[mid]) { // numA[mid+1] ~ numA[A] 탐색(오른쪽 탐색)
					low = mid + 1;
				} else if (numB[i] < numA[mid]) { // numA[0] ~ num[mid-1] 탐색(왼쪽 탐색)
					high = mid - 1;
				}
				if (low > high) { // while 조건문에 넣으면 for문때문에 0을 출력할 위치가 없음
					System.out.println("0");
					break;
				}
			}

		}

	}

}

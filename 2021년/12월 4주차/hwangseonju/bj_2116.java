package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_2116 {

	static int dice; // 주사위 개수
	static int[][] num;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int max; // 최대값
	static int sum;	// 합
	static boolean[] check;	// 위, 아래면 확인

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 주사위 쌓기
		dice = Integer.parseInt(br.readLine());
		num = new int[dice][6];

		for (int r = 0; r < dice; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 6; c++) {
				num[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		max = Integer.MIN_VALUE;

		// 1번 주사위 - A ~ F까지
		for (int i = 0; i < 6; i++) {
			check = new boolean[7];
			int start = 1;	// 주사위 2번 ~ dice번까지
			// 마주보는면(위,아래) 찾기
			int t = 0;
			switch (i) {
			case 0:
				t = 5;
				break;
			case 1:
				t = 3;
				break;
			case 2:
				t = 4;
				break;
			case 3:
				t = 1;
				break;
			case 4:
				t = 2;
				break;
			default:
				t = 0;
			}
			
			// 옆면 중 가장 큰값 찾기
			check[num[0][i]] = true;
			check[num[0][t]] = true;
			for (int n = 6; n > 0; n--) {
				if (!check[n]) {
					sum = n;
					break;
				}
			}
			search(i, num[0][t], start);
		}

		System.out.println(max);
	}

	static void search(int b, int top, int s) {
		Arrays.fill(check, false);

		// 주사위 dice번까지 찾았을때
		if (s == dice) {
			max = Math.max(max, sum);
			return;
		}

		int t = 0;
		for (int i = 0; i < 6; i++) {
			// 아래 주사위의 윗면값과 위 주사위의 아랫면값이 같을 경우
			if (num[s][i] == top) {
				switch (i) {
				case 0:
					t = 5;
					break;
				case 1:
					t = 3;
					break;
				case 2:
					t = 4;
					break;
				case 3:
					t = 1;
					break;
				case 4:
					t = 2;
					break;
				default:
					t = 0;
				}
				check[num[s][i]] = true;
				check[num[s][t]] = true;
				break;
			}
		}

		// 옆면 중 가장 큰 값 찾기
		for (int n = 6; n > 0; n--) {
			if (!check[n]) {
				sum += n;
				break;
			}
		}
		search(t, num[s][t], s + 1);
	}
}

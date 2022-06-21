package algo;

import java.io.*;
import java.util.*;

/**
 * 11472	84
 * @author CHO
 * @see https://www.acmicpc.net/problem/1722
 * @category ����
 * 
 */
public class BOJ_1722_�����Ǽ��� {

	static StringTokenizer st;
	static int N;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());

		long[] fac = new long[N];
		fac[0] = 1L;
		for (int i = 1; i < N; i++) {
			fac[i] = i * fac[i - 1];
		} // fac �迭 ����
//		System.out.println(Arrays.toString(fac));

		boolean[] flag = new boolean[N + 1];

		if (num == 1) {
			long k = Long.parseLong(st.nextToken());
			// k��° ���� ���ϱ�
			// ���° �ڸ���
			for (int j = 1; j < N + 1; j++) {
				long f = fac[N - j]; // � �ǳʶٴ���
				// �ش� �ڸ������� k���ϱ�
				for (int i = 1; i < N + 1; i++) {
					if (!flag[i]) {
						if (k - f <= 0) {
							// k�� ��Ƴ���
							flag[i] = true;
							sb.append(i + " ");
							break;
						} else {
							k -= f;
							continue;
						}
					}
				}
				// ��Ƴ��� k
			}
			System.out.println(sb);
		} else if (num == 2) {
			long result = 1; // ������ 1���� �����ϱ� 0 x
			arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			// ������ �־�����, ���°���� ���ϱ�
			for (int j = 0; j < N; j++) {
				int cur = arr[j];
				Long f = fac[N - 1 - j]; // � �ǳʶٴ���
				// �ش� �ڸ������� k���ϱ�
				// 1���� N���� ����
				for (int n = 1; n < N + 1; n++) {
					if (!flag[n]) {
						if (cur == n) {
							flag[n] = true;
							break;
						} else {
							result += f;
						}
					}
				}
			}
			System.out.println(result);
		}

	}
}

import java.io.*;
import java.util.*;

/**
 * 17392	1252
 * @author CHO
 * @see https://www.acmicpc.net/problem/20437
 * @category 문자열
 */
public class BOJ_20437_문자열게임2 {
	static StringBuilder sb = new StringBuilder();
	static int min, max;

	public static void main(String[] args) throws IOException {
		long before=System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			min = Integer.MAX_VALUE; // 3번 결과값
			max = Integer.MIN_VALUE; // 4번 결과값
			String str = br.readLine();
			int K = Integer.parseInt(br.readLine());// 입력완료
			cal(str, K);
			if (min == Integer.MAX_VALUE) {
				sb.append(-1 + "\n");
			} else {
				if (max == Integer.MIN_VALUE) {
					sb.append(min + " " + -1 + "\n");
				} else {
					sb.append(min + " " + max + "\n");
				}
			}
		}
		System.out.println(sb);
		long afterTime = System.currentTimeMillis();
		System.out.println((afterTime-before)/1000);
	}

	private static void cal(String str, int K) {
		char cur;
		for (int f = 0; f < str.length(); f++) {
			int len = 0; // 지금까지의 문자열 길이 저장
			int cnt = 0; // K개인지 아닌지 확인하기 위해
			cur = str.charAt(f); // 첫번째 문자 저장
			for (int s = f; s < str.length(); s++) {
				len++;
				if (cur == str.charAt(s)) {
					cnt++;
					if (cnt == K) {
						min = min > len ? len : min;
						max = max < len ? len : max;
						break;
					}
				}
			}
		}
	}
}

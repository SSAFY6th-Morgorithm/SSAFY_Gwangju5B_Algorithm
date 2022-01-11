import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20437_문자열게임2 {
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(input.readLine());
		for (int t = 0; t < T; t++) {
			String str = input.readLine();
			int k = Integer.parseInt(input.readLine());
			doStringGame(str, k);
		}
		System.out.println(answer);
	}

	private static void doStringGame(String str, int k) {
		// TODO Auto-generated method stub
		if (k == 1) {
			answer.append(1).append(" ").append(1).append("\n");
			return;
		}
		int shortestLength = Integer.MAX_VALUE;
		int longestLength = Integer.MIN_VALUE;
		int[] count = new int[26];
		int[] start = new int[26];
		int length = str.length();
		for (int i = 0; i < length; i++) {
			char currentChar = str.charAt(i);
			int charIndex = currentChar - 'a';
			if (count[charIndex]++ == 0) {
				start[charIndex] = i;
			}
			if (count[charIndex] == k) {
				int currentLength = i - start[charIndex] + 1;
				if (shortestLength > currentLength)
					shortestLength = currentLength;
				if (longestLength < currentLength)
					longestLength = currentLength;
				// 시작 인덱스 업데이트
				count[charIndex]--;
				for (int j = start[charIndex] + 1; j < length; j++) {
					if (str.charAt(j) == currentChar) {
						start[charIndex] = j;
						break;
					}
				}
			}

		}
		if (shortestLength == Integer.MAX_VALUE) {
			answer.append(-1).append("\n");
			return;
		}
		answer.append(shortestLength).append(" ").append(longestLength).append("\n");

	}
}

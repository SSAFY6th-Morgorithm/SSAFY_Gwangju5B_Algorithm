import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9935_문자열폭발 {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		char[] str = input.readLine().toCharArray();
		char[] explodableStr = input.readLine().toCharArray();
		String answer = explodeString(str, explodableStr);
		System.out.println(answer);
	}

	private static String explodeString(char[] str, char[] explodableStr) {

		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < str.length; i++) {
			answer.append(str[i]);
			if (answer.length() >= explodableStr.length) {
				boolean exploded = true;
				for (int j = answer.length() - explodableStr.length, k = 0; j < answer.length(); j++) {
					if (answer.charAt(j) != explodableStr[k++]) {
						exploded = false;
						break;
					}
				}
				if (exploded) {
					answer.setLength(answer.length() - explodableStr.length);
				}
			}
		}

		return answer.toString().equals("") ? "FRULA" : answer.toString();
	}
}

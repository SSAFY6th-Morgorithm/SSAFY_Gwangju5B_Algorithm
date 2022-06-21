package permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class bj_2529 {

	static int k; // 부등호 개수
	static char[] sign; // 입력받은 부등호 넣을 배열
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static List<String> list = new ArrayList<String>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 부등호(순열)
		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		sign = new char[k];
		for (int i = 0; i < sign.length; i++) {
			sign[i] = st.nextToken().charAt(0);
		}

		permutation(k + 1, new int[k + 1], new boolean[10]);
		Collections.sort(list); // 리스트 오름차순으로 정렬
		sb.append(list.get(list.size() - 1)).append("\n"); // 정렬된 리스트의 마지막 값(최대값)
		sb.append(list.get(0)); // 정렬된 리스트의 처음 값(최소값)
		System.out.println(sb);
	}

	static void permutation(int n, int[] choosed, boolean[] visited) {
		if (n == 0) {
			Chek(choosed);
			return;
		}
		for (int i = 0; i <= 9; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				choosed[choosed.length - n] = i;
				permutation(n - 1, choosed, visited);
				visited[i] = false;
			}
		}
	}

	static void Chek(int[] check) { // 입력받은 부등호에 맞는 배열인지 확인하기
		String str = "";
		for (int j = 0; j < k; j++) {
			if (sign[j] == '<') {
				if (check[j] > check[j + 1]) {
					break;
				}
			} else {
				if (check[j] < check[j + 1]) {
					break;
				}
			}
			if (j == k - 1) {
				for (int i = 0; i < check.length; i++) {
					str += check[i];
				}
				list.add(str);
			}
		}
		return;
	}

}

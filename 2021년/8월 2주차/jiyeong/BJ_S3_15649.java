package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * N과 M (1)*/

public class BJ_S3_15649 {

	static int N,R;
	static int[] numbers;
	static boolean[] isSelected;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		numbers = new int[R];
		isSelected = new boolean[N + 1];

		permutation(0);
		System.out.println(sb);

	}

	private static void permutation(int cnt) {
		if (cnt == R) {
			for (int i = 0; i < R; i++) {
				sb.append(numbers[i]+" ");
				//System.out.print(numbers[i]+ " ");
			}
			sb.append("\n");
			//System.out.println();
			return;
		}

		// 가능한 모든 수 시도
		for (int i = 1; i <= N; i++) {
			if (isSelected[i])
				continue; // 사용중인 수라면 다음 수로

			numbers[cnt] = i;
			isSelected[i] = true;

			// 다음 자리 순열뽑기
			permutation(cnt + 1);
			isSelected[i] = false;
		}
	}

}

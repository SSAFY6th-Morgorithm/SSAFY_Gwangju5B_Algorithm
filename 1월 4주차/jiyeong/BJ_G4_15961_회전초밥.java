package jan25_twoPointer;

import java.io.*;
import java.util.*;

/**
 * 쿠폰때문에 종류+1이면 sushi배열에서 +1 안함 -> 그냥 count만 올려줌
 * @author yun
 *
 */
public class BJ_G4_15961_회전초밥 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 벨트접시
		int d = Integer.parseInt(st.nextToken()); // 초밥
		int k = Integer.parseInt(st.nextToken()); // 연속접시
		int c = Integer.parseInt(st.nextToken()); // 쿠폰

		int[] arr = new int[N];
		int[] sushi = new int[d + 1];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int max = 0;

		for (int i = 0; i < k; i++) {
			if (sushi[arr[i]] == 0) {
				max++; // 처음 확인한 접시들에서 초밥 종류(초기화)
			}
			sushi[arr[i]]++;
		}

		int cur = max; 
		if (sushi[c] == 0) {
			max++; // 쿠폰으로 종류+1 한거니까 cur에는 포함x
		}

		int start = k;
		while (true) {

			sushi[arr[(start - k) % N]]--;
			if (sushi[arr[(start - k) % N]] == 0)
				cur--;

			if (sushi[arr[start % N]] == 0) {
				cur++;
			}
			sushi[arr[start % N]]++;
			
			
			if (sushi[c] == 0) {
				max = Math.max(max, cur+1); // 쿠폰때문에 +1 (실제 cur에는 반영하면 안됨)
			}else {
				max = Math.max(max, cur);				
			}

			start++;
			if (start == (N - 1) + k) // 한바퀴 돌면 끝
				break;
		}

		System.out.println(max);
	}

}

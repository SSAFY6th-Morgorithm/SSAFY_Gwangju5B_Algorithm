package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2559 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N+1];
		int[] pSum = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			pSum[i] = pSum[i-1]+arr[i];
		}
		int max = Integer.MIN_VALUE;
		for(int i=K;i<=N;i++) {
			max = Math.max(max, pSum[i]-pSum[i-K]);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(max);
		System.out.println(max);
	}

}

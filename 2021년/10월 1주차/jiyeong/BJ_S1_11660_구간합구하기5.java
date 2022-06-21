package prefix_sum;

import java.io.*;
import java.util.*;

public class BJ_S1_11660_구간합구하기5 {
	
	static int N,M;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				sum += Integer.parseInt(st.nextToken());
				arr[i][j] = arr[i-1][j]+sum; 
			}
		}
		
//		for(int[] a: arr)
//			System.out.println(Arrays.toString(a));
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			
			int sum = arr[r2][c2] - arr[r1-1][c2] - arr[r2][c1-1] + arr[r1-1][c1-1];
			sb.append(sum).append("\n");
		}
	
		System.out.println(sb);
	} 
}

package prefix_sum;

import java.io.*;
import java.util.*;

public class BJ_G5_20002_사과나무 {
	
	static int N;
	static int[][] farm;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		farm = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				sum += Integer.parseInt(st.nextToken());
				farm[i][j] = farm[i-1][j]+sum; 
			}
		}
		
//		for(int[] a: farm)
//			System.out.println(Arrays.toString(a));
		
		int max = Integer.MIN_VALUE;
		for (int sq = 0; sq < N; sq++) {
			for (int r = 1; r <= N-sq; r++) {
				for (int c = 1; c <= N-sq; c++) {
					int sum = farm[r+sq][c+sq] - farm[r-1][c+sq] - farm[r+sq][c-1]+farm[r-1][c-1];
					max = Math.max(max, sum);
				}
			}
		}
		
		System.out.println(max);

	}
}

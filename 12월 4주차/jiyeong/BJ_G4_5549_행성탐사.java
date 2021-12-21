package simulation;

import java.io.*;
import java.util.*;

public class BJ_G4_5549_행성탐사 {

	static int N, M, K;
	static char[][] map;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		map = new char[M+1][N+1];
		int[][] J = new int[M+1][N+1];
		int[][] O = new int[M+1][N+1];
		int[][] I = new int[M+1][N+1];

		for (int i = 1; i <= M; i++) {
			String s = br.readLine();
			for (int j = 1; j <= N; j++) {
				map[i][j] = s.charAt(j-1);
				
				J[i][j] = J[i-1][j] + J[i][j-1] - J[i-1][j-1];
				I[i][j] = I[i-1][j] + I[i][j-1] - I[i-1][j-1];
				O[i][j] = O[i-1][j] + O[i][j-1] - O[i-1][j-1];
				
				if(map[i][j]=='J') J[i][j]++;
				else if(map[i][j]=='O') O[i][j]++;
				else I[i][j]++;
				
			}
		}
		
		for (int i = 0; i < K; i++) {
			//JOI
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			int er = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());
			
			int answerJ =  J[er][ec] - J[sr-1][ec] - J[er][sc-1]  + J[sr-1][sc-1]; 
			int answerO =  O[er][ec] - O[sr-1][ec] - O[er][sc-1]  + O[sr-1][sc-1]; 
			int answerI =  I[er][ec] - I[sr-1][ec] - I[er][sc-1]  + I[sr-1][sc-1];
			
			sb.append(answerJ+" "+answerO+" "+answerI+"\n");
		}
		
		System.out.println(sb);
		

	}

}

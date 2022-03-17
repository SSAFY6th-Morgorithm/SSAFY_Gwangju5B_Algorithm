package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1495_array {

	static int N;
	static int S;
	static int M;
	static int[] diff;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		// 기타리스트 - 배열로 탐색(최대 : (1000*50) + 1000)
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		diff = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int v=1; v<=N; v++) {
			diff[v] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][] music = new boolean[N+1][M+1];
		music[0][S] = true;
		
		for(int n=0; n<N; n++) {
			for(int m=0; m<=M; m++) {
				if(music[n][m]) {
					if(m+diff[n+1]<=M && m+diff[n+1]>=0)
						music[n+1][m+diff[n+1]] = true;
					if(m-diff[n+1]>=0 && m-diff[n+1]<=M)
						music[n+1][m-diff[n+1]] = true;
				}
			}
		}
		
		int max = -1;
		for(int i=M; i>=0; i--) {
			if(music[N][i]) {
				max = i;
				break;
			}
		}
		System.out.println(max);
	}
}

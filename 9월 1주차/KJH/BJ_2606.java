package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2606 {
	static int N,P,cnt;
	static int[][] map;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		visited = new boolean[N+1];
		for(int i=0;i<P;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			map[n][m] = map[m][n] = 1;
		}
		cnt = 0;
		solve(1);
		StringBuilder sb = new StringBuilder();
		sb.append(cnt);
		System.out.println(sb);
	}
	
	static void solve(int n) {
		visited[n] = true;
		for(int i=1;i<=N;i++) {
			if(map[n][i]==1 && !visited[i]) {
				cnt++;
				solve(i);
			}
		}
	}

}

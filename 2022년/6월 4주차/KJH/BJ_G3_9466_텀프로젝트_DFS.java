package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G3_9466_텀프로젝트_DFS {
	static int N,cnt;
	static int[] stNum;
	static boolean[] visited,checked;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			N = Integer.parseInt(br.readLine());
			stNum = new int[N+1];
			visited = new boolean[N+1];
			checked = new boolean[N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++) {
				stNum[i] = Integer.parseInt(st.nextToken());
			}
			
			cnt = 0;
			for(int i=1;i<=N;i++) {
				if(!checked[i]) {
					DFS(i);
				}
			}
			sb.append(N-cnt).append("\n");
		}
		System.out.println(sb);

	}
	
	static void DFS(int n) {
		if(visited[n]) { // 싸이클임.
			checked[n] = true;
			cnt++;
		}
		else { // 첫방문임.
			visited[n] = true;
		}
		
		int next = stNum[n];
		if(!checked[next]) { // 검사 안한곳이면 재귀
			DFS(next);
		}
			
		checked[n] = true; // 재귀 끝나면 방문체크 풀고 검사체크 해줌.
		visited[n] = false;
	}

}

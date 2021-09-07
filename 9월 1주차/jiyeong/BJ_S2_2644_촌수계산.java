package graph;

import java.io.*;
import java.util.*;

//bfs
public class BJ_S2_2644_촌수계산 {
	
	static int N,p1,p2,answer;
	static int arr[][];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		p1 = Integer.parseInt(st.nextToken());
		p2 = Integer.parseInt(st.nextToken());
		arr = new int [N+1][N+1];
		
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			arr[u][d] = 1;
			arr[d][u] = 1;
		}
		bfs(p1, p2);
		System.out.println(answer);
		
	}
	
	private static void bfs(int p1, int p2) {
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new LinkedList<Integer>();
		visited[p1] = true;
		q.add(p1);
		
		answer = 0;
		while(!q.isEmpty()) {
			int qsize = q.size();
			for (int i = 0; i < qsize; i++) {
				int cur = q.poll();
				if(cur == p2) return;
				for (int j = 1; j <= N; j++) {
					if(arr[cur][j]==1 && !visited[j]) {
						visited[j] = true;
						q.add(j);
					}
				}
			}
			answer++;
		}
		answer = -1;
		
	}

}

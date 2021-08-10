package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_S2_1260 {
	static int[][] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 정점
		int M = Integer.parseInt(st.nextToken()); // 간선
		int V = Integer.parseInt(st.nextToken()); // 시작 정점
		
		arr = new int[N+1][N+1];
		
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			arr[m][n] = 1;
			arr[n][m] = 1;
		}
		
		// DFS
		visited = new boolean[N+1];
		dfs(V);
		
		System.out.println();
		
		// BFS
		visited = new boolean[N+1];
		bfs(V);
		
		
	}
	
	// DFS - 재귀 (or Stack)
	private static void dfs(int v) {
		visited[v] = true;
		System.out.print(v+" ");
		
		if(v == arr.length) return;
		
		for (int i = 1; i < arr.length; i++) {
			if(isVisited(v,i)) {
				dfs(i);
			}
		}
	}
	

	// BFS - Queue
	private static void bfs(int v) {
		Queue<Integer> queue  = new LinkedList<Integer>();
		
		queue.add(v);
		visited[v] = true;
		System.out.print(v+" ");
		
		while(!queue.isEmpty()) {
			int temp = queue.peek();
			queue.poll();
			for (int i = 1; i < arr.length; i++) {
				if(isVisited(temp,i)) {
					queue.add(i);
					visited[i] = true;
					System.out.print(i+" ");
				}
			}
		}
		
	}
	
	private static boolean isVisited(int v, int i) {
		return arr[v][i] == 1 && visited[i] == false;
	}

	

}

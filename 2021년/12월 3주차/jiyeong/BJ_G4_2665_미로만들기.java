package dijkstra;

import java.io.*;
import java.util.*;

public class BJ_G4_2665_미로만들기 {
	
	static class Node implements Comparable<Node>{
		int r,c,cost;

		public Node(int r, int c, int cost) {
			super();
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	static int N;
	static int[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				int a;
				if(s.charAt(j)-'0' == 0) a = 1;
				else a = 0;
				map[i][j] = a;
			}
		}
		
		dijkstra();

	}
	
	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[][] dist = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		dist[0][0] = 0;
		pq.offer(new Node(0,0,0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.r == N-1 && cur.c == N-1) {
				System.out.println(cur.cost);
				return;
			}
			
			for (int d = 0; d < deltas.length; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];
				
				if(isIn(nr,nc) && dist[nr][nc] > dist[cur.r][cur.c] + map[nr][nc]) {
					dist[nr][nc] = dist[cur.r][cur.c] + map[nr][nc];
					pq.offer(new Node(nr,nc,dist[nr][nc]));
				}
			}
		}
	}
	
	private static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}

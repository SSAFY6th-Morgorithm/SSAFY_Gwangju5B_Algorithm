package dijkstra;


import java.io.*;
import java.util.*;

public class BJ_G4_1261_알고스팟 {
	
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
	
	static int N,M;
	static int[][] map, dist;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dist = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		
		dijkstra();
		
	}
	
	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0,0,0));
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		dist[0][0] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.r == N-1 && cur.c == M-1) {
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
		return r>=0 && r<N && c>=0 && c<M; 
	}
}

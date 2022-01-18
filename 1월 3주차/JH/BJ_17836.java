package week2022_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17836 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,T;
	static int[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	static String answer;
	
	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		map[N-1][M-1] = Integer.MAX_VALUE;
//		answer = "";
		bfs();
//		answer = map[N-1][M-1] == Integer.MAX_VALUE ? "Fail" :map[N-1][M-1]+1+"";
		System.out.println(map[N-1][M-1] == Integer.MAX_VALUE ? "Fail" :map[N-1][M-1]+1+"");
	}

	private static void bfs() {
		
		boolean[][][] visited = new boolean[N][M][2];
		Queue<Node> Q = new LinkedList<>();
		Q.add(new Node(0, 0, 0, 0));
		visited[0][0][0] = true;
		
		while(!Q.isEmpty()) {
			
			Node now = Q.poll();
			
			if(map[now.r][now.c] == 2) {
				now.g = 1;
			}
			if(now.distance == T) {
				continue;
			}
			for(int d=0; d<4; d++) {
				int nr = now.r + deltas[d][0];
				int nc = now.c + deltas[d][1];
				
				if(isIn(nr,nc)) {
					
					if(nr == N-1 && nc == M-1) {
						map[N-1][M-1] = Math.min(map[N-1][M-1], now.distance);
						continue;
					}
					if(visited[nr][nc][now.g]) {
						continue;
					}
					if(now.g == 1 && map[nr][nc] == 1) {
						//칼이 있으면 부술 수 있음
						visited[nr][nc][now.g] = true;
						Q.add(new Node(nr, nc, now.g, now.distance+1));
						continue;
					}
					if(map[nr][nc] == 0 || map[nr][nc] == 2) {
						visited[nr][nc][now.g] = true;
						Q.add(new Node(nr, nc, now.g, now.distance+1));
					}
					
					
				}
			}
			
			
		}
		
		
		
	}
	
	
	
	private static boolean isIn(int nr, int nc) {
		return 0 <= nr && nr < N && 0 <= nc && nc < M;
	}



	static class Node{
		int r,c;
		int g;
		int distance;
		
		public Node(int r, int c, int g, int distance) {
			super();
			this.r = r;
			this.c = c;
			this.g = g;
			this.distance = distance;
		}
		
		
	}

}

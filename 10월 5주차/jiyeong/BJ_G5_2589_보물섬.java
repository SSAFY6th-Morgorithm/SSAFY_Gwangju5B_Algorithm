import java.io.*;
import java.util.*;

public class BJ_G5_2589_보물섬 {
	
	static class Pos{
		int r,c,dist;
		
		public Pos(int r, int c, int dist) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	
	static int R,C;
	static int[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	static boolean[][] visited;
	static int max = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 'L') { // 모든 땅을 검색
					visited	= new boolean[R][C];
					bfs(i,j);
				}
			}
		}
		System.out.println(max);
	}

	private static void bfs(int r, int c) {
		Queue<Pos> q = new LinkedList<>();
		visited[r][c] = true;
		q.add(new Pos(r,c,0));
		
		int result = 0;
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];
				
				if(isIn(nr,nc) && map[nr][nc] == 'L' && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new Pos(nr,nc,cur.dist+1)); 
					result = Math.max(result, cur.dist+1); // 각 땅마다 최대 길이
				}
			}
		}
		
		max = Math.max(result, max); // 최종 최대길이
	}
	
	public static boolean isIn(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}
}

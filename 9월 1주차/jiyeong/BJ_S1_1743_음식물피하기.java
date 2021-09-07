package graph;

import java.io.*;
import java.util.*;

// dfs
public class BJ_S1_1743_음식물피하기 {

	static int R,C,K,cnt;
	static int[][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			map[r][c] = 1;
		}
		
		/*for(int[] row : map) {
			for(int a : row)
				System.out.print(a+" ");
			System.out.println();
		}*/
		
		int answer = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c] == 1 && !visited[r][c]) {
					visited[r][c]= true;
					cnt = 1;
					dfs(r,c);
					answer = Math.max(answer, cnt);
				}
			}
		}
		
		System.out.println(answer);
	}
	
	private static void dfs(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r+deltas[d][0];
			int nc = c+deltas[d][1];
			
//			if(!isIn(nr,nc)) continue;
//			if(visited[nr][nc]) continue;
//			if(map[nr][nc] == 0) continue;
			
			if(isIn(nr,nc) && map[nr][nc]==1 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				cnt++;
				dfs(nr, nc);
			}
			
		}
	}
	
	private static boolean isIn(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}

}

package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class bj_2667 {

	static int N;	// 지도의 크기
	static int[][] map;
	static boolean[][] visited;
	static int count = 1;
	static int total = 0;	//단지수
	static List<Integer> list = new ArrayList<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 단지 번호붙이기
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int r=0; r<N; r++) {
			String str = br.readLine();
			for(int c=0; c<N; c++) {
				map[r][c] = str.charAt(c)-'0';
			}
		}
	
		visited = new boolean[N][N];
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c]==1 && visited[r][c]==false) {
					dfs(r, c);
					list.add(count);
					count=1;
				}
			}
		}
		
		Collections.sort(list);
		
		sb.append(list.size()).append("\n");
		for(int l=0; l<list.size(); l++) {
			sb.append(list.get(l)).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int deltas[][] = {{-1,0},{1,0},{0,-1},{0,1}};	// 상하좌우
	static void dfs(int r, int c) {
		visited[r][c]=true;
		
		for(int d=0; d<4; d++) {
			int nr=r+deltas[d][0];
			int nc=c+deltas[d][1];
			
			if(isIn(nr, nc) && map[nr][nc]==1 && visited[nr][nc]==false) {
				visited[nr][nc]=true;
				dfs(nr, nc);
				count++;
			}
		}
		return;
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}

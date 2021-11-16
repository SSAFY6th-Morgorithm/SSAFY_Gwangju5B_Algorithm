package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17836 {
	static class Pos{
		int x,y,cnt;
		public Pos(int x,int y,int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	static int N,M,T,res;
	static int[][] map;
	static boolean[][] visited;
	static int[][] deltas = {{0,1},{0,-1},{1,0},{-1,0}};
	static Queue<Pos> q = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		res = Integer.MAX_VALUE;
		BFS();
		
		StringBuilder sb = new StringBuilder();
		if(res<=T)
			sb.append(res);
		else
			sb.append("Fail");
		System.out.println(sb);

	}
	
	static void BFS() {
		visited[0][0] = true;
		q.add(new Pos(0,0,0));
		
		while(!q.isEmpty()) {
			Pos t = q.poll();
			
			if(map[t.x][t.y]==2) {
				res = Math.abs((N-1)-t.x) + Math.abs((M-1)-t.y) + t.cnt;
			}
			
			if(t.x==N-1 && t.y==M-1) {
				res = Math.min(res, t.cnt);
				break;
			}
			
			for(int i=0;i<4;i++) {
				int nx = t.x+deltas[i][0];
				int ny = t.y+deltas[i][1];
				
				if(isIn(nx,ny) && !visited[nx][ny] && map[nx][ny]!=1) {
					visited[nx][ny] = true;
					q.add(new Pos(nx,ny,t.cnt+1));
				}
			}
		}
	}
	
	static boolean isIn(int x,int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}

}

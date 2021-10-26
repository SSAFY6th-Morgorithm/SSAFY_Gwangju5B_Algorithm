package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14940 {
	static class Pos{
		int x,y;
		public Pos(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N,M;
	static int[][] map,res;
	static boolean[][] visited;
	static Queue<Pos> q = new LinkedList<>();
	static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		res = new int[N][M];
		visited = new boolean[N][M];
		int[] start = new int[2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					start[0] = i;
					start[1] = j;
				}
			}
		}
		solve(new Pos(start[0],start[1]));

	}

	static void solve(Pos p) {
		q.clear();
		visited[p.x][p.y]= true;
		q.add(p);
		while(!q.isEmpty()) {
			Pos t = q.remove();
			int x = t.x;
			int y = t.y;
			for(int i=0;i<4;i++) {
				int nx = x+dir[i][0];
				int ny = y+dir[i][1];
				if(isIn(nx,ny) && !visited[nx][ny]) {
					if(map[nx][ny]==1) {
						q.add(new Pos(nx,ny));
						visited[nx][ny] = true;
						res[nx][ny] = res[x][y]+1;
					}
				}
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(!visited[i][j] && map[i][j]==1)
					res[i][j] = -1;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				sb.append(res[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean isIn(int x,int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
}

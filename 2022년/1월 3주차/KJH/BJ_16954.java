package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16954 {
	static class Pos{
		int x,y;
		public Pos(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int[][] dir = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1},{0,0}};
	static char[][] map = new char[8][8];
	static Queue<Pos> wall = new LinkedList<>();
	static boolean isOk = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<8;i++)
			map[i] = br.readLine().toCharArray();
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(map[i][j]=='#')
					wall.add(new Pos(i,j));
			}
		}
		
		bfs();

		StringBuilder sb = new StringBuilder();
		if(isOk)
			sb.append(1);
		else
			sb.append(0);
		System.out.println(sb);
	}
	
	static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(7,0));
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0;s<size;s++) {
				Pos n = q.poll();
				int x = n.x;
				int y = n.y;
				
				if(map[x][y]=='#')
					continue;
			
				for(int i=0;i<9;i++) {
					int nx = x+dir[i][0];
					int ny = y+dir[i][1];
					if(isIn(nx,ny) && map[nx][ny]=='.') {
						q.add(new Pos(nx,ny));	
						if(nx==0 && ny==7) {
							isOk = true;
							return;
						}
					}
				}
			}

			for(int i=0;i<8;i++) {
				Arrays.fill(map[i], '.');
			}
			int w = wall.size();
			for(int i=0;i<w;i++) {
				Pos tmp = wall.poll();
				if(isIn(tmp.x+1,tmp.y)) {
					map[tmp.x+1][tmp.y] = '#';
					wall.add(new Pos(tmp.x+1,tmp.y));
				}
			}
		}
	}

	static boolean isIn(int x,int y) {
		return x>=0&&x<8&&y>=0&&y<8;
	}
}

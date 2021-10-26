package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16469 {
	static class Pos{
		int x,y;
		public Pos(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int R,C;
	static int[][] a,b,c,res;
	static boolean[][] map,aVisited,bVisited,cVisited;
	static int[] ap,bp,cp;
	static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
	static Queue<Pos> q = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		a = new int[R+1][C+1];
		b = new int[R+1][C+1];
		c = new int[R+1][C+1];
		res = new int[R+1][C+1];
		map = new boolean[R+1][C+1];
		aVisited = new boolean[R+1][C+1];
		bVisited = new boolean[R+1][C+1];
		cVisited = new boolean[R+1][C+1];
		ap = new int[2];
		bp = new int[2];
		cp = new int[2];
		for(int i=1;i<=R;i++) {
			String str = br.readLine();
			for(int j=1;j<=C;j++) {
				if(str.charAt(j-1)=='1')
					map[i][j] = true;
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<2;i++) {
			ap[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<2;i++) {
			bp[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<2;i++) {
			cp[i] = Integer.parseInt(st.nextToken());
		}
		check(new Pos(ap[0],ap[1]),a,aVisited);
		check(new Pos(bp[0],bp[1]),b,bVisited);
		check(new Pos(cp[0],cp[1]),c,cVisited);
		
		boolean isOk = false;
		for(int i=1;i<=R;i++) {
			for(int j=1;j<=C;j++) {
				if(aVisited[i][j]&&bVisited[i][j]&&cVisited[i][j]) {
					isOk = true;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		if(!isOk)
			sb.append(-1);
		else {
			for(int i=1;i<=R;i++) {
				for(int j=1;j<=C;j++) {
					res[i][j] = Math.max(a[i][j], Math.max(b[i][j], c[i][j]));
				}
			}
			int min = Integer.MAX_VALUE;
			for(int i=1;i<=R;i++) {
				for(int j=1;j<=C;j++) {
					if(!map[i][j] && res[i][j]!=0)
						min = Math.min(min, res[i][j]);
				}
			}
			
			int cnt = 0;
			for(int i=1;i<=R;i++) {
				for(int j=1;j<=C;j++) {
					if(res[i][j]==min)
						cnt++;
				}
			}
			sb.append(min).append("\n").append(cnt);
		}
		System.out.println(sb);
	}
	
	static void check(Pos p,int[][] arr, boolean[][] visited) {
		q.clear();
		q.add(p);
		visited[p.x][p.y] = true;
		while(!q.isEmpty()) {
			Pos t = q.remove();
			int x = t.x;
			int y = t.y;
			for(int i=0;i<4;i++) {
				int nx = x+dir[i][0];
				int ny = y+dir[i][1];
				if(isIn(nx,ny) && !visited[nx][ny]) {
					if(!map[nx][ny]) {
						q.add(new Pos(nx,ny));
						visited[nx][ny] = true;
						arr[nx][ny] = arr[x][y]+1;		
					}
				}
			}
		}
	}

	static boolean isIn(int x,int y) {
		return x>0 && x<=R && y>0 && y<=C;
	}

}

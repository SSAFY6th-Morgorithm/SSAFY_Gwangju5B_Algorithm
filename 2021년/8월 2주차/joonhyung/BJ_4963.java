package solution;

import java.util.Scanner;

public class BJ_4963 {
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};
	static int w,h,count;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			count = 0;
			w = sc.nextInt();
			h = sc.nextInt();
			if(w==0&&h==0)
				break;
			
			map = new int[h][w];
			visited = new boolean[h][w];
			
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					if(!visited[i][j] && map[i][j]==1) {
						DFS(i,j);
						count++;
					}
				}
			}
			System.out.println(count);
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++)
					System.out.print(visited[i][j]+" ");
				System.out.println();
			}
		}
	}
	
	public static void DFS(int x,int y) {
		visited[x][y] = true;
		
		for(int i=0;i<8;i++) {
			int nx = x+dir[i][0];
			int ny = y+dir[i][1];
			
			if(isIn(nx,ny)) {
				if(map[nx][ny]==1 && !visited[nx][ny])
					DFS(nx,ny);
			}
		}
	}

	public static boolean isIn(int x,int y) {
		return x>=0&&x<h&&y>=0&&y<w;
	}
}

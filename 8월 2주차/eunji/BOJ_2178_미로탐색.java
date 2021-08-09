package BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2178_미로탐색 {
	static int n,m;
	static int[][] graph;
//	static boolean[][] visited;
	static int[][] dir;
	static int cnt;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		sc.nextLine();
		
		graph=new int[n][m];
//		visited=new boolean[n][m];
		dir= new int[][]{{1,0},{-1,0},{0,1},{0,-1}}; 
		
		for (int i=0; i<n; i++) {
			String str=sc.nextLine();
			for (int j=0; j<m; j++) {
				graph[i][j]=str.charAt(j)-'0';
			}
		}
		bfs(0,0);
		System.out.println(graph[n-1][m-1]+1);
	
	}
	private static void bfs(int x, int y) {
		Queue<int[]> queue=new LinkedList<int[]>();
		queue.add(new int[] {x,y});
		graph[x][y]=0;
		while(!queue.isEmpty()) {
			int[] p=queue.poll();
			x=p[0];
			y=p[1];
			for (int i=0; i<4; i++) {
				int nx=x+dir[i][0];
				int ny=y+dir[i][1];
				if(isOkay(nx,ny)&&graph[nx][ny]==1) {
					// 사방탐색해서 범위를 벗어나지 않고, 1인 곳만 queue에 저장(미로 탐색 가능한 곳)
					queue.add(new int[]{nx,ny});
					graph[nx][ny]=graph[x][y]+1;
				}
			}
		}
	}
	private static boolean isOkay(int x, int y) {
		return x>=0 && y>=0 && x<n && y<m;
	}
}

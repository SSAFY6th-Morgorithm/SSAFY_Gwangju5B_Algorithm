package com.ssafy.ws;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1926_그림 {
	static int n,m;
	static int[][] graph;
	static int[][] dir;
	static int max,size;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		sc.nextLine();
		graph=new int[n][m];
		dir=new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				graph[i][j]=sc.nextInt();
			}
		}
		
//		System.out.println(Arrays.deepToString(graph));
		int cnt=0; // 그림의 개수
		max=0; // 그림의 최대크기
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				size=0; // 그림의 크기
				if(dfs(i,j)) {
					cnt++;
					if(max<size) max=size;
				}
			}
		}
		System.out.println(cnt);
		System.out.println(max);
		
		

	}
	private static boolean dfs(int x, int y) {
		if (graph[x][y]==1) {
			size++;
			graph[x][y]=0; //방문처리
			for(int i=0; i<4; i++) {
				int nx=x+dir[i][0];
				int ny=y+dir[i][1];
				if (isOkay(nx,ny) && graph[nx][ny]==1) dfs(nx,ny);
			}
			return true;
		}
		
		return false;
	}
	private static boolean isOkay(int x, int y) {
		// TODO Auto-generated method stub
		return x>=0 && y>=0 && x<n && y<m;
	}

}

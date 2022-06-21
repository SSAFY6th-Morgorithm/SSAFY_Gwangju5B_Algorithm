package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_15685 {
	static int N,num = 0;
	static int[][] map = new int[101][101];
	static int[][] dir = {{0,1},{-1,0},{0,-1},{1,0}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			curve(y,x,d,g);
		}

		checkSquare();
		StringBuilder sb = new StringBuilder();
		sb.append(num);
		System.out.println(sb);
	}
	
	static void curve(int x,int y,int d,int g) {
		List<Integer> li = new ArrayList<>();
		li.add(d);
		for(int i=0;i<g;i++) {
			for(int j=li.size()-1;j>=0;j--) {
				int tmp = (li.get(j)+1)%4;
				li.add(tmp);
			}
		}

		
		map[x][y] = 1;
		int nx = x;
		int ny = y;
		for(int i=0;i<li.size();i++) {
			nx += dir[li.get(i)][0];
			ny += dir[li.get(i)][1];
			map[nx][ny] = 1;
		}
	}
	
	static void checkSquare() {
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(map[i][j]==1 && map[i+1][j]==1 && map[i][j+1]==1 && map[i+1][j+1]==1)
					num++;
			}
		}
	}

}

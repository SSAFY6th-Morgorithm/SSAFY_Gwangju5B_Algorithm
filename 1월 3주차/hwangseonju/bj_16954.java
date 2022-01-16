package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class bj_16954 {

	static char[][] chess;
	static int result;		// 결과
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		// 움직이는 미로 탈출
		chess = new char[8][8];
		result = 0;
		Queue<Dot> wall = new LinkedList<>();
		
		for(int r=0; r<8; r++) {
			String str = br.readLine();
			for(int c=0; c<8; c++) {
				chess[r][c] = str.charAt(c);
				if(chess[r][c]=='#')
					wall.add(new Dot(r,c,0));
			}
		}
		
		int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1},{-1,1},{1,-1},{-1,-1},{1,1}};
		
		Queue<Dot> queue = new LinkedList<>();
		boolean[][] visited = new boolean[8][8];
		int dis = 1;
		queue.offer(new Dot(7,0,dis));	// 욱제 시작점
		
		 while(!queue.isEmpty()) {
			 if(wall.isEmpty()) {	// 벽이 없으면 도착지까지 도착 가능
				 result = 1;
				 break;
			 }else {
				 if(queue.peek().cost>dis) {	// 벽이동
					 for(int i=0; i<8; i++) {
						 Arrays.fill(chess[i], '.');
					 }
					 
					 for(int w=0; w<wall.size(); w++) {
						 Dot nwall = wall.poll();
						 
						 if(isIn(nwall.x+1, nwall.y)) {
							 chess[nwall.x+1][nwall.y] = '#';
							 wall.offer(new Dot(nwall.x+1, nwall.y, 0));
						 }
					 }
					 visited = new boolean[8][8];
				 }
			 }
			 
			 Dot current = queue.poll();
			 dis = current.cost;
			 
			 if(current.x==0 && current.y==7) {		// 도착점에 도착
				 result = 1;
				 break;
			 }
			 
			 if(chess[current.x][current.y]=='.') {
				 queue.add(new Dot(current.x, current.y, current.cost+1));	// 제자리에 있을 수도 있기 때문에 현재 위치는 다시 넣어준다.
				 visited[current.x][current.y] = true;
			 }else {
				 continue;
			 }
			 
			 for(int d=0; d<8; d++) {
				 int nr = current.x + deltas[d][0];
				 int nc = current.y + deltas[d][1];
				 
				 if(isIn(nr, nc) && chess[nr][nc]=='.' && !visited[nr][nc]) {
					 queue.add(new Dot(nr, nc, current.cost+1));
					 visited[nr][nc] = true;
				 }
			 }
		 }
		 
		 System.out.println(result);
		
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<8 && c>=0 && c<8;
	}
	
	static class Dot{
		int x, y, cost;

		public Dot(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
}
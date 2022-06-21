package week2022_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16954 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] map;
	static int[][] deltas = {{0,0},{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,1},{1,-1}};
	//현재 위치에 있거나 팔방
	static int answer=0;
	
	public static void main(String[] args) throws IOException {
		
		map = new char[8][8];
		for(int i=0; i<8; i++) {
			String str = br.readLine();
			for(int j=0; j<8; j++) {
				map[i][j] = str.charAt(j);
				
			}
		}
		/*
		........
		........
		........
		........
		........
		........
		........
		........
		(7,0)
		*/
		
		bfs();
		System.out.println(answer);

	}
	
	private static void bfs() {
		Queue<Node> Q = new LinkedList<>();
		Q.add(new Node(7, 0));
		//왔던길로 다시 돌아갈 수도 있음.
		
		while(!Q.isEmpty()) {
			int size = Q.size();
			for(int s=0; s<size; s++) {
				
				Node now = Q.poll();
//				System.out.println("now.r:"+now.r+"|||now.c: "+now.c);
				//만약 이동한 곳이 이동 후에 돌이 내려온 곳이라면 이동할 수 없었던 곳임을 판단해야 함.
				if(map[now.r][now.c] == '#') {
					continue;//앞서 이동했던 곳이 현재는 돌에 깔린 상태
				}
				for(int d=0; d<9; d++) {
					
					int nr = now.r + deltas[d][0];
					int nc = now.c + deltas[d][1];
					
					if(isIn(nr,nc)) {
						if(nr == 0 && nc == 7) {
							answer = 1;
							return;
						}
						if(map[nr][nc] != '#') {
							Q.add(new Node(nr, nc));
						}
					}
					
//					if(!isIn(nr,nc)) {
//						continue;
//					}	
//					if(nr == 0 && nc == 7) {
//						answer = 1;
//						return;
//					}
//					if(map[nr][nc] != '#') {
//						Q.add(new Node(nr, nc));
//					}
					
					
				}
				
								
			}
			fall();
		}
		answer = 0;
		return;
		
	}

	private static void fall() {
		//한번에 한 칸씩만 이동
		
		for(int r=7; r>0; r--) {
			for(int c=7; c>=0; c--) {
				map[r][c] = map[r-1][c];
			}
		}
		for(int c=0; c<8; c++) {
			map[0][c] = '.';
		}
//		for(int i=7; i>=0; i--) {
//			for(int j=7; j>=0; j--) {
//				if(i-1<0) {
//					map[i][j]= '.';
//					
//				}else {
//					map[i][j] = map[i-1][j];
//				}
//			}
//		}
	}

	private static boolean isIn(int nr, int nc) {
		return 0<= nr && nr < 8 && 0<= nc && nc <8;
	}

	static class Node{
		
		int r,c;

		public Node(int r, int c) {
			super();
			this.r = r ;
			this.c = c;
		}
		
		
		
	}

}

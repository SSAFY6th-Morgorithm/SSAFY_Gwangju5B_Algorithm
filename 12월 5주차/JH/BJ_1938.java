package week20.day1228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1938 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static char[][] map;
	static int[][] deltas = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	static boolean[][][] visited;
	static Node BBB;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N][5];
		
		int cnt=0;
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'B') {
					cnt++;
					if(cnt == 2) {
						BBB = new Node(i, j, 0, 0);
					}
				}
			}
		}
		bfs();
//		for(char[] arr : map) {
//			System.out.println(Arrays.toString(arr));
//		}

	}
	
	private static void bfs() {
		Queue<Node> Q = new LinkedList<>();
		Q.add(BBB);
		visited[???]
		
	}

	static class Node{
		int r,c,dir,cnt;
		//r,c는 통나무 중심
		//dir은 방향
		//상하좌우턴 01234
		//cnt는 이동 횟수

		public Node(int r, int c, int dir, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.cnt = cnt;
		}
		
	}
	
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_16569 {
	
	static int M;	// 행
	static int N;	// 열
	static int V;	// 화산
	static int X;	// 재상 위치 x
	static int Y;	// 재상 위치 y
	static int[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		// 화산쇄설류
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken())-1;
		Y = Integer.parseInt(st.nextToken())-1;
		
		map = new int[M][N];
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			for(int n=0; n<N; n++) {
				map[m][n] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] bomb = new int[M][N];
		for(int b=0; b<M; b++) {
			Arrays.fill(bomb[b], Integer.MAX_VALUE);
		}
		
		for(int v=0; v<V; v++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int t = Integer.parseInt(st.nextToken());
			bomb[x][y] = -1;	// 화산 체크
			
			for(int r=0; r<M; r++) {
				for(int c=0; c<N; c++) {
					if(bomb[r][c]!=-1) {
						bomb[r][c] = Math.min(bomb[r][c], Math.abs(x-r)+Math.abs(y-c)+t);
					}
				}
			}
		}
		
//		for(int r=0; r<M; r++) {
//			for(int c=0; c<N; c++) {
//				System.out.print(bomb[r][c]+" ");
//			}
//			System.out.println();
//		}
		
		Queue<Dot> queue = new LinkedList<>();
		boolean[][] visited = new boolean[M][N];
		queue.add(new Dot(X, Y, 1));
		visited[X][Y] = true;
		int maxH = map[X][Y];
		int minT = 0;
		while(!queue.isEmpty()) {
			Dot current = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nr = current.x + deltas[d][0];
				int nc = current.y + deltas[d][1];
				
				if(isIn(nr,nc) && !visited[nr][nc]) {
					if(bomb[nr][nc]>current.t) {
						queue.add(new Dot(nr, nc, current.t+1));
						visited[nr][nc] = true;
						if(maxH<map[nr][nc]) {
							maxH = map[nr][nc];
							minT = current.t;
						}
					}
				}
			}
		}

		System.out.print(maxH +" "+minT);
	}
	
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<M && c>=0 && c<N;
	}

	static class Dot {
		int x,y,t;

		public Dot(int x, int y, int t) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}
}

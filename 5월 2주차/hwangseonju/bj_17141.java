package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_17141 {

	static int N;	// 연구실 크기
	static int M;	// 바이러스 개수
	static int[][] map;
	static List<Dot> virus = new LinkedList<>();	// 바이러스를 놓을 수 있는 위치
	static int space = 0;	// 빈칸 수
	static int time = Integer.MAX_VALUE;	// 최소 시간
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		// 연구소 2
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				int num = Integer.parseInt(st.nextToken());
				map[r][c] = num;
				if(num==2)
					virus.add(new Dot(r,c,0));
				else if(num==0)
					space++;
			}
		}
		space = space + (virus.size() - M);	// 총 빈공간 = 빈칸(0) + (바이러스 위치 공간(2) - 바이러스 개수(M))
		
		if(space==0) {	// 퍼질 공간이 없을 경우
			System.out.println(0);
		} else {
			choosedBirus(M, new int[M], 0);
			
			if(time!=Integer.MAX_VALUE)
				System.out.println(time);
			else 
				System.out.println(-1);
		}
	}
	
	static void choosedBirus(int n, int[] choosed, int idx) {	// 바이러스 놓을 위치 고르기(조합)
		if(n==0) {
			Time(choosed);
			return;
		}
		
		for(int i=idx; i<virus.size(); i++) {
			choosed[M-n] = i;
			choosedBirus(n-1, choosed, i+1);
		}
	}
	
	static void Time(int[] choosed) {	// 바이러스가 퍼지는 최소 시간 구하기
		Queue<Dot> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int nspace = space;
		
		for(int m=0; m<M; m++) {
			int r = virus.get(choosed[m]).x;
			int c = virus.get(choosed[m]).y;
			queue.add(new Dot(r,c,0));
			visited[r][c] = true;
		}
		
		int[][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};
		while(!queue.isEmpty()) {
			Dot current = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nr = current.x + deltas[d][0];
				int nc = current.y + deltas[d][1];
			
				if(isIn(nr,nc) && !visited[nr][nc] && map[nr][nc]!=1) {
					visited[nr][nc] = true;
					queue.add(new Dot(nr,nc,current.time+1));
					nspace--;
					if(nspace==0)
						time = Math.min(current.time+1, time);
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
	
	static class Dot{
		int x, y, time;

		public Dot(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}

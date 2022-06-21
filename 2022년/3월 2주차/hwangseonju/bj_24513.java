package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_24513 {
	
	static int N;	// 세로 크기
	static int M;	// 가로 크기
	static int[][] map;	// 마을
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		// 좀비 바이러스
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		int[][] visited = new int[N][M];	// 방문 시간확인
		Queue<Dot> queue = new LinkedList<>();
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c]==1) {
					queue.offer(new Dot(r,c,1,0));
					visited[r][c] = -1;
				}
				if(map[r][c]==2) {
					queue.offer(new Dot(r,c,2,0));
					visited[r][c] = -1;
				}
			}
		}
		
		int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
		while(!queue.isEmpty()) {
			Dot current = queue.poll();
			if(map[current.x][current.y]==3)	// 바이러스가 3단계일 경우 -> 상화좌우 확인 불필요
				continue;
			
			for(int d=0; d<4; d++) {
				int nr = current.x + deltas[d][0];
				int nc = current.y + deltas[d][1];
				
				if(isIn(nr,nc) && map[nr][nc]!=-1 && visited[nr][nc] != -1) {	// 치료제가 있거나 바이러스 시작인 마을이 아닐 경우,
					if(visited[nr][nc]==0) {	// 바이러스가 처음 생긴 마을일 경우
						map[nr][nc] = current.virus;
						visited[nr][nc] = current.turn+1;	// 바이러스 발생 시간 확인
						queue.add(new Dot(nr,nc,current.virus, current.turn+1));
					}else if(current.turn+1==visited[nr][nc] && map[nr][nc]<3 && map[nr][nc] != current.virus) {	// 다른 바이러스가 생길 경우(1번+2번일 경우)
						map[nr][nc] += current.virus;
					}
				}
			}
		}
		
		int one = 0;
		int two = 0;
		int three = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==1) ++one;
				else if(map[i][j]==2) ++two;
				else if(map[i][j]==3) ++three;
			}
		}
		
		System.out.println(one+" "+two+" "+three);
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
	
	static class Dot {
		int x, y, virus, turn;

		public Dot(int x, int y, int virus, int turn) {
			super();
			this.x = x;
			this.y = y;
			this.virus = virus;
			this.turn = turn;
		}
	}

}

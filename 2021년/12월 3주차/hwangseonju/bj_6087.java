package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_6087 {
	
	static int W;	// 가로
	static int H;	// 세로
	static char[][] map;	// 지도
	static int[][] d;		// 거울 최소 사용
	static List<Dot> dest;	// 출발지, 도착지
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		// 레이저 통신
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];
		d = new int[H][W];
		dest = new ArrayList<>();
		
		for(int r=0; r<H; r++) {
			String str = br.readLine();
			for(int c=0; c<W; c++) {
				map[r][c] = str.charAt(c);
				if(map[r][c]=='C') {			// 출발지, 도착지 위치 저장
					dest.add(new Dot(r, c, -1, 0));
				}
			}
		}
		
		for(int r=0; r<H; r++) {
			Arrays.fill(d[r], Integer.MAX_VALUE);
		}
		
		search();
		
		System.out.println(d[dest.get(1).x][dest.get(1).y]-1);
	}
	
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	static void search() {
		PriorityQueue<Dot> pq = new PriorityQueue();
		pq.add(new Dot(dest.get(0).x, dest.get(0).y ,dest.get(0).before, dest.get(0).cost));
		d[dest.get(0).x][dest.get(0).y] = 0;
		
		while(!pq.isEmpty()) {
			Dot current = pq.poll();
			
			if(current.x==dest.get(1).x && current.y==dest.get(1).y)
				break;
			
			for(int dir=0; dir<4; dir++) {
				int nr = current.x + deltas[dir][0];
				int nc = current.y + deltas[dir][1];
				
				if(isIn(nr, nc) && map[nr][nc]!='*') {
					if(dir!=current.before) {
						if (d[nr][nc] >= current.cost+1) {
							d[nr][nc] = current.cost+1;
							pq.offer(new Dot(nr, nc, dir, current.cost+1));
						}
					}else {
						if (d[nr][nc] >= current.cost) {
							d[nr][nc] = current.cost;
							pq.offer(new Dot(nr, nc, dir, current.cost));
						}
					}
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<H && c>=0 && c<W;
	}
	
	static class Dot implements Comparable<Dot>{
		int x, y, before, cost;	// x좌표, y좌표, 이전 방향, 거울 개수

		public Dot(int x, int y, int before, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.before = before;
			this.cost = cost;
		}

		@Override
		public int compareTo(Dot o) {
			return this.cost-o.cost;
		}
	}
}
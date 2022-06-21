package M12W2;

import java.io.*;
import java.util.*;

class Dot implements Comparable<Dot>{
	int r;
	int c;
	int dir; //현재방향
	int count; //방향바뀐횟수
	
	public Dot(int r, int c, int dir, int count) {
		super();
		this.r = r;
		this.c = c;
		this.dir = dir;
		this.count = count;
	}

	@Override
	public int compareTo(Dot o) {
		return this.count-o.count;
	}
	
}
public class BOJ_G4_레이저통신 {
	static int targetR,targetC,startR,startC;
	public static void main(String[] args) throws IOException {
		//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		char[][] map =new char[R][C];
		PriorityQueue<Dot> pq = new PriorityQueue<>();
		for(int r=0;r<R;r++) {
			String s = br.readLine();
			for(int c=0;c<C;c++) {
				map[r][c]= s.charAt(c);
				if(map[r][c]=='C') {
					if(pq.isEmpty()) {
						startR=r;
						startC=c;
						pq.add(new Dot(r,c,5,-1));
					
					}else {
						targetR=r;
						targetC=c;
					}
				}
			}
		}
		int[][] vector = {{-1,0},{1,0},{0,-1},{0,1}};
		int[][] dist = new int[R][C];
		for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                dist[r][c] = Integer.MAX_VALUE;
            }
        }
		dist[startR][startC] = 0;
		boolean[][] visited = new boolean[R][C];
 		while(!pq.isEmpty()) {
 			Dot temp = pq.poll();
 			if(temp.r==targetR&&temp.c==targetC) {
 				break;
 			}
 			for(int d=0;d<4;d++) {
 				int nr = temp.r+vector[d][0];
 				int nc = temp.c+vector[d][1];
 				int nCount = temp.count;
 				if(temp.dir!=d) nCount++;
 				
 				if(nr>=0&&nr<R&&nc>=0&&nc<C  && map[nr][nc]!='*') {
 					
 					if(dist[nr][nc]>=nCount) {
 						dist[nr][nc]=nCount;
 						pq.add(new Dot(nr,nc,d,nCount));
 					}
 					
 				}
 			}
 			
 		}
 		System.out.println(dist[targetR][targetC]);
 		
	}
}
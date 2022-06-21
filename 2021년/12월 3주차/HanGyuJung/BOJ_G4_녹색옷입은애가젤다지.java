package M12W2;

import java.io.*;
import java.util.*;

public class BOJ_G4_녹색옷입은애가젤다지 {
	static int[][] vector = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T=1;
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
		});
		
		while(true) {
			pq.clear();
			int N = Integer.parseInt(br.readLine());
			if(N==0) break;
			int[][] map = new int[N][N];
			
			for(int r=0;r<N;r++) {
				st= new StringTokenizer(br.readLine());
				for(int c=0;c<N;c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			int dist[][] = new int[N][N];
			for(int i=0;i<dist.length;i++) {
				Arrays.fill(dist[i],Integer.MAX_VALUE);
			}
			dist[0][0] = map[0][0];
			pq.add(new int[] {0,0,map[0][0]});
			
			while(!pq.isEmpty()) {
				int[] temp = pq.poll();
				int r = temp[0];
				int c = temp[1];
				
				for(int d=0;d<4;d++) {
					int nr= temp[0]+vector[d][0];
					int nc= temp[1]+vector[d][1];
					if(nr>=0&&nr<N&&nc>=0&&nc<N) {
						if(dist[nr][nc]>dist[r][c]+map[nr][nc]) {
							dist[nr][nc]=dist[r][c]+map[nr][nc];
							pq.add(new int[] {nr,nc,dist[nr][nc]});
						}
					}					
				}
			}
			sb.append("Problem ").append(T).append(":").append(" ").append(dist[N-1][N-1]).append("\n");
			T++;
		}
		System.out.println(sb);
	}
}
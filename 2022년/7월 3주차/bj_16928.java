package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_16928 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		
		int[] map = new int[101];
		boolean[] check = new boolean[101];
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x] = y;
		}
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			map[u] = v;
		}
		
		PriorityQueue<Next> go = new PriorityQueue<>();
		go.add(new Next(1, 0));
		check[1] = true;
		
		while(!go.isEmpty()) {
			Next current = go.poll();
			
			if(current.x>=100) {
				System.out.println(current.c);
				break;
			}
			
			for(int d=6; d>=1; d--) {
				int next = current.x + d;
				
				if(next<=100 && !check[next]) {
					check[next] = true;
					
					if(map[next]==0) {
						go.add(new Next(next, current.c+1));
					}else {
						go.add(new Next(map[next], current.c+1));
					}
				}
			}
		}
	}
	
	static class Next implements Comparable<Next> {
		int x, c;
		
		public Next(int x, int c) {
			super();
			this.x = x;
			this.c = c;
		}

		@Override
		public int compareTo(Next o) {
			if(this.c == o.c) {
				return o.x - this.x;
			}
			return this.c - o.c;
		}
	}
}

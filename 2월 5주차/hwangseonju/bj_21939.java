package Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_21939 {
	
	static int N;	// 문제 개수
	static int M;	// 명령 개수
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 문제 추천 시스템 Version1
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Easy> easy = new PriorityQueue<>();
		PriorityQueue<Difficult> difficult = new PriorityQueue<>();
		HashMap<Integer, Integer> map = new LinkedHashMap<>();
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			easy.offer(new Easy(L, P));
			difficult.offer(new Difficult(L, P));
			map.put(P, L);
		}
		
		M = Integer.parseInt(br.readLine());
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			String start  = st.nextToken();
			if(start.equals("add")) {
				int P = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				
				easy.offer(new Easy(L, P));
				difficult.offer(new Difficult(L, P));
				map.put(P, L);
			}else if(start.equals("solved")) {
				map.remove(Integer.parseInt(st.nextToken()));
			}else {
				int i = Integer.parseInt(st.nextToken());
				if(i==1) {
					while(!difficult.isEmpty()) {
						Difficult current = difficult.poll();
						if(map.containsKey(current.p) && (map.get(current.p)==current.l)) {
							sb.append(current.p);
							difficult.offer(current);
							break;
						}
					}
				}else {
					while(!easy.isEmpty()) {
						Easy current = easy.poll();
						if(map.containsKey(current.p) && (map.get(current.p)==current.l)) {
							sb.append(current.p);
							easy.offer(current);
							break;
						}
					}
				}
				sb.append("\n");
			}
		}
		sb.deleteCharAt(sb.lastIndexOf("\n"));
		System.out.print(sb);
	}
	
	static class Easy implements Comparable<Easy>{
		int l, p;

		public Easy(int l, int p) {
			super();
			this.l = l;
			this.p = p;
		}

		@Override
		public int compareTo(Easy o) {
			if(this.l==o.l)
				return Integer.compare(this.p, o.p);
			return Integer.compare(this.l, o.l);
		}
	}
	
	static class Difficult implements Comparable<Difficult>{
		int l, p;

		public Difficult(int l, int p) {
			super();
			this.l = l;
			this.p = p;
		}

		@Override
		public int compareTo(Difficult o) {
			if(this.l==o.l)
				return Integer.compare(o.p, this.p);
			return Integer.compare(o.l, this.l);
		}
	}
}

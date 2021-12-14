package M12W2;

import java.io.*;
import java.util.*;

class Edge {
	int end;
	long weight;
	public Edge(int end, long weight) {
		this.end = end;
		this.weight = weight;
	}
}
public class BOJ_G5_백도어 {
	public static void main(String[] args) throws IOException {
		//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		boolean[] cansee = new boolean[N];
		ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
		
		for(int n=0;n<N;n++) {
			graph.add(new ArrayList<Edge>());
		}
		st =new StringTokenizer(br.readLine());
		for(int n=0;n<N;n++) {
			int flag = Integer.parseInt(st.nextToken());
			if(flag==0) cansee[n]=false; 
			else if(flag==1) cansee[n]=true; 
		}
		cansee[N-1]=false;
		
		for(int l=0;l<L;l++) {
			st= new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(start).add(new Edge(end,weight));
			graph.get(end).add(new Edge(start,weight));
		}
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1,Edge o2) {
				if(o1.weight>o2.weight) return 1;
				else return -1;
			}
		});
		long[] dist = new long[N];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[0] = 0;
		pq.add(new Edge(0,0));
		boolean[] visited = new boolean[N];
		while(!pq.isEmpty()) {
			Edge temp = pq.poll();
			if(visited[temp.end]) continue;
			visited[temp.end]= true;
			if(temp.end==N-1) break;
			for(Edge edge: graph.get(temp.end)) {
				
				if(dist[edge.end]>dist[temp.end]+edge.weight
						&& !cansee[edge.end]) {
					dist[edge.end]=dist[temp.end]+edge.weight;
					pq.add(new Edge(edge.end,dist[edge.end]));
				}
			}
		}
		if(dist[N-1]==Long.MAX_VALUE)System.out.println(-1);
		else System.out.println(dist[N-1]);
	}
}

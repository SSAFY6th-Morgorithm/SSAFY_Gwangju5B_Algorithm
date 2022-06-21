package M12W2;

import java.io.*;
import java.util.*;

class Edge2 implements Comparable<Edge2>{
	int destination;
	int weight;
	
	public Edge2(int destination, int weight) {
		this.destination = destination;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge2 o) {
		return this.weight-o.weight;
	}

	
	
}
public class BOJ_G4_특정한최단경로 {
	private static int N;
	private static ArrayList<ArrayList<Edge2>> graph;

	public static void main(String[] args) throws IOException {
		//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for(int n=0;n<=N;n++) {
			graph.add(new ArrayList<Edge2>());
		}
		for(int e=0;e<E;e++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(start).add(new Edge2(end,weight));
			graph.get(end).add(new Edge2(start,weight));
		}
		st = new StringTokenizer(br.readLine());
		int must1 = Integer.parseInt(st.nextToken());
		int must2 = Integer.parseInt(st.nextToken());
		
		long answer1 = dijk(1,must1)+dijk(must1,must2)+dijk(must2,N);
		long answer2 = dijk(1,must2)+dijk(must2,must1)+dijk(must1,N);
		if(answer1==0&&answer2==0) System.out.println("-1");
		else System.out.println(Math.min(answer1, answer2));
		
		
	}

	private static int dijk(int start, int end) {
		PriorityQueue<Edge2> pq = new PriorityQueue<>();
		pq.add(new Edge2(start,0));
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start]=0;
		boolean[] visited = new boolean[N+1];
		while(!pq.isEmpty()) {
			Edge2 temp = pq.poll();
			if(visited[temp.destination]) continue;
			visited[temp.destination] = true;
			if(temp.destination==end) break;
			
			for(Edge2 edge: graph.get(temp.destination)) {
					if(dist[edge.destination]>dist[temp.destination]+edge.weight) {
							dist[edge.destination] = dist[temp.destination]+edge.weight;
							pq.add(new Edge2(edge.destination,dist[edge.destination]));
							
					}
			}
		}
		if(dist[end]==Integer.MAX_VALUE) return 0;
		else return dist[end];
	}
}
	

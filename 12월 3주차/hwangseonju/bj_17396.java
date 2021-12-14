package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_17396 {

	static int V;
	static int E;
	static int[] sight;	// 시야
	static List<ArrayList<Node>> graph;	// 인접리스트
	static long[] d;		// 최단 거리
	static boolean[] visited;	// 방문여부
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		// 백도어
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		sight = new int[V];
		st = new StringTokenizer(br.readLine());
		for(int s=0; s<V; s++) {
			sight[s] = Integer.parseInt(st.nextToken());
		}
		
		graph = new ArrayList<ArrayList<Node>>();
		for(int i=0; i<V; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new Node(b, cost));
			graph.get(b).add(new Node(a, cost));
		}
		
		d = new long[V];
		visited = new boolean[V];
		Arrays.fill(d, Long.MAX_VALUE);
		
		d[0] = 0;	// 출발
		
		// 다익스트라
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, d[0]));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if(visited[current.b]) {
				continue;
			}
			
			visited[current.b] = true;
			if(current.b==V-1) break;
			
			for(int i=0; i<graph.get(current.b).size(); i++) {
				Node node = graph.get(current.b).get(i);
				if(d[node.b]>d[current.b]+node.cost && sight[current.b]!=1) {
					d[node.b] = d[current.b] + node.cost;
					pq.offer(new Node(node.b, d[node.b]));
				}
			}
		}
		
		if(d[V-1]==Long.MAX_VALUE) {	// 상대 넥서스까지 못가는 경우
			System.out.println(-1);
		}else {
			System.out.println(d[V-1]);
		}
	}
	
	static class Node implements Comparable<Node>{
		int b;
		long cost;

		public Node(int b, long cost) {
			super();
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			if(this.cost>o.cost) return 1;
			return -1;
		}
	}

}

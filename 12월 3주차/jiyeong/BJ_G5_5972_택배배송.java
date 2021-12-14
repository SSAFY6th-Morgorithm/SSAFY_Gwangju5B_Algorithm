package dijkstra;

import java.io.*;
import java.util.*;

public class BJ_G5_5972_택배배송 {
	
	static class Node implements Comparable<Node>{
		int no, weight;
		Node pre;
		
		public Node(int no, int weight, Node pre) {
			super();
			this.no = no;
			this.weight = weight;
			this.pre = pre;
		}
		
		public Node(int no, int weight) {
			super();
			this.no = no;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int N,M;
	static Node[] graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new Node[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[s] = new Node(e,w,graph[s]);
			graph[e] = new Node(s,w,graph[e]);
		}
		
		dijkstra(1,N);			

	}
	
	private static void dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		int[] dist = new int[N+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		pq.offer(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.no]) continue;
			visited[cur.no] = true;
			
			Node next = graph[cur.no]; 
			while(next != null) {
				if(!visited[next.no] && dist[next.no] > dist[cur.no]+next.weight) {
					dist[next.no] = dist[cur.no]+next.weight;
					pq.offer(new Node(next.no, dist[next.no]));
				}
				next = next.pre;
			}
		}
		
		System.out.println(dist[N]);
	}
}

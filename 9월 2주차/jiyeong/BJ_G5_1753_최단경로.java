package com.ssafy.webex.d0824;

import java.util.*;
import java.io.*;


public class BJ_G5_1753_최단경로 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static class LinkNode implements Comparable<LinkNode>{
		int no;
		int weight;
		int totalCost; // 각 정점에 도달하는 비용
		LinkNode pre;
		
		public LinkNode(int no, int weight, LinkNode pre) { 
			super();
			this.no = no;
			this.weight = weight;
			this.pre = pre;
		}
		
		public LinkNode(int no, int totalCost) {
			this.no = no;
			this.totalCost = totalCost;
		}

		@Override
		public String toString() {
			return "LinkNode [no=" + no + ", weight=" + weight + ", pre=" + pre + "]";
		}

		@Override
		public int compareTo(LinkNode o) {
			return Integer.compare(this.totalCost, o.totalCost);
		}
	}

	static int V, E, K;
	static int u, v, w;
	static LinkNode[] graph;
	
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점 개수
		E = Integer.parseInt(st.nextToken()); // 간선 개수
		
		K = Integer.parseInt(br.readLine()); // 시작 정점 번호
		
		graph = new LinkNode[V+1];
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			graph[u] = new LinkNode(v, w, graph[u]);
		}

		dijkstra();

	}
	
	private static void dijkstra() {
		// 필요한 요소 준비하기
		int[] dist = new int[V+1];
		boolean[] visited = new boolean[V+1];
		PriorityQueue<LinkNode> pq = new PriorityQueue<>();
		
		// 초기화
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[K] = 0;
		
		pq.offer(new LinkNode(K, 0));
		
		while( !pq.isEmpty() ) {
			LinkNode minDistNode = pq.poll();
			if(visited[minDistNode.no]) {
				continue;
			}
			
			visited[minDistNode.no] = true;
			
			// 방문 가능한 지점들 가보기
			LinkNode next = graph[minDistNode.no];
			
			while(next != null) {
				if( !visited[next.no] && dist[next.no] > dist[minDistNode.no] + next.weight ) {
					dist[next.no] = dist[minDistNode.no] + next.weight; 
					pq.offer(new LinkNode(next.no, dist[next.no]));
				}
				next = next.pre;
			}
		}
		
		for (int i = 1; i <= V; i++) {
			sb.append(dist[i] == Integer.MAX_VALUE ? "INF":dist[i]).append("\n");
		}
		System.out.println(sb);
	}
	

}

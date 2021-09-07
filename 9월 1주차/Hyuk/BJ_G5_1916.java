package BJ_Practice;

import java.io.*;
import java.util.*;


public class BJ_G5_1916 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M;
	static int S,E;
	static LinkNode[] graph;

	static class LinkNode implements Comparable<LinkNode> {
		int no;
		int weight;
		int totalCost;// 각 정점에 도달하는 비용
		LinkNode pre;

		public LinkNode(int no, int weight, LinkNode pre) { // 그래프 만들기 위해서..
			super();
			this.no = no;
			this.weight = weight;
			this.pre = pre;
		}

		public LinkNode(int no, int totalCost) { // PQ에서 사용하기 위한 생성자.
			this.no = no;
			this.totalCost = totalCost;
		}

		@Override
		public String toString() {
			return "[no=" + no + ", weight=" + weight + ", pre=" + pre + "]";
		}

		@Override
		public int compareTo(LinkNode o) {
			return Integer.compare(this.totalCost, o.totalCost);
		}

	}

	private static void dijkstra() {
		// 필요한 요소 준비하기
		int[] dist = new int[N + 1];
		boolean[] visited = new boolean[N + 1];
		PriorityQueue<LinkNode> pq = new PriorityQueue<>();

		// 자원 초기화
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[S] = 0;
		// pq에 가장 작은 점 넣어주기 - 출발점.
		pq.offer(new LinkNode(S, 0));

		while (!pq.isEmpty()) {
			LinkNode minDistNode = pq.poll();
			// 혹시 방문했던 지점??
			if (visited[minDistNode.no]) {
				continue;
			}

			// 그점 방문해보기
			visited[minDistNode.no] = true;

			// 방문 가능한 지점들 가보기.
			LinkNode next = graph[minDistNode.no];

			while (next != null) {
				if (!visited[next.no] && dist[next.no] > dist[minDistNode.no] + next.weight) {
					dist[next.no] = dist[minDistNode.no] + next.weight;// 새롭게 갱신된 정보는 PQ에 넣어주자!!
					pq.offer(new LinkNode(next.no, dist[next.no]));
				}
				next = next.pre;
			}

		}
		
		System.out.println(dist[E]);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new LinkNode[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[src] = new LinkNode(dest, cost, graph[src]);
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		dijkstra();
	}

}

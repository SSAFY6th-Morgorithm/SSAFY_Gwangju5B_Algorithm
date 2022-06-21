package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라
public class BJ_G5_1916_최소비용구하기 {
	
	static class Node implements Comparable<Node>{
		int node;
		int weight;
		Node pre;
		
		// 그래프 만들기
		public Node(int node, int weight, Node pre) { 
			super();
			this.node = node;
			this.weight = weight;
			this.pre = pre;
		}
		
		public Node(int node, int weight) {
			super();
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	static int N,M;
	static int start, end;
	static Node[] graph;
	static int[] dist;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 도시개수 (노드)
		M = Integer.parseInt(br.readLine()); // 버스개수 (간선)
		
		graph = new Node[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[s] = new Node(e, w, graph[s]);
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		dijkstra();
		System.out.println(dist[end]);
	}
	
	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist = new int[N+1];
		boolean[] visited = new boolean[N+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0; // 출발점은 언제나 0
		
		pq.offer(new Node(start, 0));

		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.node]) continue;
			
			visited[cur.node] = true;
			
			Node next = graph[cur.node];
				
			while(next != null) {
				if( !visited[next.node] && dist[next.node] > dist[cur.node] + next.weight ) {
					dist[next.node] = dist[cur.node] + next.weight;
					pq.offer(new Node(next.node, dist[next.node]));
				}
				next = next.pre;
			}
			
		}
		
	}

}

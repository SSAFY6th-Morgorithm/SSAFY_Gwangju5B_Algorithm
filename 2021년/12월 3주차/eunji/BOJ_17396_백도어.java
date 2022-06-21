package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 141684	1040
 * @author CHO
 * @see https://www.acmicpc.net/problem/17396
 * @category Dijkstra pq사용, long형
 */
public class BOJ_17396_백도어 {
	static class Node implements Comparable<Node>{
		int vertex;
		Node link;
		long weight;
		public Node(int vertex, Node link, long weight) {
			super();
			this.vertex = vertex;
			this.link = link;
			this.weight = weight;
		}
		public Node(int vertex, long weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Long.compare(this.weight, o.weight);
		}
		
		
	}
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] okay=new int[N];
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			okay[i]=Integer.parseInt(st.nextToken());
		}
		Node[] list=new Node[N];
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[a]=new Node(b, list[a], w);
			list[b]=new Node(a, list[b], w);
		}// 입력 완료
		
		// 출발:0, 도착:N-1
		boolean[] visited=new boolean[N];
		long[] distance=new long[N];
		Arrays.fill(distance, Long.MAX_VALUE);
		distance[0]=0;
		PriorityQueue<Node> pq=new PriorityQueue<>();
		pq.add(new Node(0, 0));
		while(!pq.isEmpty()) {
			Node curr=pq.poll();
			long min=curr.weight;
			if(visited[curr.vertex]) continue;
			visited[curr.vertex]=true;
			for (Node next=list[curr.vertex]; next!=null; next=next.link) {
				if(!visited[next.vertex] && distance[next.vertex]>min+next.weight) {
					// 시야에 안보이는 경로로 가야함, N-1은 보여도 ㄱㅊ
					if(next.vertex==N-1 || okay[next.vertex]==0) {
						distance[next.vertex]=min+next.weight;
						pq.add(new Node(next.vertex, distance[next.vertex]));
					}
				}
			}
		}
		if(distance[N-1]==Long.MAX_VALUE) System.out.println(-1);
		else System.out.println(distance[N-1]);
		

	}
}

package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 12224	108
 * @author CHO
 * @see https://www.acmicpc.net/problem/14938
 * @category Dijkstra
 */
public class BOJ_14938_서강그라운드 {
	static class Node{
		int vertex;
		Node link;
		int weight;
		
		public Node(int vertex, Node link, int weight) {
			super();
			this.vertex = vertex;
			this.link = link;
			this.weight = weight;
		}
		
	}
	static StringTokenizer st;
	static int n,m,r;
	static Node[] list;
	static boolean visited[];
	static int[] items;
	static int[] distance;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		items=new int[n+1];
		for (int i = 1; i < n+1; i++) {
			items[i]=Integer.parseInt(st.nextToken());
		}
		list=new Node[n+1];
		for (int i = 0; i < r; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			list[a]=new Node(b, list[a], w);
			list[b]=new Node(a, list[b], w); //양방향 그래프
		}// 입력 완료
		
		result=Integer.MIN_VALUE;
		distance=new int[n+1];
		
		// 각 노드마다 구함 
		for (int i = 1; i < n+1; i++) {
			visited=new boolean[n+1];
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[i]=0;
			dijkstra(i);
		}
		System.out.println(result);

	}

	private static void dijkstra(int start) {
		int score=items[start];
		for (int i = 0; i < n; i++) {
			int v=0;
			int min=Integer.MAX_VALUE;
			for (int j = 1; j < n+1; j++) {
				if(!visited[j] && min>distance[j]) {
					min=distance[j];
					v=j;
				}
			}
			visited[v]=true;
			for (Node next=list[v]; next!=null; next=next.link) {
				if(!visited[next.vertex] && distance[next.vertex]>min+next.weight) {
					distance[next.vertex]=min+next.weight;
				}
			}
		}// 최단거리 구함
		
		// 아이템 점수 구하기
		for (int i = 1; i < n+1; i++) {
			if(distance[i]<=m && i!=start) {
				score+=items[i];
			}
		}
		result=result<score?score:result;
	}
}

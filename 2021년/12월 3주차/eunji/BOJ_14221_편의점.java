package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 
 * @author CHO
 * @see https://www.acmicpc.net/problem/14221
 * @category Dijkstra 
 * 모든 지점을 다익스트라 하지 않고, 편의점 - 집만 갈 수 있도록 해야함.
 */
public class BOJ_14221_편의점 {
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
	static int N,M;
	static Node[] list;
	static boolean[] visited;
	static int result;
	static int result_distance;
	static int[] home;
	static int[] conv;
	static ArrayList<int[]> arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list=new Node[N+1];
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[a]=new Node(b, list[a], w);
			list[b]=new Node(a, list[b], w);
		}
		st=new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		home=new int[p];
		conv=new int[q];
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i < p; i++) {
			home[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(home); // 정점 번호 낮은순
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i < q; i++) {
			conv[i]=Integer.parseInt(st.nextToken());
		}// 입력 완료
		
		// 최단거리 구하기 Dijkstra
		int[] distance=new int[N+1];
		result=Integer.MAX_VALUE; // 정점 번호
		result_distance=Integer.MAX_VALUE; //최소 정점의 거리
		arr=new ArrayList<int[]>();
		
		for (int i = 0; i < conv.length; i++) {
			visited=new boolean[N+1];
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[conv[i]]=0; // 시작점 설정 - 변수로 지정
			dijkstra(conv[i],distance);
		}
		
		// arr에 담긴 노드들 중 가장 작은 거리의 노드 저장. 
		// 만약, 같은 노드가 나온다면 math.min으로 작은 노드 저장 
		for (int i = 0; i < arr.size(); i++) {
			if(result_distance>arr.get(i)[1]) {
				result_distance=arr.get(i)[1];
				result=arr.get(i)[0];
			}else if(result_distance==arr.get(i)[1]) {
				result=Math.min(result, arr.get(i)[0]);
			}
		}	
		System.out.println(result);
	}

	private static void dijkstra(int current, int[] distance) {
		int vv=0;
		outer: for (int i = 0; i < N; i++) {
			int min=Integer.MAX_VALUE;
			int v=0;
			for (int j = 1; j < N+1; j++) {
				if(!visited[j] && min>distance[j]) {
					min=distance[j];
					v=j;
				}
			}
			visited[v]=true;
			// 집을 발견하면 stop
			for (int j = 0; j < home.length; j++) {
				if(v==home[j]) {
					vv=v;
					break outer;
				}
			}
			for (Node next=list[v]; next!=null; next=next.link) {
				if(!visited[next.vertex] && distance[next.vertex]>distance[v]+next.weight) {
					distance[next.vertex]=distance[v]+next.weight;
				}
			}
		}
		arr.add(new int[] {vv,distance[vv]});
	}
}
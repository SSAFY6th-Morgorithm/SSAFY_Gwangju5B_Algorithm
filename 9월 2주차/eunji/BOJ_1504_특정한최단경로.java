import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1504_특정한최단경로 {
	static class Node{
		int vertex;
		int weight;
		Node link;
		
		public Node(int vertex, int weight, Node link) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.link = link;
		}
		
	}
	static Node[] NodeList;
	static StringTokenizer st;
	static int[] distance;
	static int N;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		NodeList=new Node[N+1];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// 양방향
			NodeList[a]=new Node(b, c, NodeList[a]);
			NodeList[b]=new Node(a, c, NodeList[b]);
		}
		
		st = new StringTokenizer(br.readLine());
		int mid1 = Integer.parseInt(st.nextToken());
		int mid2 = Integer.parseInt(st.nextToken());
		long result=0;

		// 1->mid1->mid2->end
		// 1->mid2->mid1->end
		// 1에서 mid1 = d1[m1]
		// mid1에서 mid2 = m1[mid2]
		// mid2에서 N = m2[N]
		distance=new int[N+1];
		int[] d1=dijkstra(1);
		distance=new int[N+1];
		int[] m1=dijkstra(mid1);
		distance=new int[N+1];
		int[] m2=dijkstra(mid2);

		/*
		 * 경로가 없을 경우 distance에서는 Integer.MAX_VALUE가 들어가게 되는데 
		 * 이때, 다른 값을 더하면 int의 범위를 넘게 되므로 long으로 타입을 바꿔주었음
		 */
		long x=(long)d1[mid1]+m1[mid2]+m2[N];
		long y=(long) d1[mid2]+m2[mid1]+m1[N];
		
		result=x>=y?y:x;
	
		if (result>=Integer.MAX_VALUE) {
			System.out.println("-1");
		}else System.out.println(result);
		
	}
	private static int[] dijkstra(int e) {
		boolean visited[]=new boolean[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[e]=0;
		
		for (int v = 1; v < N+1; v++) {
			int min=Integer.MAX_VALUE;
			int currV=0;
			
			for (int i = 0; i < distance.length; i++) {
				if(!visited[i]&&min>distance[i]) {
					min=distance[i];
					currV=i;
				}
			}
			visited[currV]=true;
			
			for (Node next=NodeList[currV]; next!=null; next=next.link) {
				if(!visited[next.vertex]&&distance[next.vertex]>min+next.weight) {
					distance[next.vertex]=min+next.weight;
				}
			}
		}
		return distance;
	}

}

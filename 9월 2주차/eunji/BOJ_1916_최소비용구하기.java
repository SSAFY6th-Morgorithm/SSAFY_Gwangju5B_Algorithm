import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1916_최소비용구하기 {
	
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
	
	
	static StringTokenizer st;
	static Node[] NodeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		NodeList=new Node[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			NodeList[v]=new Node(e, w, NodeList[v]);
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		boolean visited[]=new boolean[N+1];
		int distance[]=new int[N+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start]=0;
		
		for (int v = 1; v < N+1; v++) {
			int min=Integer.MAX_VALUE;
			int currV=0;
			for (int i = 0; i < distance.length; i++) {
				if(!visited[i] && min>distance[i]) {
					min=distance[i];
					currV=i;
				}
			}
			visited[currV]=true;
			if (currV==end) break;
			
			for (Node node=NodeList[currV]; node != null; node=node.link) {
				if(!visited[node.vertex] && distance[node.vertex]>min+node.weight) {
					distance[node.vertex]=min+node.weight;
				}
			}
		}
		System.out.println(distance[end]);

	}

}

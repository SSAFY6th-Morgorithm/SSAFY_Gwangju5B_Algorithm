import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 340280	1744
 * @author Administrator
 * @see https://www.acmicpc.net/problem/1647
 * @category MST-PRIM(우선순위 큐)
 */
public class BOJ_1647_도시분할계획 {
	static class Node implements Comparable<Node>{
		int vertex;
		Node link;
		int weight;
		
		public Node(int vertex, Node link, int weight) {
			super();
			this.vertex = vertex;
			this.link = link;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight-o.weight;
		}
		
	}
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Node[] list=new Node[N+1];
		
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int weight=Integer.parseInt(st.nextToken());
			list[x]=new Node(y, list[x], weight);
			list[y]=new Node(x, list[y], weight);
			
		}
		
		PriorityQueue<Node> pq=new PriorityQueue<>();
		int result=0;
		boolean[] visited=new boolean[N+1];
		int max=Integer.MIN_VALUE;
		
		pq.add(new Node(1,list[1],0));
		
		while(!pq.isEmpty()) {
			Node n=pq.poll();
			if(visited[n.vertex]) continue;
			visited[n.vertex]=true;
			result+=n.weight;
			max=max<n.weight?n.weight:max;
			for (Node node=list[n.vertex]; node!=null; node=node.link) {
				if(!visited[node.vertex]) pq.add(node);
			}
		}

		System.out.println(result-max);
	}
}

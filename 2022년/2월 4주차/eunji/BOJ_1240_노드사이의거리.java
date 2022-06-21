import java.io.*;
import java.util.*;

/**
 * 41820	220	
 * @author CHO
 * @see https://www.acmicpc.net/problem/1240
 * @category BFS, 그래프
 *
 */
public class BOJ_1240_노드사이의거리 {
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
	static int N,M,start,end;
	static Node[] list;
	static StringBuilder sb=new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list=new Node[N+1];
		for (int i = 0; i < N-1; i++) {
			st=new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[x]=new Node(y, w, list[x]);
			list[y]=new Node(x, w, list[y]);
		}
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			int result=bfs(start);
			sb.append(result+"\n");
		}
		System.out.println(sb);

	}

	private static int bfs(int i) {
		Queue<int[]> q=new LinkedList<>();
		boolean[] vis=new boolean[N+1];
		vis[i]=true;
		q.add(new int[] {i,0});
		while(!q.isEmpty()) {
			int[] cur=q.poll();
			if(cur[0]==end) {
				return cur[1];
			}
			for (Node next=list[cur[0]]; next!=null; next=next.link) {
				if(!vis[next.vertex]) {
					vis[next.vertex]=true;
					q.add(new int[] {next.vertex,cur[1]+next.weight});
				}
			}
		}
		return 0;
	}
}

package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1753 {
	static class Node implements Comparable<Node>{
	    int e, w;

	    public Node(int e, int w){
	        this.e = e;
	        this.w = w;
	    }

	    @Override
	    public int compareTo(Node o) {
	        return w - o.w;
	    }
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		List<Node>[] list = new ArrayList[V+1];
		int[] arr = new int[V+1];
		
		for(int i=1;i<=V;i++) {
			arr[i] = Integer.MAX_VALUE;
		}
		
		for(int i=1;i<=V;i++){
            list[i] = new ArrayList<>();
        }
		
		for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            list[s].add(new Node(e, w));
        }
		
		PriorityQueue<Node> q = new PriorityQueue<>();
		boolean[] check = new boolean[V+1];
		q.add(new Node(K,0));
		arr[K] = 0;
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			int cur = n.e;
			
			if(check[cur])
				continue;
			check[cur] = true;
			
			for(Node node : list[cur]) {
				if(arr[node.e]>arr[cur]+node.w) {
					arr[node.e]= arr[cur] + node.w;
					q.add(new Node(node.e,arr[node.e]));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=V;i++) {
			if(arr[i]==Integer.MAX_VALUE)
				sb.append("INF\n");
			else
				sb.append(arr[i]+"\n");
		}
		
		System.out.println(sb);
	}

}

package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * DP, BFS, Node 이용
 * @author CHO
 * @see https://www.acmicpc.net/problem/15681
 * 골드5
 * 
 */
public class BOJ_15681_트리와쿼리4 {
	static class Node{
		int vertex;
		Node link;
		
		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", link=" + link + "]";
		}
		
	}
	static Node[] nodeList;
	static StringBuilder sb=new StringBuilder();
	static StringTokenizer st;
	static int N,R,Q;
	static ArrayList<ArrayList<Integer>> arr;
	static boolean[] visited;
	static int size;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		Q=Integer.parseInt(st.nextToken());
		
		// 그래프 입력 값 받음
		arr=new ArrayList<>();
		for (int i = 0; i < N+1; i++) {
			arr.add(i,new ArrayList<Integer>());
		}
		for (int i = 0; i < N-1; i++) {
			st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			arr.get(start).add(end);
			arr.get(end).add(start); // 양방향
		}

		visited=new boolean[N+1];
		nodeList=new Node[N+1];
		bfs(R); //루트 R을 중심으로 트리 생성
		
		System.out.println();
		System.out.println("완성된 트리 :: "+ Arrays.toString(nodeList));
		
		// 각 노드별 서브트리의 정점 개수 구함 --> size 배열에 저장
		int size[]=new int[N+1];
		dfs(R,size);
		
		for (int i = 0; i < Q; i++) {
			int q=Integer.parseInt(br.readLine());
			sb.append(size[q]).append("\n");
		}
		System.out.println(sb);
	}
	

	private static void dfs(int current,int size[]) {
		size[current]=1; // 자기자신 포함하므로
		
		// 연결된 노드가 없을 때 까지 반복
		for (Node node=nodeList[current]; node!=null; node=node.link) {
			dfs(node.vertex,size);
			size[current]+=size[node.vertex];
		}
	}

	private static void bfs(int root) {
		Queue<Integer> queue=new LinkedList<Integer>();
		queue.add(root);
		visited[root]=true;
		while(!queue.isEmpty()) {
			int curr=queue.poll();
			for (int i = 0; i < arr.get(curr).size(); i++) {
				int next=arr.get(curr).get(i);
				if(!visited[next]) {
					nodeList[curr]=new Node(next, nodeList[curr]);
					queue.add(next);
					visited[next]=true;
				}
			}
		}
	}
}

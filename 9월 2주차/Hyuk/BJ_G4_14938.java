package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_G4_14938 {
	static int N, M, R;
	static StringTokenizer st;
	static final int INFINITY = Integer.MAX_VALUE;

	static class Node {
		int vertex;
		int weight;
		Node link;

		public Node(int vertex, int weight, Node link) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.link = link;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", weight=" + weight + ", link=" + link + "]\n";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		int[] item = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		boolean[] isGetted = new boolean[N + 1];
		Node[] adjArray = new Node[N + 1];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adjArray[s] = new Node(e, w, adjArray[s]);
			adjArray[e] = new Node(s, w, adjArray[e]);
		}

		int current = 1;
		int minDist = INFINITY;
		int answer = 0;
		for (int step = 1; step <= N; step++) {
			current = step;
			int[] distance = new int[N + 1];
			Arrays.fill(distance, INFINITY);
			boolean[] visited = new boolean[N + 1];

			distance[current] = 0;
			for (int i = 1; i <= N; i++) {
				int min = INFINITY;
				for (int j = 1; j <= N; j++) {
					if (!visited[j] && distance[j] < min) {
						min = distance[j];
						current = j;
					}
				}
				visited[current] = true;

				for (Node temp = adjArray[current]; temp != null; temp = temp.link) {
					if (!visited[temp.vertex] && distance[temp.vertex] > min + temp.weight && min + temp.weight <= M)
						distance[temp.vertex] = min + temp.weight;
				}
			}
//			System.out.println(Arrays.toString(distance));
			int sum=0;
			for (int i = 1; i <= N ; i++) {
				if(distance[i] >-1 && distance[i] < INFINITY)
					sum += item[i];
			}
			answer = Math.max(sum, answer);
		}
		System.out.println(answer);
	}
}

package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_S1_14221 {
	static int N, M;
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

		Node[] adjArray = new Node[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adjArray[s] = new Node(e, w, adjArray[s]);
			adjArray[e] = new Node(s, w, adjArray[e]);
		}

		st = new StringTokenizer(br.readLine());
		int[] Home = new int[Integer.parseInt(st.nextToken())];
		int[] Convenience = new int[Integer.parseInt(st.nextToken())];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Home.length; i++) {
			Home[i] = Integer.parseInt(st.nextToken());

		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Convenience.length; i++) {
			Convenience[i] = Integer.parseInt(st.nextToken());
		}

		int current = 0;
		int minDist = INFINITY;
		int answer = INFINITY;
		for (int step = 0; step < Convenience.length; step++) {
			current = Convenience[step];

			int[] distance = new int[N + 1];
			Arrays.fill(distance, INFINITY);
			boolean[] visited = new boolean[N + 1];

			distance[current] = 0;
			outer : for (int i = 1; i <= N; i++) {
				int min = INFINITY;
				for (int j = 1; j <= N; j++) {
					if (!visited[j] && distance[j] < min) {
						min = distance[j];
						current = j;
					}
				}
				for (int k = 0; k < Home.length; k++) {
					if(current == Home[k])
						break outer;
				}
				visited[current] = true;
				for (Node temp = adjArray[current]; temp != null; temp = temp.link) {
					if (!visited[temp.vertex] && distance[temp.vertex] > min + temp.weight)
						distance[temp.vertex] = min + temp.weight;

				}
				
			}
			for (int i = 0; i < Home.length; i++) {
				int cur = Home[i];
				if (minDist > distance[cur]) {
					minDist = distance[cur];
					answer = cur;
				} else if (minDist == distance[cur]) {
					answer = Math.min(cur, answer);
				}

			}
//			System.out.println(Arrays.toString(distance));

		}
		System.out.println(answer);
	}
}

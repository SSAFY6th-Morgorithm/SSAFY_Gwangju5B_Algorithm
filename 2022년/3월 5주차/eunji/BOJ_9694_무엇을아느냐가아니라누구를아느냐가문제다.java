package algo;

import java.io.*;
import java.util.*;

/**
 * 38228	284
 * @author CHO
 * @see https://www.acmicpc.net/problem/9694
 * @category ���ͽ�Ʈ��
 */
public class BOJ_9694_�������ƴ��İ��ƴ϶󴩱����ƴ��İ������� {
	static class Node {
		int vertex;
		int weight;
		Node link;

		public Node(int vertex, int weight, Node link) {
			this.vertex = vertex;
			this.weight = weight;
			this.link = link;
		}
	}

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // ������ ����
			int M = Integer.parseInt(st.nextToken()); // ��ġ���� ��
			Node list[] = new Node[M];
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				list[x] = new Node(y, z, list[x]);
				list[y] = new Node(x, z, list[y]);
			}
			// 0�� ����, M-1�� �ְ�����

			int[] distance = new int[M];
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[0] = 0;
			boolean[] vis = new boolean[M];

			int parents[] = new int[M];
			boolean flag = true;

			for (int i = 0; i < M; i++) {
				int min = Integer.MAX_VALUE;
				int v = -1;
				for (int j = 0; j < distance.length; j++) {
					if (!vis[j] && min > distance[j]) {
						min = distance[j];
						v = j;
					}
				}
				if (v == -1) {
					break;
				}
				vis[v] = true;
				if (v == M - 1) {
					break;
				}
				for (Node next = list[v]; next != null; next = next.link) {
					if (!vis[next.vertex] && distance[next.vertex] > min + next.weight) {
						distance[next.vertex] = min + next.weight;
						parents[next.vertex] = v;
					}
				}
			}

			sb.append("Case #" + (t + 1) + ": ");
			if (distance[M - 1] != Integer.MAX_VALUE) {
//				System.out.println(Arrays.toString(parents));
				Stack<Integer> stack = new Stack<>();
				int cur = M - 1; // �ڿ������� �ִܰ�� ���ϱ�
				for (int i = 0; i < M; i++) {
					if (cur == 0) // �������� ������ �ߴ�
						break;
					stack.push(cur);
					cur = parents[cur];
				}
				stack.push(cur);

				while (!stack.isEmpty()) {
					sb.append(stack.pop() + " "); // ������� ���
				}
			} else {
				sb.append(-1);
			}
			sb.append("\n");

		}
		System.out.println(sb);

	}
}

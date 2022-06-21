import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ22868_산책small {
	static class Node {
		int to;
		Node link;

		public Node(int to, Node link) {
			this.to = to;
			this.link = link;
		}
	}

	static class Info {
		int vertex, moveCount;
		String path;

		public Info(int vertex, int moveCount, String path) {
			this.vertex = vertex;
			this.moveCount = moveCount;
			this.path = path;
		}

	}

	static int N, M, S, E, answer;
	static String optimalPath;
	static Node[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new Node[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}
		st = new StringTokenizer(input.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		answer += goToDestination();
		answer += backToStart();
		System.out.println(answer);
		System.out.println(optimalPath);
	}

	private static int goToDestination() {
		// TODO Auto-generated method stub
		optimalPath = "";
		Queue<Info> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		visited[S] = true;
		int shortestPath = Integer.MAX_VALUE;
		q.offer(new Info(S, 0, S + " ")); // vertex, moveCount, path
		while (!q.isEmpty()) {
			Info tmp = q.poll();
			Node current = adjList[tmp.vertex];
			int moveCount = tmp.moveCount;
			String path = tmp.path;
			for (Node node = current; node != null; node = node.link) {
				int nextVertex = node.to;
				if (nextVertex == E && shortestPath >= moveCount + 1) {
					shortestPath = moveCount + 1;
					if (optimalPath.equals("") || optimalPath.compareTo(path) > 0) {
						optimalPath = path;
						continue;
					}
				}
				if (!visited[nextVertex]) {
					visited[nextVertex] = true;
					q.offer(new Info(nextVertex, moveCount + 1, path + nextVertex + " "));
				}
			}
		}
		return shortestPath;
	}

	private static int backToStart() {
		// TODO Auto-generated method stub
		// optimalWay를 가지고 안가야할 노드 결정
		Queue<int[]> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		StringTokenizer st = new StringTokenizer(optimalPath);
		st.nextToken();
		while (st.hasMoreTokens()) {
			visited[Integer.parseInt(st.nextToken())] = true;
		}
		visited[E] = true;
		q.offer(new int[] { E, 0 });
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			if (tmp[0] == S) {
				return tmp[1];
			}
			Node current = adjList[tmp[0]];
			int moveCount = tmp[1];
			for (Node node = current; node != null; node = node.link) {
				int nextVertex = node.to;

				if (!visited[nextVertex]) {
					visited[nextVertex] = true;
					q.offer(new int[] { nextVertex, moveCount + 1 });
				}
			}
		}
		return -1;
	}

}

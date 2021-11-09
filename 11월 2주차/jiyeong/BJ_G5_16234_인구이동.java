import java.io.*;
import java.util.*;

public class BJ_G5_16234_인구이동 {

	static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static int N, L, R;
	static boolean[][] visited;
	static int[][] map, copiedMap;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean canTurn;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(str));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
			
		System.out.println(sol());
	}

	private static int sol() {
		int day = 0;
		while(true) {
			boolean isPossible = false;
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						if(bfs(i,j)) {
							isPossible = true;
						}
					}
				}
			}
			if(!isPossible) return day;
			day++;
		}
		
	}

	private static boolean bfs(int r, int c) {
		Queue<Node> q = new LinkedList<>();
		Queue<Node> moveq = new LinkedList<>(); // 인구이동이 필요한 노드

		q.offer(new Node(r,c));
		moveq.offer(new Node(r,c));
		visited[r][c] = true;

//		int cnt = 1;
		int sum = map[r][c];

		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (isIn(nr, nc) && Math.abs(map[cur.r][cur.c] - map[nr][nc]) >= L
						&& Math.abs(map[cur.r][cur.c] - map[nr][nc]) <= R && !visited[nr][nc]) {
					sum += map[nr][nc];
//					cnt++;
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc));
					moveq.offer(new Node(nr,nc));
				}
			}
		}
		
//		if(cnt<=1) return false;
		if(moveq.size() <= 1) return false;

		int avgnum = sum / moveq.size();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
//				if (visited[i][j]) {
//					map[i][j] = avgnum;
//				}
				for(Node n: moveq) {
					map[n.r][n.c] = avgnum;
				}
			}
		}
		
		return true;
		
//		for(int[] row:map) {
//			for(int r : row)
//				System.out.print(r+" ");
//			System.out.println();
//		}

	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static String str = "4 10 50\r\n" + 
			"10 100 20 90\r\n" + 
			"80 100 60 70\r\n" + 
			"70 20 30 40\r\n" + 
			"50 20 100 10";
}

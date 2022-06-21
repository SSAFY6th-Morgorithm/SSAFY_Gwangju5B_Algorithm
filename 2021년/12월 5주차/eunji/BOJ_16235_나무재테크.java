import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 163004	1092
 * @author CHO
 * @see https://www.acmicpc.net/problem/16235
 * @category 구현
 * 우선순위 큐 (나이만 정렬) + 리스트 사용
 */
public class BOJ_16235_나무재테크 {
	static class Tree implements Comparable<Tree> {
		int x;
		int y;
		int age;

		public Tree(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}

	static StringTokenizer st;
	static int N;
	static int[][] dir = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N + 1][N + 1];
		int[][] A = new int[N + 1][N + 1];

		for (int i = 1; i < N + 1; i++) {
			Arrays.fill(map[i], 5);
		} // 가장 처음 양분은 5
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		PriorityQueue<Tree> pq = new PriorityQueue<>(); // 나무 정렬
		ArrayList<Tree> dead = new ArrayList<>(); // 죽은 나무
		ArrayList<Tree> temp = new ArrayList<>(); // 임시 배열
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			pq.add(new Tree(x, y, age));
		} // 입력 완료

		for (int k = 0; k < K; k++) {
			// 봄
			while (!pq.isEmpty()) {
				Tree tree = pq.poll();
				if (map[tree.x][tree.y] >= tree.age) {
					map[tree.x][tree.y] -= tree.age;
					tree.age = tree.age + 1;
					temp.add(tree);
				} else {
					dead.add(tree);
				}
			}

			// 여름
			for (int i = 0; i < dead.size(); i++) {
				Tree tree = dead.get(i);
				map[tree.x][tree.y] += (int) tree.age / 2;
			}
			dead.clear();
			// 가을
			for (int i = 0; i < temp.size(); i++) {
				Tree tree = temp.get(i);
				pq.add(tree);
				if (tree.age % 5 == 0) {
					for (int j = 0; j < 8; j++) {
						int nx = tree.x + dir[j][0];
						int ny = tree.y + dir[j][1];
						if (isOkay(nx, ny)) {
							pq.add(new Tree(nx, ny, 1));
						}
					}
				}
			}
			temp.clear();
			// 겨울
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					map[i][j] += A[i][j];
				}
			}
		}
		System.out.println(pq.size());
	}

	private static boolean isOkay(int nx, int ny) {
		return nx > 0 && ny > 0 && nx <= N && ny <= N;
	}
}

package jan18_graph;

import java.io.*;
import java.util.*;

/**
 * 인접배열은 시간초과 인접리스트로~
 * 
 * @author yun
 *
 */

public class BJ_G5_13023_ABCDE {

	static int N, M;
	static ArrayList<Integer>[] list;
	static int res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}

		res = 0;
		for (int i = 0; i < N; i++) {
			if (res == 1) {
				break;
			}
			dfs(i, new boolean[N], 0);
		}
		System.out.println(res);
	}

	private static void dfs(int v, boolean[] visited, int cnt) {
		// 간선 4개 되면 끝
		if (cnt == 4) {
			res = 1;
			return;
		}
		visited[v] = true;

		for (int i = 0; i < list[v].size(); i++) {
			int cur = list[v].get(i);
			if (!visited[cur]) {
				visited[cur] = true;
				dfs(cur, visited, cnt + 1);
				// 모든 루트를 돌기 위해 false 처리해줌
				visited[cur] = false;
			}

		}

	}
}

package algo;

import java.io.*;
import java.util.*;

/**
 * 40960	400
 * @author CHO
 * @see https://www.acmicpc.net/problem/1937
 * @category DFS+DP
 */
public class BOJ_1937_욕심쟁이판다 {
	static StringTokenizer st;
	static int N, max;
	static int[][] map, dp;
	static int[][] dir = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 }, };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < N; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		} // 입력 완료
		max = 1; // 한번 먹고 시작하므로 1
		dp = new int[N][N];
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < N; m++) {
				// 방문하지 않은 지점만
				if(dp[n][m]==0) {
					int cnt=dfs(n,m);
					max=max<cnt?cnt:max;
				}
			}
		}
		System.out.println(max);
	}

	private static int dfs(int x, int y) {
		if(dp[x][y]!=0) return dp[x][y]; //이미 최대값이 저장되어 있을 경우 더이상 계산할 필요 X
		dp[x][y]=1;
		for (int i = 0; i < dir.length; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			if (nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] > map[x][y]) {
				dp[x][y]=Math.max(dp[x][y], dfs(nx,ny)+1); //내가 얼마나 갈 수 있는지 최대값 저장
			}
		}
		return dp[x][y];
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 126556	704
 * @author CHO
 * @see https://www.acmicpc.net/problem/11660
 * @category 구간 합
 * 그림 그려보기
 */
public class BOJ_11660_구간합구하기5 {
	static StringTokenizer st;
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map=new int[N+1][N+1];
		for (int n = 1; n < N+1; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 1; m < N+1; m++) {
				int curr=Integer.parseInt(st.nextToken());
				map[n][m]=map[n-1][m]+map[n][m-1]+curr-map[n-1][m-1];
			}
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			sb.append(map[x2][y2]-map[x2][y1-1]-map[x1-1][y2]+map[x1-1][y1-1]).append("\n");
		}
		System.out.println(sb);
	}
}

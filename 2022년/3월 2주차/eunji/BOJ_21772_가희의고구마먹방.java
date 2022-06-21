package algo;

import java.io.*;
import java.util.*;

/**
 * 18864	348
 * @author CHO
 * @see https://www.acmicpc.net/problem/21772
 * @category DFS
 * 구현, 방문처리 X
 */
public class BOJ_21772_가희의고구마먹방 {

	static StringTokenizer st;
	static int R, C, T, max;
	static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		int X = 0, Y = 0;
		max=0;
		for (int r = 0; r < R; r++) {
			String input = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c] == 'G') {
					X = r;
					Y = c;
					map[r][c]='.';
				}
			}
		} // 입력완료
		sweet_potato(X, Y, 0, 0);
		System.out.println(max);

	}

	private static void sweet_potato(int x, int y, int time, int sp) {
		if (time == T) {
			max = max < sp ? sp : max;
			return;
		}
		
		sweet_potato(x, y, time+1, sp);
		for (int d = 0; d < dir.length; d++) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];
			if (nx >= 0 && ny >= 0 && nx < R && ny < C && map[nx][ny] != '#') {
				if (map[nx][ny] == 'S') {
					map[nx][ny]='.';
					sweet_potato(nx, ny, time+1, sp+1);
					map[nx][ny]='S';
				} else {
					sweet_potato(nx, ny, time+1, sp);
				}
			}
		}
		
	}
}

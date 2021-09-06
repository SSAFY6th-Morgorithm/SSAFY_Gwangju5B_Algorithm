package week9_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//시간제한: 초
public class BOJ_G5_연구소 {
	private static int[][] map, copy_map;
	private static int R;
	private static int C;
	static int max ;
	static int[][] vector = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		copy_map = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		copy_map=copy(map,copy_map);
	
		createWall(0);
		
		System.out.println(max);

	}

	private static int[][] copy(int[][] map2, int[][] copy_map2) {
		for(int r=0;r<map2.length;r++) {
			for(int c=0;c<map2[r].length;c++) {
				copy_map2[r][c] = map2[r][c]; 
			}
		}
		return copy_map2;
		
	}

	private static void createWall(int count) {

		if (count == 3) {
			max=Math.max(goVirus(),max);
			copy_map=copy(map,copy_map);
			return;
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c]==0) {
					map[r][c] = 1;
					createWall(count + 1);
					map[r][c] = 0;
				}
			}
		}
	}

	private static int goVirus() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (copy_map[r][c] == 2) {
					dfs(r,c);
				}
			}
		}
		return checkSafeArea();
	}

	private static void dfs(int r,int c) {
		for(int d=0;d<4;d++) {
			int nr= r+vector[d][0];
			int nc= c+vector[d][1];
				if(isValuable(nr,nc)) {
					copy_map[nr][nc]=2;
					dfs(nr,nc);
				}
		}
	}

	private static int checkSafeArea() {
		int count=0;
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				if(copy_map[r][c]==0) {
					count++;
				}
			}
		}
		return count;
	}

	private static boolean isValuable(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C && copy_map[nr][nc] == 0;
	}
}

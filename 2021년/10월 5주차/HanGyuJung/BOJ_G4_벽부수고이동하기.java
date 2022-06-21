package day1004;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간제한: 초
public class BOJ_G4_벽부수고이동하기 {
	static int[][] map;
	static ArrayList<int[]> walls = new ArrayList<>();
	static int[][] vector = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int R,C;
	private static int step;
	private static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		

		for (int n = 0; n < R; n++) {
			String line = br.readLine();
			for (int m = 0; m < C; m++) {
				map[n][m] = line.charAt(m) - '0';
				if (map[n][m] == 1) {
					walls.add(new int[] { n, m });
				}
			}
		}
		//bfs 시작 
		
		Queue<int[]> q = new LinkedList<>();
		while (!walls.isEmpty()) {
			int step=1; //이동거리
			boolean[][] visited = new boolean[R][C];
			int[] xWall = walls.remove(0);
			map[xWall[0]][xWall[1]] = 0; // 일단 벽 하나는 허물기
			q.add(new int[] { 0, 0 });
			boolean flag=false;
			
			label: while (!q.isEmpty()) {
				int size = q.size();
				for (int n = 0; n < size; n++) {
					int[] temp = q.poll();
					for (int d = 0; d < vector.length; d++) {
						int nr = temp[0] + vector[d][0];
						int nc = temp[1] + vector[d][1];
						if( nr == R-1 && nc == C-1) {
							flag = true;
							break label;
						}
						if (isIn(nr, nc) && map[nr][nc]==0 && !visited[nr][nc]) {
							q.add(new int[] {nr,nc});
							visited[nr][nc]=true;
						}
					}

				}
				step++;
			}
			if(flag) {
				step++;
				answer  = Math.min(step, answer);
			}
			map[xWall[0]][xWall[1]] = 1;  //bfs 끝나고 벽 다시 세우기
		}
		
		if(answer==Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(answer);
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}

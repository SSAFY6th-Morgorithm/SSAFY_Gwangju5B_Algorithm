import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_17129 {

	static int n;	// 세로
	static int m;	// 가로
	static int[][] land;	// 정보섬
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// 윌리암슨수액빨이딱따구리가 정보섬에 올라온 이유
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		land = new int[n][m];
		
		Queue<Dot> queue = new LinkedList<>();
		boolean[][] visited = new boolean[n][m];	// 방문 확인
		
		for(int r=0; r<n; r++) {
			String str = br.readLine();
			for(int c=0; c<m; c++) {
				land[r][c] = str.charAt(c)-'0';
				if(land[r][c]==2) {		// 윌리암슨 식구 위치
					queue.offer(new Dot(r,c,0));
					visited[r][c] = true;
				}
			}
		}
		
		int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};	// 상하좌우
		long result = 0;
		while(!queue.isEmpty()) {
			Dot current = queue.poll();
			
			if(land[current.x][current.y]!=0 && land[current.x][current.y]!=2) {
				result = current.cost;
				break;
			}
			
			for(int d=0; d<4; d++) {
				int nr = current.x + deltas[d][0];
				int nc = current.y + deltas[d][1];
				
				if(isIn(nr,nc) && land[nr][nc]!=1 && !visited[nr][nc]) {
					queue.offer(new Dot(nr,nc,current.cost+1));
					visited[nr][nc] = true;
				}
			}
		}
		
		if(result == 0) {
			System.out.println("NIE");
		}else {
			sb.append("TAK").append("\n").append(result);
			System.out.println(sb);
		}
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<n && c>=0 && c<m;
	}
	
	static class Dot{
		int x, y, cost;

		public Dot(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
}
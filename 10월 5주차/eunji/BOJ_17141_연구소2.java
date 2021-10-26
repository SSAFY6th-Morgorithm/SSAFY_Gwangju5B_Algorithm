import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 39428	232
 * @author CHO
 * @see https://www.acmicpc.net/problem/17141
 * @category BFS
 * 97%에서 오답 -> 5 2
				1 1 1 1 1
				1 1 2 1 1
				1 1 2 1 1
				1 1 1 1 1
				1 1 1 1 1 
				답:0 해당 경우 고려하지 못했음. 
 */
public class BOJ_17141_연구소2 {
	static class loc{
		int x;
		int y;
		
		public loc(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static StringTokenizer st;
	static int N,M;
	static int[][] map,ori;
	static ArrayList<loc> huboList;
	static int[] pick;
	static int min;
	static int[][] dir= {{0,1},{0,-1},{1,0},{-1,0}};
	static int first;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map=new int[N][N];
		huboList=new ArrayList<>(); // 바이러스를 놓을 수 있는 위치들
		
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					huboList.add(new loc(i, j));
				}else if(map[i][j]==1) first++;
			}
		}// 입력 완료
		
		if((N*N)-first-M==0) {
			System.out.println(0);
		}else {
			min=Integer.MAX_VALUE;
			pick=new int[M];
			combination(0,0);
			if(min==Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(min);
		}

	}
	private static void combination(int start, int cnt) {
		if(cnt==M) {
			// 바이러스 퍼뜨려봄
			int time=virus(pick);
			// 최소 시간 구하기 
			min=min>time?time:min;
			return;
		}
		for (int i = start; i < huboList.size(); i++) {
			pick[cnt]=i;
			combination(i+1, cnt+1);
		}
		
	}
	private static int virus(int[] pick) {
		int time=1;
		int cnt=0;
		Queue<loc> q=new LinkedList<>();
		boolean[][] visited=new boolean[N][N];
		for (int i = 0; i < pick.length; i++) {
			loc h=huboList.get(pick[i]);
			q.add(h);
			visited[h.x][h.y]=true;
		}// 바이러스의 위치 큐에 저장, 방문 처리
		
		while(!q.isEmpty()) {
			int size=q.size();
			while(size-->0) {
				loc curr=q.poll();
				// 4방탐색
				for (int i = 0; i < 4; i++) {
					int nx=curr.x+dir[i][0];
					int ny=curr.y+dir[i][1];
					if(isIn(nx,ny)&&!visited[nx][ny]&&map[nx][ny]!=1) {
						// 갈 수 있음
						visited[nx][ny]=true;
						cnt++;
						q.add(new loc(nx,ny));
						if(cnt==(N*N)-first-M) return time;
					}
				}
			}
			time++;
		}
		return Integer.MAX_VALUE;
	}
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}

}

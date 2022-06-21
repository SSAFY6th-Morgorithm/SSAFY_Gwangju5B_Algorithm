import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 38668	236
 * @author CHO
 * @see https://www.acmicpc.net/problem/17142
 * @category BFS
 * 주의! 문제 잘 읽기 -> 모든 빈칸에 바이러스가 퍼졌을 때
 */
public class BOJ_17142_연구소3 {
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
	static int N,M,zero,min;
	static int[][] map,ori;
	static ArrayList<loc> huboList;
	static int[] pick;
	static int[][] dir= {{0,1},{0,-1},{1,0},{-1,0}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][N];
		ori=new int[N][N];
		
		huboList=new ArrayList<>();// 바이러스 위치 후보
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				ori[i][j]=map[i][j];
				if(map[i][j]==2) huboList.add(new loc(i, j));
				else if(map[i][j]==0) zero++; // 빈칸의 개수
			}
		}// 입력 완료
		if(zero==0) System.out.println(0); //빈칸이 없으므로 0
		else {
			pick=new int[M];
			min=Integer.MAX_VALUE;
			combination(0,0);
			if(min==Integer.MAX_VALUE) System.out.println(-1);
			else {
				System.out.println(min);
			}
		}
		
	}

	private static void combination(int start, int cnt) {
		if(cnt==M) {
			//바이러스 퍼뜨리기
			int time=virus();
			//최소 시간 구하기
			min=min>time?time:min;
			return;
		}
		for (int i = start; i < huboList.size(); i++) {
			//huboList의 index 저장
			pick[cnt]=i;
			combination(i+1, cnt+1);
		}
		
	}

	private static int virus() {
		int time=1;
		int cnt=0;
		//활성 바이러스가 비활성 칸으로 가면 비활성v->활성v
		Queue<loc> q=new LinkedList<>();
		boolean[][] visited=new boolean[N][N];
		for (int i = 0; i < pick.length; i++) {
			//선택한 huboList의 index에 있는 loc 큐에 저장, 방문처리
			loc curr=huboList.get(pick[i]);
			q.add(curr);
			visited[curr.x][curr.y]=true;
		}
		
		while(!q.isEmpty()) {
			int size=q.size();
			while(size-->0) {
				loc curr=q.poll();
				int x=curr.x;
				int y=curr.y;
				for (int i = 0; i < 4; i++) {
					int nx=x+dir[i][0];
					int ny=y+dir[i][1];
					if(isIn(nx,ny)&&!visited[nx][ny]) {
						if(map[nx][ny]==0) {
							visited[nx][ny]=true;
							q.add(new loc(nx, ny));
							cnt++;
						}else if(map[nx][ny]==2) {
							visited[nx][ny]=true;
							q.add(new loc(nx, ny));
							//cnt++; 빈칸일때만 센다
						}
						// 모든 빈칸에 바이러스가 퍼졌을 때
						if(cnt>=zero) return time;
					}
				}
			}
			time++;
			// 더이상 구할 필요 없으므로 넘김
			if(time>min) return Integer.MAX_VALUE;
		}
		//모든 빈칸에 바이러스 x
		return Integer.MAX_VALUE;
	}

	private static boolean isIn(int nx, int ny) {
		return nx>=0 && ny>=0 && nx<N && ny<N;
	}
}

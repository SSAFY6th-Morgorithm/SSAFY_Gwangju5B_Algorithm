import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 	29504	216
 * @author CHO
 * @see https://www.acmicpc.net/problem/21610
 * @category 2021 상반기 삼성 SW역테 기출, 대각선 범위 초과했을 때 계산
 */
public class BOJ_21610_마법사상어와비바라기 {
	static class Cloud{
		int x,y;

		public Cloud(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Cloud [x=" + x + ", y=" + y + "]";
		}
		
		
	}
	static StringTokenizer st;
	static int N,M;
	static int cDir[][]= {{},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
	static int dir[][]= {{-1,-1},{-1,1},{1,1},{1,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map=new int[N+1][N+1];
		int[][] ori=new int[N+1][N+1];
		
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N+1; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				ori[i][j]=map[i][j];
			}
		}
		
		int[][] mList=new int[M][2];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			mList[m][0]=Integer.parseInt(st.nextToken());
			mList[m][1]=Integer.parseInt(st.nextToken());
		}
		
//		System.out.println(Arrays.deepToString(map));
		
		// 구름의 위치가 들어 갈 
		Queue<Cloud> cloud=new LinkedList<>();
		
		// 초기위치 넣어줌
		cloud.add(new Cloud(N, 1));
		cloud.add(new Cloud(N, 2));
		cloud.add(new Cloud(N-1, 1));
		cloud.add(new Cloud(N-1, 2));
		
		int result=0;
		
		// 방문한 구름의 위치 표시
		boolean visited[][]=new boolean[N+1][N+1];
//		visited[N][1]=true;
//		visited[N][2]=true;
//		visited[N-1][1]=true;
//		visited[N-1][2]=true;
//		
		// M만큼 반복
		for (int t = 0; t < M; t++) {
			visited=new boolean[N+1][N+1];
			int d=mList[t][0]; //방향
			int s=mList[t][1]; //이동거리
			int size=cloud.size();
			// 현재 위치한 구름만큼만 반복
			while(size-->0) {
				Cloud c=cloud.poll();
				int x=c.x;
				int y=c.y;
//				System.out.println("처리중인 구름 x::"+x+" y::"+y);
				// 이동
				int nx=0;
				int ny=0;
				
				if((x+cDir[d][0]*s)%N<=0) {
					nx=(x+cDir[d][0]*s)%N+N;
				}else {
					nx=(x+cDir[d][0]*s)%N;
				}
				if((y+cDir[d][1]*s)%N<=0) {
					ny=(y+cDir[d][1]*s)%N+N;
				}else {
					ny=(y+cDir[d][1]*s)%N;
				}
				
//				System.out.println("구름위치 x::"+nx+" y::"+ny);
				map[nx][ny]+=1; // 구름으로 만들어줌
				visited[nx][ny]=true;
				cloud.add(new Cloud(nx, ny));
			}
			
			while(!cloud.isEmpty()) {
				Cloud c=cloud.poll();
				int x=c.x;
				int y=c.y;
				// 대각선 이동
				int cnt=0;
				for (int i = 0; i < 4; i++) {
					int nx=x+dir[i][0];
					int ny=y+dir[i][1];
					if(isOkayX(nx) && isOkayY(ny) && map[nx][ny]>0) cnt++;
				}
				
				map[x][y]+=cnt; // 대각선에 물 있는지 여부만큼 더해줌
			}
		
			// 이동 끝
			// 물의 양 2이상 -> 구름 생김 && 물 양 -2
			for (int i = 1; i < N+1; i++) {
				for (int j = 1; j < N+1; j++) {
					if(!visited[i][j]&&map[i][j]>=2) {
						cloud.add(new Cloud(i, j));
						map[i][j]-=2;
					}
				}
			}
			
			// 값 확인용
			if (t==M-1) {
				for (int i = 1; i < N+1; i++) {
					for (int j = 1; j < N+1; j++) {
						result+=map[i][j];
					}
				}
			}
		}
		System.out.println(result);
		
	}
	private static boolean isOkayX(int nx) {
		// TODO Auto-generated method stub
		return nx>0 && nx<=N;
	}
	private static boolean isOkayY(int ny) {
		// TODO Auto-generated method stub
		return ny>0 && ny<=N;
	}

}

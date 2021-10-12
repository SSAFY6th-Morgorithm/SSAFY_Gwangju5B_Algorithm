package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 14336	116
 * @author CHO
 * @see https://www.acmicpc.net/problem/15683
 * @category 구현, DFS
 */

public class BOJ_15683_감시 {
	static StringTokenizer st;
	static int[][][] dir= {
			{},
			{{0},{1},{2},{3}},
			{{0,1},{2,3}},
			{{0,2},{2,1},{3,1},{0,3}},
			{{1,2,3},{0,1,2},{0,1,3},{0,2,3}},
			{{0,1,2,3}}
	};
	static int[][] deltas= {{-1,0},{1,0},{0,1},{0,-1}};
	static int N,M;
	static int[][] map;
	static ArrayList<int[]> cctv;
	static boolean[][] visited;
	static int ori,cnt,min;
	public static void main(String[] args) throws IOException,NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		cctv=new ArrayList<>();
		ori=0; //원래 사각지대 (0)의 개수
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(1<=map[i][j] && map[i][j]<=5) {
					cctv.add(new int[] {i,j,map[i][j]});
				}else if(map[i][j]==0) ori++; 
			}
		}// 입력 완료
		
		min=Integer.MAX_VALUE;
		dfs(0,0);
		System.out.println(min);

	}
	private static void dfs(int index,int clean) {
		if(index==cctv.size()) {
			// 종료 조건
			if(min>ori-clean) {
				min=ori-clean;
			}
			return;
		}
		
		int x=cctv.get(index)[0];
		int y=cctv.get(index)[1];
		int d=cctv.get(index)[2];
		
		// 방향 정하기
		for (int i = 0; i < dir[d].length; i++) {
			// 90도 회전한거 순서대로 실행해보기 
			int watched=scan(dir[d][i], -1, x, y);
			dfs(index+1,clean+watched);
			
			// 원래대로 만들기
			scan(dir[d][i],1, x, y);
			
		}
		
		
	}
	private static int scan(int dirs[] , int flag, int x,int y) {
		int cnt=0;
		for (int i = 0; i < dirs.length; i++) {
			// 방향대로 쭉 가보기
			for (int s = 1; ; s++) {
				int nx=x+deltas[dirs[i]][0]*s;
				int ny=y+deltas[dirs[i]][1]*s;
				
				if(!isIn(nx, ny) || map[nx][ny]==6) 
					break;
				else {
					if(map[nx][ny]>0) {
						continue;
					}else if(map[nx][ny]==0) {
						// 벽이 아니면 갈수 있음
						cnt+=1;
					}
					map[nx][ny]+=flag;
				}	
			}	
		}
		return cnt;
	}
	private static boolean isIn(int nx, int ny) {
		return nx>=0 && ny>=0 && nx<N && ny<M;
	}

}

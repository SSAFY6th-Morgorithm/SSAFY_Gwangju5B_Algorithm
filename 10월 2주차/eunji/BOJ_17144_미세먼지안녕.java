import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 83208	564
 * @author CHO
 * @see https://www.acmicpc.net/problem/17144
 * @category 구현, 2차원 배열 돌리기
 * 주의! 2차원배열 시계방향, 반시계방향으로 돌리기
 */
public class BOJ_17144_미세먼지안녕 {
	static StringTokenizer st;
	static int R,C;
	static int[][] dir= {{0,1},{0,-1},{1,0},{-1,0}};
	public static void main(String[] args) throws IOException,NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] map=new int[R+1][C+1];
		int[][] copy=new int[R+1][C+1]; // 미세먼지를 확산하고, 공기청정기를 돌릴 copy map
		
		ArrayList<Integer> air=new ArrayList<>(); // 공기청정기 위치(r, c=1)
		ArrayList<int[]> dust=new ArrayList<>(); // 미세먼지 위치 
		
		for (int i = 1; i < R+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < C+1; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]>0) {
					dust.add(new int[] {i,j}); // 미세먼지 위치 저장
				}else if(map[i][j]==-1) {
					air.add(i); // 공기청정기 r좌표 저장 (c는 항상 1이므로)
				}
			}
		}// 입력 완료
		
		// T초만큼 반복
		
		for (int t = 0; t < T; t++) {
			// 미세먼지 퍼뜨리기
			int cnt=0;
			for (int i = 0; i < dust.size(); i++) {
				cnt=0;
				int[] list=dust.get(i);
				int x=list[0];
				int y=list[1];
				for (int j = 0; j < 4; j++) {
					int nx=x+dir[j][0];
					int ny=y+dir[j][1];
					if(isIn(nx,ny) && map[nx][ny]!=-1) {
						copy[nx][ny]+=map[x][y]/5;
						cnt++;
					}
				}
				copy[x][y]+=map[x][y]-((map[x][y]/5)*cnt);
			}
			
			// 미세먼지 위치 바뀌었으므로 원래 미세먼지 배열 초기화
			dust.clear();
			
			// 공기청정기 작동
			// 위쪽부터 처리
			
			// 밑변
			int x=air.get(0); //위쪽의 행번호
			
			int up=copy[x][C]; //다음으로 넘어가야 할 값
			
			for (int c = C; c >= 2; c--) {
				copy[x][c]=copy[x][c-1];
			}
			copy[x][1]=0; //공기청정기 자리에서 나온 값이므로 항상 0
			
			// 오른쪽 위로 
			int rightUp=copy[1][C]; 
			for (int r = 1; r < x-1; r++) {
				copy[r][C]=copy[r+1][C];
			}
			copy[x-1][C]=up;
			
			// 윗변
			int left=copy[1][1];
			for (int c = 1; c < C-1; c++) {
				copy[1][c]=copy[1][c+1];
			}
			copy[1][C-1]=rightUp;
			
			// 왼쪽 아래
			for (int r = x-1; r>1; r--) {
				copy[r][1]=copy[r-1][1];
			}
			copy[2][1]=left;
			copy[x][1]=0;

			// 아래쪽 처리
			// 윗변
			x=air.get(1);
			int rightDown=copy[x][C];
			
			for (int c = C; c >2; c--) {
				copy[x][c]=copy[x][c-1];
			}
			copy[x][2]=0; // 공기청정기에서 나왔으므로 0
			
			// 오른쪽 밑
			int down=copy[R][C];
			for (int r = R; r >x+1 ; r--) {
				copy[r][C]=copy[r-1][C];
			}
			copy[x+1][C]=rightDown;
			
			// 밑변
			int leftUp=copy[R][1];
			for (int c = 1; c < C; c++) {
				copy[R][c]=copy[R][c+1];
			}
			copy[R][C-1]=down;
			
			// 왼쪽 위
			for (int r = x-1; r < R-1; r++) {
				copy[r][1]=copy[r+1][1];
			}
			copy[R-1][1]=leftUp;
			copy[x][1]=0;
			
			// copy를 map으로 만들기, copy 초기화, 미세먼지 위치 저장
			for (int i = 1; i < R+1; i++) {
				for (int j = 1; j < C+1; j++) {
					map[i][j]=copy[i][j];
					if(map[i][j]>0) {
						dust.add(new int[] {i,j}); // 미세먼지 위치 저장
					}
					copy[i][j]=0;
				}
			}
			for (int i = 0; i < air.size(); i++) {
				map[air.get(i)][1]=-1;
			}	
		}
		int result=0;
		for (int i = 1; i < R+1; i++) {
			for (int j = 1; j < C+1; j++) {
				if(map[i][j]>0) result+=map[i][j];
			}
		}
		System.out.println(result);
	}
	
	private static boolean isIn(int nx, int ny) {
		// TODO Auto-generated method stub
		return nx>0 && ny>0 && nx<=R && ny<=C;
	}

}

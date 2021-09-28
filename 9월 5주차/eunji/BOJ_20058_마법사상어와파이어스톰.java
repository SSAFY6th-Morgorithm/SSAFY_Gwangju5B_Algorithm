import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 59868	444
 * @author CHO
 * @see https://www.acmicpc.net/problem/20058
 * @category 구현
 * 주의! 시계방향 90도 회전 
 * 얼음 바로 녹이면 안됨 -> 리스트 만들어서 한꺼번에 녹임
 * 얼음을 녹일 때, 얼음이 0 미만으로 녹여지면 => 얼음은 0
 */
public class BOJ_20058_마법사상어와파이어스톰 {
	static StringTokenizer st;
	static int result1,iceCnt;
	static int[][] rot,ori;
	static ArrayList<int[]> meltList;
	static int[][] dir= {{0,1},{0,-1},{1,0},{-1,0}};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int D=(int) Math.pow(2, N); //2^N
		
		ori=new int[D+1][D+1];
		for (int r = 1; r < D+1; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c < D+1; c++) {
				ori[r][c]=Integer.parseInt(st.nextToken());
			}
		} //입력 완료
		
		st = new StringTokenizer(br.readLine());
		for (int q = 0; q < Q; q++) {
			int L=Integer.parseInt(st.nextToken());
			int l=(int) Math.pow(2, L); //격자 몇으로 나눌건지
			rot=new int[D+1][D+1]; //로테이션 할 배열
			
			// 90도 회전
			rotation(l);
			
			// 녹일 부분 선정
			meltList=new ArrayList<>();
			melt();
			
			// 얼음 녹이기
			for (int i = 0; i < meltList.size(); i++) {
				// 더 녹였는데 0 보다 작게 되면 그대로 0
				if(rot[meltList.get(i)[0]][meltList.get(i)[1]]-1<0) {
					rot[meltList.get(i)[0]][meltList.get(i)[1]]=0;
				}else {
					rot[meltList.get(i)[0]][meltList.get(i)[1]]-=1;
				}
			}
			copy(rot); //원래 맵 카피
		}
		visited=new boolean[ori.length][ori.length];
		int max=0;
		for (int i = 1; i < ori.length; i++) {
			for (int j = 1; j < ori.length; j++) {
				if(ori[i][j]!=0 && !visited[i][j]) {
					iceCnt=1;
					// 가장 큰 얼음 덩어리 구하기
					int ice=dfs(i,j);
					max=max<ice?ice:max;
				}
				
			}
		}
		System.out.println(result1);
		System.out.println(max);
		
	}
	private static void rotation(int l) {
		for (int i = 1; i < ori.length; i+=l) {
			for (int j = 1; j < ori.length; j+=l) {
				int x=i;
				int y=j;
				int rotX=x;
				int rotY=y+l-1; //만약 격자 4씩 나눈다면.. 4-->3-->2-->1 2-->1
				for (int nx = x; nx < x+l; nx++) {
					for (int ny = y; ny <y+l; ny++) {
						//rotX 1-->2-->3-->4
						rot[rotX++][rotY]=ori[nx][ny];
					}
					rotY--;
					rotX=x;
				}
				
			}
		}
	}
	private static void melt() {
		for (int r = 1; r < ori.length; r++) {
			for (int c = 1; c < ori.length; c++) {
				int cnt=0;
				for (int d = 0; d < 4; d++) {
					int nr=r+dir[d][0];
					int nc=c+dir[d][1];
					if(isOkay(nr,nc) && rot[nr][nc]>0) {
						cnt++;
					}
				}
				if(cnt<3) meltList.add(new int[] {r,c});
			}
		}
	}

	private static int dfs(int i, int j) {
		visited[i][j]=true;
		for (int k = 0; k < 4; k++) {
			int nx=i+dir[k][0];
			int ny=j+dir[k][1];
			if(isOkay(nx,ny)&&!visited[nx][ny]&&ori[nx][ny]>0) {
				dfs(nx,ny);
				iceCnt++;
			}
		}
		
		return iceCnt;
	}

	private static boolean isOkay(int nr, int nc) {
		return nr>=1 && nc>=1 && nr<ori.length && nc<ori.length;
	}
	private static void copy(int[][] rot) {
		result1=0;
		for (int i = 1; i < ori.length; i++) {
			for (int j = 1; j < ori.length; j++) {
				ori[i][j]=rot[i][j];
				result1+=ori[i][j];
			}
		}
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 	78148	572
 * @author CHO
 * @see https://www.acmicpc.net/problem/4179
 * @category 구현, BFS
 * 불의 이동과 사람의 이동 각각 BFS를 따로 사용 -> 불의 이동 시간과 사람의 이동 시간 비교
 * 주의부분 코드 유의하기
 * 테케 http://acm.student.cs.uwaterloo.ca/~acm00/090613/data/
 */
public class BOJ_4179_불 {
	static StringTokenizer st;
	static int R,C;
	static Queue<int []> fire;
	static int map[][],firemap[][];
	static boolean visited[][];
	static int dir[][]= {{0,1},{0,-1},{1,0},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R= Integer.parseInt(st.nextToken());
		C= Integer.parseInt(st.nextToken());
		map=new int[R][C];
		int jX=0;
		int jY=0;
		fire=new LinkedList<int[]>();
		visited=new boolean[R][C];
		for (int r = 0; r < R; r++) {
			String a = br.readLine();
			for (int c = 0; c < C; c++) {
				char curr=a.charAt(c);
				if(curr=='#') map[r][c]=-1;
				else if(curr=='.') map[r][c]=0;
				else if(curr=='J') {
					map[r][c]=0;
					jX=r;
					jY=c;
				}
				else if(curr=='F') {
					map[r][c]=1;
					fire.add(new int[] {r,c});
					visited[r][c]=true;
				}
			}
		}
		// 입력 완료
		firemap=new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				firemap[i][j]=map[i][j];
			}
		}
		
		bfs_fire();
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(firemap[i][j]+" ");
//			}
//			System.out.println();
//		}
		bfs_person(jX,jY);
		

	}

	private static void bfs_fire() {
		while(!fire.isEmpty()) {
			int[] f=fire.poll();
			int fx=f[0];
			int fy=f[1];
			for (int i = 0; i < 4; i++) {
				int dx=fx+dir[i][0];
				int dy=fy+dir[i][1];
				if(isOkay(dx,dy)&&!visited[dx][dy]&&firemap[dx][dy]==0) {
					// 불 확산 가능
					fire.add(new int[] {dx,dy});
					visited[dx][dy]=true;
					firemap[dx][dy]=firemap[fx][fy]+1;
				}
			}
		}
		
	}
	private static void bfs_person(int x,int y) {
		boolean visited[][]=new boolean[R][C];
		Queue<int[]> person=new LinkedList<>();
		visited[x][y]=true;
		person.add(new int[] {x,y});
		int time=0;
		// *주의! fire 시간 시작을 2부터해서
		map[x][y]=1;
		
		while(!person.isEmpty()) {
			int size=person.size();
			while(size-->0) {
				int list[]=person.poll();
				int px=list[0];
				int py=list[1];
				if(px==0 || px==R-1 || py==0 || py==C-1) {
					// 탈출 가능 
					System.out.println(time+1);
					return;
				}
				for (int i = 0; i < 4; i++) {
					int nx=px+dir[i][0];
					int ny=py+dir[i][1];
					if(isOkay(nx, ny)&&!visited[nx][ny]&&map[nx][ny]==0) {
						// 이동 가능한 곳
						// 불보다 먼저 도착해야 이동 가능함
						// *주의! firemap이 0일때는 불이 도착하지 않았으므로
						if(firemap[nx][ny]>map[px][py]+1 || firemap[nx][ny]==0) {
							map[nx][ny]=map[px][py]+1;
							visited[nx][ny]=true;
							person.add(new int[] {nx,ny});
						}
					}
				}
			}
			time++;
		}
		System.out.println("IMPOSSIBLE");
	}
	private static boolean isOkay(int dx, int dy) {
		return dx>=0 && dy>=0 && dx<R && dy<C ;
	}

}

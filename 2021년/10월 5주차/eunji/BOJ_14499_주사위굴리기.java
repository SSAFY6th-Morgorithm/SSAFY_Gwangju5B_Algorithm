import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11896	88
 * @author CHO
 * @see https://www.acmicpc.net/problem/14499
 * @category 구현
 * 주의! 문제 해석
 */
public class BOJ_14499_주사위굴리기 {
	static StringTokenizer st;
	static StringBuilder sb=new StringBuilder();
	static int N,M;
	static int top,front,down,back,right,left;
	static int[] value={0,0,0,0,0,0,0}; //현재 주사위의 값
	static int[][] dir= {{},{0,1},{0,-1},{-1,0},{1,0}}; //동,서,북,남
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map=new int[N][M];
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int curr=Integer.parseInt(st.nextToken());
				map[i][j]=curr;
			}
		}// 입력 완료
		// 현재 주사위의 위치들
		top=1;
		front=5;
		down=6;
		back=2;
		right=3;
		left=4;
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int d = Integer.parseInt(st.nextToken());
			// 이동시킬 수 있는 곳인지 판단
			int nx=x+dir[d][0];
			int ny=y+dir[d][1];
			
			if(isIn(nx,ny)) {
				int temp=top;
				// 주사위 굴리기
				/*
				 * 1. 동쪽 
				 * top->right,right->down,down->left,left->top
				 * 2. 서쪽
				 * top->left,left->down,down->right,right->top
				 * 3. 북쪽
				 * top->back,back->down,down->front,front->top
				 * 4. 남쪽
				 * top->front,front->down,down->back,back->top
				 */
				if(d==1) {
					// 동쪽
					top=left;
					left=down;
					down=right;
					right=temp;
				}else if(d==2) {
					// 서쪽
					top=right;
					right=down;
					down=left;
					left=temp;
				}else if(d==3) {
					// 북쪽
					top=front;
					front=down;
					down=back;
					back=temp;
				}else if(d==4) {
					// 남쪽
					top=back;
					back=down;
					down=front;
					front=temp;
				}
				x=nx;
				y=ny;
//				System.out.println(i+1+"번째 - "+"top:"+top+" front:"+front+" down:"+down+" back:"+back+" right:"+right+" left:"+left);
				// 현재 map에 있는 숫자가 0인지, 아닌지 판단
				mapCheck(map,x,y);
				sb.append(value[top]).append("\n");
			}
		}
		System.out.println(sb);
	}
	private static void mapCheck(int[][] map,int x,int y) {
		// 현재 map에 있는 숫자가 0인지, 아닌지 판단
		// 만약, 0이라면 바닥면의 숫자가 map에 복사
		// 0이 아니라면 map의 숫자가 바닥면에 복사, map의 숫자는 0
		if(map[x][y]==0) {
			map[x][y]=value[down];
		}else {
			value[down]=map[x][y];
			map[x][y]=0;
		}
	}
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}
}

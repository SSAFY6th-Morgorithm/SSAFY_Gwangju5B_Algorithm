import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 	51444	472
 * @author CHO
 * @see https://www.acmicpc.net/problem/20056
 * @category 구현
 * 모두 짝,홀 처리 / 배열 범위
 */
public class BOJ_20056_마법사상어와파이어볼 {
	
	static class FireBall implements Comparable<FireBall>{
		int x,y,m,s,d;

		public FireBall(int x, int y, int m, int s, int d) {
			super();
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		
		@Override
		public int compareTo(FireBall o) {
			if(this.x==o.x) {
				return this.y-o.y;
			}
			return this.x-o.x;
		}
		
	}
	static StringTokenizer st;
	static int N,M,K;
	static int dir[][]= {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		PriorityQueue<FireBall> fireball=new PriorityQueue<>();
		PriorityQueue<FireBall> ball=new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireball.add(new FireBall(x, y, m, s, d));
			
		}
		int result=0;
		
		for (int k = 0; k < K; k++) {
			int[][] map=new int[N+1][N+1];
			// 파이어볼 이동
			while(!fireball.isEmpty()) {
				FireBall f=fireball.poll();
				int x=f.x;
				int y=f.y;
				int m=f.m;
				int s=f.s;
				int d=f.d;
				
				int nx=(x+dir[d][0]*s)%N;
				int ny=(y+dir[d][1]*s)%N;
				if(nx<=0) {nx=nx+N;}
				if(ny<=0) {ny=ny+N;}
				ball.add(new FireBall(nx, ny, m, s, d));
				map[nx][ny]+=1;
			}
//			System.out.println("이동한 후 파이어볼"+ball);
			// 2개 이상 파이어볼이 있는 칸 찾음 
			int flag=0;
			int m=0;
			int s=0;
			while(!ball.isEmpty())  {
				FireBall f=ball.poll();
				int x=f.x;
				int y=f.y;
				int r=map[x][y];
				if(r==1) {
					fireball.add(f);
				}else {
					map[x][y]=0;
					m=f.m;
					s=f.s;
					if(f.d%2==0) flag+=1;
					else flag+=50;
					for (int i = 0; i < r-1; i++) {
						if (ball.isEmpty()) break;
						FireBall v=ball.poll();
						m+=v.m;
						s+=v.s;
						if(v.d%2==0) flag+=1;
						else flag+=50;
					}
					m=m/5;
					s=s/r;
					if (m==0) {
						flag=0;
						m=0;
						s=0;
						continue;
					}else {
						// 모두 짝수거나, 홀수일 때 
						if(flag==r || flag==50*r) {
							fireball.add(new FireBall(x, y, m, s, 0));
							fireball.add(new FireBall(x, y, m, s, 2));
							fireball.add(new FireBall(x, y, m, s, 4));
							fireball.add(new FireBall(x, y, m, s, 6));
							
						}else {
							fireball.add(new FireBall(x, y, m, s, 1));
							fireball.add(new FireBall(x, y, m, s, 3));
							fireball.add(new FireBall(x, y, m, s, 5));
							fireball.add(new FireBall(x, y, m, s, 7));
						}
						flag=0;
						m=0;
						s=0;
					}
				}
			}
			
//			System.out.println("남아있는 파이어볼"+fireball);
			if(k==K-1) {
				for (FireBall f:fireball) {
					result+=f.m;
				}
			}
		}
		System.out.println(result);
	}

}

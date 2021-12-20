package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_21609 {
	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
	static List<Pos> rainbow = new ArrayList<>();
	static Queue<Pos> rq = new LinkedList<>();
	static Queue<Pos> fq = new LinkedList<>();
	static class Pos{
		int x,y;
		public Pos(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(solve());
		System.out.println(sb);
		
	}
	
	static int solve() {
		int score = 0;
		while(true) {
			int res = play();
			if(res==0)
				break;
			score += res;
		}
		return score;
	}
	
	static int play() {
		int max_block_no = 0;
		int max_rainbow_no = 0;
		int[] max_pos = {-1,-1};
		for(int i=0;i<N;i++) {
			Arrays.fill(visited[i], false);
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]>0 && !visited[i][j]) {
					int[] res = findGroup(i,j);
					int block_no = res[0];
					int rainbow_no = res[1];
					boolean update = false;
					
					if(block_no>max_block_no)
						update = true;
					else if(block_no==max_block_no) {
						if(rainbow_no>max_rainbow_no)
							update = true;
						else if(rainbow_no==max_rainbow_no) {
							if(i>max_pos[0])
								update = true;
							else if(i==max_pos[0]) {
								if(j>max_pos[1])
									update = true;
							}
						}
					}
					
					if(update) {
						max_block_no = block_no;
						max_rainbow_no = rainbow_no;
						max_pos[0] = i;
						max_pos[1] = j;
					}
				}
			}
		}
		
		if(max_pos[0]==-1)
			return 0;
		if(max_block_no<2)
			return 0;
		removeGroup(max_pos[0],max_pos[1]);
		gravity();
		rotate();
		gravity();
		return (int)Math.pow(max_block_no, 2);
	}
	
	static void rotate() {
        int [][] tmp = new int[N][N];
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < N; ++j) {
                tmp[N-j-1][i] = map[i][j];
            }
        }
        for(int i = 0; i < N; ++i) {
            System.arraycopy(tmp[i],0,map[i],0,N);
        }
    }
	
	static void gravity() {
		for(int j=0;j<N;j++) {
			boolean isDrop = false;
			for(int i=N-1;i>=0;i--) {
				if(map[i][j]==-2) {
					isDrop = true;
					break;
				}
			}
			if(!isDrop) continue;
			
			int drop = -1;
			for(int i=N-1;i>=0;i--) {
				if(map[i][j]==-2 && drop==-1)
					drop = i;
				else if(map[i][j]>=0 && drop!=-1) {
					int temp = map[i][j];
					map[i][j] = map[drop][j];
					map[drop][j] = temp;
					drop--;
				}
				else if(map[i][j]==-1)
					drop = -1;//drop을 다시 -1로 초기화 하고 위로 올라감
			}
		}
	}
	
	static int[] findGroup(int x,int y) {
		fq = new LinkedList<>();
		fq.add(new Pos(x,y));
		int block_no = 0;
		int rainbow_no = 0;
		int color = map[x][y];
		rainbow = new ArrayList<>();
		
		while(!fq.isEmpty()) {
			Pos p = fq.poll();
			if(visited[p.x][p.y]) continue;
			visited[p.x][p.y]= true;
			block_no++;
			if(map[p.x][p.y]==0)
				rainbow.add(new Pos(p.x,p.y));
			
			for(int i=0;i<4;i++) {
				int nx = p.x+dir[i][0];
				int ny = p.y+dir[i][1];
				if(isIn(nx,ny) && !visited[nx][ny]) {
					if(map[nx][ny]==color || map[nx][ny]==0)
						fq.add(new Pos(nx,ny));
				}
			}
		}
		
		rainbow_no = rainbow.size();
		
		for(int i=0;i<rainbow_no;i++) {
			int a = rainbow.get(i).x;
			int b = rainbow.get(i).y;
			visited[a][b] = false;
		}
		int[] res = {block_no,rainbow_no};
		return res;
	}
	
	static boolean isIn(int x,int y) {
		return x>=0&&x<N&&y>=0&&y<N;
	}
	
	static void removeGroup(int x,int y) {
		rq = new LinkedList<>();
		rq.add(new Pos(x,y));
		int color = map[x][y];
		
		while(!rq.isEmpty()) {
			Pos p = rq.poll();
			if(map[p.x][p.y]!=-2)
				map[p.x][p.y] = -2; 
			
			for(int i=0;i<4;i++) {
				int nx = p.x+dir[i][0];
				int ny = p.y+dir[i][1];
				if(isIn(nx,ny) && map[nx][ny]!=-2) {
					if(map[nx][ny]==color || map[nx][ny]==0)
						rq.add(new Pos(nx,ny));
				}
			}
		}
	}
}
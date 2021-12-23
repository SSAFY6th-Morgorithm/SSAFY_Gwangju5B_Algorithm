package week19.day1221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_21609 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	static PriorityQueue<Node> PQ;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer =0;
		while(true) {
			Node Big = find();
			if(Big == null) {
				break;
			}
			answer += Big.size;
			destroy(Big);
			gravity();
			
			
		}
		
		for(int[] arr:map) {
			System.out.println(Arrays.toString(arr));
		}
		
		System.out.println(answer);
		

	}
	
	private static void gravity() {
		
		for(int c=0; c<N; c++) {
			
			
			
			for(int r=N-1; r>=0; r--) {
				if(map[r][c] == -1 || map[r][c] != -2) {
					continue;
				}
				else {
					for(int n=r-1; n>=0; n--) {
						if(map[n][c] > -1) {
							map 
						}
					}
				}
				
				
			}
			//밑에서부터 넣기
			
			
		}
		
	}

	private static void destroy(Node big) {
		
		for(int i=0; i<big.list.size(); i++) {
			int[] node = big.list.get(i);
			map[node[0]][node[1]] = -2;
		}
		
	}

	private static Node find() {
		PQ = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j] && 0<map[i][j]) {
					PQ.add(bfs(i,j));
				}
			}
		}
		
		
		
		return PQ.size() == 0 ? null :PQ.poll();
	}

	private static Node bfs(int i, int j) {
		for(int i2=0; i2<N; i2++) {
			for (int j2 = 0; j2 < N; j2++) {
				
				if(map[i2][j2] == 0) {
					visited[i2][j2] = false;
				}
			}
		}
		
		Queue<int[]> Q = new LinkedList<>();
		Q.add(new int[] {i,j});
		visited[i][j] = true;
		Node node = new Node(i, j, 0, 0);
		System.out.println("i---"+i+"====j---"+j);
		while(!Q.isEmpty()) {
			int[] now = Q.poll();
			System.out.println(Arrays.toString(now));
			if(map[now[0]][now[1]] == 0) {
				node.zCnt++;
			}
			node.list.add(new int[] {now[0],now[1]});
			node.size++;
			for(int d=0; d<4; d++) {
				int nr = now[0] + deltas[d][0];
				int nc = now[1] + deltas[d][1];
				if(isIn(nr,nc) && !visited[nr][nc] && map[nr][nc] != -1 && (map[nr][nc] == map[i][j] || map[nr][nc] == 0)) {
					Q.add(new int[] {nr,nc});
					visited[nr][nc] = true;
				}
			}
			
		}
		
		
		
		return node;
	}

	private static boolean isIn(int nr, int nc) {
		return 0 <= nr && nr < N && 0 <= nc && nc < N;
	}

	static class Node implements Comparable<Node>{
		int r,c,size,zCnt;
//		zCnt는 0의 개수 무지개 블록이 많은 거 생각할 때
		
		List<int[]> list = new ArrayList<>();

		public Node(int r, int c, int size, int zCnt) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.zCnt = zCnt;
		}



		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", size=" + size + ", zCnt=" + zCnt + "]";
		}



		@Override
		public int compareTo(Node o) {
			if(this.size != o.size) {
				return Integer.compare(this.size, o.size) * -1;//오름차순으로 정렬해서 가장 큰 거로 바로 접근
				
			}else {
				//같으면, 무지개 블록의 수가 가장 많은 블록 그룹
				if(this.zCnt != o.zCnt) {
					
					return Integer.compare(this.zCnt, o.zCnt) * -1;
				
				}else {
					if(this.r != o.r) {
						return Integer.compare(this.r, o.r) * -1;
					}else {
						return Integer.compare(this.c, o.c) * -1;
					}
				}
			}
		}
		
	}
	
}

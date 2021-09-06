package week9_1;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간제한: 초
public class BOJ_S1_경로찾기 {
	private static int N;
	private static int[][] map,answer;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		answer= new int[N][N];
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				visited= new boolean[N][N];
				answer[r][c]= bfs(r,c);
			}
		}
		
		for(int arr[]:answer) {
			for(int data:arr) {
				System.out.print(data+" ");
			}
			System.out.println();
		}
	}

	private static int bfs(int start, int end) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		while(!q.isEmpty()) {
			int temp=q.poll();
			for(int c=0;c<N;c++) {
				if(map[temp][c]==1&& !visited[temp][c]) {
					if(c==end) return 1;
					else {
						q.add(c);
						visited[temp][c]=true;
					}
				}
			}
		}
		return 0;
	}
}

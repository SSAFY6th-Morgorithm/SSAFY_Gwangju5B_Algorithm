package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS
 * @author CHO
 * @see https://www.acmicpc.net/problem/5567
 * 실버1
 */
public class BOJ_5567_결혼식 {
	static StringTokenizer st;
	static int N,M;
	static ArrayList<ArrayList<Integer>> arr;
	static boolean[] visited;
	static int result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine()); // 동기 수
		M=Integer.parseInt(br.readLine()); // 리스트 길이
		arr=new ArrayList<>();
		for (int i = 0; i < N+1; i++) {
			arr.add(i,new ArrayList<Integer>());
		}
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			arr.get(start).add(end);
			arr.get(end).add(start); // 양방향 그래프
		}
		visited=new boolean[N+1];
		result=0;
		bfs(1);
		System.out.println(result);

	}
	private static void bfs(int start) {
		int cnt=0;
		
		Queue<Integer> queue=new LinkedList<>();
		queue.add(start);
		visited[start]=true;
		
		while(!queue.isEmpty()) {
			int s=queue.size();
			while(s-->0) {
				int curr=queue.poll();
				for (int i = 0; i < arr.get(curr).size(); i++) {
					if(!visited[arr.get(curr).get(i)]) {
						queue.add(arr.get(curr).get(i));
						visited[arr.get(curr).get(i)]=true;
						result++;
					}
				}
			}
			cnt++;
			// 친구의 친구까지(depth가 2) 탐색하면, 종료
			if (cnt==2) {
				return;
			}
		}
	}

}

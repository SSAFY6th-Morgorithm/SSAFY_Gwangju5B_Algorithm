package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS
 * @author CHO
 * @see https://www.acmicpc.net/problem/6118
 * 실버1
 */
public class BOJ_6118_숨바꼭질 {
	static StringTokenizer st;
	static int N,M;
	static ArrayList<ArrayList<Integer>> arr;
	static boolean visited[];
	static int depth;
	static ArrayList<Integer> hubo;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		arr=new ArrayList<>();
		for (int i = 0; i < N+1; i++) {
			arr.add(i,new ArrayList<Integer>());
		}
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			arr.get(start).add(end);
			arr.get(end).add(start); //양방향 그래프
		}
		visited=new boolean[N+1];
		hubo=new ArrayList<Integer>(); // 후보 헛간들
		depth=0;
		bfs(1);

		int num=Collections.min(hubo);
		int d=depth-1; // 마지막에 한번 더 더해서 결과 값 출력할때는 -1 해줘야함 
		int cntHubo=hubo.size();
		
		System.out.println(num+" "+d+" "+cntHubo);
	}
	private static void bfs(int start) {
		Queue<Integer> queue=new LinkedList<Integer>();
		queue.add(start);
		visited[start]=true;
		while(!queue.isEmpty()) {
			int s=queue.size();
			hubo.clear(); // 다음 depth로 넘어갈 수 있으면 hubo 삭제
			while(s-->0) {
				int curr=queue.poll();
				hubo.add(curr);
				for (int i = 0; i < arr.get(curr).size(); i++) {
					if(!visited[arr.get(curr).get(i)]) {
						queue.add(arr.get(curr).get(i));
						visited[arr.get(curr).get(i)]=true;
					}
				}
			}
			depth++;
		}
	}
}

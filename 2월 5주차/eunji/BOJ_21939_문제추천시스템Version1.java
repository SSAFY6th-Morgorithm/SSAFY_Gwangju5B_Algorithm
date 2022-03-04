package algo;

import java.io.*;
import java.util.*;

/**
 * 54148	556
 * @author CHO
 * @see https://www.acmicpc.net/problem/21939
 * @category 맵,우선순위큐
 *
 */
public class BOJ_21939_문제추천시스템Version1{
	static class Pro implements Comparable<Pro>{
		int level;
		int num;
		public Pro(int level,int num) {
			this.level=level;
			this.num=num;
		}
		@Override
		public int compareTo(Pro o) {
			if(this.level==o.level) {
				return Integer.compare(this.num, o.num);
			}
			return Integer.compare(this.level, o.level);
		}
	}
	static StringTokenizer st;
	static StringBuilder sb=new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Map<Integer,Integer> map=new HashMap<>();
		PriorityQueue<Pro> minpq=new PriorityQueue<>();
		PriorityQueue<Pro> maxpq=new PriorityQueue<>(Collections.reverseOrder());
		for (int n = 0; n < N; n++) {
			st=new StringTokenizer(br.readLine());
			int num=Integer.parseInt(st.nextToken());
			int level=Integer.parseInt(st.nextToken());
			minpq.add(new Pro(level, num));
			maxpq.add(new Pro(level, num));
			map.put(num, level);
		}// 문제 추가
		int M = Integer.parseInt(br.readLine());
		for (int m = 0; m < M; m++) {
			st=new StringTokenizer(br.readLine());
			String str=st.nextToken();
			if(str.equals("add")) {
				int num=Integer.parseInt(st.nextToken());
				int level=Integer.parseInt(st.nextToken());
				minpq.add(new Pro(level, num));
				maxpq.add(new Pro(level, num));
				map.put(num, level);
			}else if(str.equals("recommend")) {
				int x=Integer.parseInt(st.nextToken());
				if(x==1) {
					while(!maxpq.isEmpty()) {
						Pro pro=maxpq.peek();
						if(map.containsKey(pro.num) && (pro.level==map.get(pro.num))) {
							sb.append(pro.num+"\n");
							break;
						}else maxpq.poll();
					}
				}else {
					while(!minpq.isEmpty()) {
						Pro pro=minpq.peek();
						if(map.containsKey(pro.num) && (pro.level==map.get(pro.num))) {
							sb.append(pro.num+"\n");
							break;
						}else minpq.poll();
					}
				}
				
			}else if(str.equals("solved")) {
				int num=Integer.parseInt(st.nextToken());
				map.remove(num);
			}
		}
		System.out.println(sb);
	}
}

package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1446 {
	static class Road{
		int s,e,l;
		public Road(int s,int e,int l) {
			this.s = s;
			this.e = e;
			this.l = l;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		List<Road> list = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			if(e-s < l)
				continue;
			list.add(new Road(s,e,l));
		}
		
		Collections.sort(list,new Comparator<Road>() {

			@Override
			public int compare(Road o1, Road o2) {
				// TODO Auto-generated method stub
				if(o1.s == o2.s)return o1.e-o2.e;
				return o1.s-o2.s;
			}
			
		});
		
		int[] arr = new int[10000];
		for(int i=0;i<arr.length;i++) {
			arr[i] = Integer.MAX_VALUE;
		}
		arr[0] = 0;
		int idx = 0;
		int move = 0;
		while(move < D) {
			if(idx<list.size()) {
				Road r = list.get(idx);
				if(move == r.s) {
					arr[r.e]= Math.min(arr[move]+r.l, arr[r.e]);
					idx++;
				}
				else {
					arr[move+1] = Math.min(arr[move+1], arr[move]+1);
					move++;
				}
			}
			else {
				arr[move+1] = Math.min(arr[move+1], arr[move]+1);
				move++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(arr[D]);
		System.out.println(sb);
	}
}

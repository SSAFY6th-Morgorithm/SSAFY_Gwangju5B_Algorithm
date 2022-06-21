package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class bj_2170 {

	static int N;	// 좌표 수
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		// 선 긋기
		N = Integer.parseInt(br.readLine());
		List<Dot> list = new ArrayList<>();		// 전체 좌표 리스트
		
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(list);		// x좌표를 기준으로 오름차순 정렬
		
		List<Dot> result = new ArrayList<>();	// 길이를 구해야할 좌표 리스트
		result.add(list.get(0));
		int index = 0;
		for(int n=1; n<N; n++) {
			if(result.get(index).y>=list.get(n).x) {
				if(result.get(index).y<list.get(n).y) {
					result.get(index).y = list.get(n).y;
				}
			}else {
				result.add(list.get(n));
				index++;
			}
		}

		int leng = 0;
		for(int i=0; i<=index; i++) {
			leng += result.get(i).y - result.get(i).x;
		}
		
		System.out.println(leng);
	}
	
	static class Dot implements Comparable<Dot>{
		Integer x,y;
		
		public Dot(Integer x, Integer y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Dot o) {
			if(this.x==o.x) {
				return this.y.compareTo(o.y);
			}
			return this.x.compareTo(o.x);
		}
	}
}
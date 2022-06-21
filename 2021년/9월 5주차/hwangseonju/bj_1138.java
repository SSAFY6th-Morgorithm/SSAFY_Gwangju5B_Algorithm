package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class bj_1138 {
	
	static int N;	// 인원
	static Tall[] tall;	// 자신을 기준으로 왼쪽에 키 큰 사람이 몇명 있는지 정보 저장
	static List<Integer> result;	// 순서
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 한 줄로 서기
		N = Integer.parseInt(br.readLine());
		
		tall = new Tall[N];
		result = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int n=0; n<N; n++) {
			tall[n] = new Tall(n+1, Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(tall);	// 키큰 순서로 정렬
		
		result.add(tall[0].i);	// 첫번째 사람은 무조건 리스트에 추가하고 시작
		outer:for(int n=1; n<N; n++) {
			int cnt = 0;	// 키큰 사람
			for(int l=0; l<result.size(); l++) {
				if(tall[n].i<result.get(l)) {
					++cnt;
					if(cnt==tall[n].left) {		// 결과 리스트 안에서 키큰 사람의 수와 일치하면 그 다음자리에 추가
						result.add(l+1, tall[n].i);
						continue outer;
					}
				}
			}
			result.add(0, tall[n].i);	// 결과 리스트 안에서 맞는 곳이 없었으면 맨 앞에 추가
		}
		
		for(int n=0; n<result.size(); n++) {
			sb.append(result.get(n)).append(" ");
		}
		System.out.print(sb);
	}
	
	static class Tall implements Comparable<Tall>{
		int i, left;

		public Tall(int i, int left) {
			super();
			this.i = i;
			this.left = left;
		}

		@Override
		public int compareTo(Tall o) {
			return Integer.compare(o.i, this.i);
		}
		
	}

}

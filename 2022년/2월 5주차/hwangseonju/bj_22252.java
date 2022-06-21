package Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_22252 {
	
	static int Q;	// 쿼리 개수 
	static long result;	// 정보의 총 가치
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 정보 상인 호석
		Q = Integer.parseInt(br.readLine());
		
		HashMap<String, PriorityQueue<Integer>> gorilla = new LinkedHashMap<>();
		result = 0;
		
		for(int q=0; q<Q; q++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			if(start==1) {	// 명령어가 1인 경우 : 고릴라 정보 입력
				int k = Integer.parseInt(st.nextToken());
				if(!gorilla.containsKey(name)) {	// 처음 등장하는 고릴라일 경우
					gorilla.put(name, new PriorityQueue<>(Collections.reverseOrder()));
				}
				
				for(int i=0; i<k; i++) {
					gorilla.get(name).add(Integer.parseInt(st.nextToken()));
				}
			}else {		// 명령어가 2인 경우 : 정보 구매
				if(gorilla.containsKey(name)) {		// 찾는 고릴라가 있을 경우
					int b = Integer.parseInt(st.nextToken());
					while(b-->0) {
						if(gorilla.get(name).isEmpty()) break;
						result += gorilla.get(name).poll();
					}
				}
			}
		}
		System.out.println(result);
	}
}

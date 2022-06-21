package Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_7662 {
	
	static int T;	// 테스트 케이스
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 이중 우선순위 큐
		T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			int K = Integer.parseInt(br.readLine());
			
			HashMap<Integer, Integer> map = new LinkedHashMap<>();
			PriorityQueue<Integer> min = new PriorityQueue<>();
			PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
			
			for(int k = 0; k<K; k++) {
				st = new StringTokenizer(br.readLine());
				String start = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
				if(start.equals("I")) {
					min.offer(num);
					max.offer(num);
					map.put(num, map.getOrDefault(num, 0)+1);
				} else {
					int data = 0;
					boolean flag1 = false;
					if(num==-1) {	// 최소값
						while(!min.isEmpty()) {
							data = min.poll();
							if(map.get(data)>0) {
								flag1 = true;
								break;
							}
						}
					}else {			// 최대값
						while(!max.isEmpty()) {
							data = max.poll();
							if(map.get(data)>0) {
								flag1 = true;
								break;
							}
						}
					}
					
					if(flag1) {
						map.put(data, map.get(data)-1);
					}
				}
			}
			
			boolean flag2 = false;
			while(!max.isEmpty()) {
				int maxresult = max.poll();
				if(map.get(maxresult)>0) {
					sb.append(maxresult).append(" ");
					flag2 = true;
					break;
				}
			}
			
			if(!flag2) {
				sb.append("EMPTY").append("\n");
			}else {
				while(!min.isEmpty()) {
					int minresult = min.poll();
					if(map.get(minresult)>0) {
						sb.append(minresult).append("\n");
						break;
					}
				}
			}
		}
		System.out.println(sb);
	}
}

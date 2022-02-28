package algo;

import java.io.*;
import java.util.*;

/**
 * 154544	1412
 * @author CHO
 * @see https://www.acmicpc.net/problem/22252
 * @category 맵, 구현
 */
public class BOJ_22252_정보상인호석 {

	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine());
		Map<String,Integer> map=new HashMap<String, Integer>();
		List<ArrayList<Integer>> numList=new ArrayList<ArrayList<Integer>>();
		long sum=0;
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // 1 or 2
			if (num == 1) {
				String name = st.nextToken();
				int k = Integer.parseInt(st.nextToken());
				if(map.containsKey(name)) {
					int index=map.get(name); //몇번째 
					for (int i = 0; i < k; i++) {
						numList.get(index).add(Integer.parseInt(st.nextToken()));
					}
				}else {
					int index=map.size();
					map.put(name, index);
					numList.add(index,new ArrayList<>());
					for (int i = 0; i < k; i++) {
						numList.get(index).add(Integer.parseInt(st.nextToken()));
					}
				}
			} else if (num == 2) {
				String name = st.nextToken();
				int b = Integer.parseInt(st.nextToken());
				if(map.containsKey(name)) {
					int index=map.get(name);
					if(numList.get(index).size()<=b) {
						for (int i = 0; i < numList.get(index).size(); i++) {
							sum+=numList.get(index).get(i);
							numList.get(index).remove(i);
							i--;
						}
					}else {
						Collections.sort(numList.get(index), new Comparator<Integer>() {

							@Override
							public int compare(Integer o1, Integer o2) {
								return o2-o1;
							}
						});// 내림차순 정렬
						int cnt=0;
						for (int i =0; i < b; i++) {
							if(cnt==b) break;
							sum+=numList.get(index).get(i);
							numList.get(index).remove(i);
							i--;
							cnt++;
						}
					}
					
				}
			}
		}// 입력 완료
		System.out.println(sum);
	}
}

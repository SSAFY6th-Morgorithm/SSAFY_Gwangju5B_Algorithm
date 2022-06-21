import java.io.*;
import java.util.*;

/**
 * 2022 KAKAO BLIND RECRUITMENT 신고 결과 받기
 * @author CHO
 * @see https://programmers.co.kr/learn/courses/30/lessons/92334
 * @category 구현
 */
public class Kakao_신고결과받기 {

	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		/*
		 * 확인을 위해 임시로 넣어준 값
		 */
		String[] id_list = { "muzi", "frodo", "apeach", "neo" };
		String[] report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
		int k = 2; 
		int[] answer = new int[id_list.length];

		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < id_list.length; i++) {
			map.put(id_list[i], i);
		} // id에 번호 할당

		List<List<Integer>> list = new ArrayList<List<Integer>>();
		for (int i = 0; i < id_list.length; i++) {
			list.add(new ArrayList<Integer>());
		} // list 초기화

		// --------------------신고 저장 (중복 일단 허용)
		for (int i = 0; i < report.length; i++) {
			String str = report[i];
			String[] s = str.split(" ");
			list.get(map.get(s[1])).add(map.get(s[0]));
		} 

		// --------------------중복 여부 확인하여 메일 처리
		boolean[] vis; // 중복 여부 확인
		int cnt; // 신고 횟수
		for (int i = 0; i < list.size(); i++) {
			List<Integer> cur = list.get(i);
			vis = new boolean[id_list.length];
			cnt = 0;
			for (int j = 0; j < cur.size(); j++) {
				if (!vis[cur.get(j)]) {
					vis[cur.get(j)] = true;
					cnt++;
				}
			}// 중복 여부 확인 
			// 메일 처리 
			if (cnt >= k) {
				for (int j = 0; j < id_list.length; j++) {
					if (vis[j])
						answer[j] += 1;
				}
			}

		}
		System.out.println(Arrays.toString(answer));
	}
}

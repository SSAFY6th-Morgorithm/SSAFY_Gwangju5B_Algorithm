package feb08_kakao2022;

import java.io.*;
import java.util.*;

public class 신고결과받기 {

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();

//		String[] id_list = { "muzi", "frodo", "apeach", "neo" };
		String[] id_list = { "con", "ryan" };
//		String[] report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
		String[] report = { "ryan con", "ryan con", "ryan con", "ryan con" };
		int k = 2;

		int[] answer = solution(id_list, report, k);
		for (int a : answer) {
			sb.append(a + " ");
		}

		System.out.println(sb);
	}

	public static int[] solution(String[] id_list, String[] report, int k) {
		StringTokenizer st;
		int[] answer = new int[id_list.length];

		Map<String, Integer> mailCnt = new HashMap<>(); // 받은 메일수
		Map<String, ArrayList<String>> memberList = new HashMap<>(); // 멤버 - 신고한 사람

		for (int i = 0; i < id_list.length; i++) {
			mailCnt.put(id_list[i], 0);
			memberList.put(id_list[i], new ArrayList<>());
		}

		for (int i = 0; i < report.length; i++) {
			st = new StringTokenizer(report[i]);
			String a = st.nextToken(); // 보낸
			String b = st.nextToken(); // 받은
			if(!memberList.get(b).contains(a)) { // 중복x 1번만 세기
				memberList.get(b).add(a);				
			}
		}

		for (String key : memberList.keySet()) {
			ArrayList<String> reportMem = memberList.get(key);
//			System.out.println("신고당한 사람: " + key + " -> 신고한 사람: " + reportMem);
			if (reportMem.size() >= k) {
				for (String mem : reportMem) { 
					mailCnt.put(mem, mailCnt.get(mem) + 1); // 신고한 사람한테 메일보내기
				}
			}
		}

		for (int i = 0; i < answer.length; i++) {
			answer[i] = mailCnt.get(id_list[i]);
		}

		return answer;
	}
}

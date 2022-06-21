import java.io.*;
import java.util.*;

/**
 * 2022 KAKAO BLIND RECRUITMENT 주차 요금 계산
 * @author CHO
 * @see https://programmers.co.kr/learn/courses/30/lessons/92341
 * @category 구현, 문자열
 */
public class Kakao_주차요금계산 {

	public static void main(String[] args) throws IOException {
		int[] fees = { 1, 461, 1, 10 };
		String[] records = { "00:00 1234 IN"}; // 확인을 위해 임시로 넣어둔 값 

		List<String> car = new ArrayList<>(); // 차번호 저장
		HashMap<String, ArrayList<String>> map = new HashMap<>(); // 차번호별로 내역 저장

		for (int i = 0; i < records.length; i++) {
			String[] cur = records[i].split(" ");
			String time = cur[0];
			String carNum = cur[1];
			String state = cur[2];
			if (!map.containsKey(carNum)) {
				map.put(carNum, new ArrayList<>());
				car.add(carNum);
			}
			map.get(carNum).add(time + " " + state);
		} // 차번호를 키값으로 시각과 내역 매핑
		
		int[] answer = new int[car.size()]; // 정답 배열 
		Collections.sort(car); // 차번호로 오름차순 정렬
		
		for (int i = 0; i < car.size(); i++) {
			ArrayList<String> list = map.get(car.get(i)); // 차번호별로 내역
			int total = 0;
			if (list.size() % 2 == 0) {
				// 짝수면 IN,OUT 정상적
				for (int j = 0; j < list.size(); j += 2) {
					String[] str = list.get(j).split(" ");
					if (str[1].equals("IN")) {
						// 다음 시간과 계산해야 함
						String[] nextStr=list.get(j+1).split(" ");
						total += cal(str,nextStr);
					}
				}
			} else {
				for (int j = 0; j < list.size() - 1; j += 2) {
					String[] str = list.get(j).split(" ");
					if (str[1].equals("IN")) { 
						String[] nextStr=list.get(j+1).split(" ");
						total += cal(str,nextStr);
					}
				}
				// 마지막 남은 IN 하나 계산
				// 마지막 OUT은 23:59
				String[] str = list.get(list.size() - 1).split(" ");
				String[] nextStr= {"23:59","OUT"};
				total += cal(str,nextStr);
			}
			if (total <= fees[0]) answer[i] = fees[1];
			else answer[i] = (int) (fees[1] + (Math.ceil((double)(total - fees[0]) / fees[2]) * fees[3]));
		}
		System.out.println(Arrays.toString(answer));
	}
	// 시간 계산 
	private static int cal(String[] str1, String[] str2) {
		String time=str1[0];
		String nextTime=str2[0];
		String[] timesp = time.split(":");
		String[] nextTimesp = nextTime.split(":"); //전처리 
		
		int h = Integer.parseInt(timesp[0]);
		int min = Integer.parseInt(timesp[1]);
		int nextH = Integer.parseInt(nextTimesp[0]);
		int nextMin = Integer.parseInt(nextTimesp[1]);
		
		return ((nextH * 60) + nextMin) - ((h * 60) + min);
	}
}

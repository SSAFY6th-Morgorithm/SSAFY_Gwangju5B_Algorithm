package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_20437 {
	
	static int T;	// 게임수
	static String W;	// 문자열
	static int K;	// 양의 정수
	static int[] alphabet;	// 알파벳 횟수 확인
	static char[] str;	// 문자열 분리
	static int min;	// 게임 3번 값
	static int max;	// 게임 4번 값
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 문자열 게임2 // 초기화를 잘하자...^^
		T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			W = br.readLine();
			alphabet = new int[26];
			str = new char[W.length()];
			
			for(int w=0; w<W.length(); w++) {
				alphabet[W.charAt(w)-'a']++;
				str[w] = W.charAt(w);
			}
			
			K = Integer.parseInt(br.readLine());
			
			int cnt = 0;	// K개 개수 충족 확인
			boolean flag = false;	// 만족하는 연속 문자열 여부 확인
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			for(int start = 0; start<W.length(); start++) {
				if(alphabet[str[start]-'a']>=K) {	// K개 이상 있는 알파벳만 확인
					flag = true;
					cnt = 0;
					for(int end=start; end<W.length(); end++) {
						if(str[start]==str[end])
							cnt++;
						if(cnt==K) {
							//System.out.println(start+"-"+end);
							min = Math.min(min, end-start+1);
							max = Math.max(max, end-start+1);
							break;
						}
					}
				}
			}
			
			if(flag==false)
				sb.append(-1);
			else
				sb.append(min).append(" ").append(max);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
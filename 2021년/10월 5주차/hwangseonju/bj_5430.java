package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj_5430 {

	static int T;		//테스트 케이스
	static char[] p;	// 수행할 함수
	static int N;		// 수의 개수
	static List<Integer> num;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// AC
		T = Integer.parseInt(br.readLine());
		outer:for(int t=0; t<T; t++) {
			String P = br.readLine();
			p = new char[P.length()];
			
			for(int i=0; i<P.length(); i++) {
				p[i] = P.charAt(i);
			}
			
			N = Integer.parseInt(br.readLine());
			num = new ArrayList<>();
			st = new StringTokenizer(br.readLine(), "[ , ]");
			for(int n=0; n<N; n++) {
				num.add(Integer.parseInt(st.nextToken()));
			}
			
			int current = 0;	// 현재 인덱스
			for(int i=0; i<p.length; i++) {
				if(p[i]=='R') {	// 뒤집기
					current = current==0? num.size()-1 : 0;
				}else {			// 버리기
					if(!num.isEmpty()) {
						num.remove(current);
						current = current>0? current-1:0; 
					}else {		// 숫자가 없을 경우 error를 출력하고 끝냄
						sb.append("error").append("\n");
						continue outer;
					}
				}
			}
			
			sb.append("[");
			if(current != 0) {
				for(int i=current; i>=0; i--) {
					sb.append(num.get(i)).append(",");
				}
			}else {
				for(int i=0; i<num.size(); i++) {
					sb.append(num.get(i)).append(",");
				}
			}
			sb.deleteCharAt(sb.lastIndexOf(","));	// 마지막에 나오는 쉼표(,) 제거
			sb.append("]").append("\n");
		}
		System.out.print(sb);
	}
}
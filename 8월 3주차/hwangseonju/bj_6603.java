package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_6603 {
	
	static int k;	// 49가지 수 중 k개
	static int[] num;	// k개 숫자를 넣을 배열
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		// 로또(조합)
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			k = Integer.parseInt(st.nextToken());
			if(k==0) {	// 0 입력시에 멈추기
				break;
			}
			num = new int[k];
			for(int i=0; i<k; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			combination(6, new int[6], 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void combination(int n, int[] choosed, int start) {
		if(n==0) {
			for(int i:choosed) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<num.length; i++) {
			choosed[choosed.length-n] = num[i];
			combination(n-1, choosed, i+1);
		}
	}
}

package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_18222 {
	
	static long k;	// 몇번째 숫자
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 투에-모스 문자열
		k = Long.parseLong(br.readLine())-1;
		
		String str = Long.toBinaryString(k);		// 2진수로 변환
		
		int count = 0;	//	 1의 개수
		char[] num = new char[str.length()];
		for(int n=0; n<num.length; n++) {
			num[n] = str.charAt(n);
			if(num[n]=='1') {
				count++;
			}
		}

		if(count%2==0) {	// 짝수일때
			System.out.println('0');
		}else {		// 홀수일때
			System.out.println('1');
		}
	}
}

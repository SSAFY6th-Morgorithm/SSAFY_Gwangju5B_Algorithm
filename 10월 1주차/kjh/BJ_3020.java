package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3020 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[] bot = new int[H+1];
		int[] top = new int[H+1];
		for(int i=0;i<N/2;i++) {
			bot[Integer.parseInt(br.readLine())]++;
			top[Integer.parseInt(br.readLine())]++;
		}
		
		int[] pSumBot = new int[H+1];
		int[] pSumTop = new int[H+1];
		for(int i=1;i<=H;i++) {
			pSumBot[i] = pSumBot[i-1]+bot[i];
			pSumTop[i] = pSumTop[i-1]+top[i];
		}
		
		int min = Integer.MAX_VALUE;
		int cnt = 0;
		int[] sumArr = new int[H+1];
		for(int i=1;i<=H;i++) {
			int sum = 0;
			sum += pSumBot[H]-pSumBot[i-1];
			sum += pSumTop[H]-pSumTop[H-i];
			
			sumArr[i] = sum;
			min = Math.min(min, sum);
		}
		
		for(int i=1;i<=H;i++) {
			if(sumArr[i]==min)
				cnt++;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(min).append(" ").append(cnt);
		System.out.println(sb);
	}

}

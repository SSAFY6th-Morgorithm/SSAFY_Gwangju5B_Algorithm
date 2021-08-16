package 순열_조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * @see https://www.acmicpc.net/problem/1759
 * 정렬, 조합
 */
public class BOJ_1759_암호만들기 {
	static StringTokenizer st;
	static StringBuilder sb=new StringBuilder();
	static int L,C;
	static char[] input;
	static char[] pick;
	static String apb= "aeiou"; //알파벳 모음
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		L=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		input=new char[C];
		st=new StringTokenizer(br.readLine());
		for (int c=0; c<C; c++) {
			input[c]=st.nextToken().charAt(0);
		}
		// 암호는 오름차순으로 배열 -> input 정렬
		Arrays.sort(input);
		pick=new char[L];
		combination(0,0);
		System.out.println(sb);
		
	}

	private static void combination(int cnt, int start) {
		if (cnt==L) {
			// 최소 하나의 a,e,i,o,u 가 들어가는지 && 최소 두개의 자음 확인
			int count_apb=0;
			int count_no=0;
			for(char x:pick) {
				if(apb.contains(Character.toString(x))) count_apb++; // 모음 확인
				else count_no++;
			}
			if (count_apb>=1 && count_no>=2) {
				for(char x:pick) {
					sb.append(x);
				}
				sb.append("\n");
			}
			return;
		}
		for (int i=start; i<C; i++) {
			pick[cnt]=input[i];
			combination(cnt+1, i+1);
		}
	}

}

package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_1759 {

	static int L;	// 암호 길이
	static int C;	// 주어진 알파벳 개수
	static char[] alphabet;	// 입력받은 알파벳을 저장할 배열
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// 암호 만들기(조합)
		st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		alphabet = new char[C];
		for (int i = 0; i < alphabet.length; i++) {
			alphabet[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alphabet);	// 정렬 - 사전식으로 가능성 있는 암호이기 때문에
		
		combination(L, new char[L], 0);
		System.out.println(sb);
	}
	
	static void combination(int l, char[] choosed, int startIndx) {
		if(l==0) {
			Check(choosed);
			return;
		}
		
		for(int i=startIndx; i<alphabet.length; i++) {
			choosed[choosed.length-l] = alphabet[i];
			combination(l-1, choosed, i+1);
		}
	}

	static void Check(char[] check) {	// 모음이 최소 1개 이상, 자음은 최소 2개 이상 포함되어있는지 확인(암호의 길이가 3이상으로 입력조건에서 지정되어있지만, 모음2개 자음1개도 길이가 3이기 때문에 둘 다 검사해야함.)
		int xcount = 0;	// 모음 개수
		int ycount = 0;	// 자음 개수
		for(int i=0; i<check.length; i++) {
			if(check[i]=='a'||check[i]=='e'||check[i]=='i'||check[i]=='o'||check[i]=='u') {
				xcount++;
			}else {
				ycount++;
			}
		}
		if(xcount>=1 && ycount>=2) {
			for(char c:check) {
				sb.append(c);
			}
			sb.append("\n");
		}
		return;
	}
}

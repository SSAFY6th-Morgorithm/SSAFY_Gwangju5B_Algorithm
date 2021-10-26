package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class bj_9935 {
	
	static List<Character> str;	// 문자열
	static char[] bb;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// 문자열 폭발
		String orign = br.readLine();
		str = new ArrayList<>();
		
		String bomb = br.readLine();
		bb = new char[bomb.length()];

		for(int i=0; i<orign.length(); i++) {
			str.add(i, orign.charAt(i));
		}
		
		for(int j=0; j<bb.length; j++) {
			bb[j] = bomb.charAt(j);
		}
		
		/*for(int i=0; i<size; i++) {
			System.out.println("여기");
			System.out.print(str.get(i)+" ");
		}*/
		
		for(int s=0; s<str.size(); s++) {
			if(str.get(s).equals(bb[0])){
				s = find(s)-1;
			}
		}
		
		if(str.isEmpty()) {				// 남은 문자열이 없을 경우
			sb.append("FRULA");
		}else {
			for (char items : str) {	// 문자열이 남아있을 경우
				sb.append(items);
			}
		}
		
		System.out.println(sb);
	}
	
	static int find(int s) {
		// 폭발 문자열 찾기
		int e;
		for(e=s+1; e<s+bb.length; e++) {
			if(!str.get(e).equals(bb[e-s])) {
				return e;
			}
		}
		
		// 문자열 폭발시키기
		for(int r=0; r<bb.length; r++) {
			str.remove(s);
		}
		
		if(s==0) return 0;
		return s-1;
	}

}

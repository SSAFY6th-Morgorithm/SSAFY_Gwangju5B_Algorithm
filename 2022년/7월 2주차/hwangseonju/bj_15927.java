package string;

import java.io.*;

public class bj_15927 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		// 회문은 회문아니야!!

		String str = br.readLine();
		int size = str.length();
		char[] pal = new char[size];
		
		for(int s=0; s<size; s++) {
			pal[s] = str.charAt(s);
		}
		
		int result = -1;
		int right = size/2;
		int left = size/2;
		
		if(size%2==0) {
			right -= 1;
		}
	
		boolean flag = false;
		for(int i=right; i>=0; i--) {
			if(pal[i] != pal[left++]) {
				result = size;
				flag = false;
				break;
			} 
			
			if(size>1 && pal[i] != pal[i+1])
				flag = true;
		}
		
		if(flag) {
			result = size-1;
		}
		
		System.out.println(result);
	}
}
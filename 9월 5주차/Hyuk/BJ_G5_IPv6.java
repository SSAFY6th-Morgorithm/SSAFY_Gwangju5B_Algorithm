package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_G5_3107 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] strs = br.readLine().split(":");
		String[] restore = new String[8];
		int size = strs.length;

		for (int i = 0, j = 0; i < size; i++) {
			restore[j] = strs[i];
			if (strs[i].length() == 4) {
				j++;
				continue;
			} else if (strs[i].length() > 0) {
				for (int k = 0; k < 4 - strs[i].length(); k++) {
//					System.out.println(i + " : " +restore[j]);
					restore[j] = "0" + restore[j];
				}
				j++;
			} else {
				while (j <= 8 - (size - i)) {
					restore[j++] = "0000";
				}
			}

		}
		for (int i = 0; i < 8; i++) {
			if (restore[i] != null)
				sb.append(restore[i]);
			else sb.append("0000");
			if (i != 7)
				sb.append(":");
		}
		System.out.println(sb);

	}
}

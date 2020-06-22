package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1120_¹®ÀÚ¿­ {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] str = br.readLine().split(" ");
		String A = str[0];
		String B = str[1];
		int dif = B.length() - A.length();
		int cnt = 0;
		int min = 50;
		int k = 0;

		
		for (int i = 0; i < dif+1; i++) {
			
		
			cnt = 0;
			for (int j = 0; j < A.length(); j++) {
				
				if (A.charAt(j) != B.charAt(k + j))
					cnt++;
			}
			
			if (min > cnt)
				min = cnt;
			
			k++;
		}

		bw.write(String.valueOf(min));

		bw.flush();
		br.close();
		bw.close();
	}

}

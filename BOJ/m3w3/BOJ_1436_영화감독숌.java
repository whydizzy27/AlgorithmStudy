package m3w3;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class BOJ_1436_영화감독숌 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		int cnt=0;
		int num=665;
		while(cnt!=n) {
			num++;
			String s = String.valueOf(num);
			for (int i = 0; i < s.length()-2; i++) {
				if(s.charAt(i)=='6'&&s.charAt(i+1)=='6'&&s.charAt(i+2)=='6') {
					cnt++;
					break;
				}
			}
			
		}
		
		bw.write(String.valueOf(num) + " ");
		bw.flush();
		br.close();
		bw.close();
	}

}

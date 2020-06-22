package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_8958_OXÄûÁî {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tn = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= tn; tc++) {
			int cnt=0;
			int score=0;
			String str = br.readLine();
			
			for (int j = 0; j < str.length(); j++) {
				if(str.charAt(j)=='O') {
					cnt++;
					score+=cnt;
				}
				else {
					cnt=0;
				}
			}
			
			bw.write(String.valueOf(score)+"\n");
		}
		
		bw.flush();
		br.close();
		bw.close();
	}

}

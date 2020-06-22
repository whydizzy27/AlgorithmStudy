package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_4366_정식이의은행업무 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			String two = br.readLine();
			String three = br.readLine();
			
			int ans = 0;
			L:for (int i = 0; i < two.length(); i++) {
				for (int j = 0; j < three.length(); j++) {
					for (int j2 = 0; j2 < 2; j2++) {
						if(j2==two.charAt(i)) continue;
						StringBuilder sb1 = new StringBuilder(two);
						sb1.setCharAt(i, (char)(j2+'0'));
						for (int k = 0; k < 3; k++) {
							if(k==three.charAt(j)) continue;
							StringBuilder sb2 = new StringBuilder(three);
							sb2.setCharAt(j, (char)(k+'0'));
							
							int a = Integer.parseInt(sb1.toString(), 2);
							int b = Integer.parseInt(sb2.toString(), 3);
							
							if(a==b) {
								ans = a;
								break L;
							}
						}
					}
				}
			}
			
			bw.write("#" + tc + " " + String.valueOf(ans) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

}

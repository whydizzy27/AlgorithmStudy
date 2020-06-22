package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Solution_D3_1234_��й�ȣ_������ {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int tc = 1; tc <= 10; tc++) {

			String[] str = br.readLine().split(" ");
			int n = Integer.parseInt(str[0]);
			ArrayList<Character> list = new ArrayList<>();
//			char[] arr = new char[str[1].length()];
			for (int i = 0; i < str[1].length(); i++) {
				list.add(str[1].charAt(i));
			}
			L :while(true) {

				for (int i = 0; i < list.size()-1; i++) {
					if(i==list.size()-2&&!(list.get(i)==list.get(i+1))) {
						break L;
					}
					
					
					else if(list.get(i)==(list.get(i+1))) {
						list.remove(i);
						list.remove(i);
						break;
					}
				}
			}
			
			bw.write("#"+tc+" ");

			for (int i = 0; i < list.size(); i++) {
				bw.write(list.get(i));

			}
			bw.write("\n");

		}

			
		
		
		bw.flush();
		br.close();
		bw.close();
	}

}

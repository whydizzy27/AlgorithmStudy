package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class swexpert_�踷����ڸ��� {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int Tnum = Integer.parseInt(br.readLine());
		Stack<Character> st = new Stack<>();
		char ch;
		for (int y = 0; y < Tnum; y++) {
			int cnt = 0;

			String str = br.readLine();
			for (int i = 0; i < str.length(); i++) {
				ch = str.charAt(i);
				if (ch == '(')
					st.push(ch);
				else
					if(str.charAt(i-1)=='(') {
						st.pop();

						cnt+=st.size();
					}
					else {
						st.pop();
						cnt+=1;
					}

			}
			
			bw.write("#"+(y+1)+" "+String.valueOf(cnt)+"\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

}

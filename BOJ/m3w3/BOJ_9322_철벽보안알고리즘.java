package m3w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class BOJ_9322_철벽보안알고리즘 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < tn; tc++) {
			int n = Integer.parseInt(br.readLine());
			
			String[] pass1 = br.readLine().split(" ");
			String[] pass2 = br.readLine().split(" ");
			String[] solve = br.readLine().split(" ");
			
			String[] answer = new String[n];
			int[] idxArr = new int[n];
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(pass2[i].equals(pass1[j]))
						idxArr[j]=i;
				}
			}
			
			for (int i = 0; i < n; i++) {
				answer[i]=solve[idxArr[i]];
			}
			
			for (int i = 0; i < n; i++) {
				bw.write(answer[i] + " ");
			}
			bw.write("\n");
			
		}



		bw.flush();
		br.close();
		bw.close();
	}

}

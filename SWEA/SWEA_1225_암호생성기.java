package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1225_암호생성기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		Queue<Integer> q = new LinkedList<>();
		
		for (int tc = 0; tc < 10; tc++) {
			br.readLine();
			
			String[] str = br.readLine().split(" ");
			for(int i=0;i<8;i++) {
				q.offer(Integer.parseInt(str[i]));
			}
			
			int temp;
			boolean isOver = false;
			while(true) {
				for(int j=1;j<=5;j++) {
					temp=q.poll();
					if(temp-j<=0) {
						q.offer(0);
						isOver=true;
						break;
					}
					q.offer(temp-j);
				}
				if(isOver)
					break;
			}
			
			bw.write("#"+(tc+1)+" ");
			while(!q.isEmpty()) {
				bw.write(String.valueOf(q.poll())+" ");
			}
			bw.write("\n");

			
		}
		
		bw.flush();
		br.close();
		bw.close();

	}

}

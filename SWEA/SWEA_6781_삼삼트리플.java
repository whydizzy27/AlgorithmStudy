package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_6781_ªÔªÔ∆Æ∏Æ«√ {
	static int[] Number;
	static char[] Color;
	static int[] idxArr;
	static boolean[] visited;
	static boolean win;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());
		Number = new int[9];
		idxArr = new int[9];
		Color = new char[9];
		visited = new boolean[9];
		
		for (int i = 1; i <= tn; i++) {
			
			String str[] = br.readLine().split("");
			String str2 = br.readLine();
			
			for (int j = 0; j < 9; j++) {
				Number[j]=Integer.parseInt(str[j]);
				Color[j]=str2.charAt(j);
			}
			win=false;

			Sol(0);
			
			if(win==true)
				bw.write("#"+i+" Win\n");
			else
				bw.write("#"+i+" Continue\n");

		}
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void Sol(int idx) {
		if(idx==9) {
			if(Color[idxArr[0]]==Color[idxArr[1]]&&Color[idxArr[1]]==Color[idxArr[2]]&&
					Color[idxArr[3]]==Color[idxArr[4]]&&Color[idxArr[4]]==Color[idxArr[5]]&&
					Color[idxArr[6]]==Color[idxArr[7]]&&Color[idxArr[7]]==Color[idxArr[8]]) {
				
				if((Number[idxArr[0]]==Number[idxArr[1]]&&Number[idxArr[1]]==Number[idxArr[2]])||
						(Number[idxArr[0]]+1==Number[idxArr[1]]&&Number[idxArr[1]]+1==Number[idxArr[2]])) {
					
					if((Number[idxArr[3]]==Number[idxArr[4]]&&Number[idxArr[4]]==Number[idxArr[5]])||
							(Number[idxArr[3]]+1==Number[idxArr[4]]&&Number[idxArr[4]]+1==Number[idxArr[5]])) {
						
						if((Number[idxArr[6]]==Number[idxArr[7]]&&Number[idxArr[7]]==Number[idxArr[8]])||
								(Number[idxArr[6]]+1==Number[idxArr[7]]&&Number[idxArr[7]]+1==Number[idxArr[8]])) {
							
							win=true;
						}
					}
				}
			}
			
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if(visited[i]==true) continue;
			visited[i]=true;
			idxArr[idx]=i;
			Sol(idx+1);
			visited[i]=false;
			if(win==true)
				return;
		}
	}

}

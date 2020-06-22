package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2991_사나운개 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] str = br.readLine().split(" ");
		
		int As = Integer.parseInt(str[0]);
		int Ae = Integer.parseInt(str[1]);
		int Bs = Integer.parseInt(str[2]);
		int Be = Integer.parseInt(str[3]);
		
		boolean chk=false;
		int[] arr = new int[1000];
		int idx=1;
		while(true) {
			for (int i = 0; i <As ; i++) {
				arr[idx]+=1;
				idx++;
				if(idx==1000) {
					chk=true;
					break;
				}
			}
			if(chk==true)
				break;
			for (int i = 0; i <Ae ; i++) {
				
				idx++;
				if(idx==1000) {
					chk=true;
					break;
				}
			}
			if(chk==true)
				break;
		}
		idx=1;
		chk =false;
		while(true) {
			for (int i = 0; i <Bs ; i++) {
				arr[idx]+=1;
				idx++;
				if(idx==1000) {
					chk=true;
					break;
				}
			}
			if(chk==true)
				break;
			for (int i = 0; i <Be ; i++) {
				
				idx++;
				if(idx==1000) {
					chk=true;
					break;
				}
			}
			if(chk==true)
				break;
			
			
		}
		String[] str2=br.readLine().split(" ");
		int p = Integer.parseInt(str2[0]);
		int q = Integer.parseInt(str2[1]);
		int w = Integer.parseInt(str2[2]);
		
		bw.write(String.valueOf(arr[p])+"\n");
		bw.write(String.valueOf(arr[q])+"\n");
		bw.write(String.valueOf(arr[w])+"\n");
		bw.flush();
		br.close();
		bw.close();
		
	}

}

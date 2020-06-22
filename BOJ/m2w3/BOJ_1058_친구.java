package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class YooDongGyun_BOJ_1058_ģ�� {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//ģ�� ��
		int n = Integer.parseInt(br.readLine());
		//�� ĳ������
		char[][] map = new char[n][n];
		
		for(int i=0;i<n;i++) {
			String str = br.readLine();
			for(int j=0;j<n;j++) {
				if(i==j)
					map[i][j]='X';
				else
					map[i][j]=str.charAt(j);
			}
		}
		//�Է� ��
		
		
		int max = 0;
		int cnt;
		for(int i=0;i<n;i++) {
			cnt=0;
			for(int j=0;j<n;j++) {
				if(map[i][j]=='Y') {
					cnt++;
				}
				else if(map[i][j]=='N') {
					for(int k=0;k<n;k++) {
						if(map[j][k]=='Y'&&map[i][k]=='Y') {
							cnt++;
							break;
						}
					}
				}
			}
			if(max<cnt)
				max=cnt;
		}
		
		bw.write(String.valueOf(max));
		bw.flush();
		br.close();
		bw.close();
	}
}

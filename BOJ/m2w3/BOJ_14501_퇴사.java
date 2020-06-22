package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_14501_퇴사{

	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		
		int[] T = new int[n+2];
		int[] P = new int[n+2];
		int[] dp = new int[n+2];
		
		for(int i=1;i<=n;i++) {
			String[] str = br.readLine().split(" ");
			T[i] = Integer.parseInt(str[0]);
			P[i] = Integer.parseInt(str[1]);
		}
		
		//dp[n] : n�Ϻ��� �� �� �ִ� ��
		for(int i=n;i>0;i--) {
			int day = i+T[i]; //���� + ���ϻ��Ҹ�ð� . �� ���� ���� ��� ���� ��
			
			if(day<=n+1) //7�� + 7�� �ҿ�1���̶� ġ�� �ּ� 8
				dp[i]=Math.max(P[i]+dp[i+T[i]], dp[i+1]); //������� �� ���� ���� ��������
			else
				//����� �ʰ� �� 0��
				dp[i]=dp[i+1];
		}
		
		bw.write(String.valueOf(dp[1]));
	
		bw.flush();
		br.close();
		bw.close();
	}

}

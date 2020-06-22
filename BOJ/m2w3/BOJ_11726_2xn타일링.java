import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_11726_2xn타일링 {
	static int[] dp;//main�� ����ƽ�̶� ����ƽ���� ����� �ʵ�� �޼ҵ�� & �̰� ��������Ŭ������..

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s = br.readLine();
		int n = Integer.parseInt(s);
		dp = new int[n+1];

		bw.write(String.valueOf(func(n)));
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static int func(int n) {
		if(n==0) return 1;//�̰ŵ� �ϳ��� ģ�� �ȳֱ��� ����
		if(n==1) return 1;
		if(dp[n]>0) return dp[n];
		dp[n]=func(n-1)+func(n-2);
		dp[n]%=10007;
		return dp[n];
	}

}

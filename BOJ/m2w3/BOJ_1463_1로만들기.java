import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1463_1로만들기 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));;
		String s = br.readLine();
		int n = Integer.parseInt(s);
		int[] dp = new int[n+1];//0���ʹϱ� 
		
		dp[0] = 0;
		dp[1] = 0;
		for(int i=2;i<=n;i++) {
			dp[i]=dp[i-1]+1;
			if(i%2==0 && dp[i]>dp[i/2]+1) {
				dp[i]=dp[i/2]+1;
			}
			if(i%3==0 && dp[i]>dp[i/3]+1) {
				dp[i]=dp[i/3]+1;
			}
			
		}
		bw.write(String.valueOf(dp[n]));
		bw.flush();
		br.close();
		bw.close();
	}

}

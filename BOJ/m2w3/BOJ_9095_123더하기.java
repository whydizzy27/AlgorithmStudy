import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_9095_123더하기 {

	static int[] dp;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    String s = br.readLine();
	    int num = Integer.parseInt(s);
	    dp = new int[10+1];
	    
	    for(int i=0;i<num;i++) {
	    	int test = Integer.parseInt(br.readLine());
	    	bw.write(String.valueOf(func(test)+"\n"));
	    }
	    
	    bw.flush();
	    br.close();
	    bw.close();
	}
	
	public static int func(int n) {
		if(n==1||n==0) return 1;
		if(n==2) return 2;
		if(dp[n]>0) return dp[n];//�̰� �޸������̼� ����!!!! �̰� ������ �ð��ʰ��� func �ߴ��� �� ������ϴϱ� �̰ɽἭ �ð�����!!!
		//���� : dp[n]�� ���� ���� ������ �ؽ����� ä��� �������� ���� �ֱ⿡ �� �� �Ȱ��� �ٷ� dp[n] ���-> �ð�����

		dp[n]=func(n-1)+func(n-2)+func(n-3);
		
		return dp[n];
	}

}

package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_7206_숫자게임 {
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			max=0;
			int sjs = Integer.parseInt(br.readLine());
			dfs(sjs,0);
			bw.write("#" + tc + " " + String.valueOf(max) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void dfs(int cur, int cnt) {
		if(cur<10) {
			if(max<cnt)
				max=cnt;
			return;
		}
		
		//99999까지이므로 최대 5분할
		int k = 10;
		while(true) {
			//2분할
			if(k>cur)break; //10씩 곱해가며 2분할 위치 수 위로 한칸씩. 그러다 cur보다 커지면 자릿수 오버이므로 break;
			//몫,나머지. 그럼 나눠진 것과 같음
			int m = cur/k;
			int n = cur%k;
			
			dfs(m*n,cnt+1);
			k*=10;
			
			//3분할. 2분할 하고 난 나머지로 분할 진행
			int kk=10;
			while(true) {
				if(kk>n)break;
				int mm = n/kk;
				int nn = n%kk;
				
				dfs(m*mm*nn,cnt+1);
				kk*=10;
				
				//4분할
				int kkk = 10;
				while(true) {
					if(kkk>nn)break;
					int mmm=nn/kkk;
					int nnn=nn%kkk;
					
					dfs(m*mm*mmm*nnn,cnt+1);
					kkk*=10;
					
					int kkkk = 10;
					while(true) {
						if(kkkk>nnn)break;
						int mmmm=nnn/kkkk;
						int nnnn=nnn%kkkk;
						
						dfs(m*mm*mmm*mmmm*nnnn,cnt+1);
						kkkk*=10;
					}
				}
			}
		}
		
	}
}

package m5w1;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class YooDongGyun_월평_딸라거슬러주기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tn = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <=  tn; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sg = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int id = sg*time;
			if(id%10!=0) {
				bw.write("#"+tc+" -1\n");

			}else {
				int fc=id/400;
				int fn=id%400;
				int oc=fn/100;
				int on=fn%100;
				int cc=on/10;
				
				bw.write("#"+tc+" "+String.valueOf(fc)+" "+String.valueOf(oc)+" "+String.valueOf(cc) + "\n");
			}
			
		}

		bw.flush();
		br.close();
		bw.close();
	}

}

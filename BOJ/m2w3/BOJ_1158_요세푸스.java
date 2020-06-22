import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1158_요세푸스 {

   public static void main(String[] args) throws IOException{
      // TODO Auto-generated method stub
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      String s = br.readLine();
      
      String str[] = s.split(" ");
      int num = Integer.parseInt(str[0]);
      int rem = Integer.parseInt(str[1]);
      //�̰žƴϳ� int num = Character.getNumericValue(s.charAt(0));//�̰� ������ '0'�̳� 48����
      //int rem = Character.getNumericValue(s.charAt(2));//�ƴϸ� Character.getNumericValue(n)������
      Queue<Integer> q1 = new LinkedList<Integer>();

      for(int i=1;i<=num;i++) {
         q1.offer(i);
      }
      
      bw.write("<");
      for(int j=0;j<num-1;j++) {
          for(int i=0;i<rem-1;i++) {
             q1.offer(q1.poll());
          }
          bw.write(String.valueOf(q1.poll()) + ", ");
       }
      for(int i=0;i<rem-1;i++) {
          q1.offer(q1.poll());
       }
       bw.write(String.valueOf(q1.poll()));
       
      bw.write(">");
      
      bw.flush();
      br.close();
      bw.close();
   }

}
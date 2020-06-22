import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class BOJ_1406_에디터 {

   public static void main(String[] args) throws IOException{
      // TODO Auto-generated method stub
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      String s = br.readLine();
      int num = Integer.parseInt(br.readLine());
      Stack<Character> stack1 = new Stack<>();
      Stack<Character> stack2 = new Stack<>();
      
      for(int i=0;i<s.length();i++) {
         stack1.push(s.charAt(i));//�ϴ� ���ڿ� �� ����1�� Ǫ��
      }
      for(int i=0;i<num;i++) {
         String x = br.readLine();
         if(x.charAt(0)=='L') {
            if(!stack1.empty())
            stack2.push(stack1.pop());
         }
         else if(x.charAt(0)=='D') {
            if(!stack2.empty())
            stack1.push(stack2.pop());
         }
         else if(x.charAt(0)=='B') {
            if(!stack1.empty())
            stack1.pop();
         }
         else if(x.charAt(0)=='P') {
            stack1.push(x.charAt(2));
         }
         
      }
      
      while(!stack1.empty()) {
         stack2.push(stack1.pop());
      }
      while(!stack2.empty()) {
         bw.write(stack2.pop());
      }
      bw.flush();
      br.close();
      bw.close();
   }

}
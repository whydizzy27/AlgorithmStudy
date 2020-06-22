import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class BOJ_10799_쇠막대기{

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		Stack<Character> stack = new Stack<>();
		int result=0;
		
		
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='(') {
				stack.push('(');
			}
			else if(s.charAt(i)==')') {
				if(s.charAt(i-1)=='(') {
					stack.pop();
					result+=stack.size();
				}
				else {
					stack.pop();
					result+=1;
				}
			}
		}
		
		bw.write(String.valueOf(result));
		bw.flush();
		br.close();
		bw.close();

	}

}

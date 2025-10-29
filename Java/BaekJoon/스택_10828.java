/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;
import java.io.*;


public class Main
{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Deque<Integer> dq = new ArrayDeque<>();
		
		for(int i = 0 ; i < N ; i++){
		    String input = br.readLine();
		    //command 자르기
		    String[] tokens = input.split(" ");
		    String command = tokens[0];
		    //뒤에 인자가 있는 경우우
		    if(tokens.length > 1){
		        int value = Integer.parseInt(tokens[1]);
		        //push 실행
		        dq.push(value);
		        continue;
		    }
		    //인자가 없는 경우 = 다른 명령어
		    if (command.equals("top")){
		        //top인 경우
		        if (dq.isEmpty()){
		            System.out.println(-1);
		        } else {
		            System.out.println(dq.peek());
		        }
		    } else if (command.equals("pop")){
		        //pop인 경우 
		        if (dq.isEmpty()){
		            System.out.println(-1);
		        } else {
		            System.out.println(dq.pop());
		        }
		    } else if (command.equals("size")){
		        //size인 경우 
		        System.out.println(dq.size());
		    } else {
		        //empty인경우
		        if(dq.size() == 0){
		            System.out.println(1);
		        } else{
		            System.out.println(0);
		        }
		    }
		    
		    
		}
	}
}

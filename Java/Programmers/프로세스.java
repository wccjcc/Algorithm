
import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] priorities, int location) throws IOException {
        int answer = 0;
        Deque<int[]> dq = new ArrayDeque<>();
        for(int i = 0 ; i < priorities.length; i++){
            dq.add(new int[]{i,priorities[i]});
        }
        int turn = 1;
        while(true){
            int[] temp = dq.poll();
            int loc = temp[0];
            int pri = temp[1];
            //우선순위가 더 높은 프로세스가 있다면 큐에 다시 넣기
            boolean flag = false;
            for(int[] t : dq){
                if(t[1] > pri){
                    flag = true;
                    break;
                }
            }
            if(flag == true) dq.add(temp); 
              //내가찾는 위치라면 현재 turn을 반환
            else if (loc == location){
                return turn;
            } else{ //내가찾는위치가 아니라면 turn만 증가하고 프로세스 실행하고넘어가기
                turn++;
            }

        }

    }
}

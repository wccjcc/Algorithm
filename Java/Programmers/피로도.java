import java.util.*;
import java.io.*;

class Solution {
    // static List<Integer> o = new ArrayList<>();
    static Deque<Integer> o = new ArrayDeque<>();
    static int answer = -1;
    static boolean[] visited;
    //시뮬레이션 메서드
    public static int simulation(int k, int[][] dungeons,Deque<Integer> order){
        int result = 0;
        for(int o : order){
            //현재 던전에 입장할수있다면 던전 탐험
            if(k >= dungeons[o][0]){
                result++;
                k -= dungeons[o][1];
            }
            //아니면 넘어가
        }
        
        return result;
    }
    //순열 메서드
    public static void permutation(int k, int[][] dungeons){
        int dunNum = dungeons.length;
        
        
        //순열이 완성되었다면
        if(o.size() == dunNum){
            int result = simulation(k,dungeons,o);
            answer = Math.max(answer,result);
            return;
        }
        //완성되지 않았다면
        for(int i = 0; i < dunNum; i++){
            //방문하지 않았다면
            if(!visited[i]){
                visited[i] = true;
                o.addLast(i);
                permutation(k,dungeons);
                visited[i] = false;
                o.removeLast(); 
            }
        }
    }
    //메인 메서드
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        permutation(k,dungeons);
        return answer;
    }
}

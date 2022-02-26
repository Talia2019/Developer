import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
//  높은 곳의 상자를 낮은 곳에 옮기는 방식으로 최고점과 최저점의 간격을 줄이는 작업을 평탄화라고 한다.
//  평탄화를 모두 수행하고 나면, 가장 높은 곳과 가장 낮은 곳의 차이가 최대 1 이내가 된다.
//  평탄화 작업을 위해서 상자를 옮기는 작업 횟수에 제한이 걸려있을 때, 제한된 횟수만큼 옮기는 작업을 한 후 최고점과 최저점의 차이를 반환하는 프로그램을 작성하시오.
//  가장 높은 곳에 있는 상자를 가장 낮은 곳으로 옮기는 작업을 덤프라고 정의한다.
//  가로 길이는 항상 100으로 주어진다.
//  모든 위치에서 상자의 높이는 1이상 100이하로 주어진다.
//  덤프 횟수는 1이상 1000이하로 주어진다.
//  주어진 덤프 횟수 이내에 평탄화가 완료되면 더 이상 덤프를 수행할 수 없으므로 그 때의 최고점과 최저점의 높이 차를 반환한다 (주어진 data에 따라 0 또는 1이 된다).
//  [입력]
//  총 10개의 테스트 케이스가 주어지며, 각 테스트 케이스의 첫 번째 줄에는 덤프 횟수가 주어진다. 그리고 다음 줄에 각 상자의 높이값이 주어진다.
//  [출력]
//  #부호와 함께 테스트 케이스의 번호를 출력하고, 공백 문자 후 테스트 케이스의 최고점과 최저점의 높이 차를 출력한다.
 
//  정렬
//  제일높은걸 제일낮은쪽에 넣음
//  젤높은거-두번째높은거
//  맨뒤-그앞
//  더작은거로 채움
//  차가 같으면 1만큼만 하고 다시 정렬
 
//  만약 top - bottom <=1이면 탈출
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        final int TEST_CASE = 10;
 
        int dump = 0;
        int[] boxes = new int[101];
        for (int i = 0; i < TEST_CASE; i++) {
            dump = Integer.parseInt(in.readLine());
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < 100; j++) {
                int h = Integer.parseInt(st.nextToken());
                boxes[j] = h;
            }
            Arrays.sort(boxes);
            int cnt = 0;
            int top = boxes[99], bottom = boxes[0];
            int gapF, gapB, gap = 1;
            int indexF = 0, indexB = 99;
            while (cnt < dump) {
                if(top-bottom<=1) {
                    System.out.println(top-bottom);
                    return;
                }
                gapF = boxes[indexF + 1] - boxes[indexF];
                gapB = boxes[indexB] - boxes[indexB - 1];
                gap = gapF > gapB ? gapB : gapF;
                if(gap==0) {
                    gap = 1;
                    boxes[indexB] -= gap;
                    boxes[indexF] += gap;
                    Arrays.sort(boxes);
                    top = boxes[99];
                    bottom = boxes[0];
                }
                else {
                    boxes[indexB] -= gap;
                    boxes[indexF] += gap;
                }
                cnt += gap;
            }
            System.out.println("#" + (i+1) + " " + (boxes[99]-boxes[0]));
        }
    }
 
}

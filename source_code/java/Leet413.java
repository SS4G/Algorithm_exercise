/**
 * Created by VULCAN on 2017/2/5.
 */
package AlgorithmTraining.exercise.leetcode.java_src;
class Leet413 {
    public int numberOfArithmeticSlices(int[] A) {
        //int length=A.length;
        int continue_arth=0;
        boolean is_tri_arth=false;
        boolean tri_arth_first_detect=false;
        int arth_sum=0;
        for(int i=0;i<=A.length-3;i++){
            is_tri_arth=tri_num_arth(A,i);
            if(is_tri_arth && ! tri_arth_first_detect){
                continue_arth=3;
                tri_arth_first_detect=true;
                if(i==A.length-3)
                    arth_sum+=calculate_arth_num(continue_arth);
            }
            else if(is_tri_arth && tri_arth_first_detect){
                continue_arth+=1;
                if(i==A.length-3)
                    arth_sum+=calculate_arth_num(continue_arth);
            }
            else{
                tri_arth_first_detect=false;
                arth_sum+=calculate_arth_num(continue_arth);//calculate nums
                continue_arth=0;
            }
        }
        return arth_sum;
    }
    boolean tri_num_arth(int[] arr,int start_index){
        return (arr[start_index]-arr[start_index+1])==(arr[start_index+1]-arr[start_index+2]);
    }
    int calculate_arth_num(int arth_len){
        if(arth_len>=3)
            return (arth_len-1)*(arth_len-2)/2;
        else
            return 0;
    }
}

package AlgorithmTraining.data_structure.Sort;

import sun.misc.Compare;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * Created by BUPT_SS4G on 2017/4/6.
 */
class MyElement implements Comparable<MyElement> {
    public String name;
    public int age;
    public MyElement(int age, String name){
        this.age = age;
        this.name = name;
    }
    //this < e return -int
    //this == e return 0
    //this > e return +int
    public int compareTo(MyElement e){

        if(e.age < this.age)
            return 1;
        else if(e.age == this.age)
            return 0;
        else
            return -1;
    }
}

public class SortFunctionTest {

    //产生一组要排序的MyElement序列
    public static LinkedList<MyElement[]> testcase_loader(){
        String testCaseFileName = "/home/mi/SS4G/Alg_java_linux/src/AlgorithmTraining/data_structure/Sort/sort_testcases2.txt";
        LinkedList<MyElement[]> testcases = new LinkedList<MyElement[]>();
        File f = new File(testCaseFileName);
        int[] init_arr=null;
        MyElement[] element_arr = null;

        try{
            String line;
            Scanner sc = new Scanner(f);
            String[] nums_str;

            while (sc.hasNextLine()){  //handle 1 line
                line = sc.nextLine();
                nums_str = line.trim().split("\\s"); // 真正的正则表达式是 \s 但是在字符串中 \ 应该写成\\
                init_arr = new int[nums_str.length];

                for(int p = 0; p < init_arr.length; p++)
                    init_arr[p] = Integer.parseInt(nums_str[p]);

                element_arr = new MyElement[init_arr.length];
                for(int j=0; j < init_arr.length; j++)
                    element_arr[j] = new MyElement(init_arr[j],"AA");
                testcases.add(element_arr);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("sss");
            e.printStackTrace();
        }
        return testcases;
    }

    //显示一个MyElement序列
    public static void show_arr(MyElement[] arr){
        for(MyElement e : arr)
            System.out.println("age: "+e.age+" name: "+e.name+" hash_code:"+e.hashCode());
        System.out.println("---------------\n");
    }

    public static void main(String[] args){
        // testbench
        LinkedList<MyElement[]> test_arr = testcase_loader();

        for(int index=0; index < test_arr.size(); index++) {
            MyElement[] arr = test_arr.get(index);
            BubleSort.sort(arr);
            //show_arr(arr);
            assert SortTemplate.isSorted(arr) : "sort result error!";
        }
        System.out.println("process terminate successfully!");
    }
}


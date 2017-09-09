package AlgorithmTraining.data_structure.Base_Knowledge.Sort;

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
    public static Comparable[][] testcase_loader(){
        String testCaseFileName = "/home/mi/SS4G/Alg_java_linux/src/AlgorithmTraining/data_structure/Sort/sort_testcases2.txt";
        LinkedList<MyElement[]> testcases = new LinkedList<MyElement[]>();
        File f = new File(testCaseFileName);
        int[] init_arr=null;
        MyElement[] element_arr = null;
        Comparable[][] testcase_arr = null;
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

            int testcase_length = testcases.size();
            testcase_arr = new Comparable[testcase_length][];
            for (int i = 0; i < testcase_length; i++) {
                testcase_arr[i] = testcases.removeFirst();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("sss");
            e.printStackTrace();
        }
        return testcase_arr;
    }

    public static Integer[][] littleTestCaseLoader() {
        Integer[][] littleTestCase = {
                {1, 2, 3, 4, 5},
                {2, 5, 7, 8},
                {4, 7, 6},
                {2, 1},
                {1},
                {0,0,0},
        };
        return littleTestCase;
    }
    //显示一个MyElement序列
    public static void show_arr(MyElement[] arr){
        for(MyElement e : arr)
            System.out.println("age: "+e.age+" name: "+e.name+" hash_code:"+e.hashCode());
        System.out.println("---------------\n");
    }

    private static void runTest(String[] sortTypes, Comparable[][] testcases) {
        for(String type : sortTypes) {
            for(Comparable[] case1 : testcases) {
                Comparable[] case0 = new Comparable[case1.length];
                Comparable[] result = null;
                for (int x = 0; x < case1.length; x++) {
                    case0[x] = case1[x];
                }

                switch (type) {
                    case "BubbleSort" : result = BubbleSort.sort(case0); break;
                    case "HeapSort" : result = HeapSort.sort(case0); break;
                    case "MergeSort" : result = MergeSort.sort(case0); break;
                    case "QuickSort" : result = QuickSortNaive.sort(case0); break;
                    case "Quick3SplitSort" : result = QuickSort3Split.sort(case0); break;
                    case "SelectSort" : result = SelectSort.sort(case0); break;
                    case "ShellSort" : result = ShellSort.sort(case0); break;
                    case "InsertSort" : result = InsertSort.sort(case0); break;
                    default: System.out.println("sort function is not selected!"); break;
                }

                try {
                    assert SortTemplate.isSorted(result);
                    /*for (int i = 0; i < case0.length; i++) {
                        System.out.println(case0[i]);
                    }
                    System.out.println("=------");*/
                }
                catch (AssertionError e) {
                    System.out.println("ERROR: sort result is wrong when use sort function " + type);

                    System.out.println("last test case is :");
                    String[] b = new String[case1.length];
                    for (int k = 0; k < case1.length; k++) b[k] = case0[k].toString();
                    System.out.println(String.join(",", b));

                    System.out.println("after sorted is :");
                    String[] a = new String[case0.length];
                    for (int k = 0; k < case0.length; k++) a[k] = case0[k].toString();
                    System.out.println(String.join(",", a));
                }
            }
            System.out.println(type+" passed!");
        }

    }

    public static void main(String[] args){
        // testbench

        //buble pass
        //heap pass
        //insert pass
        //shell pass
        //select pass
        //merge sort pass
        //QuickSortNaive sort pass
        //String[] typesToBeTest = {"BubbleSort", "HeapSort", "InsertSort", "ShellSort", "SelectSort"};
        //String[] typesToBeTest = {"Quick3SplitSort",};
        String[] typesToBeTest = {"BubbleSort", "HeapSort", "MergeSort", "QuickSort", "Quick3SplitSort",
                "SelectSort", "ShellSort", "InsertSort"};
        //Comparable[][] testcases = littleTestCaseLoader();
        Comparable[][] testcases = testcase_loader();
        runTest(typesToBeTest, testcases);
        System.out.println("process terminate successfully!");
    }
}


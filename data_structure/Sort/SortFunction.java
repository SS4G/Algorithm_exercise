package AlgorithmTraining.data_structure.Sort;

/**
 *
 * Created by BUPT_SS4G on 2017/4/6.
 */
class MyElement implements Comparable<MyElement>{
    public String name;
    public int age;
    public MyElement(int age, String name){
        this.age = age;
        this.name = name;
    }
    public int compareTo(MyElement e){
        if(e.age < this.age)
            return -1;
        else if(e.age == this.age)
            return 0;
        else
            return 1;
    }
}

public class SortFunction {

    //产生一组要排序的MyElement序列
    public static MyElement[] testcase_loader(int[] init_arr){
        MyElement[] element_arr = new MyElement[init_arr.length];
        for(int j=0; j<init_arr.length; j++){
            element_arr[j] = new MyElement(init_arr[j],"AA");
       }
       return element_arr;
    }

    //显示一个MyElement序列
    public static void show_arr(MyElement[] arr){
        for(MyElement e : arr)
            System.out.println("age: "+e.age+" name: "+e.name+" hash_code:"+e.hashCode());
        System.out.println("---------------\n");
    }



    public static void main(String[] args){
        // testbench
        MyElement[][] test_arr = new MyElement[][]{
                testcase_loader(new int[]{1,2,3,4,5}),
                testcase_loader(new int[]{5,4,3,2,1}),
                testcase_loader(new int[]{1,1,1,1,1}),
        };
        //显示排序前的序列
        System.out.println("before sort:");
        for(MyElement[] e_arr : test_arr)
            show_arr(e_arr);

        for(MyElement[] e_arr : test_arr)
            BubleSort.sort(e_arr);

        System.out.println("after sort:");
        for(MyElement[] e_arr : test_arr)
            show_arr(e_arr);
        System.out.println("process terminate successfully!");
    }

    public static void BubleSort(Comparable[] arr){
        int hi = arr.length-1;
        int lo = 0;
        int i, j, k;
        for(i=0; i<=hi; i++){

        }
    }
}
class BubleSort{
    // hi 为数组的上边界（不包含）
    // lo 为数组的下边界（包含）
    private static boolean lessthan(Comparable a, Comparable b){
        int cmp = a.compareTo(b);
        System.out.print(cmp);
        return cmp < 0;
    }

    public static void exch(Comparable[] arr, int index_a, int index_b){
        Comparable tmp = arr[index_a];
        arr[index_a] = arr[index_b];
        arr[index_b] = tmp;
    }

    public static void sort(Comparable[] arr){
        int hi = arr.length;
        int lo = 0;
        for(int i=lo; i < hi; i++)
            for(int j=i; j < hi; j++) {
                if(!lessthan(arr[i], arr[j])){
                    System.out.println(lessthan(new MyElement(2,"SA"), new MyElement(1,"OO")));
                    exch(arr, i, j);
                }
            }
    }
}

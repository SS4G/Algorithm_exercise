package AlgorithmTraining.java_learn;

/**
 *
 * Created by g55 on 2017/5/21.
 */
//内部类能够访问外部类的所有权限成员 外部类只能访问部分内部类的部分成员
//建立一个内部类的对象必须 先建立一个外部类的对象 然后使用.new来建立 内部对象
public class InnerClassT {
    public static void main(String[] args) {
        Outter o0 = new Outter(100);
        Outter.Inner i0 = o0.new Inner(102);
        o0.showOutter();
        i0.ShowInner();
        Show s = new Show() { //匿名内部类 创建一个实现了Show接口的匿名对象
            @Override
            public void prFun() {
                System.out.println("Show object s");
            }
        };
        Show r = new Show() {
            @Override
            public void prFun() {
                System.out.println("Show object r");
            }
        };
        s.prFun();
        r.prFun();
    }
}
class Outter{
    private int outterId = 1;
    public Outter(int id) {
        outterId = id;
    }

    public void showOutter() {
        System.out.println("Outter:"+outterId);
        Inner i1 = new Inner(23);
        i1.ShowInner();
    }

    public class Inner{
        private int innerId = 0;
        public Inner(int id) {
            innerId = id;
        }
        public void ShowInner() {
            System.out.println("Inner:"+innerId+"with OutterId:"+outterId);
        }
    }
}
interface Show{
    void prFun();
}

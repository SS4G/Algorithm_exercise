package AlgorithmTraining.learning.java_learn;

/**
 * Created by BUPT_SS4G on 2017/8/14.
 *
 */
import java.util.*;
import java.io.*;

class Company implements Serializable {
    String name;
    int value;
    Company(String name, int value) {
        this.name = name;
        this.value =value;
    }

    public String toString() {
          return name + ":" + value + ":" + this.hashCode();
    }
}

class Company2 implements Externalizable {
    String name = "NAME";
    int value = 1000;

    public Company2() {
        System.out.println("defalut constructor");
    }

    public String toString() {
        return name + ":" + value + ":" + this.hashCode();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("writeExtern");
        //add your SerializeCodeHere
    }

    @Override
    public void readExternal(ObjectInput out) throws IOException {
        System.out.println("readExtern");
    }
}

class Company3 implements Serializable {
    String name = "NAME";
    int value = 1000;
    transient String pwd = "10237";

    public Company3() {
        System.out.println("defalut constructor");
    }

    public String toString() {
        return name + ":" + value + ":" + this.hashCode();
    }

    //这里的writeObject方法不是覆盖 Serializable  这个借口本身没有任何方法 这里的两个private方法是通过 反射机制来调用的
    //反射机制可以调用任何权限的方法
    //使用这种方法的好处就是 可以决定哪些域要序列化而哪些不用
    //注意static的序列化需要自己去实现
    private void writeObject(ObjectOutputStream stream) throws IOException {
        System.out.println("write");
        stream.defaultWriteObject(); //根据jdk文档 这个默认的序列化方法将写入·所有的非静态以及 非瞬时域到序列化的输出文件中
        stream.writeObject(pwd); //
        //add your SerializeCodeHere
    }

    private void readObject(ObjectInputStream stream) throws IOException {
        System.out.println("read");
        //add your SerializeCodeHere
        try {
            stream.defaultReadObject();
            pwd = (String)stream.readObject(); //
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}

class Man implements Serializable {
    String name;
    int age;
    Company company;
    Man(String name, int age, Company company) {
        this.name = name;
        this.age = age;
        this.company = company;
    }

    public String toString() {
        return name + ":" + age + ":" + ":" + company + ":" + this.hashCode();
    }
}

public class IOTest {
    private static void copyTextFileAndAddMark(String srcFileName, String dstFileName) {
        try {
            //面向字符流的IO可以处理包括中文在内的unicode.
            //bufferedReader 之类的带有缓冲的输入输出流 可以使得每次读写都发生真正的磁盘IO 这样可以大大的提高读写的效率
            //带有缓冲的IO对象一般需要一个非缓冲的IO对象作为构造器的参数
            BufferedReader br = new BufferedReader(new FileReader(srcFileName));
            BufferedWriter bw = new BufferedWriter(new FileWriter(dstFileName));
            String x;
            while ((x = br.readLine()) != null) {
                bw.write(x + "\n");
            }
            bw.write("copied by g55");
            br.close(); //用完了记得关闭文件
            bw.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
        catch (IOException e) {
            System.out.println("an io exception found!");
        }
    }

    private static void readFromMem() {
        StringReader sr = new StringReader("123 456 789");
        try {
            int c;
            do {
                c = sr.read();
                System.out.print((char)c);
            } while (c!= -1);
        }
        catch (IOException e) {
            System.out.println("IO ERROR");
        }
    }

    private static void copyGenericFile(String srcFileName, String dstFileName) {
        try {
            BufferedInputStream bi = new BufferedInputStream(
                    new FileInputStream(srcFileName));
            //File f = new File(dstFileName);
            //f.createNewFile();
            BufferedOutputStream bo = new BufferedOutputStream(
                    new FileOutputStream(dstFileName));
            int offset = 0;
            while (bi.available() > 0) {
                //if (offset > 200 && offset % 3 == 0) {
                //if (offset > 200 && offset % 3 == 1) {
                if (offset > 200 && offset % 3 != 0) {
                    int data = bi.read();
                    bo.write(0);
                }
                else {
                    bo.write(bi.read());
                }
                offset++;
            }
            bi.close();
            bo.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR!!!");
        }
        catch (IOException e) {
            System.out.println("ERROR2!!!");
        }
    }

    private static void fileTest() {
        String path = "D:\\work_space\\Algorithm_training_java\\src\\AlgorithmTraining\\learning\\java_learn";
        File f = new File(path); //File只是一个抽象的问价家或者文件的路径对象 并不代表一定存在这个文件 但是可以通过这个文件路径
        //对象实现各种获取文件信息已经新建删除对应文件资源的操作
        //File f2 = new File(path + "\\HAHA");
        //f2.mkdir();
        System.out.println(f.isAbsolute());
        System.out.println(f.isDirectory());
        System.out.println(f.isDirectory());
        for (String name : f.list()) { //打印f对应路径下的文件 此时f应该是一个目录 如果f不是一个文件 将返回一个空指针
            System.out.println(name);
        }

    }

    private static void basicSer() {
        Company c0 = new Company("Baidu", 334);
        Company c1 = new Company("Alibaba", 1312);
        Company c2 = new Company("Tencent", 1311);

        Man m0 = new Man("whk", 25, c0);
        Man m1 = new Man("fs", 25, c2);
        Man m2 = new Man("szh", 25, c1);

        try {
            File f = new File("D:\\work_space\\Algorithm_training_java\\src\\AlgorithmTraining\\learning\\java_learn\\oos1.out");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(c0); //连续的将三个对象写入
            oos.writeObject(c1);
            oos.writeObject(c2);
            System.out.println(c0);
            System.out.println(c1);
            System.out.println(c2);


            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Company rc0 = (Company)ois.readObject(); //反序列化对象时应该 加上强制类型转换
            Company rc1 = (Company)ois.readObject();
            Company rc2 = (Company)ois.readObject();
            System.out.println(rc0); //如果序列化的时候只是将同一个对象连续的写入三次 那么 反序列化的时候只会产生一个对象
            System.out.println(rc1);
            System.out.println(rc2);
        }
        catch (Exception e) {
            for (StackTraceElement s: e.getStackTrace()) {
                System.out.println(s);
            }
        }
    }

    private static void externalizeSer() {
        //实现Externalizable 这个接口将会空出两个方法 这样在调用ObjectOutputStream 的时候 会调用这个借口下面的readObject方法
        //实现由用户自己定制的序列化以及反序列化方法
        try {
            File f = new File("D:\\work_space\\Algorithm_training_java\\src\\AlgorithmTraining\\learning\\java_learn\\oos1.out");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Company2 c2 = new Company2();
            oos.writeObject(c2);

            Company2 c3 = (Company2)ois.readObject(); //反序列化的对象必须有默认构造器 而且这个默认构造器的权限必须是public
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void externalizeSer2() {
        try {
            File f = new File("D:\\work_space\\Algorithm_training_java\\src\\AlgorithmTraining\\learning\\java_learn\\oos1.out");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Company3 c3 = new Company3();
            oos.writeObject(c3);

            Company3 c4 = (Company3)ois.readObject();
            System.out.println(c4.pwd);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        TestType typeNow = TestType.SERIALIZE_TEST;
        switch (typeNow) { //switch 会根据内置变量的枚举类型 来确定里面的常量值
            case FILE_OPERATE_TEST: fileTest(); break;
            case FILE_COPY:
                String srcFile = "D:\\work_space\\Algorithm_training_java\\src\\AlgorithmTraining\\learning\\java_learn\\SrcSampleFile.html";
                String dstFile = "D:\\work_space\\Algorithm_training_java\\src\\AlgorithmTraining\\learning\\java_learn\\DstSampleFile.html";
                copyTextFileAndAddMark(srcFile, dstFile);
                break;
            case FILE_COPY2:
                String srcFile2 = "D:\\work_space\\Algorithm_training_java\\src\\AlgorithmTraining\\learning\\java_learn\\samplePicture.bmp";
                String dstFile2 = "D:\\work_space\\Algorithm_training_java\\src\\AlgorithmTraining\\learning\\java_learn\\samplePicture2.bmp";
                copyGenericFile(srcFile2, dstFile2);
                break;
            case MEM_BUFFER_TEST:
                readFromMem();
                break;
            case SERIALIZE_TEST:
                //basicSer();
                //externalizeSer();
                externalizeSer2();
                break;
            default:;
        }
    }
}

enum TestType {FILE_OPERATE_TEST, MEM_BUFFER_TEST, SERIALIZE_TEST, XML_TEST, FILE_COPY, FILE_COPY2};

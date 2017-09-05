package AlgorithmTraining.statistic;

/**
 * Created by BUPT_SS4G on 2017/8/19.
 */


import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.*;


class LineStatisticUtil {
    private static String dirSpliter = "\\";

    private static int getLines(BufferedReader br) {
        int i = 0;
        try {
            while (br.readLine() != null) {
                i++;
            }
        }
        catch (IOException e) {
            System.out.println("IOException got");
            System.exit(0);
        }
        return i;
    }

    private static int getFileLines(String fileName) {
        File fname = new File(fileName);
        int lines = 0;
        FileReader fr;
        BufferedReader br;
        try {
            fr = new FileReader(fname);
            br = new BufferedReader(fr);
            lines = getLines(br);
            fr.close();
            br.close();
        }
        catch (IOException e1) {
            System.out.println(e1);
            System.exit(0);
        }
        return lines;
    }

    private static void getDirStatistic(HashMap<String, Integer> output, String[] fileTypes, String dir) {
        File dirHandler = new File(dir);
        String[] filesBelow = dirHandler.list();
        List<Pattern> patterns = new ArrayList<>();
        for (String s : fileTypes) {
            patterns.add(Pattern.compile("^\\w+\\." + s + "$"));
        }

        for (String s : filesBelow) {
            File fileHandler = new File(dir + s);
            if (fileHandler.isDirectory()) {
                getDirStatistic(output, fileTypes, dir + s + dirSpliter);
            }
            else {
                for (Pattern p : patterns) {
                    Matcher m = p.matcher(s);
                    if (m.lookingAt()) {
                        //System.out.println(s);
                        String type = (s.split("\\."))[1];
                        //System.out.println(type);
                        output.put(type, output.get(type) + getFileLines(dir + s));
                        break;
                    }
                }
            }
        }
    }

    public static HashMap<String, Integer> getRootStatistic(String rootDir, String[] fileTypes) {
        HashMap<String, Integer> output = new HashMap<>();
        for (String s : fileTypes) {
            output.put(s, 0);
        }
        getDirStatistic(output, fileTypes, rootDir);
        return output;
    }
}

public class LineStatistic {
    private static String targetDir = "D:\\workSpace\\alg_java\\src\\AlgorithmTraining\\";
    private static String[] fileTypes = {"cpp", "py", "c", "java"};
    public static void main(String[] args) {
        HashMap<String, Integer> res = LineStatisticUtil.getRootStatistic(targetDir, fileTypes);
        int sum = 0;
        for (String type : res.keySet()) {
            System.out.println(type + ":  " + res.get(type) + " lines");
            sum += res.get(type);
        }
        System.out.println("total :" + sum + " lines!");
    }
}

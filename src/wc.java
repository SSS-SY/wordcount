/**
 * Created by SSS on 2018/3/18.
 */

import java.io.*;
import java.util.*;
import java.lang.*;
public class wc {
    //stop words:
    private static Set<String> stopWords(String stopfile) {
        Set<String> s = new HashSet<>();
        s.add("");
        if (stopfile == null)
            return s;
        try {
            String[] words = getWords(stopfile);
            s.addAll(Arrays.asList(words));
            return s;
        } catch (Exception e) {
            error("cannot read stopLists");
            e.printStackTrace();
        }
        return s;
    }

    //计算单词数
    private static int wc(String contents, Set<String> stopLists) {
        int count = 0;
        String[] spStr = contents.split("[ ,]");
        for (String word : spStr) {
            if (!stopLists.contains(word))
                count++;
        }
        return count;
    }

    //计算字符数
    private static int cc(String[] contents) {
        int count = 0;
        for (String line : contents)
            count += line.length();
        return count;
    }

    //计算行数
    private static int[] cl(String[] contents) {
        //code／blank/comments
        int[] lines = {0, 0, 0};
        for (String line : contents) {
            if (line.trim().length() <= 1)
                lines[1]++;
            else {
                if (!line.trim().contains("//")) {
                    lines[0]++;
                } else if (line.replaceAll(" ", "").indexOf("//") > 1) {
                    lines[0]++;
                } else if (line.contains("//")) {
                    lines[2]++;
                }
            }
        }
        return lines;
    }

    //按行获取文件内容
    private static ArrayList<String> getLines(String path) {
        ArrayList<String> contents = new ArrayList<>();
        Scanner scanner = null;
        try {
            File file = new File(path);
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            error("cannot find file: " + path);
            e.printStackTrace();
        }
        if (scanner != null) {
            while (scanner.hasNextLine()) {
                contents.add(scanner.nextLine());
            }
            scanner.close();
        }
        return contents;
    }

    //提示出错
    public static void error(String error) {
        System.out.printf("error: %s\n", error);
    }

    //按字获取文件内容
    private static String[] getWords(String path) {
        String[] res = null;
        try {
            InputStream is = new FileInputStream(path);
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            res = buffer.toString().split("[\n \t]]");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    //输出结果
    private static void printResultToFile(String file, HashMap<Character, String> args, PrintWriter output) {
        ArrayList<String> lineContents = getLines(file);
        String[] contents = lineContents.toArray(new String[lineContents.size()]);
        if (args.containsKey('c')) {
            output.printf("%s, 字符数: %d\n", file, cc(contents));
        }
        if (args.containsKey('w')) {
            Set<String> stopwords = stopWords(args.get('e'));
            int words = 0;
            for (String line : contents) {
                words += wc(line, stopwords);
            }
            output.printf("%s，单词数: %d\n", file, words);
        }
        if (args.containsKey('l')) {
            output.printf("%s，行数: %d\n", file, contents.length);
        }
        if (args.containsKey('a')) {
            int[] lines = cl(contents);
            output.printf("%s, 代码行／空行／注释行: %d/%d/%d\n", file, lines[0], lines[1], lines[2]);
        }
    }

    //递归寻找文件
    private static String[] searchFiles(String path, boolean s) {
        File fp = new File(path);
        int idx = path.lastIndexOf('/');
        String dirpath, filename;
        ArrayList<String> sal = new ArrayList<>();
        if (fp.isDirectory()) {
            for (File f : fp.listFiles()) {
                if (f.isFile()) {
                    sal.add(f.getPath());
                } else if (f.isDirectory() && s) {
                    sal.addAll(Arrays.asList(searchFiles(f.getPath(), s)));
                }
            }
        } else {
            if (idx > 0) {
                dirpath = "./" + path.substring(0, idx + 1);
                filename = path.substring(idx + 1, path.length());
            } else {
                dirpath = "./";
                filename = path;
            }
            if (filename.contains("*")) {
                filename = filename.replaceAll("\\*", "\\\\w*");
            }
            File[] fs = (new File(dirpath)).listFiles();
            assert fs != null;
            for (File f : fs) {
                if (f.getName().matches(filename) && f.isFile()) {
                    sal.add(dirpath + f.getName());
                }
            }
        }
        return sal.toArray(new String[sal.size()]);
    }

    //参数设置
    private static HashMap<Character, String> parseArgs(String[] args) {
        HashMap<Character, String> argsmap = new HashMap<Character, String>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == '-' && args[i].length() < 3) {
                switch (args[i].charAt(1)) {
                    case 'o':
                        i++;
                        if (i < args.length) {
                            argsmap.put(args[i].charAt(1), args[i]);
                        } else {
                            error("lacking path of out put file");
                        }
                        break;
                    case 'e':
                        i++;
                        if (i < args.length) {
                            argsmap.put(args[i].charAt(1), args[i]);
                        } else {
                            error("lacking path of out put file");
                        }
                        break;
                    default:
                        argsmap.put(args[i].charAt(1), "");
                        break;
                }
            } else {
                argsmap.put('i', args[i]);
            }
        }

        return argsmap;
    }

    public static void main(String[] args) {
        HashMap<Character, String> argsmap = parseArgs(args);
        String output = "result.txt";
        if (!argsmap.containsKey('i')) {
            error("please input the name of inputFile");
            return;
        }
        if (argsmap.containsKey('o')) {
            output = argsmap.get('o');
        }
        File file = new File(output);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(output);
            String[] fileList = searchFiles(argsmap.get('i'), argsmap.containsKey('s'));
            for (String f : fileList)
                printResultToFile(f, argsmap, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
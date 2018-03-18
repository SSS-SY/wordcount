/**
 * Created by SSS on 2018/3/18.
 */
import java.io.*;
import java.util.*;
import java.lang.*;
public class wc {
    //提示出错
    public static void error(String error) {
        System.out.printf("error: %s\n", error);
    }

    //参数设置
    private static class Args {
        boolean c = false;
        boolean w = false;
        boolean l = false;
        boolean o = false;
        String input;
        String output;

        Args(String[] args) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].charAt(0) == '-' && args[i].length() < 3) {
                    switch (args[i].charAt(1)) {
                        case 't':
                            this.c = true;
                            break;
                        case 'w':
                            this.w = true;
                            break;
                        case 'l':
                            this.l = true;
                            break;
                        case 'c':
                            this.c = true;
                            break;
                        case 'o':
                            this.o = true;
                            i++;
                            if (i < args.length) {
                                this.output = args[i];
                            } else {
                                error("lacking path of out put file");
                            }
                            break;
                        default:
                            break;
                    }
                } else {
                    this.input = args[i];
                }
            }
        }
    }

    //计算单词数
    private static int wc(String contents) {
        int count = 0;
        String[] spStr = contents.split("[ ,]");
        for (String word : spStr) {
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
    private static int[] cl(String[] contents){
        //code／blank/comments
        int[] lines={0,0,0};
        for (String line: contents){
            if(line.trim().length()<=1)
                lines[1]++;
            else {
                if (!line.trim().contains("//")){
                    lines[0]++;
                }
                else if(line.replaceAll(" ","").indexOf("//")>1){
                    lines[0]++;
                }
                else if(line.contains("//")){
                    lines[2]++;
                }
            }
        }
        return lines;
    }

    //按行获取文件内容
    private static ArrayList<String> getLines(String path){
        ArrayList<String> contents=new ArrayList<>();
        Scanner scanner=null;
        try{
            File file=new File(path);
            scanner=new Scanner(file);
        }
        catch (FileNotFoundException e){
            error("cannot find file: "+path);
            e.printStackTrace();
        }
        if(scanner!=null){
            while(scanner.hasNextLine()){
                contents.add(scanner.nextLine());
            }
            scanner.close();
        }
        return contents;
    }

    //按字获取文件内容
    private static String[] getWords(String path){
        String[] res=null;
        try {
            InputStream is = new FileInputStream(path);
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null){
                buffer.append(line);
            }
            res= buffer.toString().split("[\n \t]]");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }

    //输出结果
    private static void ptintResultToFile(String file,Args args,PrintWriter output){
        ArrayList<String> lineContents=getLines(file);
        String[] contents=lineContents.toArray(new String[lineContents.size()]);
        if (args.c) {
            output.printf("%s, 字符数: %d\n", file, cc(contents));
        }
        if (args.w){
            int words=0;
            for (String line:contents){
                words+=wc(line);
            }
            output.printf("%s，单词数: %d\n",file,words);
        }
        if (args.l){
            output.printf("%s，行数: %d\n",file,contents.length);
        }
    }

    public static void main(String[] args){
        String[] args2=new String[]{"wc.exe","-c","test.txt"};
        Args args1=new Args(args2);
        String output="result.txt";
        if (args1.input==null){
            error("please input name of inputFile");
        }
        //修改输出文件
        if (args1.o){
            output=args1.output;
        }
        //创建输出文件
        File file=new File(output);
        try{
            if (!file.exists()){
                file.createNewFile();
            }
            //输出到文件
            PrintWriter out=new PrintWriter(output);
            ptintResultToFile(args1.input,args1,out);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

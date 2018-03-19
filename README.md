<h1>第二周个人作业：WordCuont</h1>

<h2>1 PSP表格</h2>

PSP2.1表格

PSP2.1              | PSP阶段           | 预估耗时（分钟）        |实际耗时（分钟）|
--------------------|------------------|-----------------------|-------------|
Planning|计划|20|15
· Estimate|· 估计这个任务需要多少时间|20|15
Development|开发|360|605
· Analysis|· 需求分析 (包括学习新技术)|60|80
· Design Spec|· 生成设计文档|0|5
· Design Review|· 设计复审 (和同事审核设计文档)|0|0
· Coding Standard|· 代码规范 (为目前的开发制定合适的规范)|10|10
· Design|· 具体设计|20|30
· Coding|· 具体编码|180|360
· Code Review|· 代码复审|40|60
· Test|· 测试（自我测试，修改代码，提交修改）|60|100
Reporting|报告|60|85
· Test Report|· 测试报告|40|60
· Size Measurement|· 计算工作量|10|5
· Postmortem & Process Improvement Plan|· 事后总结, 并提出过程改进计划|10|20
  |合计|440|705


<h2>2 编码实现，并在Github提交</h2>

（1）Github地址：https://github.com/SSS-SY/wordcount

（2）语言：java

（3）博客地址：

<h2>3 测试用例</h2>

* wc.exe -c test1.c
* wc.exe -c -o result1.text test1.c 
* wc.exe -w test1.c -o result2.text
* wc.exe -l test1.c -o result3.text
* wc.exe -a test1.c -o result4.text
* wc.exe -w -e stopLists.c test1.c -o result5.text
* wc.exe -l -w -a -c test1.c -o result6.text
* wc.exe -c -s ./test/ -o result7.text
* wc.exe -l -a -w -c -s ./test/ -o result8.text
* wc.exe -l -a -c -e stopLists.c test2.c -o result9.text

具体测试文件在test文件夹中

<h2>4 测试结果</h2>

result文件夹中：

* result.txt 
* result1.txt
* result2.txt
* result3.txt
* result4.txt
* result5.txt
* result6.txt
* result7.txt
* result8.txt
* result9.txt

<h2>5 解题思路</h2>

* 创建Args类实现对功能的选择
* 基本功能比较简单，只需读取文件内容后分别按行、按单词、按字符循环统计总数即可
* 对输出文件只需判断是否有选择输出文件然后修改Args的参数output即可
* 对于-e StopLists只需读取文件内容以后创建Hash表然后统计字数时先判断是否在Hash表中即可
* 对于-a功能在统计字符数时使用trim()函数判断blank，使用contains()判断comments，其余情况即可视为code
* 对于-s功能递归处理输入文件列表然后在输出时循环调用输出功能结果功能即可

<h2>6 程序实现过程</h2>

* 查阅资料实现读取以及文件输出
* 实现基本功能
* 实现-e功能
* 实现-a功能
* 实现-s功能

<h2>7 代码说明</h2>

//参数设置

private static class Args

//计算单词数

private static int wc(String contents,Set<String> stopLists)

//计算行数

private static int[] cl(String[] contents)

//计算字符数

private static int cc(String[] contents)

//stop words:
　　
private static Set<String> stopWords(String stopfile)

//按行获取文件内容

private static ArrayList<String> getLines(String path)

//按字获取文件内容

private static String[] getWords(String path)

//输出结果

private static void ptintResultToFile(String file,Args args,PrintWriter output)

//递归寻找文件

private static String[] findFiles(String path,boolean s)

<h2>8 参考文献链接</h2>

* 手把手教你如何把jar文件，打包成jar文件以及转换为exe可执行文件：http://blog.csdn.net/sunkun2013/article/details/13167099
* 作业内容：http://www.cnblogs.com/ningjing-zhiyuan/p/8563562.html
* java读取文件内容方法：https://zhidao.baidu.com/question/620468183851139852.html
* java输出内容至文件：http://blog.csdn.net/liuweiyuxiang/article/details/69487326
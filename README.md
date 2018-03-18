#第二周个人作业：WordCuont

##1 PSP表格

PSP2.1表格

PSP2.1              | PSP阶段           | 预估耗时（分钟）        |实际耗时（分钟）|
--------------------|------------------|-----------------------|-------------|
Planning|计划|20|20
· Estimate|· 估计这个任务需要多少时间|240|20
Development|开发|360|600
· Analysis|· 需求分析 (包括学习新技术)|60|80
· Design Spec|· 生成设计文档|0|0
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
 |合计


##2 编码实现，并在Github提交

（1）Github地址：https://github.com/SSS-SY/wordcount

（2）语言：java

##3 测试用例

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

##4 测试结果
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

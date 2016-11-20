# JavaSocketPractice
有关java socket的练习

# 编辑器
本人使用win10环境下的IntelliJ IDEA 2016

# 项目一 

存放于MyServer文件夹。

## 内容

1.	复习Java I/O和Socket编程相关概念和方法；
1.	基于Java Socket TCP和UDP实现一个简易的网络文件服务程序，包含服务器端FileServer和客户端FileClient；
1.	服务器端启动时需传递root目录参数，并校验该目录是否有效；
1.	服务器启动后，开启TCP：2021端口，UDP：2020端口，其中，TCP连接负责与用户交互，UDP负责传送文件；
1.	客户端启动后，连接指定服务器的TCP 2021端口，成功后，服务器端回复信息：“客户端IP地址:客户端端口号>连接成功”；
1.	连接成功后，用户可通过客户端命令行执行以下命令：
	*	ls	服务器返回当前目录文件列表（<file/dir>	name	size）
	*	cd  <dir>	进入指定目录（需判断目录是否存在，并给出提示）
	*	get  <file>	通过UDP下载指定文件，保存到客户端当前目录下
	*	bye	断开连接，客户端运行完毕
1.	服务器端支持多用户并发访问，不用考虑文件过大或UDP传输不可靠的问题。

该作业已经完成。

缺陷：udp传输没有做任何纠错，比较不可靠。

## 使用方法

1. 配置Server.java中的SERVER_PATH，Client.java中的SAVEPATH
1. 依次运行项目内的Server.java和Client.java的main
1. 支持ls cd get bye 四种指令，支持Client的多开

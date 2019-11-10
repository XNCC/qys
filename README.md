# qys
契约锁测试题


后台：端口：8080
数据库在backstage根目录的derby_DB文件夹下面,使用的是qys4数据库
文件保存路径在e盘：
            public static final String DATABASE="qys4";
            public static final String BASES_PATH="e:\\";
            可以跟根据需要在在项目backstage下的Utils下的Options中设置
后台接口说明：
            /checksas   用于检测签名是否合法
            /download   用于下载
            /getOneMeta 用于获取一条元数据信息‘
            /showTenMetasServlet    用于获取最近插入的十条元数据信息
            /upload     上传文件使用接口


前台：端口：8081
指定后台访问地址
            ncc.remoteServer=http://127.0.0.1:8080
            可以在application.property中设置
            
![3DO2HQSWP__L5UJEJJ7_J_R.png](https://i.loli.net/2019/11/07/MaQ9LU7xoDKXvH6.png)

![0PADB`V__29YE2_DOVUZ_YT.png](https://i.loli.net/2019/11/07/Lqb7vpC61RPBF54.png)


session的管理及客户端负载均衡：

            #redis配置
            spring.redis.host=192.168.43.202
            spring.redis.port=6379
	    
redis采用docker安装。
nginx安装在centos。
session管理采用springsession。
nginx.config配置为

    upstream springsession {
        server 192.168.43.243:8090 weight=1;
        server 192.168.43.243:8091 weight=1;
        }

    server {
        listen       80;
        server_name  localhost;
        location / {
            proxy_pass http://springsession;
	proxy_redirect default;
        }
        
![WKB_QMDR`P_N0_TFNH81__7.png](https://i.loli.net/2019/11/10/Of3phYa8F5SVetJ.png)





学习记录：
maven学习笔记之IDEA+Maven+Jetty运行一个简单的web项目
https://blog.csdn.net/qq_28640763/article/details/79868092
maven jetty配置：
https://blog.csdn.net/qq_39571197/article/details/86214159
session一致性：
https://blog.csdn.net/u010889616/article/details/79954000
公钥秘钥签名
https://blog.csdn.net/zh521zh/article/details/51819826
http://47.102.131.146:8090/archives/%E5%8A%A0%E5%AF%86%E7%AE%97%E6%B3%95%E5%AE%9E%E4%BE%8B
https://blog.csdn.net/weixin_42231507/article/details/80899273
restTemplate：
设置请求头
https://blog.csdn.net/u010139801/article/details/72770123
derby：
介绍：https://blog.csdn.net/Mrs_haining/article/details/82256437
配置使用：https://blog.csdn.net/lncdzh/article/details/78723201
连接用户名密码设置：
java org.apache.derby.tools.ij
connect 'jdbc:derby:D:/MYDB;create=true;user=root;password=123';
ij：命令操作
语法：http://www.360doc.com/content/13/0303/17/11098634_269063762.shtml

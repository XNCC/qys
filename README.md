# qys
契约锁测试题


# 后台：端口：8080
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


# 前台：端口：8081
指定后台访问地址
            ncc.remoteServer=http://127.0.0.1:8080
            可以在application.property中设置
            
![3DO2HQSWP__L5UJEJJ7_J_R.png](https://i.loli.net/2019/11/07/MaQ9LU7xoDKXvH6.png)

![0PADB`V__29YE2_DOVUZ_YT.png](https://i.loli.net/2019/11/07/Lqb7vpC61RPBF54.png)


# session的管理及客户端负载均衡：

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


# redis安装（centos7版本，基于docker）：
# docker pull redis
不设置密码：
docker run -p 6379:6379 --name some-redis -d redis redis-server --appendonly yes

# nginx安装（基于centos7）：分两步：

1：nginx安装：
docker可以安装：
windows安装：
centos7安装：
安装版本：1.8.1
## 一：安装前奏：四个依赖
yum install gcc-c++
yum install -y pcre pcre-devel
yum install -y zlib zlib-devel
yum install -y openssl openssl-devel


对以来的解释：可以不看：
yum install gcc-c++
c语言环境：需要安装 gcc 的环境

yum install -y pcre pcre-devel
 PCRE(Perl Compatible Regular Expressions)是一个 Perl 库，
 包括 perl 兼容的正则表达式库。nginx 的 http 模块使用 pcre 来解析正则表达式，所以需要在 linux 上安装 pcre 库。
 pcre-devel 是使用 pcre 开发的一个二次开发库。nginx 也需要此库
 
 yum install -y zlib zlib-devel
 zlib 库提供了很多种压缩和解压缩的方式，nginx 使用 zlib 对 http 包的内容进行 gzip，所以需要在 linux 上安装 zlib 库。

yum install -y openssl openssl-devel
OpenSSL 是一个强大的安全套接字层密码库，
囊括主要的密码算法、常用的密钥和证书封装管理功能及 SSL 协议，
并提供丰富的应用程序供测试或其它目的使用。nginx 不仅支持
http 协议，还支持 https（即在 ssl 协议上传输 http），所以需要在 linux安装 openssl 库。

## 二：安装：
1：下包：http://nginx.org/
2：上传到/usr/local目录下（一定要跟我的目录一样）
3：解压：tar zxvf nginx-1.8.1.tar.gz
4：进入nginx-1.8.1目录   使用 configure 命令创建一 makeFile 文件。
                使用方式直接在控制台粘贴configure 命令
5：编译：  make
6：安装：  make install

configure命令 内容：
./configure \
--prefix=/usr/local/nginx \
--pid-path=/var/run/nginx/nginx.pid \
--lock-path=/var/lock/nginx.lock \
--error-log-path=/var/log/nginx/error.log \
--http-log-path=/var/log/nginx/access.log \
--with-http_gzip_static_module \
--http-client-body-temp-path=/var/temp/nginx/client \
--http-proxy-temp-path=/var/temp/nginx/proxy \
--http-fastcgi-temp-path=/var/temp/nginx/fastcgi \
--http-uwsgi-temp-path=/var/temp/nginx/uwsgi \
--http-scgi-temp-path=/var/temp/nginx/scgi

对configure命令的解释：按照配置来可以不看：
---  知识点小贴士 ----
Makefile是一种配置文件， Makefile 一个工程中的源文件不计数，
其按类型、功能、模块分别放在若干个目录中，makefile定义了一系列的规则来指定，
哪些文件需要先编译，哪些文件需要后编译，哪些文件需要重新编译，甚至于进行更复杂的功能操作，
因为 makefile就像一个Shell脚本一样，其中也可以执行操作系统的命令。

----  知识点小贴士 ----
configure参数
./configure \
--prefix=/usr \                                                        指向安装目录
--sbin-path=/usr/sbin/nginx \                                 指向（执行）程序文件（nginx）
--conf-path=/etc/nginx/nginx.conf \                      指向配置文件
--error-log-path=/var/log/nginx/error.log \              指向log
--http-log-path=/var/log/nginx/access.log \            指向http-log
--pid-path=/var/run/nginx/nginx.pid \                      指向pid
--lock-path=/var/lock/nginx.lock \                         （安装文件锁定，防止安装文件被别人利用，或自己误操作。）
--user=nginx \
--group=nginx \
--with-http_ssl_module \                      启用ngx_http_ssl_module支持（使支持https请求，需已安装openssl）
--with-http_flv_module \                       启用ngx_http_flv_module支持（提供寻求内存使用基于时间的偏移量文件）
--with-http_stub_status_module \     启用ngx_http_stub_status_module支持（获取nginx自上次启动以来的工作状态）
--with-http_gzip_static_module \   启用ngx_http_gzip_static_module支持（在线实时压缩输出数据流）
--http-client-body-temp-path=/var/tmp/nginx/client/ \ 设定http客户端请求临时文件路径
--http-proxy-temp-path=/var/tmp/nginx/proxy/ \ 设定http代理临时文件路径
--http-fastcgi-temp-path=/var/tmp/nginx/fcgi/ \ 设定http fastcgi临时文件路径
--http-uwsgi-temp-path=/var/tmp/nginx/uwsgi \ 设定http uwsgi临时文件路径
--http-scgi-temp-path=/var/tmp/nginx/scgi \ 设定http scgi临时文件路径
--with-pcre 启用pcre库





2：nginx的启动和访问：
注意：启动nginx 之前，上边将临时文件目录指定为/var/temp/nginx/client， 需要在/var  下创建此 目录
mkdir /var/temp/nginx/client -p    前面配置中写了这个路径
sbin下就是启动文件
cd /usr/local/nginx/sbin
./nginx       启动：端口默认是80，所以在浏览器直接输入IP地址即可访问：




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

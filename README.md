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

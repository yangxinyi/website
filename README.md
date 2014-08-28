website
=======
1、项目仓库：https://github.com/yangxinyi/website.git
2、eclipse安装maven插件:http://download.eclipse.org/technology/m2e/releases
3、eclipse中window->show view确认有maven，server两个目录
4、git clone项目到本地，右键website->configure->convert to maven project。转成maven项目的同时，会下载依赖包
5、打开项目src.main.resource.conf目录下的 config.properties，配置数据库
6、右键website->maven test，会将src.test中的文件编译到target.test-classes中。
完成此步后，即可执行单元测试（如：ProductServiceTest.java)

7、配置eclipse自带服务器。target目录下找到项目编译的目标地址；
show view->点出servers并双击,配置tomcat，主要项目路径，笔者<Context docBase="C:\Users\admin\git\website\src\main\webapp" path="/" reloadable="true"/>
右键tomcat->run。查看console，如果没有错误提示，访问localhost:8080/test
  

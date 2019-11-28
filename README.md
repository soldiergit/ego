# ego

#### 1 电商行业介绍
##### 1.1 电商行业发展
  近年来，中国的电子商务快速发展，交易额连创新高，电子商务在各领域的应用不断
拓展和深化、相关服务业蓬勃发展、支撑体系不断健全完善、创新的动力和能力不断增强。
电子商务正在与实体经济深度融合，进入规模性发展阶段，对经济社会生活的影响不断增大，
正成为我国经济发展的新引擎。
中国电子商务研究中心数据显示，2017 年底，中国电子商务市场交易规模达 29.16 万亿
人民币,全年 29.16 万亿元电商交易额中，商品类电商交易额 16.87 万亿元，服务类电商务交
易额 4.96 万亿元，同比分别增长 21%和 35.1%。全国网上零售额达 7.18 万亿元，同比增逾
三成，占据全球份额的半壁江山，成为中国电商发展一大亮点。而伴随电商发展，移动端网
络支付也在悄然改变传统支付习惯，2017 年非银行支付机构发生网络支付 143.26 亿万元，
同比增长 44.32%。
图 1.2012-2017 年中国电子商务市场交易规模（万亿元）

##### 1.2 电商狂欢节 11.11

##### 1.3 互联网电商技术特征
1. 技术新
2. 技术范围广
3. 分布式
4. 高并发、集群、负载均衡、高可用
5. 海量数据
6. 业务复杂
7. 系统安全

##### 1.4 电商行业模式
1. B2B：企业到企业，商家到商家。代表：阿里巴巴、慧聪网
2. B2C：商家到客户。代表：京东、淘宝商城（B2B2C）。
3. C2C：客户到客户。淘宝集市。
4. O2O：线上到线下。

#### 2 易购商城项目

##### 2.1 易购商城模式
1. 易购网上商城是一个综合性的 B2C 平台，类似京东商城、天猫商城。
2. 会员：可以通过商城浏览商品、下订单、积分兑换以及参加各种活动。
3. 管理员、运营：可以在平台后台管理系统中管理商品、订单、会员等。
4. 客服：可以在后台管理系统中处理用户的询问以及投诉。

##### 2.2 易购商城功能介绍
1. B2C电商系统架构
2. 前台
3. 注册&登陆 支付 会员中心
4. 商品展示
5. 订单提交
6. 网站门户 商品搜索
7. 客户服务 公告 帮助中心 社区
8. 后台
9. 财务管理
10. CRM 采购管理 WMS
11. 商品管理 订单管理
12. 统计报表
13. CMS
14. 购物车
15. 系统管理 网络管理
16. 活动管理 商品推荐

##### 2.3 易购商城技术架构

###### 2.3.1 传统架构(单体项目)
1. 单体项目：将所有的功能放入一个项目中进行管理和开发。
2. 优点：
 方便调试，代码都在一起。
 没有分布式开销，所有服务都在本地容器内。
 中小型项目可以快速迭代，不需要太多资源。
3. 缺点：
 版本管理难：当项目规模变大时，代码容易产生冲突。
 稳定性差：局部服务有问题，可能会影响整体；
 可维护性差：规模扩大复杂性直线上升，造成系统不易理解；
 可扩展性差：无法满足高并发下对应用的要求，不利于较高利用率的横向扩展；
 可复用性差：服务被打包在应用中，功能不易复用；

###### 2.3.2 分布式项目架构
分布式架构：通过 SOA:面向服务的体系结构，它将应用程序的分为不同服务，通过服务之
间定义良好的接口和契约联系起来，把系统按照模块拆分成多个子系统开发，子系统之间通
过接口进行通信。
优点：
 把模块拆分，使用接口通信，降低模块之间的耦合度。
 把项目拆分成若干个子项目，不同的团队负责不同的子项目。
 增加功能时只需要在增加一个子项目，调用其它系统的接口就可以。
 可以灵活的进行分布式部署。
 
缺点：
 系统之间交互需要使用远程通信，接口开发增加工作量。

######2.3.3 技术选型
1. Spring、SpringMVC、Mybatis
2. JSP、JSTL、jQuery、jQuery plugin、EasyUI、KindEditor（富文本编辑器）、CSS+DIV
3. Redis    （缓存服务器）
4. Solr     （搜索）
5. Dubbo RPC（Remote Procedure Call）解决方案
6. Mysql    （数据库）
7. Nginx    （web 服务器）
8. Maven    （项目构建管理工具）

######2.3.4 开发工具和环境
1. Idea
2. Maven 3.6.1
3. Tomcat 7（Maven Tomcat Plugin）
4. JDK 1.8
5. Mysql 5.7
6. Nginx 1.8.0
7. Solr  4.10.3
8. Redis 3.0.0
9. Ubuntu 19.04 操作系统

##### 2.4 人员配置
1. 产品经理：3 人，确定需求以及给出产品原型图。Axure
2. 项目经理：1 人，项目管理。
3. 前端团队：5 人，根据产品经理给出的原型制作静态页面。
4. 后端团队：20 人，实现产品功能。
5. 测试团队：5 人，测试所有的功能。
6. 运维团队：3 人，项目的发布以及维护。

#### 项目model结构:
1. [INFO] ego[pom]
2. [INFO] ego-common[jar]
3. [INFO] ego-rpc[pom]
4. [INFO] ego-rpc-pojo[jar]
5. [INFO] ego-rpc-mapper[jar]
6. [INFO] ego-rpc-service[jar]
7. [INFO] ego-rpc-service-impl[jar]
8. [INFO] ego-manager-web Maven Webapp[war]-->非伪静态化
9. [INFO] ego-portal-web Maven Webapp[war]-->伪静态化，接收.html结尾的请求
10. [INFO] ego-search-web Maven Webapp[war]-->伪静态化，接收.html结尾的请求
10. [INFO] ego-sso-web Maven Webapp[war]-->非伪静态化
11. [INFO] ego-item-web Maven Webapp[war]-->伪静态化，接收.html结尾的请求；有登录拦截器
12. [INFO] ego-order-web Maven Webapp[war]-->非伪静态化；有登录拦截器

#### 运行项目
1. 启动Zookeeper服务注册中心(172.18.25.171，192.168.1.171)
```cfml
ssh 172.18.25.171
cd /usr/local/zookeeper
./zk1/bin/zkServer.sh start
./zk2/bin/zkServer.sh start
./zk3/bin/zkServer.sh start
```
2. 启动ftp服务器(172.18.25.172，192.168.1.172)
```cfml
ssh 172.18.25.172（已配置开机默认启动）
```
3. 启动Http服务器(172.18.25.172，192.168.1.172)
```cfml
ssh 172.18.25.172
cd /usr/local/nginx/sbin/
./nginx
```
4. 启动Redis集群(172.18.25.174，192.168.1.174)
```cfml
 ssh 172.18.25.174
 ./usr/local/redis/bin/redis-server redis-6380.conf
 ./usr/local/redis/bin/redis-server redis-6381.conf
 ./usr/local/redis/bin/redis-server redis-6382.conf
 ./usr/local/redis/bin/redis-server redis-6383.conf
 ./usr/local/redis/bin/redis-server redis-6384.conf
 ./usr/local/redis/bin/redis-server redis-6385.conf
```
5. 启动Solr集群(172.18.25.173，192.168.1.173)
 ```cfml
 ssh 172.18.25.173
 ./usr/local/tomcat-01/bin/startup.sh
 ./usr/local/tomcat-02/bin/startup.sh
 ./usr/local/tomcat-03/bin/startup.sh
 ./usr/local/tomcat-04/bin/startup.sh
 ```
6. 启动nginx代理的tomcat集群(172.18.25.176，192.168.1.176)
 ```cfml
 ssh 172.18.25.176
 ./usr/local/tomcat/apache-tomcat-9.0.20-01/bin/startup.sh
 ./usr/local/tomcat/apache-tomcat-9.0.20-02/bin/startup.sh
 ./usr/local/tomcat/apache-tomcat-9.0.20-03/bin/startup.sh
 ```
7. 启动nginx反向代理_负载均衡服务器(172.18.25.175，192.168.1.175)
 ```cfml
 ssh 172.18.25.175
 ./usr/local/nginx/sbin/nginx
 # 重启命令#
 ./usr/local/nginx/sbin/nginx -s reload
 ```
8. 发布RPC服务
```html
运行ego-rpc-service-impl包下的com.soldier.ego.test.ProviderTest的main方法
```
9. 启动商品门户(ego-portal-web)和商品检索(ego-search-web)的tomcat

### 项目的打包发布
#### ego-rpc服务提供者的发布
1、install ego项目，启动Zookeeper服务注册中心、ftp服务器、Http服务器
2、将ego-rpc-service-impl的target下生成的ego-rpc-service-impl-assembly.tar.gz上传到nginx代理的tomcat服务器
```cfml
scp /home/soldier/SOLDIER/IDE_project/idea_project/ego/ego-rpc/ego-rpc-service-impl/target/ego-rpc-service-impl-assembly.tar.gz 172.18.25.176:/soldier/app
```
3、连接tomcat服务器，解压文件
 ```cfml
 ssh 172.18.25.176
 cd /soldier/app
 tar -zxvf ego-rpc-service-impl-assembly.tar.gz
 ./ego-rpc-service-impl/bin/start.sh
 ```
#### ego-manager-web服务消费者的发布
1、修改tomcat服务器的3个tomcat的tomcat-users.xml文件，配置tomcat账户
 ```cfml
 vi apache-tomcat-9.0.20-01/conf/tomcat-users.xml
 vi apache-tomcat-9.0.20-02/conf/tomcat-users.xml
 vi apache-tomcat-9.0.20-03/conf/tomcat-users.xml
 末尾加入
# 2019.11.28 10:16:25
<role rolename="admin-gui"/>
<role rolename="admin-script"/>
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<user username="admin" password="admin" roles="manager-gui,manager-script,admin-script,admin-gui"/>
 ```
2、删除每个tomcat默认访问的webapps下的ROOT
```cfml
 rm -rf apache-tomcat-9.0.20-01/webapps/ROOT/
 rm -rf apache-tomcat-9.0.20-02/webapps/ROOT/
 rm -rf apache-tomcat-9.0.20-03/webapps/ROOT/
```
3、启动三个tomcat
 ```cfml
 ./apache-tomcat-9.0.20-01/bin/startup.sh
 ./apache-tomcat-9.0.20-02/bin/startup.sh
 ./apache-tomcat-9.0.20-03/bin/startup.sh
 ```
 4、修改本地maven的settings.xml文件，关联tomcat账户；因为是用maven打包上传的
 ```cfml
vim /home/soldier/SOLDIER/IDE_environment/apache-maven-3.6.1/conf/settings.xml
```
 加入
 ```cfml
<!-- 在maven中关联tomcat服务器的tomcat账户 -->
    <server>
      <id>176_tomcatCloud</id>
      <username>admin</username>
      <password>admin</password>
    </server>
```
修改ego-manager-web的pom文件
5、允许ego-manager-web的tomcat7:deploy，上传tomcat的war包
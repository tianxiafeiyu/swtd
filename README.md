# Skywalking demo
用于测试 Skywalking 系统的 demo 程序。

涉及到以下模块的探针：
- spring boot
- tomcat
- kafka
- elasticsearch
- mysql

## build
```$xslt
mvn package
```
在 `target` 目录下得到程序包：
- sw_demo-0.0.1-SNAPSHOT-package.tar.gz
- sw_demo-0.0.1-SNAPSHOT-package.zip

## run
- windows  
    bin/star-server.bat

- linux  
    bin/start-server.sh
 
## skywalking agent

修改启动脚本的 `SW_OPS` 变量 ：
```$xslt
SW_OPS=-javaagent:${absolute path}/skywalking-agent.jar=agent.service_name=sw_demo,collector.backend_service=127.0.0:11800
```

运行启动脚本，即可添加 `skywalking agent` 代理。
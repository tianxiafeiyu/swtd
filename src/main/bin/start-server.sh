#! /bin/bash
APP_NAME=sw_demo-0.0.1-SNAPSHOT.jar
BASE_DIR=$(cd `dirname $0`; pwd)/..
SW_OPS=

#检查程序是否在运行
is_exist(){
  pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}' `
  #如果不存在返回1，存在返回0
  if [ -z "${pid}" ]; then
   return 1
  else
    return 0
  fi
}

#启动方法
start(){
  is_exist
  if [ $? -eq "0" ]; then
    echo "${APP_NAME} is already running. pid=${pid} ."
  else
    java ${SW_OPS} -jar ${BASE_DIR}/lib/${APP_NAME} --spring.config.location=${BASE_DIR}/config/application.yml
  fi
}

start
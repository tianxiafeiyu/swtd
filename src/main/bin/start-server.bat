@echo off
set APP_NAME=sw_demo-0.0.1-SNAPSHOT.jar
set SW_OPS=
cd /d %~dp0
cd ..
java %SW_OPS% -jar lib/%APP_NAME% --spring.config.location=config/application.yml
pause
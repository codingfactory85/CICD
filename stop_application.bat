@echo off
setlocal enabledelayedexpansion
for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8080"') do (
    set "pid=%%a"
    tasklist /fi "pid eq !pid!" | findstr /i "java.exe" >nul && taskkill /F /PID !pid!
)
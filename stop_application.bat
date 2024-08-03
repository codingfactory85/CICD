@echo off
echo Stopping application...
setlocal enabledelayedexpansion
for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8080"') do (
    set "pid=%%a"
    echo Found PID: !pid!
    tasklist /fi "pid eq !pid!" | findstr /i "java.exe" >nul && (
        echo Stopping PID: !pid!
        taskkill /F /PID !pid!
    ) || (
        echo No matching java.exe process found for PID: !pid!
    )
)

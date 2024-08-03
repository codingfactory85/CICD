@echo off
echo Stopping application...
setlocal enabledelayedexpansion
set "found=0"
for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8080"') do (
    set "pid=%%a"
    echo Found PID: !pid!
    tasklist /fi "pid eq !pid!" | findstr /i "java.exe" >nul && (
        echo Stopping PID: !pid!
        taskkill /F /PID !pid!
        set "found=1"
    ) || (
        echo No matching java.exe process found for PID: !pid!
    )
)
if "!found!"=="0" (
    echo No application running on port 8080.
    exit /b 0
)

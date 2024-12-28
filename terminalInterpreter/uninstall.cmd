:: This script is used to uninstall the 5D Deadfish Command Interpreter from the system.
:: It will remove the files from the system and remove the path to the system path.
:: This script is written by Gianluca Rainis
:: This script is free to use and modify, but please give credit to the original author.

@echo off

:: Check for administrative privileges
net session >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: This script requires administrative privileges. Please run as administrator.
    pause
    exit /b
)

REM Check if the directory exists, if so remove it
if exist "C:\5DDeadfishCmdInterpreter" (
    taskkill /F /IM main.exe >nul 2>&1
    rmdir /S /Q "C:\5DDeadfishCmdInterpreter"
)

REM Remove the file from the system path
if exist "C:\Windows\System32\5Ddf.bat" (
    taskkill /F /IM 5Ddf.bat >nul 2>&1
    del /Q "C:\Windows\System32\5Ddf.bat"
)

setx PATH "%PATH:C:\5DDeadfishCmdInterpreter;=%" >nul 2>&1

echo Uninstallation completed successfully.
pause
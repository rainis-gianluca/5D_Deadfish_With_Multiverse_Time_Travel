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

if exist "C:\Program Files\5DDeadfishCmdInterpreter" (
    set "installDir=C:\Program Files\5DDeadfishCmdInterpreter"
) else if exist "C:\5DDeadfishCmdInterpreter" (
    set "installDir=C:\5DDeadfishCmdInterpreter"
) else (
    echo ERROR: Installation directory not found.
    pause
    exit /b
)

REM Check if the directory exists, if so remove it
if exist "%installDir%" (
    taskkill /F /IM main.exe >nul 2>&1
    rmdir /S /Q "%installDir%"
)

REM Remove the file from the system path
if exist "C:\Windows\System32\5Ddf.bat" (
    del /Q "C:\Windows\System32\5Ddf.bat"
)

set "newPath=%installDir%"
echo %PATH% | find /I "%newPath%" >nul
if %errorlevel% neq 0 (
    echo %newPath% is not in the PATH.
) else (
    setx /M PATH "%PATH:%newPath%;=%"
    echo %newPath% successfully removed from the PATH.
)

pause
:: This script is used to install the 5D Deadfish Command Interpreter to the system.
:: It will copy the files to the system and add the path to the system path.
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

set "installDir=C:\5DDeadfishCmdInterpreter"

REM Get the directory where the script is located
set "currentDir=%~dp0"

if exist "C:\Program Files" (
    set "installDir=C:\Program Files\5DDeadfishCmdInterpreter"
)

REM Check if the directory exists, if not create it
if not exist "%installDir%" (
    mkdir "%installDir%"
)

REM Copy the files to the directory
xcopy "%currentDir%\*" "%installDir%\" /E /I /Y

REM Change the directory to the dist folder
if exist "%installDir%\dist" (
    cd "%installDir%\dist"
) else (
    echo ERROR: dist folder not found in the specified directory.
    pause
    exit /b
)

REM Add the installation directory to the system PATH if it's not already there
set "newPath=%installDir%"
echo %PATH% | find /I "%newPath%" >nul
if %errorlevel% neq 0 (
    setx /M PATH "%newPath%"
    echo Added %newPath% to PATH.
) else (
    echo %newPath% is already in the PATH.
)

echo Installation completed successfully in %installDir%.
pause
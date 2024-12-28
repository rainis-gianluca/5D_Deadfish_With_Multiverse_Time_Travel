:: This script is used to install the 5D Deadfish Command Interpreter to the system.
:: It will exe the main.exe file.
:: This script is written by Gianluca Rainis
:: This script is free to use and modify, but please give credit to the original author.

@echo off

REM Check if the main.exe file exists
if exist "C:\5DDeadfishCmdInterpreter\dist\main.exe" (
    C:\5DDeadfishCmdInterpreter\dist\main.exe %1
) else (
    echo ERROR: main.exe not found in C:\5DDeadfishCmdInterpreter\dist
    pause
)
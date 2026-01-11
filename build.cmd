@echo off
echo Building VeneClicker...
mvn clean package "-Dmaven.compiler.fork=true" "-Dmaven.compiler.executable=javac"
if %errorlevel% neq 0 (
    echo.
    echo Build failed! Please ensure you have Maven and a JDK installed.
    pause
    exit /b %errorlevel%
)
echo.
echo Build successful! 
echo Launching VeneClicker in a new terminal to monitor warnings...
echo.
start "VeneClicker Console" cmd /k "java -jar target\vene-clicker-1.0-SNAPSHOT.jar"
pause
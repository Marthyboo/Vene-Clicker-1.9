@echo off
call mvn clean package
if %ERRORLEVEL% EQU 0 (
    echo Cleaning up folders...
    pushd target
    for /d %%i in (*) do rd /s /q "%%i"
    del /q original-*.jar
    popd
    if exist "dependency-reduced-pom.xml" del /q "dependency-reduced-pom.xml"
    echo Done. Only the final JAR remains in target.
)

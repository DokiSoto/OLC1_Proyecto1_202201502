@echo off
echo ========================================
echo Generando analizador BattleScript
echo ========================================

if exist "src\Analizadores\Lexer.java" del /Q "src\Analizadores\Lexer.java"
if exist "src\Analizadores\Parser.java" del /Q "src\Analizadores\Parser.java"
if exist "src\Analizadores\sym.java" del /Q "src\Analizadores\sym.java"

echo.
echo Generando Lexer con JFlex...
java -jar "Libraries\jflex-full-1.9.1.jar" -d "src\Analizadores" "src\Analizadores\Lexer.flex"

if errorlevel 1 (
    echo Error generando Lexer.
    pause
    exit /b 1
)

echo.
echo Generando Parser con CUP...

java -jar "Libraries\java-cup-11b.jar" -parser Parser -symbols sym -destdir "src\Analizadores" "src\Analizadores\Parser.cup"

if errorlevel 1 (
    echo Error generando Parser.
    pause
    exit /b 1
)

echo.
echo ========================================
echo GENERACION COMPLETADA
echo ========================================
pause

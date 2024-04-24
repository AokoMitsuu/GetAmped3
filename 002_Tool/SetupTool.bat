@echo off
echo Debut du script: %time%
dir src /b /s .\\*.java > sources.txt
set javac6=%cd%\jdk\bin\javac.exe
set java6=%cd%\jdk\bin\java.exe
set lib=".;./lib/classes.jar;./lib/javassist.jar;./lib/jackson-core-asl.jar;./lib/jackson-mapper-asl.jar"
set args=-Xms256m -Xmx512m -Xincgc -Djava.library.path=bin -Dktpraf.file=ktprafl -cp %lib%

%javac6% -cp %lib% @sources.txt
@echo off
%java6% %args% MiniTools.SetupTool -transfert 1.kxr 2.kxr 3.kxr 4.kxr 5.kxr
%java6% %args% MiniTools.SetupTool -extract ./bin/1.kxr aoao
%java6% %args% MiniTools.SetupTool -extract ./bin/2.kxr aoao
%java6% %args% MiniTools.SetupTool -extract ./bin/3.kxr aoao
%java6% %args% MiniTools.SetupTool -extract ./bin/4.kxr aoao
%java6% %args% MiniTools.SetupTool -extract ./bin/5.kxr aoao
%java6% %args% MiniTools.SetupTool -copy
%java6% %args% MiniTools.SetupTool -clean
echo Fin du script: %time%
@pause
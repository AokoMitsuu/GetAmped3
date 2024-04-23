@echo off
echo Debut du script: %time%
dir src /b /s .\\*.java > sources.txt
set javac6=%cd%\jdk\bin\javac.exe
set java6=%cd%\jdk\bin\java.exe
set lib=".;./lib/classes.jar;./lib/javassist.jar;./lib/jackson-core-asl.jar;./lib/jackson-mapper-asl.jar"
set args=-Xms256m -Xmx512m -Xincgc -Djava.library.path=bin -Dktpraf.file=ktprafl -cp %lib%

%javac6% -cp %lib% @sources.txt
@echo off
%java6% %args% MiniTools.Repack -copy chara #version.2.kxr
%java6% %args% MiniTools.Repack bin/2.kxr aoao chara #version.2.kxr
%java6% %args% MiniTools.Repack -clean chara #version.2.kxr
%java6% %args% MiniTools.Repack -transfert 2.kxr
echo Fin du script: %time%
@pause
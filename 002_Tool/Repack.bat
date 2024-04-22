@echo off
dir src /b /s .\\*.java > sources.txt
set javac6=%cd%\jdk\bin\javac.exe
set java6=%cd%\jdk\bin\java.exe
set lib=".;./lib/classes.jar;./lib/javassist.jar;./lib/jackson-core-asl.jar;./lib/jackson-mapper-asl.jar"
set args=-Xms256m -Xmx512m -Xincgc -Djava.library.path=bin -Dktpraf.file=ktprafl -cp %lib%

%javac6% -cp %lib% @sources.txt
@echo off
%java6% %args% resource.Repack ../bin/5.kxr aoao ./kwtamped2 ./lang ./#version.5.kxr
@pause
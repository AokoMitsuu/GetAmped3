@echo off
set javac6=%cd%\jdk\bin\javac.exe
set java6=%cd%\jdk\bin\javaw.exe
set lib=".;./lib/classes.jar;./lib/javassist.jar"
set args=-Xms256m -Xmx512m -Xincgc -Djava.library.path=bin -Dktpraf.file=ktprafl -cp %lib%

%javac6% -cp %lib% @sources.txt
@echo off
cd Console
start cmd /k java FileWatcher ../logs.txt false
cd ../
%java6% %args% com.nr.GA2 -port 8000


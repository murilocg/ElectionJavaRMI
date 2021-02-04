cd ..

if not exist jar mkdir jar
if not exist bin mkdir bin

cd .\src

javac .\service\*.java -d ..\bin
cd ..\bin
jar cfv ..\jar\service.jar .\service\*.class

cd ..\src
javac -cp ..\jar\service.jar .\client\exception\*.java .\client\*.java .\client\handler\*.java .\server\*.java .\server\cache\*.java -d ..\bin

copy .\client\client.mf ..\bin\client
copy .\server\server.mf ..\bin\server

cd ..\bin
jar --create --file ..\jar\client.jar --main-class client.Client -m .\client\client.mf -v .\client\*.class .\client\exception\*.class .\client\handler\*.class
jar --create --file ..\jar\server.jar --main-class server.Server -m .\server\server.mf -v  .\server\*.class .\server\cache\*.class
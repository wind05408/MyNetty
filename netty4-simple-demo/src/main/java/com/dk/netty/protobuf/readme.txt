protoc.exe V2.5

run_java.bat:
protoc ./proto/*.proto --java_out=../main/java
pause
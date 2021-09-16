call mvn clean install
md .\release\ 2> nul
move /Y .\target\*.jar .\release\
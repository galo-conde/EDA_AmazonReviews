Compile and run JUnit5 testing, within your IDE or download junit-platform-console-standalone-1.7.0.jar from [here]( https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0) and place it in lib folder at the same level with src, ID, and material folders. 

```
>>javac -d bin –cp ".;bin\;lib\*"  src\main\java\project3\*.java src\test\java\project3\*.java
>>java -cp ".;bin\;lib\*" org.junit.platform.console.ConsoleLauncher --exclude-engine=junit-vintage --include-engine=junit-jupiter --fail-if-no-tests --scan-classpath
```

Check in changes often to  **ID** repository 
-> make sure you are in **ID** folder for git commandline:
-> change only src\test\java\project3\*.java files 

```
cd CS3354/ID
git add project3/src/test/java/project3/*.java
git commit -m "Project 3 code update comment here"
gitk
```
gitk will show you the status, close it to continue
```
git push origin:<ID>
```

Repeat step 4 and 5 often to save your work until done. 
  * it allows you to re-trace your steps
  * do not forget to list **ALL** team members under @author javadoc tag

runTests: runFrontendDeveloperTests runAlgorithmEngineerTests

runFrontendDeveloperTests: Frontend.java FrontendDeveloperTests.java
	javac -cp .:../junit5.jar FrontendDeveloperTests.java
	java -jar ../junit5.jar -cp . -c FrontendDeveloperTests

runAlgorithmEngineerTests: Graph.java AlgorithmEngineerTests.java
	javac -cp .:../junit5.jar AlgorithmEngineerTests.java
	java -jar ../junit5.jar -cp . -c AlgorithmEngineerTests

clean:
	@rm *.class
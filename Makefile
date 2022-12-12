runTests: compileTests
	java -jar junit5.jar -cp . --scan-class-path
	@$(MAKE) clean

runFrontendDeveloperTests: compileFrontendDeveloperTests
	java -jar junit5.jar -cp . -c FrontendDeveloperTests

runAlgorithmEngineerTests: compileAlgorithmEngineerTests
	java -jar junit5.jar -cp . -c AlgorithmEngineerTests

runBackendDeveloperTests: compileBackendDeveloperTests
	 java -jar junit5.jar -cp . -c BackendDeveloperTests

compileTests: compileFrontendDeveloperTests compileAlgorithmEngineerTests compileBackendDeveloperTests

compileBackendDeveloperTests: Backend.java BackendDeveloperTests.java
	javac -cp .:junit5.jar BackendDeveloperTests.java

compileFrontendDeveloperTests: Frontend.java FrontendDeveloperTests.java
	javac -cp .:junit5.jar FrontendDeveloperTests.java

compileAlgorithmEngineerTests: Graph.java AlgorithmEngineerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

clean:
	@rm *.class

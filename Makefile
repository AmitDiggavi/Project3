run: MadMapper.java Frontend.java Backend.java Graph.java LocationLoader.java Location.java
	javac MadMapper.java
	java MadMapper
	@$(MAKE) clean

runTests: compileTests
	java -jar junit5.jar -cp . --scan-class-path
	@$(MAKE) clean

runFrontendDeveloperTests: compileFrontendDeveloperTests
	java -jar junit5.jar -cp . -c FrontendDeveloperTests

runAlgorithmEngineerTests: compileAlgorithmEngineerTests
	java -jar junit5.jar -cp . -c AlgorithmEngineerTests

runBackendDeveloperTests: compileBackendDeveloperTests
	 java -jar junit5.jar -cp . -c BackendDeveloperTests

runDataWranglerTests: compileDataWranglerTests
	java -jar junit5.jar -cp . -c DataWranglerTests

compileTests: compileFrontendDeveloperTests compileAlgorithmEngineerTests compileDataWranglerTests compileBackendDeveloperTests

compileFrontendDeveloperTests: Frontend.java FrontendDeveloperTests.java
	javac -cp .:junit5.jar FrontendDeveloperTests.java

compileAlgorithmEngineerTests: Graph.java AlgorithmEngineerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

compileDataWranglerTests: Location.java LocationLoader.java DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java

compileBackendDeveloperTests: Backend.java BackendDeveloperTests.java
	javac -cp .:junit5.jar BackendDeveloperTests.java

clean:
	@rm *.class

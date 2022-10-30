This project has 2 testcases automated as per the problem statement given:

1) UI testcase: for Patient Overview site verification

2) API testcase: to extract population data over a series of years from a given URL and print the max and min percentage population rise year wise.

TECHNOLOY USED:
Language: Java;
Build tool: Maven;
Testing framework: TESTNG;
API: Rest Assured.

Page Object Model used for UI testcase as per the page.

Centralized configuration (config.properties file) for data which can be changed such as:
URLs, test-data - country, language


Instructions on how to run:
1. The testng.xml file can be used to run both the testcases.
The testcase can be run by right click > run in testng.xml file or by maven command: 
mvn clean test -DsuiteXmlFile=testng.xml
The 2 classes will run in parallel.

2. For UI testing, the chrome driver used is 106.0.5249.61.
The chromedriver version to be updated as per local chrome version.
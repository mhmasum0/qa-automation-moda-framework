# QA Automation framework

## Run the test
```shell
mvn clean test -Prun-tests -DsuiteXmlFile=src/test/resources/test-suite/all-tests.xml
mvn clean test -Prun-tests -DsuiteXmlFile=src/test/resources/test-suite/valid-tests.xml
mvn clean test -Prun-tests -DsuiteXmlFile=src/test/resources/test-suite/pcp-bh360-eligibility-tests.xml
mvn clean test -Prun-tests -DsuiteXmlFile=src/test/resources/test-suite/dev-tests.xml
```

## SonarQube command
```shell
./sonar.sh start
mvn compile
mvn sonar:sonar
```

## Generate allure report
```
allure serve allure-results
allure generate --single-file allure-results --clean
allure generate allure-results
allure open allure-report
```
## Allure test report: PDF
Repository link: [https://github.com/eroshenkoam/allure-pdf/releases](https://github.com/eroshenkoam/allure-pdf/releases)

**Command:**
```shell
allure-pdf allure-results -o report.pdf
```
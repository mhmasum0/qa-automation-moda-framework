# QA Automation framework


# Run the test
By Default, It will run individual-test
```bash
mvn test -Pparallel-tests
mvn test -Pindividual-tests
```
## Run all tests
```shell
mvn test -DsuiteXmlFile=src/test/resources/valid-test.xml
mvn test -DsuiteXmlFile=src/test/resources/all-tests.xml
```
## Run individual test

```
mvn test -DsuiteXmlFile=src/test/resources/individual-tests/invalid-login.xml
mvn test -DsuiteXmlFile=src/test/resources/individual-tests/valid-login.xml
mvn test -DsuiteXmlFile=src/test/resources/individual-tests/care-reminder.xml
mvn test -DsuiteXmlFile=src/test/resources/individual-tests/bh360-programs.xml
```

## SonarQube command
```shell
./sonar.sh start
mvn sonar:sonar
```

# Generate allure report
```
allure generate --single-file allure-results --clean
allure serve allure-results
allure generate allure-results
allure open allure-report
```
## Allure test report: PDF
Repository link: [https://github.com/eroshenkoam/allure-pdf/releases](https://github.com/eroshenkoam/allure-pdf/releases)
**Command:**
```shell
allure-pdf allure-results -o report.pdf
```

# Delete folder from command line
```
rmdir /s .git
rmdir /s allure-results
rmdir /s logs
```

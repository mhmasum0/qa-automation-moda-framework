# QA Automation framework


# Run the test
By Default, It will run individual-test
```bash
mvn test -Pparallel-tests -DlogFilePath=
mvn test -Pindividual-tests -DlogFilePath=
allure generate allure-results
allure open allure-report
```
**Run Both**
```shell
mvn test -Pall-tests
```
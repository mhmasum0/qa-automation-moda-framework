# QA Automation framework


# Run the test
By Default, It will run individual-test
```bash
mvn test -Pparallel-tests
mvn test -Pindividual-tests
```
**Run Both**
```shell
mvn test -Pall-tests
```

# Generate allure report
```
allure serve allure-results
allure generate allure-results
allure open allure-report
```

# Delete folder from command line
```
rmdir /s .git
rmdir /s allure-results
rmdir /s logs
```

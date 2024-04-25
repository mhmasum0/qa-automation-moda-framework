# How to run testng test on Jenkins from Github

## Prerequisites

- GitHub Integration Plugin
- Maven Integration plugin
- TestNG Results Plugin
- Allure

Steps:
1. Jenkins Dashboard
2. Click on New Item
3. Select Maven Project and Write Item Name and Click Ok
4. Source Code Management -> Git -> [repository-url] -> Select Credentials -> Branch: usecase-4
5. Build -> Root POM : "pom.xml" -> Goals and options: clean test -Prun-tests -DsuiteXmlFile=src/test/resources/test-suite/valid-tests.xml
6. Post Steps: Select Run regardless of build result
7. Post-build Actions: Allure Report

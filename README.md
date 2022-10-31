# Wikipedia-UI-Engine
The Project has been develop with Selenium & Java8 
Each user can create Allure & Cucumber Report.
Feature file has 2 different test cases. 
The first test case searches a value and verify results are exist,
Also another one searches an invalid value and verify the information message. These are main cases to be sure that user can search a value.


There are several different approach to run test case.
* You can write the terminal "mvn clean install". After that you will see running test cases.
* You can trigger the cucumber feature file.
* You can trigger TestRunner.java.

If you want to create Allure report, firstly make sure allure framework is exist on your local. You can follow the page to confiure it.--> https://docs.qameta.io/allure-report/
After the installation, run test cases, you will see allure-result file in the project.
Open the terminal and write "allure serve", that's all. Allure Report is automatically created.
![image](https://user-images.githubusercontent.com/62480904/199030400-362064d6-efe2-4453-9648-ecae94d8cf5a.png)
![image](https://user-images.githubusercontent.com/62480904/199030453-427496f2-dc8b-480a-9734-8704b09828e2.png)


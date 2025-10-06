# testTaskAPI
Цей проєкт — це фреймворк для автоматизованого тестування REST API, розроблений на основі **Java**, **Rest Assured**, **Cucumber (BDD)**, **TestNG** і **Allure Reports**.

## **Використані технології**

### **Java 17+** | **Maven** | **Rest Assured** | **Cucumber (BDD)** | **TestNG** | **Allure Report** | **SLF4J + Logback** 

## Налаштування середовища

### 1. Встановити необхідне:
### - **Java JDK 17+**
### - **Apache Maven**
### - **Allure Commandline**
### - IDE (рекомендовано **IntelliJ IDEA**)

### 2. Склонувати проект:
### - git clone https://github.com/zilper/testTaskAPI

### 3. Встановити залежності:
### - **Java JDK 17+**
### - **Apache Maven**
### - **Allure Commandline**
### - IDE (рекомендовано **IntelliJ IDEA**)

## Параметри запуску:
### 1. Через консоль
### Для запуску всіх тестів

    mvn clean test

### Для запуску тестів за тегом

    mvn test -Dcucumber.filter.tags=@tagName

### 2. Через TestRunner

Відкрити клас TestRunner.java -> Натиснути кнопку Run

### 3. Через TestNG configurations

Відкрити Run\Debug Configurations у IntelliJIDEA -> Натиснути на + -> Додати TestNG конфігурацію ->  В налаштуваннях class: обрати runner.TestRunner

За необхідності в полі VM казати -Dcucumber.filter.tags=@tagName (Для запуску тестів за тегами)

### 4. Стандартний Cucumber runner теж працює (зручно під час розробки автотестів та дебагінгу)

## Генерація Allure звіту
### Запустити тести

    mvn clean test

### Згенерувати звіт

    mvn allure:report

### Відкрити звіт у браузері

    mvn allure:serve

### P.S Allure піднімає локальний сервер. Тому не забудь його закрити -> Ctrl + C


## Короткий опис

За основу взятий BDD підхід зокрема Cucumber. Що дозволяє розділити фреймворк на зрозумілі шари та робить його читабельним.

Для опису сценаріїв використовується **Gherkin** та feature файли.

Для опису кроків використовується stepdefinitions файли у яких відбувається виклик методів, що збережені у **JAVA** класах проекту.

На рівні **JAVA** класів реалізовані методи що взаємодіють з API для виклику запитів та перевірки їх наповнення, зокрема статус коду, полів респонсу або його структури.


    
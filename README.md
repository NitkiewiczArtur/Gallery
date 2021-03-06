# Gallery
 Założeniem projektu jest, aby fotograf mógł dodawać galerię zdjęć dla każdego klienta. Każda galeria powinna być widoczna tylko dla klienta po podaniu odpowiedniego hasła.
Dzięki takiemu rozwiązaniu fotograf zabezpiecza się, że zdjęcia innego klienta nie wejdą w posiadanie innej osoby. Stroną startową powinien być formularz do logowania (użytkownik, hasło).
Jeśli zaloguje się administrator ma on opcję zdefiniowania galerii zdjęć dla danego klienta. 
W momencie zalogowania klienta mamy podgląd jego zdjęć.

## Getting Started

1 Copy/download the repository from github and open it with IDE like Intellij.

2 Open MySQL Workbench and execute following command:
        
        create databse gallery;

3 Go to application.properties in resource folder and fill parameters below

    spring.datasource.url= "Your database port"  (default = "jdbc:mysql://${MYSQL_HOST:localhost}:3306/gallery")
    spring.datasource.username= "Your MySQL username"
    spring.datasource.password= "Your MySQL password"
4 Launch the app to let hibernate create all database tables.

5 Execute data.sql in MySQL Workbench.

6 Login with following accounts and have fun ;) 

    To login as Photographer use following account:
    Login : arli 
    Password: password

    To login as Client use following account:
    Login : mama
    Password: password
    
    
## Running the tests

No requrements for tests.


## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - Java framework used for web apps development.
* [Maven](https://maven.apache.org/) - Dependency Management
* [MySQL Database](https://www.mysql.com/) - SQL database
* [Thymeleaf](https://www.thymeleaf.org/) - Server-side Java template engine for both web and standalone environments
* [Apache Commons IO](https://mvnrepository.com/artifact/commons-io/commons-io/2.5) - Apache library 
* [Lombock](https://projectlombok.org/) -library that automatically plugs into your editor and build tools, spicing up your java

## Written With Help Of...
https://www.devglan.com/spring-boot/spring-boot-file-upload-download
https://github.com/jonathanborenstein/image-gallery
https://www.javaguides.net/2018/09/spring-boot-spring-mvc-role-based-spring-security-jpa-thymeleaf-mysql-tutorial.html
https://stackoverflow.com/questions/48235379/how-to-display-byte-array-from-a-model-in-thymeleaf
https://bykowski.pl/spring-boot-23-definiowanie-wlasnych-wyjatkow/
https://mkyong.com/spring-security/spring-security-password-hashing-example/

## Author

* **Artur Nitkiewicz** 
# Run using mvn
```
$ mvn clean spring-boot:run -Dspring-boot.run.profiles=prod -Dspring-boot.run.arguments="--dburl=jdbc:mysql://localhost:6009/testDB2 --dbuser=root --dbpass=root --jwtsecret=ajwndkajdbu1b%$6oinoiaenaod"
```

<br>

# Run JAR using Java
```
$ mvn clean package

$ java -jar target/quinots-app-0.1.0.jar --spring.profiles.active=prod --dburl='jdbc:mysql://localhost:6009/testDB2' --dbuser='root' --dbpass='root' --jwtsecret='ajwndkajdbu1b%$6oinoiaenaod'
```

<br>


# Run Development Instance with Embedded H2 DB
```
$ mvn clean package

$ java -jar target/quinots-app-0.1.0.jar --spring.profiles.active=dev --jwtsecret='ajwndkajdbu1b%$6oinoiaenaod'
```

# Run as Docker Container with Embedded MYSQL DB
```
```
<br>

# Run as Docker Container with Separate MYSQL DB Container
```
```

<br>

# Default users

| username  | password  |   role    |
|   :----:  |   :----:  |   :----:  |
|   user1   |   1111    |USER_ROLE  |
|   admin   |   admin   |ADMIN_ROLE |
----------


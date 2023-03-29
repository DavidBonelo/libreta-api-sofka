## liibreta-api-sofka

demo of a contact list REST api, built with java and spring.

### Setup
- Set up your database settings in the [application.properties](./src/main/resources/application.properties) file.
- Load the tables structure from the file [dbstructure.sql](./dbstructure.sql) into your database.

Run the `source sql_file_path` command in your sql database.
```sh
source ~/Documents/code/java/libreta/dbstructure.sql
```
- Run the project
- Make requests to the endpoints described in the api docs at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

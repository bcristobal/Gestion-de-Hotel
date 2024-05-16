Gestión de Hotel
==================================================
## Run Locally

Run the next command to clean and compile:

      mvn clean compile

Run the following command to compile all classes and launch the unit tests:

      mvn test

Make sure that the database was correctly configured. Use the contents of the file *create-hotels.sql* to create the database and grant privileges. For example:

      mysql –uroot -p < sql/create-hotels.sql

In this example, the class enhancement required by DataNucleus must be manually executed after the unit testing is performed.
This is required to avoid cluttering the JaCoCo report with all the methods generated automatically by DataNucleus.

Therefore, execute the following command to enhance the database classes

      mvn datanucleus:enhance

Run the following command to create database schema for this sample.

      mvn datanucleus:schema-create

Use the contents of the file *seed.sql* to generate some rooms and an admin user. For example:

      mysql –uroot -p < sql/seed-rooms.sql

Integration tests can be launched using the following command. An embedded Grizzly HTTP server will be launched to perform real calls
to the REST API and to the MySQL database.

      mvn verify -Pintegration-tests

Performance tests can be launched using the following command. In this example, these tests are the same integration tests but executed
multiple times to calculate some statistics

      mvn verify -Pperformance-tests

To launch the server run the command

      mvn jetty:run

Now, the client sample code can be executed in a new command window with
      
      mvn exec:java -Pclient

## Documentation

Maven site generation can be launched with

    mvn site

After process is finished go to **target/site** folder and open the main **index.hmtl** file to see the reports.

To generate the Doxygen documentation you first need to download and configure doxygen tool on your path.
Generate the output directory by running

    mvn compile

Then change to **src/main/resources** directory and run the following command

    cd src/main/resources
    doxygen Doxyfile

The generated documentation will be written to the **/target/doxygen** directory.

## Authors

* [@AlexGarcia0](https://www.github.com/AlexGarcia0)
* [@bcristobal](https://www.github.com/bcristobal)
* [@BeltranSendagorta](https://www.github.com/BeltranSendagorta)
* [@MarioArmellini](https://www.github.com/MarioArmellini)
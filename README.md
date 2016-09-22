# employee project integration with AppDirect

This project mainly has CRUD EmployeeService for an Employee Entity.
It has two end points one for Subscription and the other one for Single Sign On.
You can test the application with the help of JerseyClient class.

The technologies used are Spring Boot, JPA and Jersey
The application has been tested with an Oracle and a My SQL database.

Use will need to point it to your database. However it will create table automatically.
- spring.datasource.url=jdbc:mysql://localhost:3306/appdirect
- spring.datasource.username=root
- spring.datasource.password=******

Note: Some issues were being faced in testing the code so it may not be complete.


## Online demo

## Usage

### Maven

## Download release

## Development with Eclipse IDE

For further development of this add-on, the following tool-chain is recommended:
- Eclipse IDE
- m2e wtp plug-in (install it from Eclipse Marketplace)
- Vaadin Eclipse plug-in (install it from Eclipse Marketplace)
- JRebel Eclipse plug-in (install it from Eclipse Marketplace)
- Chrome browser

### Importing project

Choose File > Import... > Existing Maven Projects

Note that Eclipse may give "Plugin execution not covered by lifecycle configuration" errors for pom.xml. Use "Permanently mark goal resources in pom.xml as ignored in Eclipse build" quick-fix to mark these errors as permanently ignored in your project. Do not worry, the project still works fine. 

## Release notes

### Version 1.0.1
- Initial release

## Roadmap


## Issue tracking


## Contributions


## License & Author


## Developer Guide


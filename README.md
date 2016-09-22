# employee project integration with AppDirect

This project mainly has CRUD EmployeeService for an Employee Entity.

It has two end points one for Subscription and the other one for Single Sign On.

I have tried to follow each step required for the Subscription End point but was facing issues with 401 Unauthorized status.
I have also implemented SSL security but since Tomcat SSL configuration doesn't work with classpath resources so was not able to set the trust-store.

You can test the application with the help of JerseyClient class.

The application has been tested with an Oracle and a My SQL database.

Use will need to point it to your database. However it will create table automatically.
spring.datasource.url=jdbc:mysql://localhost:3306/appdirect
spring.datasource.username=root
spring.datasource.password=******


## Online demo

## Usage

### Maven

## Download release

Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, go to http://vaadin.com/addon/vaadin-excel-exporter

## Building and running demo

git clone https://github.com/bonprix/vaadin-excel-exporter
mvn clean install
cd demo
mvn jetty:run

To see the demo, navigate to http://localhost:8080/

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

### Debugging server-side

If you have not already compiled the widgetset, do it now by running vaadin:install Maven target for vaadin-excel-exporter-root project.

If you have a JRebel license, it makes on the fly code changes faster. Just add JRebel nature to your vaadin-excel-exporter-demo project by clicking project with right mouse button and choosing JRebel > Add JRebel Nature

To debug project and make code modifications on the fly in the server-side, right-click the vaadin-excel-exporter-demo project and choose Debug As > Debug on Server. Navigate to http://localhost:8080/vaadin-excel-exporter-demo/ to see the application.

### Debugging client-side

Debugging client side code in the vaadin-excel-exporter-demo project:
  - run "mvn vaadin:run-codeserver" on a separate console while the application is running
  - activate Super Dev Mode in the debug window of the application or by adding ?superdevmode to the URL
  - You can access Java-sources and set breakpoints inside Chrome if you enable source maps from inspector settings.
 
## Release notes


### Version 1.0.1
- Initial release


## Roadmap


## Issue tracking


## Contributions


## License & Author


## Developer Guide


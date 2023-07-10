# MyVotingApplication

Tools Used

    Eclipse
    JDK 20
    Apache Maven 3.9.2

Steps to run:

    Install Eclipse.
    Download the zip project from GitHub Repository and extract it to any location.
    Open Eclipse and import the project as a Maven project.
    Run MyVotingApplication.java as Java Application.
    Once embedded tomcat server starts, type http://localhost:8080/login in browser.
    Login to application and move to homepage.

Single-Page Application Code:
    https://github.com/ssingh83buffaloedu/MyVotingApplication/blob/main/src/main/resources/templates/login.html
    
Back-End Code:
    https://github.com/ssingh83buffaloedu/MyVotingApplication/tree/main/src/main/java/com/my/voting/application/MyVotingApplication

The application stores values in spring H2 in-memory database and will create a temporary data file in users folder. The properties are set in application.properties.

Note: login.html is a static html page and need to be refreshed after calling an API to reflect the changes.

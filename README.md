# ENI-Encheres
ENI J2EE project in group. Context of app is to sale objects by auction without financial exchanges.

# Install
Pre-requisites : [Java JDK 16](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html), [Tomcat 9.0.52](https://tomcat.apache.org/download-90.cgi), Intellij IDE
1) (IntelliJ) Edit Configurations > Add New Configuration
2) Select Tomcat Server, Local
3) In the tab 'Server' > Application server > Click "Configure"
4) Click + / Add Application Server 
5) In Tomcat Home > Browse to your unzipped Tomcat folder (ex: C:/.../.../apache-tomcat-9.0.52)
7) (have fun)

# Proxy issues 
## IntelliJ / Maven : 
1) (IntelliJ) Files > Settings > Maven 
2) Under Importing > VM Options : -DproxySet=true -DproxyHost=10.35.0.248 -DproxyPort=8080
3) Under Runner > VM Options : -DproxySet=true -DproxyHost=10.35.0.248 -DproxyPort=8080

## GitHub :
1) In Terminal : git config --global http.proxy http://10.35.0.248:8080

# ENI-Encheres

ENI J2EE project in group. Context of app is to sale objects by auction without financial exchanges.

# Install / Config

Pre-requisites : [Java JDK 16](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html)
, [Tomcat 9.0.52](https://tomcat.apache.org/download-90.cgi), Intellij IDE

### Installation | Intellij/Tomcat

1) (IntelliJ) Edit Configurations > Add New Configuration
2) Select Tomcat Server, Local
3) In the tab 'Server' > Application server > Click "Configure"
4) Click + / Add Application Server
5) In Tomcat Home > Browse to your unzipped Tomcat folder (ex: C:/.../.../apache-tomcat-9.0.52)
7) (have fun)

### Installation || Database creation + data insertion

Pre-requisites : SQL Server

1) (SQL Server) Create the database 'ENCHERES'
    1) Run script_creation.sql (package fr.eni.util)
    2) Run script_data.sql (package fr.eni.util)
2) In the tab Security > Connections > Add a new connection
    1) username : utilisateurBDD, pwd : Pa$$w0rd
    2) Give the rights for database 'ENCHERES'
    3) Under the "Mapping" tab : check 'ENCHERES', then 'dbreader' & 'dbwriter' below
3) (SQL Server Manager Configuration) : In Network, activate all services.
4) (Windows Services) : Scroll down to SQL Server Agent & SQL Server Browser, and for both :
    1) Right-click > Properties
    2) Activate the service at the start of Windows, then click Apply
    3) Start the service & close the window
5) Restart the computer (shutdown /r) *OR* kill all SQL Server tasks running (desk & in the background)
6) (IntelliJ) In the project :
    1) Create a folder 'META-INF' in folder 'webapp'
    2) Create a file 'context.xml'
    3) In context.xml, make sure this line is correct :  
       `url="jdbc:sqlserver://localhost;databasename=ENCHERES"
       username="utilisateurBDD"
       password="Pa$$w0rd"`

# Issues

## Proxy troubleshoting

### IntelliJ / Maven :

1) (IntelliJ) Files > Settings > Maven
2) Under Importing > VM Options : -DproxySet=true -DproxyHost=10.35.0.248 -DproxyPort=8080
3) Under Runner > VM Options : -DproxySet=true -DproxyHost=10.35.0.248 -DproxyPort=8080

### GitHub :

1) In Terminal : git config --global http.proxy http://10.35.0.248:8080

## Internalionalization not working
1) Right-click ressources folder - the one containing your .properties
2) In 'Mark Directory as' select 'Ressource root'
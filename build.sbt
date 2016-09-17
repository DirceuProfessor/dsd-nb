version := "0.1"

name := "DSD-Netbeans"

libraryDependencies ++= Seq("org.springframework.data" % "spring-data-jpa" % "1.8.2.RELEASE" , 
"org.hibernate" % "hibernate-entitymanager" % "5.0.0.Final" , 
"com.h2database" % "h2" % "1.4.185", 
"com.zaxxer" % "HikariCP" % "2.2.5"
, "junit" % "junit" % "4.12" % Test
,"com.novocode" % "junit-interface" % "0.11" % Test
        exclude("junit", "junit-dep"),
    "org.springframework" % "spring-test" % "4.3.2.RELEASE" % Test    )


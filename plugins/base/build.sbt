libraryDependencies ++= Seq(
  jdbc,
  javaCore,
  javaEbean,
  "mysql" % "mysql-connector-java" % "5.1.6",
  "org.freemarker" % "freemarker" % "2.3.19",
  "org.springframework" % "spring-context" % "3.2.5.RELEASE",
  "org.springframework" % "spring-test" % "3.2.5.RELEASE"
)

play.Project.playScalaSettings
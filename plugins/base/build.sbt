libraryDependencies ++= Seq(
  jdbc,
  cache,
  javaCore,
  javaEbean,
  "com.typesafe.play" % "routes-compiler_2.10" % "2.2.1",
  "com.github.mumoshu" %% "play2-memcached" % "0.4.0",
  "com.lmax" % "disruptor" % "3.3.2",
  "mysql" % "mysql-connector-java" % "5.1.6",
  "org.freemarker" % "freemarker" % "2.3.19",
  "org.springframework" % "spring-context" % "3.2.5.RELEASE",
  "org.springframework" % "spring-test" % "3.2.5.RELEASE"
)

resolvers += "Spy Repository" at "http://files.couchbase.com/maven2"
name := "platform"

organization in ThisBuild := "com.zhangmeng"

scalaVersion in ThisBuild := "2.10.2"

lazy val root = project.in(file("."))
  .dependsOn(core)
  .aggregate(core)

lazy val base = project.in(file("plugins/base"))
  .settings(
    libraryDependencies ++= Seq(
      javaEbean,
      "org.springframework" % "spring-context" % "3.2.5.RELEASE",
      "org.springframework" % "spring-test" % "3.2.5.RELEASE"
    )
  )

lazy val models = project.in(file("plugins/models"))
  .dependsOn(base)
  .aggregate(base)

lazy val core = project.in(file("plugins/core"))
  .dependsOn(base, models)
  .aggregate(base, models)

version := "1.0"


play.Project.playJavaSettings
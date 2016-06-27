name := "platform"

organization in ThisBuild := "com.zhangmeng"

scalaVersion in ThisBuild := "2.10.2"

val commonSetting = Seq(
    unmanagedResources in Compile <<= (
      javaSource in Compile,
      classDirectory in Compile,
      unmanagedResources in Compile
      ) map {
      (app, classes, resources) =>
        IO.copyDirectory(app / "views", classes / "views", overwrite = true)
        resources
    },
    generateReverseRouter := false
)

lazy val root = project.in(file("."))
  .dependsOn(backend)
  .aggregate(backend)

lazy val base = project.in(file("plugins/base"))

lazy val models = project.in(file("plugins/models"))
  .dependsOn(base)
  .aggregate(base)

lazy val backend = project.in(file("plugins/backend"))
  .settings(playJavaSettings : _*)
  .settings(commonSetting: _*)
  .dependsOn(base, models)
  .aggregate(base, models)

play.Project.playJavaSettings
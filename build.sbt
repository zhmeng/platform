name := "platform"

organization in ThisBuild := "com.zhangmeng"

scalaVersion in ThisBuild := "2.10.2"

val freemarkerSetting = {
    unmanagedResources in Compile <<= (
      javaSource in Compile,
      classDirectory in Compile,
      unmanagedResources in Compile
      ) map {
      (app, classes, resources) =>
        IO.copyDirectory(app / "views", classes / "views", overwrite = true)
        resources
    }
}

lazy val root = project.in(file("."))
  .dependsOn(core)
  .aggregate(core)

lazy val base = project.in(file("plugins/base"))

lazy val models = project.in(file("plugins/models"))
  .dependsOn(base)
  .aggregate(base)

lazy val core = project.in(file("plugins/core"))
  .settings(freemarkerSetting)
  .dependsOn(base, models)
  .aggregate(base, models)

play.Project.playJavaSettings
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

lazy val base = project.in(file("plugins/base"))
  .settings(playScalaSettings: _*)

lazy val models = project.in(file("plugins/models"))
  .settings(playJavaSettings: _*)
  .dependsOn(base)
  .aggregate(base)

lazy val backend = project.in(file("plugins/backend"))
  .settings(playJavaSettings ++ commonSetting: _*)
  .dependsOn(base, models)
  .aggregate(base, models)

lazy val minishop = project.in(file("plugins/minishop"))
  .settings(playJavaSettings ++ commonSetting: _*)
  .dependsOn(backend)
  .aggregate(backend)
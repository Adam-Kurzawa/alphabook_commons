ThisBuild / scalaVersion     := "3.1.3"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.alphabooks"
ThisBuild / organizationName := "Alpha Book"

lazy val root = (project in file("."))
  .settings(
    name := "Commons",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio"                 % "2.0.0-RC6",
      "dev.zio" %% "zio-config-typesafe" % "3.0.0-RC9",
      "dev.zio" %% "zio-config-magnolia" % "3.0.0-RC9",
      "io.d11"  %% "zhttp"               % "2.0.0-RC9",
      circe("core"),
      circe("generic"),
      circe("parser"),
      "dev.zio" %% "zio-test" % "2.0.0-RC6" % Test
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
    publishTo := Some("GitHub Adam-Kurzawa Apache Maven Packages" at "https://maven.pkg.github.com/Adam-Kurzawa/alphabook_commons"),
    publishMavenStyle := true,
    credentials += Credentials(
      "GitHub Package Registry",
      "maven.pkg.github.com",
      "Adam-Kurzawa",
      System.getenv("GITHUB_TOKEN")
    )
  )

def circe(module: String) = "io.circe" %% s"circe-$module" % "0.14.2"

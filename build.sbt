name := "code-style-settings-plugin"
scalaVersion := "2.10.6"
sbtPlugin := true

addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.15")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")

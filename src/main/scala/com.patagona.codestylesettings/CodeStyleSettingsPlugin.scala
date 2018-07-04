package com.patagona.codestylesettings

import com.lucidchart.sbt.scalafmt.ScalafmtCorePlugin.autoImport._
import org.scalastyle.sbt.ScalastylePlugin.autoImport._
import sbt.Keys._
import sbt._

object CodeStyleSettingsPlugin extends AutoPlugin {
  object autoImport {
    val testScalaStyle = taskKey[Unit]("testScalaStyle")
    val compileScalastyle = taskKey[Unit]("compileScalastyle")
  }

  import autoImport._

  override def requires: Plugins = org.scalastyle.sbt.ScalastylePlugin && com.lucidchart.sbt.scalafmt.ScalafmtCorePlugin
  override def trigger: PluginTrigger = allRequirements
  override def projectSettings: Seq[sbt.Def.Setting[_]] = {
    Seq(
      scalastyleConfig := (baseDirectory in ThisBuild).value / "project" / "scalastyle_config.xml",
      scalastyleConfig in Test := (baseDirectory in ThisBuild).value / "project" / "scalastyle_config.xml",
      scalafmtVersion := "1.5.1",
      testScalaStyle := scalastyle.in(Test).toTask("").value,
      compileScalastyle := scalastyle.in(Compile).toTask("").value,
      (test in Test) := ((test in Test) dependsOn testScalaStyle).value,
      (test in Test) := ((test in Test) dependsOn compileScalastyle).value
    )
  }
}

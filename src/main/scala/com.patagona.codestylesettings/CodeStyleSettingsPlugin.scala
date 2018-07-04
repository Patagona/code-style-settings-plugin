import com.lucidchart.sbt.scalafmt.ScalafmtCorePlugin.autoImport.scalafmtVersion
import org.scalastyle.sbt.ScalastylePlugin.autoImport.scalastyleConfig
import sbt.Keys.baseDirectory
import sbt._

object CodeStyleSettingsPlugin extends AutoPlugin {
  override def requires: Plugins = org.scalastyle.sbt.ScalastylePlugin && com.lucidchart.sbt.scalafmt.ScalafmtCorePlugin
  override def trigger: PluginTrigger = allRequirements
  override def projectSettings: Seq[sbt.Def.Setting[_]] = {
    Seq(
      scalastyleConfig := (baseDirectory in ThisBuild).value / "project" / "scalastyle_config.xml",
      scalastyleConfig in Test := (baseDirectory in ThisBuild).value / "project" / "scalastyle_config.xml",
      scalastyleConfigUrl := Some("https://raw.githubusercontent.com/Patagona/code-style-settings-plugin/master/scalastyle_config.xml"),
      scalastyleConfigUrlCacheFile := scalastyleConfig.value,
      scalafmtVersion := "1.5.1"
    )
  }
}

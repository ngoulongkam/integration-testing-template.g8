import sbt.Keys._
import sbt._

object $projectName$ extends Build {

  val nexusPreviewHost: String = System.getProperty("hmrc.repo.host", "https://nexus-preview.tax.service.gov.uk")

  //  credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

  lazy val govUkTaxIntegrationTests: Project = Project("$name$", file("."))
    .settings(version := "0.0.1-SNAPSHOT",
      scalaVersion := "2.11.8",
      resolvers ++= Seq("hmrc-snapshots" at nexusPreviewHost + "/content/repositories/hmrc-snapshots",
        "hmrc-releases" at nexusPreviewHost + "/content/repositories/hmrc-releases",
        "typesafe-releases" at nexusPreviewHost + "/content/repositories/typesafe-releases",
        Resolver.url("hmrc-sbt-plugin-releases", url("https://dl.bintray.com/hmrc/sbt-plugin-releases"))(Resolver.ivyStylePatterns)),
      libraryDependencies ++= Seq(
        "com.typesafe.play" %% "play-json" % "2.5.12",
        "org.pegdown" % "pegdown" % "1.4.2" % "test",
        "net.lightbody.bmp" % "browsermob-core" % "2.1.1",
        "org.scalactic" %% "scalactic" % "3.0.1",
        "uk.gov.hmrc" %% "hmrctest" % "2.3.0" % "test" exclude("ch.qos.logback", "logback-classic"),
        "org.scalatest" %% "scalatest" % "3.0.1",
        "com.fasterxml.jackson.core" % "jackson-annotations" % "2.7.0",
        "com.fasterxml.jackson.core" % "jackson-databind" % "2.7.2",
        "com.fasterxml.jackson.core" % "jackson-core" % "2.7.4",
        "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.7.2"),

      parallelExecution in Test := false,
      testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-reports/html-report"),
      testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/test-reports"),
      testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oDF"))

  addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.8")
  addSbtPlugin("uk.gov.hmrc" % "sbt-settings" % "3.2.0")
  addSbtPlugin("uk.gov.hmrc" % "hmrc-resolvers" % "0.4.0")
}

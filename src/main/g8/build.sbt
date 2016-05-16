name := "$name$"

organization := "$organization$"

version := "$version$"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.6.1" % "provided" withSources() withJavadoc(),
  "org.apache.spark" %% "spark-sql" % "1.6.1" % "provided" withSources() withJavadoc(),
  "org.apache.hadoop" % "hadoop-common" % "2.7.2" % "provided" withSources() withJavadoc() excludeAll ExclusionRule(organization = "javax.servlet"),

  "com.databricks" %% "spark-avro" % "2.0.1" withSources() withJavadoc(),

  "org.scalatest" %% "scalatest" % "2.2.6" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.13.0" % "test" withSources() withJavadoc(),
  "org.scala-lang" % "scala-reflect" % "2.11.8" withSources() withJavadoc(),
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.4" withSources() withJavadoc(),

  "com.holdenkarau" %% "spark-testing-base" % "1.6.1_0.3.3" withSources() withJavadoc()
)

initialCommands := "import $organization$.$name;format="lower,word"$._"

// Allow more memory
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")

// Disable parallel tests (Required by https://github.com/holdenk/spark-testing-base)
parallelExecution in Test := false

// Config for building the JAR file
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case PathList("javax", "servlet", xs@_*) => MergeStrategy.discard
  case PathList(ps@_*) if ps.last endsWith ".html" => MergeStrategy.discard
  case n if n.startsWith("reference.conf") => MergeStrategy.concat
  case n if n.endsWith(".conf") => MergeStrategy.concat
  case x => MergeStrategy.first
}

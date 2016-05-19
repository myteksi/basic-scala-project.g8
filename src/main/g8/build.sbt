name := "$name$"
organization := "$organization$"
version := "$version$"

scalaVersion := "2.10.6"

val sparkVersion = settingKey[String]("The version of Spark")
sparkVersion := "1.6.1"

// Dependencies
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion.value % "provided" withSources() withJavadoc(),
  "org.apache.spark" %% "spark-streaming" % sparkVersion.value % "provided" withSources() withJavadoc(),
  "org.apache.spark" %% "spark-sql" % sparkVersion.value % "provided" withSources() withJavadoc(),

  "com.databricks" %% "spark-avro" % "2.0.1" withSources() withJavadoc()
)

// Test Dependencies
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.6" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.13.0" % "test" withSources() withJavadoc(),

  "com.holdenkarau" %% "spark-testing-base" % "1.6.1_0.3.3" % "test" withSources() withJavadoc()
)

initialCommands := "import $organization$.$name;format="lower,word"$._"

// Build and Run Options
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:+CMSClassUnloadingEnabled")
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

// Disable parallel tests (Required by https://github.com/holdenk/spark-testing-base)
parallelExecution in Test := false

// Set a nicer build name
assemblyJarName in assembly := s"\${name.value}-with-dependencies.jar"

// Check src/main style on compile
lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")
compileScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Compile).toTask("").value
(compile in Compile) <<= (compile in Compile) dependsOn compileScalastyle

// Check src/test style on test
lazy val testScalastyle = taskKey[Unit]("testScalastyle")
testScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Test).toTask("").value
(test in Test) <<= (test in Test) dependsOn testScalastyle

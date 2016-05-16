name := "$name$"

organization := "$organization$"

version := "$version$"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
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


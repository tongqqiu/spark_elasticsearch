import AssemblyKeys._

name := "Simple Project"

version := "1.0"

scalaVersion := "2.10.4"


resolvers ++= Seq( "maven.org" at "http://repo2.maven.org/maven2",
  "conjars.org" at "http://conjars.org/repo",
  "codahale.com" at "http://repo.codahale.com" )

assemblySettings

assemblyOption in assembly ~= { _.copy(makeExecutable = true) }

mainClass in assembly := Some("SimpleApp")

jarName in assembly := "myMain"

libraryDependencies ++=  Seq (
  "org.apache.spark" %% "spark-core" % "1.1.0" % "provided",
  "net.sf.opencsv" % "opencsv" % "2.3" % "provided",
  "org.elasticsearch" % "elasticsearch-hadoop" % "2.1.0.Beta1"
)


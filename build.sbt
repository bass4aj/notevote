import Libraries.android._

androidBuild

name := """NoteVote"""

version := Versions.appV

scalaVersion := Versions.scalaV

libraryDependencies ++= Seq(
  aar(androidDesign),
  "com.spotify.sdk" % "spotify-auth" % "1.0.0-beta12",
  "com.spotify.sdk" % "spotify-player" % "1.0.0-beta12"
)




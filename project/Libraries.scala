import sbt._
/**
 * Created by Aaron on 5/12/2016.
 */
object Libraries {

    def onCompile(dep: ModuleID): ModuleID = dep % "compile"

  object scala {

    lazy val scalaReflect = "org.scala-lang" % "scala-reflect" % Versions.scalaV
    lazy val scalap = "org.scala-lang" % "scalap" % Versions.scalaV
  }

  object android {

    def androidDep(module: String) = "com.android.support" % module % Versions.androidV

    lazy val androidRecyclerview = androidDep("recyclerview-v7")
    lazy val androidCardView = androidDep("cardview-v7")
    lazy val androidDesign = androidDep("design")
  }

  object playServices {

    def playServicesDep(module: String) = "com.google.android.gms" % module % Versions.playServicesV

    lazy val playServicesGooglePlus = playServicesDep("play-services-plus")
    lazy val playServicesAccountLogin = playServicesDep("play-services-identity")
    lazy val playServicesActivityRecognition = playServicesDep("play-services-location")
    lazy val playServicesAppIndexing = playServicesDep("play-services-appindexing")
    lazy val playServicesCast = playServicesDep("play-services-cast")
    lazy val playServicesDrive = playServicesDep("play-services-drive")
    lazy val playServicesFit = playServicesDep("play-services-fitness")
    lazy val playServicesMaps = playServicesDep("play-services-maps")
    lazy val playServicesAds = playServicesDep("play-services-ads")
    lazy val playServicesPanoramaViewer = playServicesDep("play-services-panorama")
    lazy val playServicesGames = playServicesDep("play-services-games")
    lazy val playServicesWallet = playServicesDep("play-services-wallet")
    lazy val playServicesWear = playServicesDep("play-services-wearable")
    // Google Actions, Google Analytics and Google Cloud Messaging
    lazy val playServicesBase = playServicesDep("play-services-base")
  }
}

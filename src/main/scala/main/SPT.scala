package main

import scala.annotation.tailrec
import scala.util.Random

/**
 * Created by Aaron and Jonesy716151 on 5/12/2016.
 */

class SPT {

  class Song(var songTitle:String, var trackArtist:String, var uri:String) {

    override def toString ={
      "SongName: "+songTitle +", SongArtist: "+trackArtist+", uri: "+uri
    }
  }

  private val songList = List(
  //        --Song Name--              --Artist Name--        --URI--
    new Song("Beer For My Horses",      "Toby Keith",          Random.nextInt(999999999)+""),
    new Song("I See Fire",              "Ed Sheeran",          Random.nextInt(999999999)+""),
    new Song("TNT",                     "ACDC",                Random.nextInt(999999999)+""),
    new Song("Work",                    "Rihanna",             Random.nextInt(999999999)+""),
    new Song("No",                      "Megan Trainor",       Random.nextInt(999999999)+""),
    new Song("Love Yourself",           "Justin Bieber",       Random.nextInt(999999999)+""),
    new Song("Stressed Out",            "Twenty One Pilots",   Random.nextInt(999999999)+""),
    new Song("My House",                "Flo Rida",            Random.nextInt(999999999)+""),
    new Song("Sorry",                   "Justin Bieber",       Random.nextInt(999999999)+""),
    new Song("Hands To Myself",         "Selina Gomez",        Random.nextInt(999999999)+""),
    new Song("Hello",                   "Adele",               Random.nextInt(999999999)+""),
    new Song("Close",                   "Nick Jonas",          Random.nextInt(999999999)+""),
    new Song("Humble and Kind",         "Tim McGraw",          Random.nextInt(999999999)+""),
    new Song("The Hills",               "The Weeknd",          Random.nextInt(999999999)+""),
    new Song("Lost Boy",                "Ruth B",              Random.nextInt(999999999)+""),
    new Song("Night's On Fire",         "David Nail",          Random.nextInt(999999999)+""),
    new Song("Drink You Away",          "Justin Timberlake",   Random.nextInt(999999999)+""),
    new Song("The Driver",              "Charles Kelley",      Random.nextInt(999999999)+""),
    new Song("What Do You Mean?",       "Justin Bieber",       Random.nextInt(999999999)+""),
    new Song("Same Old Love",           "Selena Gomez",        Random.nextInt(999999999)+""),
    new Song("Here",                    "Alessia Cara",        Random.nextInt(999999999)+""),
    new Song("Snitches",                "Shawn Mendes",        Random.nextInt(999999999)+""),
    new Song("Like I'm Gonna Lose you", "Meghan Trainor",      Random.nextInt(999999999)+""),
    new Song("679",                     "Fetty Wap",           Random.nextInt(999999999)+""),
    new Song("In The Night",            "The Weeknd",          Random.nextInt(999999999)+""),
    new Song("Jumpman",                 "Drake & Future",      Random.nextInt(999999999)+""),
    new Song("On My Mind",              "Ellie Goulding",      Random.nextInt(999999999)+""),
    new Song("Can't Feel My Face",      "The Weeknd",          Random.nextInt(999999999)+""),
    new Song("Die A Happy Man",         "Thomas Rhett",        Random.nextInt(999999999)+""),
    new Song("Confident",               "Demi Lovato",         Random.nextInt(999999999)+""),
    new Song("Trap Queen",              "Fetty Wap",           Random.nextInt(999999999)+""),
    new Song("Perfect",                 "One Direction",       Random.nextInt(999999999)+""),
    new Song("Focus",                   "Ariana Grande",       Random.nextInt(999999999)+""),
    new Song("Break Up In A Small Town","Sam Hunt",            Random.nextInt(999999999)+""),
    new Song("See You Again",           "Wiz Khalifa",         Random.nextInt(999999999)+""),
    new Song("Cake By The Ocean",       "DNCE",                Random.nextInt(999999999)+""),
    new Song("Drag Me Down",            "One Direction",       Random.nextInt(999999999)+""),
    new Song("Cheerleader",             "OMI",                 Random.nextInt(999999999)+""),
    new Song("When We Were Young",      "Adele",               Random.nextInt(999999999)+""),
    new Song("Back To Sleep",           "Chris Brown",         Random.nextInt(999999999)+""),
    new Song("I Got The Boy",           "Jana Kramer",         Random.nextInt(999999999)+""),
    new Song("Top Of The World",        "Tim McGraw",          Random.nextInt(999999999)+""),
    new Song("Irresistible",            "Fall Out Boy",        Random.nextInt(999999999)+""),
    new Song("New Americana",           "Halsey",              Random.nextInt(999999999)+""),
    new Song("Dibs",                    "Kelsea Ballerini",    Random.nextInt(999999999)+""),
    new Song("Strip It Down",           "Luke Bryan",          Random.nextInt(999999999)+""),
    new Song("Dessert",                 "Dawin",               Random.nextInt(999999999)+""),
    new Song("Right Hand",              "Drake",               Random.nextInt(999999999)+""),
    new Song("Smoke Break",             "Carrie Underwood",    Random.nextInt(999999999)+""),
    new Song("Purpose",                 "Justin Bieber",       Random.nextInt(999999999)+""),
    new Song("Somebody to Love",        "Jordan Smith",        Random.nextInt(999999999)+""),
    new Song("Thinking Out Loud",       "Ed Sheeran",          Random.nextInt(999999999)+""),
    new Song("Sugar",                   "Maroon 5",            Random.nextInt(999999999)+""),
    new Song("Shut Up And Dance",       "Walk The Moon",       Random.nextInt(999999999)+""),
    new Song("Watch Me",                "Silento",             Random.nextInt(999999999)+""),
    new Song("Love Me Like You Do",     "Ellie Goulding",      Random.nextInt(999999999)+""),
    new Song("Take Me To Church",       "Hozier",              Random.nextInt(999999999)+""),
    new Song("Bad Blood",               "Taylor Swift",        Random.nextInt(999999999)+""),
    new Song("Want To Want Me",         "Jason Derulo",        Random.nextInt(999999999)+""),
    new Song("Fight Song",              "Rachel Platten",      Random.nextInt(999999999)+""),
    new Song("Lips Are Movin",          "Meghan Trainor",      Random.nextInt(999999999)+""),
    new Song("Post To Be",              "Omarion",             Random.nextInt(999999999)+"")

  )

  private val playListNames = List("Party Time", "Chill Out", "Bonfire Time", "Workout", "Wine and Dine", "Late Night", "Awesome Songs")

  private val playLists = Map(
    "Party Time" -> performSearchWithQuery(countdown = 10),
    "Chill Out" -> performSearchWithQuery(countdown = 10),
    "Bonfire Time" -> performSearchWithQuery(countdown = 10),
    "Workout" -> performSearchWithQuery(countdown = 10),
    "Wine and Dine" -> performSearchWithQuery(countdown = 10),
    "Late Night" -> performSearchWithQuery(countdown = 10),
    "Awesome Songs" -> performSearchWithQuery(countdown = 10)
  )

  /**
   * Returns a random selection of playlist names,
   * but at least 1
   * @param options
   * @param countdown
   * @param output
   * @return
   */
  @tailrec
  final def playlistsForUserWithSession(options:List[String] = Random.shuffle(playListNames), countdown:Int = Random.nextInt(playListNames.length)+1, output:List[String]= List()): List[String] = {
    countdown match{
      case 0 => output
      case _ => playlistsForUserWithSession(options.tail, countdown-1, output:+options.head)
    }
  }

  /**
   * Pulls a random list of songs with a random size between 1 - 50 and returns it.
   * @param songs
   * @param countdown
   * @param output
   * @return
   */
  @tailrec
  final def performSearchWithQuery(songs:List[Song] = Random.shuffle(songList), countdown:Int = Random.nextInt(50), output:List[Song] = List()): List[Song] = {
    countdown match{
      case 0 => output
      case _ => performSearchWithQuery(songs.tail, countdown-1, output:+songs.head)
    }

  }

  /**
   * Returns a list of songs from a name given for a playlist.
   * @param playlistName
   * @return
   */
  def playlistForPlaylistName(playlistName:String):List[Song] ={
    this.playLists.get(playlistName).get
  }
}

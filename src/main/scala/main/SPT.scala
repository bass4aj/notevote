package main
import main.PF
/**
 * Created by Aaron on 5/12/2016.
 */
class SPT {

  class Song(var songTitle:String, var trackArtist:String, var uri:String) {

  }

  private val songList = List(
    new Song("Beer For My Horses", "Toby Keith", "21365514835"),
    new Song("I See Fire","Ed Sheeran","12385491851"),
    new Song("TNT","ACDC","12385491851"),
    new Song("Work","Rihanna","12385491851"),
    new Song("No","Megan Trainor", "12385491851"),
    new Song("Love Yourself","Justin Bieber","12385491851"),
    new Song("Stressed Out","Twenty One Pilots","12385491851"),
    new Song("My House","Flo Rida","12385491851"),
    new Song("Sorry","Justin Bieber","12385491851"),
    new Song("Hands To Myself","Selina Gomez","12385491851"),
    new Song("Hello","Adele","12385491851"),
    new Song("Close","Nick Jonas","12385491851"),
    new Song("Humble and Kind","Tim McGraw","12385491851"),
    new Song("The Hills","The Weeknd","12385491851"),
    new Song("Lost Boy","Ruth B","12385491851"),
    new Song("Night's On Fire", "David Nail", "21685489642"),
    new Song("Drink You Away", "Justin Timberlake", "2136845156"),
    new Song("The Driver", "Charles Kelley", "86457968469")
  )

  def playlistsForUserWithSession: List[String] = {
    return List("Party Time", "Chill Out", "Bonfire Time", "Workout", "Wine and Dine")
  }

  //pull a random  list of songs with a random size between 1 - 50
  def performSearchWithQuery: List[Song] = {
    return songList
  }
}

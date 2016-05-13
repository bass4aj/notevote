package main
import scala.annotation.tailrec
import Build.SPT

/**
 * Created by Jonesy716151 on 5/12/16.
 */
class SearchHandler {
  /**
   * Searches SPT for a random assortment of songs
   * Can get back anywhere form 1-50 songs but only passes top 20
   */
  def search(): List[Any] ={
    val list = SPT.performSearchWithQuery()
    if(list.length > 20){
      val result = cut(list)
      result
    }
    else {
      list
    }
  }

  /**
   * decrements the vote on a specific song by 1.
   * -takes in a String(songURI) -> the Spotify track URI for the song voted on.
   * uses that to find the correct song.
   */
  def getPlayLists: List[Any] ={
    val result = SPT.playlistsForUserWithSession()
    result
  }

  /**
   * Returns the list of songs associated to the given playlist name.
   * @param playListName
   * @return
   */
  def getPlayListWithName(playListName:String): List[SPT.Song] ={
    SPT.playlistForPlaylistName(playListName)
  }


  /**
   * Will take any List and reduce it to length of 20
   */
  @tailrec
  final def cut(objects:List[Any]): List[Any] ={
    objects match{
      case head::tail if tail.length < 20 => objects
      case head::tail => cut(tail)
    }
  }

}

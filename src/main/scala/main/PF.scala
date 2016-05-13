package main

import scala.annotation.tailrec
import scala.util.Random
import Build.Search
import Build.SPT

/**
 * Created by Jonesy716151 on 4/19/16.
 */
class PF() {
  var partyObjects:List[PartyObject] = List()
  var songObjects:List[SongObject] = List()

  //Used for finding things

  /**
   * Returns a list of party from fake Server.
   */
  class query() {
    def getObjects(): List[PartyObject] ={
      return Random.shuffle(partyObjects)
    }

    /**
     * Finds songObjects associated to a party and returns them.
     * @param key
     * @param objects
     * @param output
     * @return
     */
    @tailrec
    final def findObjects(key:String,objects:List[SongObject] = songObjects, output:List[SongObject] = List()): List[SongObject] ={
      objects match {
        case head::tail if(head.partyID == key) => findObjects(key,tail,head::output)
        case _::tail => findObjects(key,tail,output)
        case List() => return output
      }
    }
  }

  /**
   * Adds a songObject to the fake Server.
   * @param song
   */
  def addSongObject(song:SongObject): Unit ={
    songObjects = songObjects:+song
  }

  /**
   * Adds a partyObject to the fake Server.
   * @param party
   */
  def addPartyObject(party:PartyObject): Unit ={
    partyObjects = partyObjects:+party
    Search.getPlayListWithName(Search.getPlayLists.head.asInstanceOf[String])
    addSongs(Search.getPlayListWithName(Search.getPlayLists.head.asInstanceOf[String]).asInstanceOf[List[SPT.Song]],party.partyID)
  }

  @tailrec
  final def addSongs(songs:List[SPT.Song],partyID:String): Unit ={
    songs match{
      case head::tail => {
        val song = songs.head
        addSongObject(new SongObject(song.songTitle,song.trackArtist,song.uri,partyID,Random.nextInt(10)+1))
        addSongs(songs.tail,partyID)
      }
      case _ => return
    }
  }

  /**
   * Deletes specified party given from fake server.
   * Also calls deleteSongs to remove all songs that were in the party.
   * @param party
   * @param objects
   * @param result
   */
  @tailrec
  final def deletePartyObject(party:PartyObject, objects:List[PartyObject] = partyObjects, result:List[PartyObject] = List()){
    objects match {
      case head::tail if(head == party) => deletePartyObject(party,tail,result)
      case head::tail if(head != party) => deletePartyObject(party,tail,result :+ head)
      case _ => {
        partyObjects = result
        deleteSongs(party.partyID)
      }
    }
  }

  /**
   * Deletes all songs associated to given partyID from fake server.
   * @param partyID
   */
  @tailrec
  final def deleteSongs(partyID:String,objects:List[SongObject] = songObjects, result:List[SongObject] = List()): Unit ={
    objects match {
      case head::tail if(head.partyID == partyID) => deleteSongs(partyID,tail,result)
      case head::tail if(head != partyID) => deleteSongs(partyID,tail,result :+ head)
      case _ => songObjects = result
    }
  }

  /**
   * Deletes a given song from the fake server.
   * @param songURI
   * @param objects
   * @param result
   */
  @tailrec
  final def removeSong(songURI:String,partyID:String,objects:List[SongObject] = songObjects, result:List[SongObject] = List()): Unit ={
    objects match {
      case head::tail if(head.uri == songURI) => removeSong(songURI,partyID,tail,result)
      case head::tail if(head != songURI) => removeSong(songURI,partyID,tail,result :+ head)
      case _ => songObjects = result
    }
  }

  /**
   *
   * @param x
   * @param y
   */
  class GeoLocation(x:Float, y:Float) {
    val X:Float = x
    val Y:Float = y

    def getLocation(){
      (X,Y)
    }
  }

  /**
   *
   * @param partyid
   * @param partyname
   * @param partypin
   * @param partyprivate
   * @param geoLocation
   */
  class PartyObject( partyid:String, partyname:String,  partypin:String,  partyprivate:Boolean,  geoLocation:GeoLocation) {
    val partyID:String = partyid
    val partyName:String = partyname
    val partyPin:String = partypin
    val partyPrivate:Boolean = partyprivate
    val GeoLocation:GeoLocation = geoLocation

    override def toString(): String = "partyID = " + partyID + ", partyName = "+partyName

  }

  /**
   *
   * @param URI
   * @param songname
   * @param songartist
   * @param partyid
   * @param Votes
   */
  class SongObject( songname:String,songartist:String,URI:String, partyid:String,  Votes:Int) {
    val uri:String = URI
    val songName:String = songname
    val songArtist:String = songartist
    val partyID:String = partyid
    var votes:Int = Votes

    /**
     * Increments votes by 1
     */
    def increment(): Unit ={
      votes = votes + 1
    }

    /**
     * Decrements votes by 1
     */
    def decrement(): Unit = {
      votes = votes - 1
    }

    override def toString(): String = {
      "songName: " + songName + ",    songArtist: "+songArtist+ ",    votes: "+votes + ",   PartyID:"+partyid
    }

  }






}

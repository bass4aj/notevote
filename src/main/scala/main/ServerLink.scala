package main
import main.Build.{PF, SPT, query}

import scala.annotation.tailrec


/**
 * Created by Jonesy716151 on 4/5/16.
 */
class ServerLink {

  /*Mark: Variables.*/
  val SearchHandler = new SearchHandler()
  var userID = "guest"
  var isHosting = false
  var cleanUp = false
  var partyObject:PF.PartyObject = null
  var songCleanup:Map[String, Int] = Map()
  var currentLocation = new PF.GeoLocation(100,120)
  var musicList:List[PF.SongObject] = List()
  var rooms:List[PF.PartyObject] = List()
  var songBatch:List[SPT.Song] = List()

  /*Mark: internal Methods.*/

  /**
   * Finds rooms based on a geolocation point of the current device.
   * Will append results to rooms variable -- as a list of PFObjects.
   * Finds rooms within 2.0 miles.
   */
  def findRooms(){
    val query = new PF.query()
    this.rooms = query.getObjects()
  }

  /**
   * Adds a party object to the Parse PartyObject class.
   * -takes in a String(partyName) -> used to set the parties name.
   * -takes in a String(partyID) -> used to set the partiesID to the hosts Spotify ID.
   * -takes in a Bool(priv) -> used to set the room as private or not.
   */
  def addParty(partyName:String, partyID:String,priv:Boolean,partyPin:String = ""){
    val party = new PF.PartyObject(userID,partyName,partyPin,priv,currentLocation)
    setParty(party)
    PF.addPartyObject(party)
  }

  /**
   * Sets the current room to the room selected by the user.
   * -takes in an Int(objectNum) -> used to locate correct room from rooms variable.
   */
  def partySelect(objectIndex:Int){
    partyObject = rooms(objectIndex)
  }

  /**
   * Sets the party PFObject to the partyObject for hosting.
   */
  def setParty(party:PF.PartyObject){  //Might have to change PFObject.
    partyObject = party
  }

  /**
   * deletes a party object from the Parse PartyObject class.
   * -takes in a String(roomID) -> hosts Spotify ID, used to find correct object for deletion.
   */
  def deleteParty(): Unit = {
    PF.deletePartyObject(partyObject)
    partyObject = null
  }

  /**
   * First Creates a song object from given input.
   * Then adds that song object to songBatch.
   * Used when poeple are clicking on songs in Search
   */
  def addSongToBatch(songTitle:String, trackArtist:String, uri:String): Unit ={
    songBatch = songBatch :+ new SPT.Song(songTitle,trackArtist,uri)
  }

  /**
   * Removes a song from songBatch
   * Used when people are unclicking songs on Search
   */
  @tailrec
  final def removeSongFromBatch(songTitle:String, objects:List[SPT.Song] = songBatch, result:List[SPT.Song] = List()){
    objects match {
      case head::tail if(head.songTitle == songTitle) => removeSongFromBatch(songTitle,tail,result)
      case head::tail => removeSongFromBatch(songTitle,tail,result :+ head)
      case _ => songBatch = result
    }
  }

  /**
   * Adds songs to the parties subclass of SongLibrary.
   * uses songBatch and adds its songs SongLibrary on Parse.
   * ASync
   */
  def addSongBatch(objects:List[SPT.Song] = songBatch): Unit ={
    PF.addSongs(objects,partyObject.partyID)
  }

  /**
   * increments the vote on a specific song by 1.
   * -takes in a String(songURI) -> the Spotify track URI for the song voted on.
   * uses that to find the correct song.
   */
  @tailrec
  final def increment(songURI:String, objects:List[PF.SongObject] = PF.songObjects, partyID:String = partyObject.partyID): Unit ={
    objects match {
      case head::tail if(head.uri == songURI && head.partyID == partyID) => {
        head.increment()
        return
      }
      case head::tail => increment(songURI, tail)
    }
  }

  /**
   * decrements the vote on a specific song by 1.
   * -takes in a String(songURI) -> the Spotify track URI for the song voted on.
   * uses that to find the correct song.
   */
  @tailrec
  final def decrement(songURI:String, objects:List[PF.SongObject] = PF.songObjects, partyID:String = partyObject.partyID): Unit ={
    objects match {
      case head::tail if(head.uri == songURI && head.partyID == partyID) => {
        head.decrement()
        return
      }
      case head::tail => decrement(songURI, tail)
    }
  }

  /**
   * removes the song with the URI given that matches the partyID the host has.
   * -Paramaters:
   *      uri:String -> used to find the song to remove.
   */
  def removeSong(uri:String): Unit ={
    PF.removeSong(uri,partyObject.partyID)
  }

  /**
   * Asynchronous way to get an updated list of music in the song queue.
   */
  def getQueue(): Unit = {
    this.musicList = query.findObjects(partyObject.partyID)

  }

}


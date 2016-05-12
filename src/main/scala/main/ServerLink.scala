package main

import main.PF

import scala.annotation.tailrec


/**
 * Created by Jonesy716151 on 4/5/16.
 */
class ServerLink {

  /*Mark: Variables.*/
  val PF = new PF()
  var userID = "guest"
  var isHosting = false
  var cleanUp = false
  var partyObject:PF.PartyObject = null
  var songsVoted:Map[String,List[String]] = Map()
  var songCleanup:Map[String, Int] = Map()
  var currentLocation = new PF.GeoLocation(100,120)
  //var songBatch: Array[(String,String,String)] = Array()
  var musicList/*: [PFObject] */ = Array()
  var rooms:List[PF.PartyObject] = List()
  var songBatch:List[Song] = List()
  /* var currentLocation:PFGeoPoint? */

  class Song(var songTitle:String, var trackArtist:String, var uri:String) {

  }
  /*Mark: internal Methods.*/

  /**
   * Finds rooms based on a geolocation point of the current device.
   * Will append results to rooms variable -- as a list of PFObjects.
   * Finds rooms within 2.0 miles.
   */
  def findRooms(){ //might need completion handler added.
    val query = new PF.query()
    rooms = query.getObjects()
  }

  /**
   * Adds a party object to the Parse PartyObject class.
   * -takes in a String(partyName) -> used to set the parties name.
   * -takes in a String(partyID) -> used to set the partiesID to the hosts Spotify ID.
   * -takes in a Bool(priv) -> used to set the room as private or not.
   */
  def addParty(partyName:String, partyID:String,priv:Boolean,partyPin:String = ""){
    PF.partyObjects :+ new PF.PartyObject(userID,partyName,partyPin,priv,currentLocation)
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
    val query = new PF.query()
    query.deletePartyObject(partyObject)
  }

  /**
   * Gets a list of voted songs from the songsVoted dictionary if the room has been entered before.
   */
  def getSongsVoted() : List[String] = {
    return songsVoted(partyObject.partyID)
  }

  /**
   * On party entry it checks to see if the user has voted on any songs in the party
   *      if they have it sets songsVoted to that list of song titles.
   */
  def songsVotedCheck(): Unit ={

  }

  /**
   * First Creates a song object from given input.
   * Then adds that song object to songBatch.
   * Used when poeple are clicking on songs in Search
   */
  def addSongToBatch(songTitle:String, trackArtist:String, uri:String): Unit ={
    songBatch = songBatch :+ new Song(songTitle,trackArtist,uri)
  }

  /**
   * Removes a song from songBatch
   * Used when people are unclicking songs on Search
   */
  @tailrec
  final def removeSongFromBatch(songTitle:String, objects:List[Song] = songBatch, result:List[Song] = List()){
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
  @tailrec
  final def addSongBatch(objects:List[Song] = songBatch): Unit ={ //Might have to add a completion handler
    if(songBatch.isEmpty){
      return
    }
    PF.songObjects = PF.songObjects :+ new PF.SongObject(objects.head.uri,objects.head.songTitle,objects.head.trackArtist,partyObject.partyID,1)
    val tempList = songsVoted(partyObject.partyID) :+ objects.head.uri
    songsVoted += (partyObject.partyID -> tempList)
    addSongBatch(objects.tail)

  }

  /**
   * Adds a uri of a song voted on into songsVoted.
   * Used to know which songs are voted on in a party already by a user.
   */
  def voteURI(uri:String){
    val tempList = songsVoted(partyObject.partyID) :+ uri
    songsVoted += (partyObject.partyID -> tempList)
  }

  /**
   * Removes a uri of a song voted on into songsVoted.
   * Used to know which songs are voted on in a party already by a user.
   */
  @tailrec
  final def unVoteURI(uri:String,objects:List[String] = songsVoted(partyObject.partyID), result:List[String] = List()){
    objects match{
      case head::tail if(head == uri) => unVoteURI(uri,tail,result)
      case head::tail => unVoteURI(uri,tail,result :+ head)
      case _ => songsVoted += (partyObject.partyID -> result)
    }
  }

  /**
   * increments the vote on a specific song by 1.
   * -takes in a String(songURI) -> the Spotify track URI for the song voted on.
   * uses that to find the correct song.
   */
  def increment(songURI:String): Unit ={
    voteURI(songURI)
    //PF.increment(songURI)
  }

  /**
   * decrements the vote on a specific song by 1.
   * -takes in a String(songURI) -> the Spotify track URI for the song voted on.
   * uses that to find the correct song.
   */
  def decrement(songURI:String): Unit ={
    unVoteURI(songURI)
   // PF.decrement(songURI)
  }

  /**
   * This is a function that can be activated by the user in createRoom
   * This will remove songs from songLibrary on Parse if
   * the song has note been voted above 1 vote for 5 consecutive songs played.
   */
  def songClean(): Unit ={

  }

  /**
   * removes the song with the URI given that matches the partyID the host has.
   * -Paramaters:
   *      uri:String -> used to find the song to remove.
   */
  def removeSong(uri:String): Unit ={

  }


  /**
   * Asynchronous way to get an updated list of music in the song queue.
   */
  def getQueue(): Unit = { //Might have to add a completion handler.

  }

}


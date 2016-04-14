package sample.hello


/**
 * Created by Jonesy716151 on 4/5/16.
 */
class ServerLink {

  /*Mark: Variables.*/
  var isHosting = false
  var cleanUp = false
  /* var partyObject:PFObject */
  var songsVoted:Map[String,String] = Map()
  var songCleanup:Map[String, Int] = Map()
  var songBatch: Array[(String,String,String)] = Array()
  var musicList/*: [PFObject] */ = Array()
  var rooms/*: Array[PFObject]*/ = Array()
  var songBatch /*: Array[PFObject]*/ = Array()
  /* var currentLocation:PFGeoPoint? */


  /*Mark: internal Methods.*/

  /**
   * Finds rooms based on a geolocation point of the current device.
   * Will append results to rooms variable -- as a list of PFObjects.
   * Finds rooms within 2.0 miles.
   */
  def findRooms() :Unit ={ //might need completion handler added.

  }

  /**
   * Adds a party object to the Parse PartyObject class.
   * -takes in a String(partyName) -> used to set the parties name.
   * -takes in a String(partyID) -> used to set the partiesID to the hosts Spotify ID.
   * -takes in a Bool(priv) -> used to set the room as private or not.
   */
  def addParty(partyName:String, partyID:String,priv:Boolean): Unit ={ //Might need completion handler added.

  }

  /**
   * Sets the current room to the room selected by the user.
   * -takes in an Int(objectNum) -> used to locate correct room from rooms variable.
   */
  def partySelect(objectIndex:Int) :Unit ={

  }

  /**
   * Sets the party PFObject to the partyObject for hosting.
   */
  def setParty(party:PFObject) :Unit ={  //Might have to change PFObject.

  }

  /**
   * deletes a party object from the Parse PartyObject class.
   * -takes in a String(roomID) -> hosts Spotify ID, used to find correct object for deletion.
   */
  def deleteRoom(): Unit = {

  }

  /**
   * Deletes a party object from the Parse PartyObject class.
   * Sync
   */
  def deleteRoomNow(): Unit ={

  }

  /**
   * Gets a list of voted songs from the songsVoted dictionary if the room has been entered before.
   */
  def getSongsVoted() : Array[String] = {
    return Array()
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

  }

  /**
   * Removes a song from songBatch
   * Used when people are unclicking songs on Search
   */
  def removeSongFromBatch(songTitle:String, trackArtist:String): Unit ={

  }

  /**
   * Adds songs to the parties subclass of SongLibrary.
   * uses songBatch and adds its songs SongLibrary on Parse.
   * ASync
   */
  def addSongBatch(): Unit ={ //Might have to add a completion handler

  }

  /**
   * Adds a uri of a song voted on into songsVoted.
   * Used to know which songs are voted on in a party already by a user.
   */
  def voteURI(uri:String): Unit ={

  }

  /**
   * Removes a uri of a song voted on into songsVoted.
   * Used to know which songs are voted on in a party already by a user.
   */
  def unvoteURI(uri:String): Unit ={

  }

  /**
   * increments the vote on a specific song by 1.
   * -takes in a String(songURI) -> the Spotify track URI for the song voted on.
   * uses that to find the correct song.
   */
  def increment(songURI:String): Unit ={

  }

  /**
   * decrements the vote on a specific song by 1.
   * -takes in a String(songURI) -> the Spotify track URI for the song voted on.
   * uses that to find the correct song.
   */
  def decrement(songURI:String): Unit ={

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
   * Synchronous way to get an updated list of music in the song queue.
   */
  def syncGetQueue(): Unit ={

  }

  /**
   * Asynchronous way to get an updated list of music in the song queue.
   */
  def getQueue(): Unit = { //Might have to add a completion handler.

  }

}


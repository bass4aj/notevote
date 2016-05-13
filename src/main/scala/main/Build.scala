package main

import scala.annotation.tailrec

object Build {
  val PF = new PF()
  val SPT = new SPT()
  val Search = new SearchHandler()
  val ServerLink = new ServerLink()
  val query = new PF.query()
  def main(args: Array[String]): Unit = {

    //Initial setup.
    PF.addPartyObject(new PF.PartyObject("jonesy716151","Dustin's Party","",false, new PF.GeoLocation(100,120)))
    PF.addPartyObject(new PF.PartyObject("Aaron8765","Aaron's Party","",false, new PF.GeoLocation(100,118)))
    PF.addPartyObject(new PF.PartyObject("Geoff5231","Geoff's Party","",false, new PF.GeoLocation(102,120)))
    PF.addPartyObject(new PF.PartyObject("Jimbo12","Jim's Party","",false, new PF.GeoLocation(100,120)))
    PF.addPartyObject(new PF.PartyObject("EmmaLing43","Emma's Party","",false, new PF.GeoLocation(100,120)))
    PF.addPartyObject(new PF.PartyObject("Zachattack22","Zach's Party","",false, new PF.GeoLocation(100,120)))
    PF.addPartyObject(new PF.PartyObject("fixitfelix4","felix's Party","",false, new PF.GeoLocation(100,120)))

    println("Running Tests")
    println()
    println("_______Tests_______")

    var Tests:List[Boolean] = List()
    Tests = Tests :+ PerformTest1
    Tests = Tests :+ PerformTest2
    Tests = Tests :+ PerformTest3
    Tests = Tests :+ PerformTest4
    Tests = Tests :+ PerformTest5


    println()
    println()

    println("__Results__")

    if(results()){
      println("Final results are all tests Passed")
    }
    else{
      println("Final results are not all tests Passed")
    }



    @tailrec
    def results(objects:List[Boolean] = Tests, Passed:Boolean = true, TestNum:Int = 1):Boolean ={
      objects match{
        case head::tail if(head) => {
          println("Test"+TestNum + " was a success")
          if(Passed){
            results(tail,true,TestNum+1)
          }
          else{
            results(tail,false,TestNum+1)
          }
        }
        case head::tail if(!head) => {
          println("Test"+TestNum + " was a failure")
          results(tail,false,TestNum+1)
        }
        case _ => Passed
      }
    }


    @tailrec
    def printList(objects:List[Any]){
      objects match{
        case head::tail => {
          println(head.toString)
          printList(tail)
        }
        case _ => return
      }
    }


    /*
     *Checks to see if a party can be found and chosen
     */
    def PerformTest1:Boolean ={
      println()
      println()
      println("_______Test1_______")
      try{
        ServerLink.findRooms()
        println(ServerLink.rooms)
        ServerLink.partySelect(0)
        println("Currently in "+ServerLink.partyObject.partyName)
        ServerLink.getQueue()
        println(ServerLink.partyObject.partyName +" has "+ServerLink.musicList.length + " songs in it")
        println("Songs Are:")
        printList(ServerLink.musicList)
        println()
        println("Passed Test1")
        true
      }
      catch{
        case _ => {
          println()
          println("Failed Test1")
          false
        }
      }
    }

    /*
     *Checks to see if a party can be created and has music in it already from the playlist.
     */
    def PerformTest2:Boolean ={
      println()
      println()
      println("_______Test2_______")
      try{
        ServerLink.addParty("Guest Party","user1234",false,"")
        println("Currently hosting "+ServerLink.partyObject.partyName)
        ServerLink.getQueue()
        println(ServerLink.partyObject.partyName +" has "+ServerLink.musicList.length + " songs in it")
        println("Songs Are:")
        printList(ServerLink.musicList)

        println()
        println("Passed Test2")
        true
      }
      catch{
        case _ => {
          println()
          println("Failed Test2")
          false
        }
      }

    }

    /*
     * Checks to see if current hosting party can be closed and removed.
     * And to make sure all songs with that ID have been removed.
     */
    def PerformTest3:Boolean ={
      println()
      println()
      println("_______Test3_______")
      try{
        val oldParty = ServerLink.partyObject
        ServerLink.deleteParty()
        if(!PF.partyObjects.contains(oldParty)){
          println("Party has been deleted successfully.")
        }
        else{
          println("ERROR: Party wasnt deleted")
          false
        }
        if(query.findObjects(oldParty.partyID).isEmpty){
          println("All songs with party have been deleted successfully.")
        }
        else{
          println("ERROR: Not all songs were deleted")
          false
        }
        println()
        println("Passed Test3")
        true
      }
      catch{
        case _ => {
          println()
          println("Failed Test3")
          false
        }
      }
    }

    /*
     * Checks for entering a new room
     * searching for a song while in the room
     * adding that song to the songs queue
     */
    def PerformTest4:Boolean ={
      println()
      println()
      println("_______Test4_______")
      try{
        ServerLink.findRooms()
        ServerLink.partySelect(0)
        println("Currently in "+ServerLink.partyObject.partyName)
        ServerLink.getQueue()
        val startingLength = ServerLink.musicList.length
        println(ServerLink.partyObject.partyName +" has "+ServerLink.musicList.length + " songs in it")
        println("Songs are:")
        printList(ServerLink.musicList)
        val song = Search.search().head.asInstanceOf[SPT.Song]
        println("adding: => "+song)
        ServerLink.addSongToBatch(song.songTitle,song.trackArtist,song.uri)
        ServerLink.addSongBatch()
        ServerLink.getQueue()
        println("Ending Length = " + ServerLink.musicList.length)
        println("Songs are now:")
        printList(ServerLink.musicList)
        println()
        if(startingLength +1 == ServerLink.musicList.length){
          println("Song added successfully.")
        }
        else{
          println("ERROR: Song adding Failed")
          false
        }
        println()
        println("Passed Test4")
        true
      }
      catch{
        case _ => {
          println()
          println("Failed Test4")
          false
        }
      }
    }

    /*
     * Checks for voting on a song.
     * Also checks for unVoting a song.
     */
    def PerformTest5:Boolean ={
      println()
      println()
      println("_______Test5_______")
      try{
        val song = ServerLink.musicList.head
        val startingVote = song.votes
        println("First song in queue => "+song)
        println("voting on song")
        ServerLink.increment(song.uri)
        ServerLink.getQueue()
        println(song)
        if(startingVote != song.votes){
          println("Voting successful")
        }
        else{
          println("ERROR: Voting failed")
          false
        }
        println("unvoting song")
        ServerLink.decrement(song.uri)
        ServerLink.getQueue()
        println(song)
        if(startingVote == song.votes){
          println("Unvoting successful")
        }
        else{
          println("ERROR: Unvoting failed")
          false
        }

        println()
        println("Passed Test5")
        true
      }
      catch{
        case _ => {
          println()
          println("Failed Test5")
          false
        }
      }
    }
  }
}
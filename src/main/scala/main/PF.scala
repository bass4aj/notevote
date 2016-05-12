package main

import scala.annotation.tailrec
import scala.collection.JavaConverters._
import scala.util.Random

/**
 * Created by Jonesy716151 on 4/19/16.
 */
class PF() {
  var partyObjects:List[PartyObject] = List()
  var songObjects:List[SongObject] = List()
  partyObjects = partyObjects:+ new PartyObject("jonesy716151","Dustin's Party","",false, new GeoLocation(100,120))
  partyObjects = partyObjects:+ new PartyObject("Aaron8765","Aaron's Party","",false, new GeoLocation(100,118))
  partyObjects = partyObjects:+ new PartyObject("Geoff5231","Geoff's Party","",false, new GeoLocation(102,120))
  partyObjects = partyObjects:+ new PartyObject("Jimbo12","Jim's Party","",false, new GeoLocation(100,120))
  partyObjects = partyObjects:+ new PartyObject("EmmaLing43","Emma's Party","",false, new GeoLocation(100,120))
  partyObjects = partyObjects:+ new PartyObject("Zachattack22","Zach's Party","",false, new GeoLocation(100,120))
  partyObjects = partyObjects:+ new PartyObject("fixitfelix4","felix's Party","",false, new GeoLocation(100,120))
  songObjects = songObjects:+ new SongObject("12385491851","I See Fire","Ed Sheeran","jonesy716151",3)
  songObjects = songObjects:+ new SongObject("12385491851","TNT","ACDC","jonesy716151",2)
  songObjects = songObjects:+ new SongObject("12385491851","Work","Rihanna","Aaron's Party",3)
  songObjects = songObjects:+ new SongObject("12385491851","No","Megan Trainor","Geoff5231",2)
  songObjects = songObjects:+ new SongObject("12385491851","Love Yourself","Justin Bieber","Geoff5231",7)
  songObjects = songObjects:+ new SongObject("12385491851","Stressed Out","Twenty One Pilots","Jimbo12",1)
  songObjects = songObjects:+ new SongObject("12385491851","My House","Flo Rida","Jimbo12",4)
  songObjects = songObjects:+ new SongObject("12385491851","Sorry","Justin Bieber","EmmaLing43",2)
  songObjects = songObjects:+ new SongObject("12385491851","Hands To Myself","Selina Gomez","jonesy716151",3)
  songObjects = songObjects:+ new SongObject("12385491851","Hello","Adele","Zachattack22",1)
  songObjects = songObjects:+ new SongObject("12385491851","Close","Nick Jonas","Zachattack22",1)
  songObjects = songObjects:+ new SongObject("12385491851","Humble and Kind","Tim McGraw","Zachattack22",4)
  songObjects = songObjects:+ new SongObject("12385491851","The Hills","The Weeknd","jonesy716151",1)
  songObjects = songObjects:+ new SongObject("12385491851","Lost Boy","Ruth B","fixitfelix4",2)

/**
//  @tailrec
  final def increment(songURI:String, objects:List[SongObject] = songObjects){
    objects match{
      case head::tail if(head.uri == songURI) =>
    }
  }

//  @tailrec
  final def decrement(songURI:String){

  }
**/

  //Used for finding things
  class query() {
    def getObjects(): List[PartyObject] ={
      return partyObjects
    }

    @tailrec
    final def findObjects(key:String,objects:List[SongObject] = songObjects, output:List[SongObject] = List()): List[SongObject] ={
      objects match {
        case head::tail if(head.partyID == key) => findObjects(key,tail,head::output)
        case _::tail => findObjects(key,tail,output)
        case List() => return output
      }
    }

    def getQueue(partyID:String): Unit ={

    }

    @tailrec
    final def deletePartyObject(party:PartyObject, objects:List[PartyObject] = partyObjects, result:List[PartyObject] = List()){
      objects match {
        case head::tail if(head == party) => deletePartyObject(party,tail,result)
        case head::tail if(head != party) => deletePartyObject(party,tail,result :+ head)
        case _ => partyObjects = result
      }
    }
  }


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
  class SongObject( URI:String, songname:String,  songartist:String,  partyid:String,  Votes:Int) {
    val uri:String = URI
    val songName:String = songname
    val songArtist:String = songartist
    val partyID:String = partyid
    var votes:Int = Votes

    def increment(): Unit ={
      votes = votes + 1
    }

    def decrement(): Unit = {
      votes = votes - 1
    }

    override def toString(): String = "songName = " + songName + ", songArtist = "+songArtist+ ", songURI = "+uri+ " votes = "+votes

  }






}

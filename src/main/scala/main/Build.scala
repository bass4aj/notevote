package main

object Build {

  def main(args: Array[String]): Unit = {
    val PF = new PF()
    val query = new PF.query()
    var rooms = query.findObjects("jonesy716151")
    for( i <- 0 to rooms.length-1 ){
      println(rooms.head)
      if(rooms.length == 1){
        return
      }
      rooms = rooms.tail
    }
    println(rooms.head)
    println("Hello World")
  }

}
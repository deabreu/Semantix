case class Result(uniqueHosts: Int,
                  total404: Int,
                  top5URL404: Array[String],
                  number404PerDay: Map[Int, Int],
                  returnedBytes: Int)

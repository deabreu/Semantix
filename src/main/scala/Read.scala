import org.apache.spark.sql._
case object Read {
  val pattern: scala.util.matching.Regex = """(.*) - - \[(.*):(.*):(.*):(.*)\] "(.*)" ([0-9]*) ([0-9]*|-)""".r

  def getDF(textfile: String)(implicit spark: SparkSession): DataFrame = {
    import spark.implicits._

    val sc = spark.sparkContext
    val data = sc.textFile(textfile).map(line => {
      val values = pattern.findAllIn(line)
      val day: Int = values.group(2).toInt
      val caller:String = values.group(1)
      val request: String = values.group(6)
      val code: Int = values.group(7).toInt
      val bytes: Int = values.group(8).toInt
      (caller, day, request, code, bytes)
    }).toDF("Caller", "Day", "Request", "Code", "Bytes_sent" )
    data
  }
}

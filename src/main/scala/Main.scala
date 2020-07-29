import org.apache.spark.sql.SparkSession

object Main extends App {
  implicit val session = SparkSession
    .builder()
    .appName("Semantix Challenge")
    .config("spark.master", "local")
    .getOrCreate

  val baseDir = args(0)

  val processList = if (baseDir.isEmpty || baseDir.isBlank)
    Array.empty
  else {
      val junFile = "/source/NASA_access_log_Jul95"
      val augFile = "/source/NASA_access_log_Aug95"
      Array(junFile, augFile).map(baseDir+_)
  }

  for (process <- processList) {
    val localDF = Read.getDF(process)
    val localResult = Process.processAll(localDF)
    DisplayResults.onScreen(localResult)
  }
}

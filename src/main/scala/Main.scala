import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object Main extends App {
  val conf = new SparkConf().setAppName("Semantix Challenge")
  val sc = new SparkContext(conf)

  implicit val session = SparkSession.builder.config(conf).getOrCreate

  val dfJun = Read.getDF(args(0))

  val dfAug = Read.getDF(args(1))


}

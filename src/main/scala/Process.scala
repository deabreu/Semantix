import org.apache.spark.sql.{DataFrame, SparkSession}

object Process {

  def getNumberOfUniqueHosts(inDF: DataFrame): Integer = inDF.dropDuplicates("Caller").count.toInt

  def getNumbersOfError404(inDF: DataFrame)(implicit spark: SparkSession): Integer = {
    import spark.implicits._
    inDF.select("Code").where($"Code" === 404).count.toInt
  }

  def getTop5URLforError404(inDF: DataFrame)(implicit spark: SparkSession): Array[String] = {
    import spark.implicits._
    inDF.groupBy($"Request").count().orderBy($"Request".desc).take(5).map(_.getAs[String](0))
  }

  def getErrors404PerDay(inDF: DataFrame)(implicit spark: SparkSession): Map[Int,Int] = {
    import spark.implicits._
    inDF.groupBy($"Day").count().as("Count").
      rdd.map(r => r(0).asInstanceOf[Int] -> r(1).asInstanceOf[Int]).collect().toMap
  }

  def getTotalReturnedBytes(inDF: DataFrame)(implicit spark: SparkSession): Int = inDF.
    selectExpr("SUM (Bytes)").rdd.map(r => r(0).asInstanceOf[Int]).collect().head

  def processAll(inDF: DataFrame)(implicit spark: SparkSession): Result = {
    val r1 = getNumberOfUniqueHosts(inDF)
    val r2 = getNumbersOfError404(inDF)
    val r3 = getTop5URLforError404(inDF)
    val r4 = getErrors404PerDay(inDF)
    val r5 = getTotalReturnedBytes(inDF)

    Result(r1, r2, r3, r4, r5)
  }
}

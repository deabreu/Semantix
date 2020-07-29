object DisplayResults {

  def onScreen(in: Result): Unit = {
    println(s"Número de hosts únicos: ${in.uniqueHosts}.")
    println(s"Total de erros 404: ${in.total404}.")
    println(s"URLs que causaram maior número de códigos 404 :")
    for (url <- in.top5URL404) println(s"  ###  ${url}")
    println("Quantidade de erros 404 por dia:")
    for (quant <- in.number404PerDay) println(s"Dia ${quant._1} -- ${quant._2} erros.")
    println(s"Quantidade de bytes retornados: ${in.returnedBytes}")
  }

}

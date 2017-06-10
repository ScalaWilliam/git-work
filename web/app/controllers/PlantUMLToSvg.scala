package controllers

/**
  * Created by william on 10/6/17.
  */
object PlantUMLToSvg {
  def apply(uml: String): String = {
    import net.sourceforge.plantuml.FileFormat
    import net.sourceforge.plantuml.FileFormatOption
    import net.sourceforge.plantuml.SourceStringReader
    import java.io.ByteArrayOutputStream
    import java.nio.charset.Charset
    var source = "@startuml\n"
    source += "Bob -> Alice : hello\n"
    source += "@enduml\n"

    val reader = new SourceStringReader(source)
    val os = new ByteArrayOutputStream
    // Write the first image to "os"
    val desc = reader.generateImage(os, new FileFormatOption(FileFormat.SVG))
    os.close()

    // The XML is stored into svg
    val svg = new String(os.toByteArray, Charset.forName("UTF-8"))
    svg
  }
}

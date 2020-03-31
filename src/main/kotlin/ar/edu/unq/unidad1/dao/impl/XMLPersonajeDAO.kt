package ar.edu.unq.unidad1.dao.impl

import ar.edu.unq.unidad1.dao.PersonajeDAO
import ar.edu.unq.unidad1.modelo.Item
import ar.edu.unq.unidad1.modelo.Personaje
import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.xml.StaxDriver
import java.io.FileOutputStream
import java.io.IOException

/**
 * Esta implementacion de [PersonajeDAO] persistirá toda la agregación
 * del [Personaje] (es decir, el [Personaje] y sus [Item])
 * en un archivo binario
 *
 */
class XMLPersonajeDAO : BaseFileDAO("xml"), PersonajeDAO {
    private val xstream: XStream
    override fun guardar(personaje: Personaje) {
        val dataFile = getStorage(personaje.nombre)
        deleteIfExists(dataFile)
        try {
            FileOutputStream(dataFile).use { stream -> xstream.toXML(personaje, stream) }
        } catch (e: IOException) {
            throw RuntimeException("No se puede guardar " + personaje.nombre, e)
        }
    }

    override fun recuperar(nombre: String): Personaje? {
        val dataFile = getStorage(nombre)
        return if (!dataFile.exists()) {
            // No existe el personaje
            null
        } else try {
            xstream.fromXML(dataFile) as Personaje
        } catch (e: Exception) {
            throw RuntimeException("No se puede recuperar $nombre", e)
        }
    }

    init {
        xstream = XStream(StaxDriver())
        xstream.processAnnotations(Personaje::class.java)
        xstream.processAnnotations(Item::class.java)
    }
}
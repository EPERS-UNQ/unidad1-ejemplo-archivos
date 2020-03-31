package ar.edu.unq.unidad1.dao.impl

import ar.edu.unq.unidad1.dao.PersonajeDAO
import ar.edu.unq.unidad1.modelo.Personaje
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import java.io.File
import java.io.IOException

/**
 * Esta implementacion de [PersonajeDAO] persistirá toda la agregación
 * del [Personaje] (es decir, el [Personaje] y sus [Item])
 * en un archivo binario
 *
 */
class JSONPersonajeDAO : BaseFileDAO("json"), PersonajeDAO {
    private val mapper: ObjectMapper = ObjectMapper()

    override fun guardar(personaje: Personaje) {
        val dataFile: File = getStorage(personaje.nombre)
        deleteIfExists(dataFile)
        try {
            mapper.writeValue(dataFile, personaje)
        } catch (e: IOException) {
            throw RuntimeException("No se puede guardar " + personaje.nombre, e)
        }
    }

    override fun recuperar(nombre: String): Personaje? {
        val dataFile: File = getStorage(nombre)
        return if (!dataFile.exists()) {
            null
        } else try {
            mapper.readValue(dataFile, Personaje::class.java)
        } catch (e: IOException) {
            throw RuntimeException("No se puede recuperar $nombre", e)
        }
    }

    init {
        mapper.enable(SerializationFeature.INDENT_OUTPUT)
    }
}
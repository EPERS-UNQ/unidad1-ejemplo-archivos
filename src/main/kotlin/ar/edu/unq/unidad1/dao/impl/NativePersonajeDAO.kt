package ar.edu.unq.unidad1.dao.impl

import ar.edu.unq.unidad1.dao.PersonajeDAO
import ar.edu.unq.unidad1.modelo.Personaje
import java.io.*

/**
 * Esta implementacion de [PersonajeDAO] persistirá toda la agregación
 * del [Personaje] (es decir, el [Personaje] y sus [Item])
 * en un archivo binario
 *
 */
class NativePersonajeDAO : BaseFileDAO("bin"), PersonajeDAO {
    override fun guardar(personaje: Personaje) {
        val dataFile = getStorage(personaje.nombre)
        deleteIfExists(dataFile)

        //Resource block - asegura que stream.close() se llama en todos los casos
        try {
            ObjectOutputStream(FileOutputStream(dataFile)).use { stream -> stream.writeObject(personaje) }
        } catch (e: IOException) {
            throw RuntimeException("No se puede guardar " + personaje.nombre, e)
        }
    }

    override fun recuperar(nombre: String): Personaje? {
        val dataFile = getStorage(nombre)
        if (!dataFile.exists()) {
            // No existe el personaje
            return null
        }
        try {
            ObjectInputStream(FileInputStream(dataFile)).use { stream -> return stream.readObject() as Personaje }
        } catch (e: IOException) {
            throw RuntimeException("No se puede recuperar $nombre", e)
        } catch (e: ClassNotFoundException) {
            throw RuntimeException("No se puede recuperar $nombre", e)
        }
    }
}
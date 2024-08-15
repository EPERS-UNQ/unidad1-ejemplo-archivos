package ar.edu.unq.unidad1.dao.impl;

import ar.edu.unq.unidad1.dao.PersonajeDAO;
import ar.edu.unq.unidad1.modelo.Personaje;

import java.io.*;

/**
 * Esta implementacion de {@link PersonajeDAO} persistirá toda la agregación
 * del {@link Personaje} (es decir, el {@link Personaje} y sus {@link Item})
 * en un archivo binario
 */
public class NativePersonajeDAO extends BaseFileDAO implements PersonajeDAO {

    public NativePersonajeDAO() {
        super("bin");
    }

    @Override
    public void guardar(Personaje personaje) {
        var dataFile = getStorage(personaje.getNombre());
        deleteIfExists(dataFile);

        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            stream.writeObject(personaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Personaje recuperar(String nombre) {
        var dataFile = getStorage(nombre);
        if (!dataFile.exists()) {
            // No existe el personaje
            return null;
        }

        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(dataFile))) {
            return (Personaje) stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
package ar.edu.unq.unidad1.dao.impl;

import ar.edu.unq.unidad1.dao.PersonajeDAO;
import ar.edu.unq.unidad1.modelo.Personaje;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

/**
 * Esta implementacion de {@link PersonajeDAO} persistirá toda la agregación
 * del {@link Personaje} (es decir, el {@link Personaje} y sus {@link Item})
 * en un archivo JSON
 */
public class JSONPersonajeDAO extends BaseFileDAO implements PersonajeDAO {
    private final ObjectMapper mapper;

    public JSONPersonajeDAO() {
        super("json");
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public void guardar(Personaje personaje) {
        var dataFile = getStorage(personaje.getNombre());
        deleteIfExists(dataFile);
        try {
            mapper.writeValue(dataFile, personaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Personaje recuperar(String nombre) {
        var dataFile = getStorage(nombre);
        if (!dataFile.exists()) {
            return null;
        }
        try {
            return mapper.readValue(dataFile, Personaje.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
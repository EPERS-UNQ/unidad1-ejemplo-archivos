package ar.edu.unq.unidad1.dao.impl;

import ar.edu.unq.unidad1.dao.PersonajeDAO;
import ar.edu.unq.unidad1.modelo.Item;
import ar.edu.unq.unidad1.modelo.Personaje;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Esta implementacion de {@link PersonajeDAO} persistirá toda la agregación
 * del {@link Personaje} (es decir, el {@link Personaje} y sus {@link Item})
 * en un archivo XML
 */
public class XMLPersonajeDAO extends BaseFileDAO implements PersonajeDAO {
    private final XStream xstream;

    public XMLPersonajeDAO() {
        super("xml");
        xstream = new XStream(new StaxDriver());
        xstream.allowTypesByRegExp(new String[]{".*"});
        xstream.processAnnotations(Personaje.class);
        xstream.processAnnotations(Item.class);
    }

    @Override
    public void guardar(Personaje personaje) {
        var dataFile = getStorage(personaje.getNombre());
        deleteIfExists(dataFile);

        try (FileOutputStream stream = new FileOutputStream(dataFile)) {
            xstream.toXML(personaje, stream);
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
        return (Personaje) xstream.fromXML(dataFile);
    }
}
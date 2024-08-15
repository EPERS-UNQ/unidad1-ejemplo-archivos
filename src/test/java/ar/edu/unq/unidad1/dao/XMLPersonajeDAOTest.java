package ar.edu.unq.unidad1.dao;

import ar.edu.unq.unidad1.dao.impl.XMLPersonajeDAO;

public class XMLPersonajeDAOTest extends PersonajeDAOTest {
    @Override
    protected PersonajeDAO createDAO() {
        return new XMLPersonajeDAO();
    }
}
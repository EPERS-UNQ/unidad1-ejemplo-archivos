package ar.edu.unq.unidad1.dao;

import ar.edu.unq.unidad1.dao.impl.NativePersonajeDAO;

public class NativePersonajeDAOTest extends PersonajeDAOTest {
    @Override
    protected PersonajeDAO createDAO() {
        return new NativePersonajeDAO();
    }
}
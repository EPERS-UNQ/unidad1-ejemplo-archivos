package ar.edu.unq.unidad1.dao;

import ar.edu.unq.unidad1.dao.impl.JSONPersonajeDAO;

public class JSONPersonajeDAOTest extends PersonajeDAOTest {
    @Override
    protected PersonajeDAO createDAO() {
        return new JSONPersonajeDAO();
    }
}
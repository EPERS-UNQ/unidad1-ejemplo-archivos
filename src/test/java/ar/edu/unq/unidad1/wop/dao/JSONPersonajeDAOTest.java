package ar.edu.unq.unidad1.wop.dao;

import ar.edu.unq.unidad1.wop.dao.impl.JSONPersonajeDAO;


public class JSONPersonajeDAOTest extends PersonajeDAOTest {

	@Override
	public PersonajeDAO createDAO() {
		return new JSONPersonajeDAO();
	}
}

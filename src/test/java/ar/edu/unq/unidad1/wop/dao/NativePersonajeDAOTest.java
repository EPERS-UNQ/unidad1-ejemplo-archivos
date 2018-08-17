package ar.edu.unq.unidad1.wop.dao;

import ar.edu.unq.unidad1.wop.dao.impl.NativePersonajeDAO;


public class NativePersonajeDAOTest  extends PersonajeDAOTest {

	@Override
	public PersonajeDAO createDAO() {
		return new NativePersonajeDAO();
	}
}

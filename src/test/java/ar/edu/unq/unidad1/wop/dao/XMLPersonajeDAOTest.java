package ar.edu.unq.unidad1.wop.dao;

import ar.edu.unq.unidad1.wop.dao.impl.XMLPersonajeDAO;


public class XMLPersonajeDAOTest  extends PersonajeDAOTest {

	@Override
	public PersonajeDAO createDAO() {
		return new XMLPersonajeDAO();
	}
}

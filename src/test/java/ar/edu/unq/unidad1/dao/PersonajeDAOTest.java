package ar.edu.unq.unidad1.dao;

import ar.edu.unq.unidad1.modelo.Item;
import ar.edu.unq.unidad1.modelo.Personaje;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class PersonajeDAOTest {
    private final PersonajeDAO dao;
    private Personaje maguito;

    public PersonajeDAOTest() {
        this.dao = createDAO();
    }

    @BeforeAll
    public  void crearModelo() {
        maguito = new Personaje("Maguito");
        maguito.setPesoMaximo(15);
        maguito.setVida(198);
        maguito.setXp(2500);
        maguito.recoger(new Item("Tunica gris", 1));
        maguito.recoger(new Item("Baculo gris", 5));
    }

    @Test
    public void alGuardarYLuegoRecuperarSeObtienenObjetosSimilares() {
        dao.guardar(maguito);

        Personaje otroMaguito = dao.recuperar("Maguito");
        Assertions.assertEquals(maguito.getNombre(), otroMaguito.getNombre());
        Assertions.assertEquals(maguito.getPesoMaximo(), otroMaguito.getPesoMaximo());
        Assertions.assertEquals(maguito.getVida(), otroMaguito.getVida());
        Assertions.assertEquals(maguito.getXp(), otroMaguito.getXp());
        Assertions.assertEquals(maguito.getInventario().size(), otroMaguito.getInventario().size());

        Assertions.assertNotSame(maguito, otroMaguito);
    }

    protected abstract PersonajeDAO createDAO();
}
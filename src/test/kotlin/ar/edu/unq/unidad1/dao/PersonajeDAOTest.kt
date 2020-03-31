package ar.edu.unq.unidad1.dao

import ar.edu.unq.unidad1.modelo.Item
import ar.edu.unq.unidad1.modelo.Personaje
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * TODO: Esta es una implementacion sencilla que tiene como objeto
 * mostrar algunas formas de persistir en archivos (usando distintas
 * herramientas para guardar en distintos formatos)
 *
 * Entre todas las distintas implementaciones de test el codigo es
 * exactamente el mismo. Se mantiene asi solo para mostrar el funcionamiento
 * sin demasiada complicacion, pero el codigo repetido NO deberia existir.
 *
 */
abstract class PersonajeDAOTest {
    private val dao: PersonajeDAO
    lateinit var maguito: Personaje

    init {
        dao = createDAO()
    }

    @Before
    fun crearModelo() {
        maguito = Personaje("Maguito")
        maguito.pesoMaximo = 15
        maguito.vida = 198
        maguito.xp = 2500
        maguito.recoger(Item("Tunica gris", 1))
        maguito.recoger(Item("Baculo gris", 5))
    }

    @Test
    fun al_guardar_y_luego_recuperar_se_obtiene_objetos_similares() {
        dao.guardar(maguito)

        //Los personajes son iguales
        val otroMaguito = dao.recuperar("Maguito")
        Assert.assertEquals(maguito.nombre, otroMaguito!!.nombre)
        Assert.assertEquals(maguito.pesoMaximo, otroMaguito.pesoMaximo)
        Assert.assertEquals(maguito.vida, otroMaguito.vida)
        Assert.assertEquals(maguito.xp, otroMaguito.xp)
        Assert.assertEquals(maguito.inventario.size.toLong(), otroMaguito.inventario.size.toLong()
        )

        //Pero no son el mismo objeto =(
        //A esto nos referimos con "perdida de identidad"
        Assert.assertTrue(maguito != otroMaguito)
    }

    abstract fun createDAO(): PersonajeDAO
}
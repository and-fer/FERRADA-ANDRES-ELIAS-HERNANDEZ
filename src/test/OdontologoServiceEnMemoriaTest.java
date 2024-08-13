package test;

import dao.Impl.OdontologoDaoEnMemoria;
import model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceEnMemoriaTest {

    static final Logger logger = Logger.getLogger(OdontologoService.class);
    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoEnMemoria());

    @Test
    @DisplayName("Testear inserción de una nuevo Odontólogo en memoria")

    void guardarOdontologoTest(){
        Odontologo odontologo = new Odontologo(123, "Francisco", "Asis");
        Odontologo odontologoGuardadoMemoria = odontologoService.guardarOdontologo(odontologo);

        assertNotNull(odontologoGuardadoMemoria.getId());
        assertEquals("Francisco", odontologoGuardadoMemoria.getNombre());
    }

    @Test
    @DisplayName("Testear que se enlisten todos los elementos del Array List")

    void testListarTodos(){
        Odontologo odontologo1 = new Odontologo(12345, "Juan", "Pérez");
        Odontologo odontologo2 = new Odontologo(67890, "Ana", "Gómez");

        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);

        List<Odontologo> odontologos = odontologoService.listarTodos();

        assertFalse(odontologos.isEmpty());

    }

}
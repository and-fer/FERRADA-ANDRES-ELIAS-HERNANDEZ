package test;

import dao.Impl.OdontologoDaoH2;
import model.Odontologo;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    static final Logger logger = Logger.getLogger(OdontologoServiceTest.class);
    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
    
    @BeforeAll
    static void cargarTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./clinica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Testear que Odontólogo se ha guardado en base de datos")

    void testInsertarDatos(){
        Odontologo odontologo = new Odontologo(666, "María", "Perez");
        Odontologo odontologoGuardadoDB = odontologoService.guardarOdontologo(odontologo);

        assertNotNull(odontologoGuardadoDB.getId());
        assertTrue(odontologoGuardadoDB.getId() > 0);
        assertEquals(3, odontologoGuardadoDB.getId());
    }

    @Test
    @DisplayName("Testear enlistado de todos los elementos")

    void testListarTodos(){
        List<Odontologo> odontologos = odontologoService.listarTodos();

        assertFalse(odontologos.isEmpty());

    }

}
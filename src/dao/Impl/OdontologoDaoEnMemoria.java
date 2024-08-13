package dao.Impl;

import dao.IDao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoEnMemoria implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDaoEnMemoria.class);
    List<Odontologo> odontologos = new ArrayList<>();

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        odontologo.setId(odontologos.size()+1);

        odontologos.add(odontologo);

        logger.info("Odontólogo ha sido guardado con éxito: " + odontologo);

        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        return new ArrayList<>(odontologos);
    }
}

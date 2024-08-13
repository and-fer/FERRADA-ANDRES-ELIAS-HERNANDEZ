package service;

import dao.IDao;
import model.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> OdontologoIdao;

    public OdontologoService(IDao<Odontologo> odontologoIdao) {
        OdontologoIdao = odontologoIdao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return OdontologoIdao.registrar(odontologo);
    }

    public List<Odontologo> listarTodos(){
        return OdontologoIdao.listarTodos();
    }
    
}

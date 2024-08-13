package dao.Impl;

import dao.IDao;
import db.H2Connection;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);
    private static final String INSERT = "INSERT INTO ODONTOLOGO VALUES(DEFAULT, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM ODONTOLOGO";
    private static final String SELECT_ID = "SELECT * FROM ODONTOLOGO WHERE ID = ?";

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Odontologo odontologoARetornar = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            preparedStatement.executeUpdate();
            connection.commit();

            resultSet = preparedStatement.getGeneratedKeys();
            Integer id = null;

            while(resultSet.next()){
                id = resultSet.getInt(1);
                odontologoARetornar = new Odontologo(id, odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());
            }


            logger.info("Odontólogo guardado con éxito: " + odontologoARetornar);

        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();

            if(connection != null){
                try{
                    connection.rollback();
                    logger.info("La transacción se ha revertido");
                } catch(SQLException ex) {
                    logger.error("No se ha podido ejecutar el rollback", ex);
                }
            }
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("No se ha podido cerrar la conexión", e);
            }
        }

        return odontologoARetornar;
    }

    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();
        Odontologo odontologo = null;

        try {
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);


            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                Integer matricula = resultSet.getInt(2);
                String nombre = resultSet.getString(3);
                String apellido = resultSet.getString(4);

                odontologo = new Odontologo(id, matricula, nombre, apellido);

                logger.info(odontologo);

                odontologos.add(odontologo);
            }

        }catch (Exception e){
            logger.error("No se ha podido realizar la consulta: ", e);

        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("No se ha podido cerrar la conexión: ", e);
            }

        }

        return odontologos;
    }

}

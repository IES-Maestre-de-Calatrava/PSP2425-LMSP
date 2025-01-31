package org.example;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionOracle implements Serializable {
    private final String driver;
    private final String urlconnection;
    private Properties propiedades = null;
    private static DatabaseMetaData dbmd;

    private static ConexionOracle instance;

    private Connection conexion;
    private PreparedStatement preparedStatement;

    public ConexionOracle() {
        this.driver = "oracle.jdbc.driver.OracleDriver";
        this.urlconnection = "jdbc:oracle:thin:@localhost:1521:FREE";
    }

    public static ConexionOracle getInstance() {
        if (instance == null) {
            instance = new ConexionOracle();
        }
        return instance;
    }

    public void abrirConexion(){
        try {
            this.propiedades = new Properties();
            this.propiedades.setProperty("user","empresapsp");
            this.propiedades.setProperty("password","empresapsp");
            this.propiedades.setProperty("bbdd","free");

            Class.forName(driver);
            this.conexion = DriverManager.getConnection(urlconnection, propiedades);

            dbmd = conexion.getMetaData();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cerrarConexion() {
        try {
            this.conexion.close();
        } catch (SQLException e) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Optional<ResultSet> insertar(String insertarSQL, Object ... parametros) throws SQLException {
        preparedStatement = conexion.prepareStatement(insertarSQL, PreparedStatement.RETURN_GENERATED_KEYS);

        for (int i = 0; i < parametros.length; i++) {
            preparedStatement.setObject(i+1, parametros[i]);
        }

        preparedStatement.executeUpdate();
        return Optional.of(preparedStatement.getGeneratedKeys());
    }

    private ResultSet ejecutarConsulta(String consultaSQL, Object ... parametros) throws SQLException {
        preparedStatement = conexion.prepareStatement(consultaSQL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        for (int i = 0; i < parametros.length; i++) {
            preparedStatement.setObject(i+1, parametros[i]);
        }

        return preparedStatement.executeQuery();
    }

    public Optional<ResultSet> select(String consultaSQL, Object ... parametros){
        try {
            return Optional.of(ejecutarConsulta(consultaSQL, parametros));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Optional<ResultSet> insert(String insertSQL, Object... parametros) throws SQLException{


        preparedStatement = conexion.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS); // PreparedStatement.RETURN_GENERATED_KEYS devolvia la clave generada que se autoincrementaba

        // "insert into Departamentos values (?,?,?)"
        //                                    1,2,3
        // parametros = [1, "Informatica", "Ciudad Real"]
        //              0,        1,             2

        for (int i = 0; i < parametros.length; i++) {
            preparedStatement.setObject(i+1, parametros[i]);
        }

        // "insert into Departamentos values (1,"informatica","Ciudad Real")",

        preparedStatement.executeUpdate();

        return Optional.of(preparedStatement.getGeneratedKeys());
    }


}

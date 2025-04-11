package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionDB {
    //Preparo los la URL donde se ubica la base de datos, el usuario y la contraseña
    private static final String URL = "jdbc:mysql://yamanote.proxy.rlwy.net:37467/railway";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "aVOfFnLPpcgbSrMsJHCsAGIsSGMDXsLr";
    private static Connection conexion = null;

    private conexionDB() {}

    //Abro la conexion
    public static Connection getConexion() throws SQLException {
        try{
            if(conexion == null || conexion.isClosed()){
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("Conexion establecida.");
            }
        }catch(SQLException e){
            System.out.println("Error al conectar con la base de datos");
            throw e;
        }
        return conexion;
    }

    //Cierro la conexion
    public static void cerrarConexion() throws SQLException {
        try{
            if(conexion != null && !conexion.isClosed()){
                conexion.close();
                System.out.println("Conexion cerrada");
            }
        }catch(SQLException e){
            System.out.println("Error al cerrar conexion");
            throw e;
        }
    }
}
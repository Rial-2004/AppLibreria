import dao.libroDAO;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) {
        libroDAO libdao= new libroDAO();
        try{
            for(String fila : libdao.listarTodos()) System.out.println(fila);
        }catch(Exception e){
            System.out.println("Fallo al ejecutar el programa");
        }
    }
}

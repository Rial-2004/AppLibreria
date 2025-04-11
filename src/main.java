import dao.libroDAO;


public class main {
    public static void main(String[] args) {
        libroDAO libdao= new libroDAO();
        try{
            for(String fila : libdao.listarTodos()) System.out.println(fila);
            libdao.cambiarDisponibilidad(15,true);
            for(String fila : libdao.listarTodos()) System.out.println(fila);

        }catch(Exception e){
            System.out.println("Fallo al ejecutar el programa");
        }
    }
}

package dao;

import modelo.libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class libroDAO {
    public void insertLibro(libro libro) throws SQLException {

        Connection con = conexionDB.getConexion();
        if(!buscaPorIsbn(libro.getIsbn()).equals("")){
            String consulta = "insert into libros (titulo,autor,genero,isbn,paginas,anio,disponible) values(?,?,?,?,?,?,?)";

            try(PreparedStatement ps = con.prepareStatement(consulta)) {
                ps.setString(1, libro.getTitulo());
                ps.setString(2, libro.getAutor());
                ps.setString(3, libro.getGenero());
                ps.setString(4, libro.getIsbn());
                ps.setInt(5, libro.getPaginas());
                ps.setInt(6, libro.getAnio());
                ps.setBoolean(7, libro.isDisponible());

                ps.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException("Error al insertar libro en la base de datos");
            }
        }else{
            System.out.println("El libro ya existe");
        }

    }
    public List<String> listarTodos() throws SQLException {
        List<String> lista = new ArrayList<>();
        Connection con = conexionDB.getConexion();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from libros");
        while (rs.next()) {
            libro l = convertirObjeto(rs);
            lista.add("Id: "+rs.getInt("id")+" "+l);
        }
        return lista;
    }
    public void eliminarLibro(int id) throws SQLException {
        Connection con = conexionDB.getConexion();
        String consulta = "delete from libros where id = ?";
        try(PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Libro eliminado");
        }catch(SQLException e) {
            throw new SQLException("Error al eliminar libro");
        }
    }
    public List<libro> buscarPorTitulo(String tituloLibro) throws SQLException {
        List<libro> lista = new ArrayList<>();
        Connection con = conexionDB.getConexion();
        String consulta = "select * from libros where titulo = ?";
        try(PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, tituloLibro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                while (rs.next()) {
                    libro l = convertirObjeto(rs);
                }
                return lista;
            }
            return lista;
        }catch(SQLException e) {
            throw new SQLException("Error al buscar libros en la base de datos");
        }
    }
    public List<libro> buscaPorGenero(String generoLibro) throws SQLException {
        List<libro> lista = new ArrayList<>();
        Connection con = conexionDB.getConexion();
        String consulta = "select * from libros where genero = ?";
        return obtenerResultado(consulta,generoLibro ,con);
    }
    public List<libro> buscaPorAnio(String anioLibro) throws SQLException {
        List<libro> lista = new ArrayList<>();
        Connection con = conexionDB.getConexion();
        String consulta = "select * from libros where anio = ?";
        return obtenerResultado(consulta,anioLibro ,con);
    }
    public String buscaPorIsbn(String isbn) throws SQLException {
        Connection con = conexionDB.getConexion();
        String consulta = "select * from libros where isbn = ?";
        try(PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                libro l = new libro(
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("genero"),
                        rs.getString("isbn"),
                        rs.getInt("paginas"),
                        rs.getInt("anio"),
                        rs.getBoolean("disponible")
                );
                return "Id: "+rs.getInt("id")+" "+l;
            }
            return "";
        }
    }
    public List<libro> librosDisponible() throws SQLException {
        List<libro> lista = new ArrayList<>();
        Connection con = conexionDB.getConexion();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from libros where disponible = true");
        while (rs.next()) {
            libro l = convertirObjeto(rs);
            lista.add(l);
        }
        return lista;
    }
    public List<libro> obtenerResultado(String consulta, String x, Connection con) throws SQLException {
        List<libro> lista = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, x);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                libro l = convertirObjeto(rs);
                lista.add(l);
            }
            return lista;
        }catch (SQLException e) {
            throw new SQLException("Error en la consulta");
        }
    }
    public libro convertirObjeto(ResultSet rs) throws SQLException {
        return new libro(
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getString("genero"),
                rs.getString("isbn"),
                rs.getInt("paginas"),
                rs.getInt("anio"),
                rs.getBoolean("disponible")
        );
    }
    public void cambiarDisponibilidad(int id, boolean x) throws SQLException {
        Connection con = conexionDB.getConexion();
        String consulta = "update libros set disponible = ? where id = ?";
        try(PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setBoolean(1, x);
            ps.setInt(2, id);
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new SQLException("Error al cambiar el disponibilidad");
        }
    }
}
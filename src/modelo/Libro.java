package modelo;

public class Libro {
    private String titulo;
    private String autor;
    private String genero;
    private String isbn;
    private int paginas;
    private int anio;
    private boolean disponible;

    public Libro(String titulo, String autor, String genero, String isbn, int paginas, int anio, boolean disponible) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.isbn = isbn;
        this.paginas = paginas;
        this.anio = anio;
        this.disponible = disponible;
    }

    public String getTitulo() {return titulo;}
    public String getAutor() {return autor;}
    public String getGenero() {return genero;}
    public String getIsbn() {return isbn;}
    public int getPaginas() {return paginas;}
    public int getAnio() {return anio;}
    public boolean isDisponible() {return disponible;}


    @Override
    public String toString() {
        return "libro{titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", genero='" + genero + '\'' +
                ", isbn='" + isbn + '\'' +
                ", paginas=" + paginas +
                ", anio=" + anio +
                ", disponible=" + disponible +
                '}';
    }
}
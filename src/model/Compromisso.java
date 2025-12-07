package src.model;

import java.sql.Date;
import java.sql.Time;

public class Compromisso {
    private int id;
    private String titulo;
    private Date data;
    private Time hora;
    private int contatoId;
    private int categoriaId;

    public Compromisso() {}

    public Compromisso(String titulo, Date data, Time hora, int contatoId, int categoriaId) {
        this.titulo = titulo;
        this.data = data;
        this.hora = hora;
        this.contatoId = contatoId;
        this.categoriaId = categoriaId;
    }

    public Compromisso(int id, String titulo, Date data, Time hora, int contatoId, int categoriaId) {
        this.id = id;
        this.titulo = titulo;
        this.data = data;
        this.hora = hora;
        this.contatoId = contatoId;
        this.categoriaId = categoriaId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public Time getHora() { return hora; }
    public void setHora(Time hora) { this.hora = hora; }

    public int getContatoId() { return contatoId; }
    public void setContatoId(int contatoId) { this.contatoId = contatoId; }

    public int getCategoriaId() { return categoriaId; }
    public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }

    @Override
    public String toString() {
        return "Compromisso [id=" + id + ", titulo=" + titulo + ", data=" + data + ", hora=" + hora +
               ", contatoId=" + contatoId + ", categoriaId=" + categoriaId + "]";
    }
}
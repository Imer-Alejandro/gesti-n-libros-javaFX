package com.example.bookstorage;

public class Libro {

    public static int countBook = 1;
    String nombre= "";
    String autor="";
    String categoria="";
    String description="";

    String tema="";
    String paginas = "";
    int id = 0;

    public Libro(String nombre,String autor,String categoria,String description, String tema, String paginas){
        this.nombre=nombre;
        this.autor=autor;
        this.categoria=categoria;
        this.description=description;
        this.paginas=paginas;
        this.tema=tema;
        this.id = countBook++;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public String getNombre(){
        return nombre;
    }


    public void setAutor(String autor){
        this.autor=autor;
    }
    public String getAutor(){
        return autor;
    }

    public void setCategoria(String categoria){
        this.categoria=categoria;
    }

    public String getCategoria(){
        return categoria;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getDescription(){
        return description;
    }

    public void setTema(String tema){
        this.tema =tema;
    }


    public void  setPaginas(String paginas){
        this.paginas=paginas;
    }

    public String getPaginas(){
        return paginas;
    }

    public int getId(){
        return id;
    }

    public String getTema(){
        return tema;
    }

}

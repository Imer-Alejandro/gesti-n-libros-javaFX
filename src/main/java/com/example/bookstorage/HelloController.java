package com.example.bookstorage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.bookstorage.Libro;
import java.util.ArrayList;
import java.util.Objects;

import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    AnchorPane contenViewCard,contenResult;
    @FXML
     Pane formCreateBook,formEdit,viewResult;

    //input de registro de libro
    @FXML
    TextField nombreLib,autorLib,categoriaLib,paginasLib,datoBusquedad;
    @FXML
     TextArea descriptionLib;

    @FXML
    //input de editar libros
    TextField editNombre,editAutor,editCategoria,editPagina;
    @FXML
    TextArea editDescription;
    @FXML
     String tema= "blue";
    @FXML
     int idLibro = 0;
    ArrayList<Libro> listadoLibros = new ArrayList<>();

    public void abrirRegistroLibro(){
        formCreateBook.setVisible(true);
    }

    public void cerrarRegistroLibro(){
        formCreateBook.setVisible(false);
    }
    //eventos para asignar el tema del color al libro
    public void asignarTema1(){
        this.tema="#D96941";
    }
    public void asignarTema2(){
        this.tema="#C2C0A6";
    }
    public void asignarTema3(){
        this.tema="#6A8C69";
    }
    public void asignarTema4(){
        this.tema="#026873";
    }

    public void renderizarElementos(){
        double verticalPosition = 10.0; // Posición vertical inicial
        double verticalSpacing = 10.0; // Espacio vertical entre paneles

        for (Libro librosItem : listadoLibros){

            Pane cardPane = new Pane();
            cardPane.setPrefSize(413, 219);
            cardPane.setLayoutX(29);
            cardPane.setLayoutY(verticalPosition);
            cardPane.setStyle("-fx-background-color: white; -fx-border-color: gray; -fx-border-radius: 3;");

            // Crear el panel superior azul
            Pane bluePane = new Pane();
            bluePane.setPrefSize(413, 85);
            bluePane.setStyle("-fx-background-color: " + librosItem.getTema() + ";");
            cardPane.getChildren().add(bluePane);

            // Añadir el texto "Nombre del libro" en el panel azul
            Label nameLabel = new Label(librosItem.getNombre());
            nameLabel.setLayoutX(14);
            nameLabel.setLayoutY(36);
            nameLabel.setTextFill(javafx.scene.paint.Color.WHITE);
            nameLabel.setStyle("-fx-font-size: 16px;");
            bluePane.getChildren().add(nameLabel);

            // Añadir los otros labels
            Label authorLabel = new Label("Autor:");
            authorLabel.setLayoutX(14);
            authorLabel.setLayoutY(88);

            Label categoryLabel = new Label("Categoría:");
            categoryLabel.setLayoutX(14);
            categoryLabel.setLayoutY(122);

            Label descriptionLabel = new Label("Descripción:");
            descriptionLabel.setLayoutX(14);
            descriptionLabel.setLayoutY(161);

            Label authorValueLabel = new Label(librosItem.getAutor());
            authorValueLabel.setLayoutX(65);
            authorValueLabel.setLayoutY(91);
            authorValueLabel.setTextFill(javafx.scene.paint.Color.web("#909090"));

            Label categoryValueLabel = new Label(librosItem.getCategoria());
            categoryValueLabel.setLayoutX(98);
            categoryValueLabel.setLayoutY(125);
            categoryValueLabel.setTextFill(javafx.scene.paint.Color.web("#909090"));

            Label descriptionValueLabel = new Label(librosItem.getDescription());
            descriptionValueLabel.setLayoutX(104);
            descriptionValueLabel.setLayoutY(164);
            descriptionValueLabel.setTextFill(javafx.scene.paint.Color.web("#909090"));

            Label pagesLabel = new Label("Páginas:");
            pagesLabel.setLayoutX(320);
            pagesLabel.setLayoutY(197);

            Label pagesValueLabel = new Label(librosItem.getPaginas());
            pagesValueLabel.setLayoutX(372);
            pagesValueLabel.setLayoutY(197);
            pagesValueLabel.setTextFill(javafx.scene.paint.Color.web("#909090"));

            // Añadir los labels al contenedor principal
            cardPane.getChildren().addAll(authorLabel, categoryLabel, descriptionLabel,
                    authorValueLabel, categoryValueLabel, descriptionValueLabel,
                    pagesLabel, pagesValueLabel);

            // Crear el menú contextual
            ContextMenu contextMenu = new ContextMenu();

            // Crear el item de menú "Eliminar"
            MenuItem deleteItem = new MenuItem("Eliminar");
            deleteItem.setOnAction((ActionEvent event) -> {
                // Lógica para eliminar el card panel
                for (int i = 0; i < listadoLibros.size(); i++) {
                    // Verificamos si el ID del usuario coincide con el ID buscado
                    if (listadoLibros.get(i).getId() == librosItem.getId()) {
                        // Eliminamos el usuario de la lista
                        listadoLibros.remove(i);
                        break; // Salimos del bucle una vez que se haya eliminado el usuario
                    }
                }
                // Limpiamos los elementos del scrollView una vez que se hayan eliminado los usuarios
                contenViewCard.getChildren().clear();

                renderizarElementos();
            });

            // Crear el item de menú "Editar"
            MenuItem editItem = new MenuItem("Editar");
            editItem.setOnAction((ActionEvent event) -> {
                formEdit.setVisible(true);
                editNombre.setText(librosItem.getNombre());
                editAutor.setText(librosItem.getAutor());
                editCategoria.setText(librosItem.getCategoria());
                editDescription.setText(librosItem.getDescription());
                editPagina.setText(librosItem.getPaginas());

                this.idLibro = librosItem.getId();

            });

            // Agregar los items al menú contextual
            contextMenu.getItems().addAll(deleteItem, editItem);

            // Agregar el menú contextual al card panel
            cardPane.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(cardPane, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                } else {
                    contextMenu.hide();
                }
            });
            contenViewCard.getChildren().add(cardPane);
            verticalPosition += cardPane.getPrefHeight() + verticalSpacing;
        }

        contenViewCard.setMinHeight(verticalPosition);


    }

    public void cerrarEditar(){
        formEdit.setVisible(false);
    }
    public void editarLibro(){
        for (Libro listadoLibro : listadoLibros) {
            // Verificamos si el ID del usuario coincide con el ID buscado
            if (listadoLibro.getId() == this.idLibro) {
                //editar el libro
                listadoLibro.setNombre(editNombre.getText());
                listadoLibro.setCategoria(editCategoria.getText());
                listadoLibro.setAutor(editAutor.getText());
                listadoLibro.setTema(this.tema);
                listadoLibro.setDescription(editDescription.getText());
                listadoLibro.setPaginas(editPagina.getText());
                break; // Salimos del bucle una vez que se haya eliminado el usuario
            }
        }
        // Limpiamos los elementos del scrollView una vez que se hayan eliminado los usuarios
        contenViewCard.getChildren().clear();

        cerrarEditar();

        renderizarElementos();
    }
    public void createBook(){
        listadoLibros.add(new Libro(nombreLib.getText(),autorLib.getText(),categoriaLib.getText(),descriptionLib.getText(),tema,paginasLib.getText()));

        formCreateBook.setVisible(false);
        //limpiar entrada luego de crear
        nombreLib.setText("");
        autorLib.setText("");
        categoriaLib.setText("");
        descriptionLib.setText("");
        paginasLib.setText("");

        //llamar la funcion que renderiza los elementos
        this.renderizarElementos();
    }

    public void buscarLibro(){
        viewResult.setVisible(true);
        double verticalPosition = 10.0; // Posición vertical inicial
        double verticalSpacing = 10.0; // Espacio vertical entre paneles

        for (Libro libro : listadoLibros){
            if (Objects.equals(libro.getNombre(), datoBusquedad.getText())){

                Pane cardPane = new Pane();
                cardPane.setPrefSize(413, 219);
                cardPane.setLayoutX(29);
                cardPane.setLayoutY(verticalPosition);
                cardPane.setStyle("-fx-background-color: white; -fx-border-color: gray; -fx-border-radius: 3;");

                // Crear el panel superior azul
                Pane bluePane = new Pane();
                bluePane.setPrefSize(413, 85);
                bluePane.setStyle("-fx-background-color: " + libro.getTema() + ";");
                cardPane.getChildren().add(bluePane);

                // Añadir el texto "Nombre del libro" en el panel azul
                Label nameLabel = new Label(libro.getNombre());
                nameLabel.setLayoutX(14);
                nameLabel.setLayoutY(36);
                nameLabel.setTextFill(javafx.scene.paint.Color.WHITE);
                nameLabel.setStyle("-fx-font-size: 16px;");
                bluePane.getChildren().add(nameLabel);

                // Añadir los otros labels
                Label authorLabel = new Label("Autor:");
                authorLabel.setLayoutX(14);
                authorLabel.setLayoutY(88);

                Label categoryLabel = new Label("Categoría:");
                categoryLabel.setLayoutX(14);
                categoryLabel.setLayoutY(122);

                Label descriptionLabel = new Label("Descripción:");
                descriptionLabel.setLayoutX(14);
                descriptionLabel.setLayoutY(161);

                Label authorValueLabel = new Label(libro.getAutor());
                authorValueLabel.setLayoutX(65);
                authorValueLabel.setLayoutY(91);
                authorValueLabel.setTextFill(javafx.scene.paint.Color.web("#909090"));

                Label categoryValueLabel = new Label(libro.getCategoria());
                categoryValueLabel.setLayoutX(98);
                categoryValueLabel.setLayoutY(125);
                categoryValueLabel.setTextFill(javafx.scene.paint.Color.web("#909090"));

                Label descriptionValueLabel = new Label(libro.getDescription());
                descriptionValueLabel.setLayoutX(104);
                descriptionValueLabel.setLayoutY(164);
                descriptionValueLabel.setTextFill(javafx.scene.paint.Color.web("#909090"));

                Label pagesLabel = new Label("Páginas:");
                pagesLabel.setLayoutX(320);
                pagesLabel.setLayoutY(197);

                Label pagesValueLabel = new Label(libro.getPaginas());
                pagesValueLabel.setLayoutX(372);
                pagesValueLabel.setLayoutY(197);
                pagesValueLabel.setTextFill(javafx.scene.paint.Color.web("#909090"));

                // Añadir los labels al contenedor principal
                cardPane.getChildren().addAll(authorLabel, categoryLabel, descriptionLabel,
                        authorValueLabel, categoryValueLabel, descriptionValueLabel,
                        pagesLabel, pagesValueLabel);

                // Crear el menú contextual
                ContextMenu contextMenu = new ContextMenu();

                // Crear el item de menú "Eliminar"
                MenuItem deleteItem = new MenuItem("Eliminar");
                deleteItem.setOnAction((ActionEvent event) -> {
                    // Lógica para eliminar el card panel
                    for (int i = 0; i < listadoLibros.size(); i++) {
                        // Verificamos si el ID del usuario coincide con el ID buscado
                        if (listadoLibros.get(i).getId() == libro.getId()) {
                            // Eliminamos el usuario de la lista
                            listadoLibros.remove(i);
                            break; // Salimos del bucle una vez que se haya eliminado el usuario
                        }
                    }
                    // Limpiamos los elementos del scrollView una vez que se hayan eliminado los usuarios
                    contenViewCard.getChildren().clear();

                    renderizarElementos();
                });

                // Crear el item de menú "Editar"
                MenuItem editItem = new MenuItem("Editar");
                editItem.setOnAction((ActionEvent event) -> {
                    formEdit.setVisible(true);
                    editNombre.setText(libro.getNombre());
                    editAutor.setText(libro.getAutor());
                    editCategoria.setText(libro.getCategoria());
                    editDescription.setText(libro.getDescription());
                    editPagina.setText(libro.getPaginas());

                    this.idLibro = libro.getId();

                });

                // Agregar los items al menú contextual
                contextMenu.getItems().addAll(deleteItem, editItem);

                // Agregar el menú contextual al card panel
                cardPane.setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                        contextMenu.show(cardPane, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                    } else {
                        contextMenu.hide();
                    }
                });
                contenResult.getChildren().add(cardPane);
                verticalPosition += cardPane.getPrefHeight() + verticalSpacing;

            }
        }
        contenResult.setMinHeight(verticalPosition);
    }

    public void cerrarResult(){
        viewResult.setVisible(false);
    }
}
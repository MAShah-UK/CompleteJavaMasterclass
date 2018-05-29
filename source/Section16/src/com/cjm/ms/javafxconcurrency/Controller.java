package com.cjm.ms.javafxconcurrency;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class Controller {
    private Task<ObservableList<String>> task;

    @FXML
    private ListView listView;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;

    public void initialize() {
        task = new Task<>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                String[] names = {
                        "Tim Buchalka",
                        "Bill Rogers",
                        "Jack Jill",
                        "Joan Andrews",
                        "Mary Johnson",
                        "Bob McDonald"
                };
                ObservableList<String> employees = FXCollections.observableArrayList();
                // Simulates retrieving data from database.
                for (int i = 0; i < names.length; i++) {
                    employees.add(names[i]);
                    updateMessage("Added " + names[i] + " to the list.");
                    updateProgress(i+1, 6);
                    Thread.sleep(200);
                }
//                // This code can be avoided by binding the ListView's itemsProperty to the task's valueProperty.
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        listView.setItems(employees);
//                    }
//                });
                return employees;
            }
        };
        // Binds data between controls and task so that manually updating the controls isn't required.
        listView.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());
        progressLabel.textProperty().bind(task.messageProperty());
    }
    @FXML
    public void buttonPressed() {
        new Thread(task).start();
    }
}

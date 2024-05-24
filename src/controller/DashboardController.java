package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;
import repository.DiscordRepository;

public class DashboardController {

    @FXML
    private TextField nomeSv;

    @FXML
    private TextField membrosSv;

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> cNome;

    @FXML
    private TableColumn<User, Integer> cMembros;

    private ObservableList<User> data;

    private DiscordRepository repoDc;

    public void initialize() {
        cNome.setCellValueFactory(new PropertyValueFactory<>("nomeSv"));
        cMembros.setCellValueFactory(new PropertyValueFactory<>("MembrosSv"));

        data = FXCollections.observableArrayList();
        tableView.setItems(data);
        repoDc = new DiscordRepository();
        carregarDadosSalvos();
    }

    public void carregarDadosSalvos() {
        try (BufferedReader br = new BufferedReader(new FileReader("database.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    User user = new User();
                    user.setId(Integer.parseInt(parts[0]));
                    user.setNomeSv(parts[1]);
                    user.setMembrosSv(Integer.parseInt(parts[2]));
                    data.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cadastrar() {
        System.out.println("Clicou no botao cadastrar");

        // Validação dos campos
        if (nomeSv.getText().isEmpty()) {
            showAlert("Erro", "Insira um nome para o servidor!", Alert.AlertType.ERROR);
            return;
        }

        if (membrosSv.getText().isEmpty()) {
            showAlert("Erro", "Insira a quantidade de membros do servidor!", Alert.AlertType.ERROR);
            return;
        }
        
        try {
            User user = new User();
            user.setNomeSv(nomeSv.getText());
            user.setMembrosSv(Integer.parseInt(membrosSv.getText()));
            repoDc.addUser(user);
            data.add(user);
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Erro de Formato", "O campo membros deve ser um número.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void clearFields() {
        nomeSv.clear();
        membrosSv.clear();
    }

    public void limpar() {
        System.out.println("Clicou no botao limpar");
        clearFields();
    }
}

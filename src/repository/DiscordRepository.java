package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.User;

public class DiscordRepository {

	private List<User> mensagem;
	private File database;

	public DiscordRepository() {
		this.database = new File("database.txt");
		this.mensagem = new ArrayList<>();
		loadfiles();
	}

	// carregar
	private void loadfiles() {
		try (Scanner sc = new Scanner(database)) {
			while (sc.hasNextLine()) {
				String[] data = sc.nextLine().split(";");
				System.out.println(data.length);
				if (data.length >= 3) {
					// 0 -> id, 1 -> nome, 2 -> inicio, 3 -> fim
					User user = new User();
					user.setId(Integer.parseInt(data[0]));
					user.setNomeSv(data[1]);
					user.setMembrosSv(Integer.parseInt(data[2]));
					mensagem.add(user);
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado, criando um novo!!");
		}
	}

	// Crud -> create, read, Update, delete

	// update - atualizar
	public void updateUser(User updateUser) {
		for (int i = 0; i < mensagem.size(); i++) {
			if (mensagem.get(i).getId() == updateUser.getId()) {
				mensagem.set(i, updateUser);
				saveUser();
				break;
			}
		}
	}

	// buscar unico
	public User getUserById(int id) {
		for (User user : mensagem) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	// buscar todos
	public List<User> buscarTodosUsers() {
		return new ArrayList<>(mensagem);
	}

	// deletar objeto Especifico
	public void deleteUser(int id) {
		// percorrer todo o array, caso seja igual ele vai remover
		mensagem.removeIf(msg -> msg.getId() == id);
	}

	// criando fly
	public void addUser(User user) {
		// vai faltar o id
		user.setId(getNextId());
		mensagem.add(user);
		// sobrescrever o arquivo database
		saveUser();
	}

	// sobreescrever o arquivo
	private void saveUser() {
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(database, false))) {
			for (User user : mensagem) {
				String data = user.getId() + ";" + user.getNomeSv() + ";" + user.getMembrosSv();
				// escreva uma linha e passa para a proxima
				writer.println(data);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo database nao encontrado! Deu ruim");
		}
	}

	// logica para pegar o proximo Id
	public long getNextId() {
		long maxId = 0;
		for (User user : mensagem) {
			// verificar se o numero e maior que o nosso numero maximo
			if (user.getId() > maxId) {
				maxId = user.getId();
			}
		}
		return maxId + 1;
	}

}

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModifyUser {

	public static void display() {
		// Main Scene
		BorderPane borderpane = new BorderPane();
		VBox v = new VBox();
		HBox bottom = new HBox();
		Insets insets = new Insets(10);

		// Remove User Scene
		BorderPane borderpane2 = new BorderPane();
		VBox vre = new VBox();
		HBox bottomre = new HBox();

		// Add User Scene
		BorderPane borderpane3 = new BorderPane();
		VBox vadd = new VBox();
		HBox bottomadd = new HBox();

		Stage window7 = new Stage();
		window7.setTitle("Mod Room Menu");
		window7.initModality(Modality.APPLICATION_MODAL);

		Label modifyuser = new Label();
		modifyuser.setText("Modify User");
		modifyuser.setFont(new Font("Arial", 20));
		modifyuser.setPadding(new Insets(50, 10, 10, 10));

		// main modRoom scene
		Button close = new Button("Close");
		close.setMaxWidth(150);
		close.setOnAction(e -> {
			window7.close();
		});

		Button help = new Button("Help");
		help.setMaxWidth(150);
		help.setOnAction(e -> {
			getHelp();
		});

		// Remove User Scene
		Label remUserlbl = new Label();
		remUserlbl.setText("Enter a UserName");
		remUserlbl.setFont(new Font("Arial", 20));
		remUserlbl.setPadding(new Insets(50, 10, 10, 10));

		TextField EnterUsername = new TextField();
		EnterUsername.setMaxWidth(250);

		Button removeTeacher = new Button("Remove Teacher");
		removeTeacher.setMaxWidth(200);
		removeTeacher.setOnAction(e -> {
			if (isAlphabetic(EnterUsername)) {

				String usert = EnterUsername.getText();
				ConnectionClass.RemoveTeacher(usert);
			} else {
				Technicians.inputError();
			}

		});

		Button removeTechnician = new Button("Remove Technician");
		removeTechnician.setMaxWidth(200);
		removeTechnician.setOnAction(e -> {
			if (isAlphabetic(EnterUsername)) {

				String usert = EnterUsername.getText();
				ConnectionClass.RemoveTechnician(usert);
			} else {
				Technicians.inputError();
			}

		});

		bottom.getChildren().addAll(close, help);
		bottom.setAlignment(Pos.BOTTOM_LEFT);
		bottom.setSpacing(6);

		borderpane.setTop(modifyuser);
		BorderPane.setAlignment(modifyuser, Pos.CENTER);
		borderpane.setPadding(new Insets(6));
		borderpane.setCenter(v);
		BorderPane.setMargin(v, insets);
		borderpane.setBottom(bottom);
		borderpane.setPadding(new Insets(6));

		Scene modUser = new Scene(borderpane, 400, 400);
		modUser.getStylesheets().add("style.css");

		// add User Scene
		Button closeadd = new Button("Close");
		closeadd.setMaxWidth(150);
		closeadd.setOnAction(e -> {
			window7.setScene(modUser);
		});

		Button helpadd = new Button("Help");
		helpadd.setMaxWidth(150);
		helpadd.setOnAction(e -> {
			getHelpadd();
		});

		// Add a User Scene
		Label addUserlbl = new Label();
		addUserlbl.setText("Add a User");
		addUserlbl.setFont(new Font("Arial", 23));
		addUserlbl.setPadding(new Insets(50, 10, 10, 10));

		Label Enterusernamelbl = new Label();
		Enterusernamelbl.setText("Enter a username");
		Enterusernamelbl.setFont(new Font(14));
		Enterusernamelbl.setPadding(new Insets(50, 10, 10, 10));

		TextField usernametxt = new TextField();
		usernametxt.setMaxWidth(250);

		Label Enterpasslbl = new Label();
		Enterpasslbl.setText("Enter a password");
		Enterpasslbl.setFont(new Font(14));
		Enterpasslbl.setPadding(new Insets(50, 10, 10, 10));

		TextField passtxt = new TextField();
		passtxt.setMaxWidth(250);

		Label Enterfnamelbl = new Label();
		Enterfnamelbl.setText("Enter a full name");
		Enterfnamelbl.setFont(new Font(14));
		Enterfnamelbl.setPadding(new Insets(50, 10, 10, 10));

		TextField fnametxt = new TextField();
		fnametxt.setMaxWidth(250);

		Button addteach = new Button("Add Teacher");
		addteach.setMaxWidth(200);
		addteach.setOnAction(e -> {
			if (isAlphabetic(usernametxt) && isAlphabetic(passtxt) && isAlphabetic(fnametxt)) {
				String tu = usernametxt.getText();
				String tp = passtxt.getText();
				String tfn = fnametxt.getText();

				ConnectionClass.AddTeacher(tu, tp, tfn);
			} else {
				Teacher.inputError();
			}
		});

		Button addtec = new Button("Add Technician");
		addtec.setMaxWidth(200);
		addtec.setOnAction(e -> {
			if (isAlphabetic(usernametxt) && isAlphabetic(passtxt) && isAlphabetic(fnametxt)) {
				String tu = usernametxt.getText();
				String tp = passtxt.getText();
				String tfn = fnametxt.getText();

				ConnectionClass.AddTech(tu, tp, tfn);

			} else {
				Teacher.inputError();
			}
		});

		vadd.getChildren().addAll(Enterusernamelbl, usernametxt, Enterpasslbl, passtxt, Enterfnamelbl, fnametxt,
				addteach, addtec);
		vadd.setAlignment(Pos.CENTER);
		vadd.setPadding(new Insets(10, 10, 10, 10));
		vadd.setSpacing(15);

		bottomadd.getChildren().addAll(closeadd, helpadd);
		bottomadd.setAlignment(Pos.BOTTOM_LEFT);
		bottomadd.setSpacing(6);

		borderpane3.setTop(addUserlbl);
		BorderPane.setAlignment(addUserlbl, Pos.CENTER);
		borderpane3.setPadding(new Insets(6));
		borderpane3.setCenter(vadd);
		BorderPane.setMargin(vadd, insets);
		borderpane3.setBottom(bottomadd);
		borderpane3.setPadding(new Insets(6));

		Scene adduser = new Scene(borderpane3, 600, 700);
		adduser.getStylesheets().add("style.css");

		// Add user Button
		Button AddUser = new Button("Add User");
		AddUser.setMaxWidth(200);
		AddUser.setOnAction(e -> {
			window7.setScene(adduser);
		});

		// remove User Scene
		Button closere = new Button("Close");
		closere.setMaxWidth(150);
		closere.setOnAction(e -> {
			window7.setScene(modUser);
		});

		Button helpre = new Button("Help");
		helpre.setMaxWidth(150);
		helpre.setOnAction(e -> {
			getHelpre();
		});
		vre.getChildren().addAll(EnterUsername, removeTeacher, removeTechnician);
		vre.setAlignment(Pos.CENTER);
		vre.setPadding(new Insets(10, 10, 10, 10));
		vre.setSpacing(15);

		bottomre.getChildren().addAll(closere, helpre);
		bottomre.setAlignment(Pos.BOTTOM_LEFT);
		bottomre.setSpacing(6);

		borderpane2.setTop(remUserlbl);
		BorderPane.setAlignment(remUserlbl, Pos.CENTER);
		borderpane2.setPadding(new Insets(6));
		borderpane2.setCenter(vre);
		BorderPane.setMargin(vre, insets);
		borderpane2.setBottom(bottomre);
		borderpane2.setPadding(new Insets(6));

		Scene removeAuser = new Scene(borderpane2, 400, 400);
		removeAuser.getStylesheets().add("style.css");

		// Remove User Button
		Button RemoveUser = new Button("Remove User");
		RemoveUser.setMaxWidth(200);
		RemoveUser.setOnAction(e -> {
			window7.setScene(removeAuser);
		});

		// Main User Scene
		v.getChildren().addAll(AddUser, RemoveUser);
		v.setAlignment(Pos.CENTER);
		v.setPadding(new Insets(10, 10, 10, 10));
		v.setSpacing(15);

		window7.setTitle("Modify User Menu");
		window7.setScene(modUser);
		window7.show();

	}

	public static Alert getHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText(
				"Please Press one the buttons Available add\n" + "1. To add a user press the first button\n"
						+ "2. To remove a user press the second button\n" + "3. Press exit press the close button\n");
		alert.showAndWait();

		return alert;
	}

	public static Alert getHelpre() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText(
				"Enter the username of the user\n" + "1. If the user is a teacher press the teacher button\n"
						+ "2. If the user is a technician press the technician button\n"
						+ "3. Press exit press the close button\n");
		alert.showAndWait();

		return alert;
	}

	public static Alert getHelpadd() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText("Enter the username of the user\n" + "1. Enter the password of the user\n"
				+ "2. Enter the full name of the user\n" + "3. Press the add user button to add the user\n"
				+ "4. Press exit press the close button\n");
		alert.showAndWait();

		return alert;
	}

	public static boolean isAlphabetic(TextField text) { // Method for validating if a String was entered
		String field = text.getText();

		if (!field.matches("[a-zA-Z\\s']+")) {
			return false;

		} else

			return true;
	}

}

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

public class modComp {

	public static void dispay() {
		// Main Scene
		BorderPane borderpane = new BorderPane();
		VBox v = new VBox();
		HBox bottom = new HBox();
		Insets insets = new Insets(10);

		// remove a room Scene

		BorderPane borderpane2 = new BorderPane();
		VBox remove = new VBox();
		HBox bottomre = new HBox();

		// add a room scene
		BorderPane borderpane3 = new BorderPane();
		VBox addvbox = new VBox();
		HBox bottomadd = new HBox();

		Stage window6 = new Stage();
		window6.setTitle("Mod PC Menu");
		window6.initModality(Modality.APPLICATION_MODAL);

		// Remove a PC Scene
		Label remRoom = new Label();
		remRoom.setText("Enter a Room");
		remRoom.setFont(new Font(14));
		remRoom.setPadding(new Insets(50, 10, 10, 10));

		TextField EnterRoom = new TextField();
		EnterRoom.setMaxWidth(250);

		Label remPC = new Label();
		remPC.setText("Enter a PC");
		remPC.setFont(new Font(14));
		remPC.setPadding(new Insets(50, 10, 10, 10));

		TextField EnterPC = new TextField();
		EnterPC.setMaxWidth(250);

		Button remv = new Button("Remove");
		remv.setMaxWidth(150);
		remv.setOnAction(e -> {
			if (Technicians.isInt(EnterRoom) && Technicians.isInt(EnterPC)) {
				int RmNumber = Integer.parseInt(EnterRoom.getText());
				int pcnumber = Integer.parseInt(EnterPC.getText());
				ConnectionClass.RemoveAPC(RmNumber, pcnumber);
			} else {
				Technicians.inputError();
			}

		});

		Button helprem = new Button("Help");
		helprem.setMaxWidth(150);
		helprem.setOnAction(e -> {
			getHelprve();
		});

		Button close = new Button("Close");
		close.setMaxWidth(150);
		close.setOnAction(e -> {
			window6.close();
		});

		Button help = new Button("Help");
		help.setMaxWidth(150);
		help.setOnAction(e -> {
			getHelp();
		});

		bottom.getChildren().addAll(close, help);
		bottom.setAlignment(Pos.BOTTOM_LEFT);
		bottom.setSpacing(6);

		Label ModRoomMenu = new Label();
		ModRoomMenu.setText("Computer Mod Menu");
		ModRoomMenu.setFont(new Font("Arial", 20));
		ModRoomMenu.setPadding(new Insets(50, 10, 10, 10));

		borderpane.setTop(ModRoomMenu);
		BorderPane.setAlignment(ModRoomMenu, Pos.CENTER);
		borderpane.setPadding(new Insets(6));
		borderpane.setCenter(v);
		BorderPane.setMargin(v, insets);
		borderpane.setBottom(bottom);
		borderpane.setPadding(new Insets(6));

		Scene ModComp = new Scene(borderpane, 400, 400);
		ModComp.getStylesheets().add("style.css");

		// add a computer Scene

		Label addPclbl = new Label();
		addPclbl.setText("ADD A PC");
		addPclbl.setFont(new Font("Arial", 23));
		addPclbl.setPadding(new Insets(15, 10, 10, 10));

		Label serialnolbl = new Label();
		serialnolbl.setText("Enter a Serial Number");
		serialnolbl.setFont(new Font(14));
		serialnolbl.setPadding(new Insets(10, 10, 10, 10));

		TextField serialNoTxt = new TextField();
		serialNoTxt.setMaxWidth(250);

		Label modellbl = new Label();
		modellbl.setText("Enter a model name");
		modellbl.setFont(new Font(14));
		modellbl.setPadding(new Insets(10, 10, 10, 10));

		TextField modeltxt = new TextField();
		modeltxt.setMaxWidth(250);

		Label manufactlbl = new Label();
		manufactlbl.setText("Enter a manufacturer name");
		manufactlbl.setFont(new Font(14));
		manufactlbl.setPadding(new Insets(10, 10, 10, 10));

		TextField manufacttxt = new TextField();
		manufacttxt.setMaxWidth(250);

		Label Enterroomolbl = new Label();
		Enterroomolbl.setText("Enter a Room Number");
		Enterroomolbl.setFont(new Font(14));
		Enterroomolbl.setPadding(new Insets(10, 10, 10, 10));

		TextField roomnotxt = new TextField();
		roomnotxt.setMaxWidth(250);

		Label EnterPclbl = new Label();
		EnterPclbl.setText("Enter a Pc Number");
		EnterPclbl.setFont(new Font(14));
		EnterPclbl.setPadding(new Insets(10, 10, 10, 10));

		TextField pcNotxt = new TextField();
		pcNotxt.setMaxWidth(250);

		Button addpc = new Button("Add PC");
		addpc.setMaxWidth(200);
		addpc.setOnAction(e -> {
			if (ModRoom.isInt(serialNoTxt) && ModRoom.isInt(roomnotxt) && ModRoom.isInt(pcNotxt)
					&& ModifyUser.isAlphabetic(modeltxt) && ModifyUser.isAlphabetic(manufacttxt)) {
				int serialNumber = Integer.parseInt(serialNoTxt.getText());
				int RoomNumber = Integer.parseInt(roomnotxt.getText());
				int pcNumber = Integer.parseInt(pcNotxt.getText());
				String model = modeltxt.getText();
				String Manufacturer = manufacttxt.getText();
				ConnectionClass.AddPC(serialNumber, RoomNumber, pcNumber, model, Manufacturer);
			} else
				Teacher.inputError();
		});

		addvbox.getChildren().addAll(serialnolbl, serialNoTxt, modellbl, modeltxt, manufactlbl, manufacttxt,
				Enterroomolbl, roomnotxt, EnterPclbl, pcNotxt, addpc);
		addvbox.setAlignment(Pos.CENTER);
		addvbox.setPadding(new Insets(10, 10, 10, 10));
		addvbox.setSpacing(10);

		Button closeadd = new Button("Close");
		closeadd.setMaxWidth(150);
		closeadd.setOnAction(e -> {
			window6.setScene(ModComp);
		});

		Button helpadd = new Button("Help");
		helpadd.setMaxWidth(150);
		helpadd.setOnAction(e -> {
			getHeladd();
		});

		bottomadd.getChildren().addAll(closeadd, helpadd);
		bottomadd.setAlignment(Pos.BOTTOM_LEFT);
		bottomadd.setSpacing(6);

		borderpane3.setTop(addPclbl);
		BorderPane.setAlignment(addPclbl, Pos.CENTER);
		borderpane3.setPadding(new Insets(6));
		borderpane3.setCenter(addvbox);
		BorderPane.setMargin(addvbox, insets);
		borderpane3.setBottom(bottomadd);

		Scene addscene = new Scene(borderpane3, 600, 700);
		addscene.getStylesheets().add("style.css");

		// Add Room
		Button AddComp = new Button("Add PC");
		AddComp.setMaxWidth(200);
		AddComp.setOnAction(e -> {
			window6.setScene(addscene);
		});

		// remove a PC Scene
		Button closere = new Button("Close");
		close.setMaxWidth(150);
		close.setOnAction(e -> {
			window6.setScene(ModComp);
		});

		remove.getChildren().addAll(remRoom, EnterRoom, remPC, EnterPC, remv);
		remove.setAlignment(Pos.CENTER);
		remove.setPadding(new Insets(10, 10, 10, 10));
		remove.setSpacing(11);

		bottomre.getChildren().addAll(closere, helprem);
		bottomre.setAlignment(Pos.BOTTOM_LEFT);
		bottomre.setSpacing(6);

		borderpane2.setTop(remRoom);
		BorderPane.setAlignment(remRoom, Pos.CENTER);
		borderpane2.setCenter(remove);
		BorderPane.setMargin(remove, insets);
		borderpane2.setBottom(bottomre);
		borderpane2.setPadding(new Insets(4));

		Scene removeScene = new Scene(borderpane2, 400, 400);
		removeScene.getStylesheets().add("style.css");

		// Remove Room
		Button RmvComp = new Button("Remove PC");
		RmvComp.setMaxWidth(200);
		RmvComp.setOnAction(e -> {
			window6.setScene(removeScene);
		});

		// Main Scene
		v.getChildren().addAll(RmvComp, AddComp);
		v.setAlignment(Pos.CENTER);
		v.setPadding(new Insets(10, 10, 10, 10));
		v.setSpacing(15);

		Button RmvComputer = new Button("Remove PC");
		RmvComputer.setMaxWidth(200);
		RmvComputer.setOnAction(e -> {

		});

		window6.setTitle("Modify Computer Menu");
		window6.setScene(ModComp);
		window6.show();

	}

	public static Alert getHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText("Please Press one the buttons Available add\n"
				+ "1. To remove a pc press the first button\n" + "2. To add a pc press the second button\n"
				+ "3. To modify a pc press the third button\n" + "4. Press exit press the close button\n");
		alert.showAndWait();

		return alert;
	}

	public static Alert getHelprve() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText("Enter the room number\n" + "1. Enter the Computer number you wish to remove\n"
				+ "2. press the remove number\n"

		);
		alert.showAndWait();

		return alert;
	}

	public static Alert getHeladd() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText("Enter the serial number of the pc you wish to add\n" + "1. Enter the Model Name\n"
				+ "2. Enter the Manufacturer Name\n" + "3. Enter the room Number\n" + "4. Enter the PC Number\n"
				+ "5. Press the Add Button\n" + "6. To go back press the close button\n"

		);
		alert.showAndWait();

		return alert;
	}

}

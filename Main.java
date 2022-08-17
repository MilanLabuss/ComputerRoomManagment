import java.sql.SQLException;
import javafx.application.Application;
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
import javafx.stage.Stage;



public class Main extends Application  {
	
	Button button1;
	Stage window;
	static Scene loginScreen;
	private static String TName;  //The info entered into the textbox for username for teacher
	String TPass;    //The info entered into the textbox for password for teacher
	String TecName;  //The info entered into the textbox for username for technician
	String TecPass;    //The info entered into the textbox for password for technician
    String AdName;     //The info entered into the textbox for password for Admin
	String AdPass;     //The info entered into the textbox for password for Admin
	
	public static String getTName() {
		return TName;
	}

	public static void setOldPassword(String tName) {
		TName = tName;
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
	
		ConnectionClass.connect();
	

		window=primaryStage;
		window.setTitle("Login Menu");//Creating the Title
	
		//Creating the Label for the Username and pin Textfields
		Label log = new Label();
		log.setText("Login Menu");
		log.setFont(new Font(23));
		log.setPadding(new Insets(40,10,10,10));
		
		
		Label username = new Label();
		username.setFont(new Font(14));
		username.setText("Username: "); 

		//username Textfield
		TextField userNameText = new TextField();
		userNameText.setPromptText("Username");
		userNameText.setMaxWidth(250);

		Label password = new Label();
		password.setFont(new Font(14));
		password.setText("Password: ");

		// creating Pin Textfield
		TextField enterPassword = new TextField();
		enterPassword.setPromptText("Password");
		enterPassword.setMaxWidth(250);

		
		Button Teacherlogin = new Button();		//button to login as a teacher
		Teacherlogin.setText("Teacher Login");
		Teacherlogin.setMaxWidth(150);
		Teacherlogin.setOnAction(e -> {
			TName=userNameText.getText();
			TPass=enterPassword.getText();
			LoginTeachers();
			
			
		});
		
		Button TechLogin = new Button();		//button to login as a technician
		TechLogin.setText("Teachnician Login");
		TechLogin.setMaxWidth(150);
		TechLogin.setOnAction(e -> {
			TecName=userNameText.getText();
			TecPass=enterPassword.getText();
			LoginTechs();
			window.close();
			
		});
		
		Button AdminLogin = new Button();		//button to login as a admin
		AdminLogin.setText("Admin Login");
		AdminLogin.setMaxWidth(150);
		AdminLogin.setOnAction(e -> 
		{
			
			AdName=userNameText.getText();
			AdPass=enterPassword.getText();
			LoginAdmin();
			window.close();
			
		});
				
		
		Button help = new Button("Help");
		help.setOnAction(e -> {
			getHelp();
		});
		
		VBox v = new VBox();
		v.getChildren().addAll(username,userNameText,password, enterPassword,Teacherlogin,TechLogin,AdminLogin);
		v.setAlignment(Pos.CENTER);
		v.setPadding(new Insets(10,10,10,10));
		v.setSpacing(15);
		
		HBox bottom = new HBox();
		bottom.getChildren().addAll(help);
		bottom.setAlignment(Pos.BOTTOM_LEFT);
		bottom.setSpacing(8);
		
		Insets insets = new Insets(10);
		
		BorderPane borderpane = new BorderPane();
		borderpane.setTop(log);
		BorderPane.setAlignment(log, Pos.CENTER);
		borderpane.setCenter(v);
		BorderPane.setMargin(v, insets);
		borderpane.setBottom(bottom);
		borderpane.setPadding(new Insets(10));
		
	    loginScreen = new Scene(borderpane, 500 ,500);		//scene for the login screen
	    loginScreen.getStylesheets().add("style.css");

		
		window.setScene(loginScreen);
		window.setTitle("Welcome");
		window.show();

	
	}
	
	public static Alert inputError() {
		//Alert displaying that your account Balance is under 2 Euro
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText("Error");
		alert.setContentText("The Username or Password is not correct!");	
		alert.showAndWait();
		return alert;
	}
	
	public static Alert getHelp() {
		//An alert that will Display when the help button is Pressed
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help Box");
		alert.setHeaderText("Hints and Tips");
		alert.setContentText("Please input your username and password and click which menu you want to log into\n"
				+ "1. For the teachers menu press the Teacher Login button\n"
				+ "2. For the Technicians menu press the technician login butotn\n"
				+ "3. For the Admin menu press the Admin login button"
				);	
		alert.showAndWait();

		return alert;
	}
	
	
	public void LoginTeachers() {
		 try {
		        if (TName != null && TPass != null) {
		    		ConnectionClass.connect();
		            String sqlt = "Select * from Teachers Where username='" + TName + "' and password='" + TPass + "'";
		            ConnectionClass.rs = ConnectionClass.st.executeQuery(sqlt);
		             if (ConnectionClass.rs.next()) {
		            	// Checking if the name and pass entered is in the table
		
		               	Teacher.setOldPassword(TPass);		//setting the old password equal to tpass
		            	Teacher.display();
		            	
		             } else {
		                //in this case enter when  result size is zero  it means user is invalid
		            	inputError();
		             }
		        } 

		    } catch (SQLException err) {
		        System.out.println("Error in input");
		    }
		
}
	

	
	public void LoginTechs() {
		 try {
		        if (TecName != null && TecPass != null) {
		        	ConnectionClass.connect();
		            String sqlTec = "Select * from tecnicians Where username='" + TecName + "' and password='" + TecPass + "'";
		            ConnectionClass.rs = ConnectionClass.st.executeQuery(sqlTec);
		             if (ConnectionClass.rs.next()) {
		               // Checking if the name and pass entered is in the table
		            	Technicians.display();
		            	
		            	
		             } else {
		            	 //in this case enter when  result size is zero  it means user is invalid
		            	inputError();
		             }
		        } 

		       

		    } catch (SQLException err) {
		        System.out.println("error");
		    }
		
}
	
	public void LoginAdmin() {
		 try {
	
		        if (AdName != null && AdPass != null) {
		        	ConnectionClass.connect();
		            String sqlAdmin = "Select * from admin Where username='" + AdName + "' and password='" + AdPass + "'";
		            ConnectionClass.rs = ConnectionClass.st.executeQuery(sqlAdmin);
		             if (ConnectionClass.rs.next()) {
		            	// Checking if the name and pass entered is in the table
		            	Admin.display();
		            	
		             } else {
		            	 //in this case enter when  result size is zero  it means user is invalid
		            	inputError();
		             }
		        } 

		    } catch (SQLException err) {
		        System.out.println("error");
		    }
		
}

		
}


	





	
	









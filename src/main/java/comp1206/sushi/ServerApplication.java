package comp1206.sushi;
import javax.swing.*;

import comp1206.sushi.mock.MockServer;
import comp1206.sushi.server.ServerWindow;
import javafx.application.Application;

public class ServerApplication {

	public static void main(String[] args) {


		Application.launch(ServerWindow.class, MockServer.class.getName());

		/**
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch (ReflectiveOperationException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(() -> new ServerWindow(new MockServer()));
		 */
	}
}


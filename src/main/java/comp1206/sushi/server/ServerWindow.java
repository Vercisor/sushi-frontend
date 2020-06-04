package comp1206.sushi.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import comp1206.sushi.common.*;
import comp1206.sushi.mock.MockServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Provides the Sushi Server user interface
 *
 */
public class ServerWindow extends Application implements UpdateListener{

	private static final long serialVersionUID = -4661566573959270000L;
	private ServerInterface server;
	private String serverName;


    @Override
    public void init() throws Exception {
        this.serverName = getParameters().getRaw().get(0);
        Class<? extends ServerInterface> serverClass = Class.forName(serverName).asSubclass(MockServer.class);
        this.server = serverClass.getConstructor().newInstance();
    }

    @Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Home.fxml"));

		stage.setTitle("Sushi");
		stage.setScene(new Scene(root, 600, 400));


		stage.show();
	}

	public void startTimer() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);     
        int timeInterval = 5;
        
        scheduler.scheduleAtFixedRate(() -> refreshAll(), 0, timeInterval, TimeUnit.SECONDS);
	}

	
	/**
	 * Refresh all parts of the server application based on receiving new data, calling the server afresh
	 */
	public void refreshAll() {
		
	}

	/**
	 * Respond to the model being updated by refreshing all data displays
	 */
	public void updated(UpdateEvent updateEvent) {
		refreshAll();
	}

}

package in.labulle.anycode.editor.ux.dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Dialogs {
	public static void showError(String msg) {
		Stage dialogStage = new Stage();
		

		dialogStage.initModality(Modality.APPLICATION_MODAL);
		dialogStage.setResizable(false);
		dialogStage.setFullScreen(false);
		dialogStage.setIconified(false);

	
		dialogStage.setScene(new Scene(VBoxBuilder.create()
				.children(new Text(msg), getOkButton(dialogStage)).alignment(Pos.CENTER)
				.padding(new Insets(5)).build()));
		dialogStage.show();
	}
	
	private static Button getOkButton(final Stage st){
		Button b = new Button("OK");
		b.setOnAction(onCloseEventHandler(st));
		return b;
	}

	private static EventHandler<ActionEvent> onCloseEventHandler(final Stage st) {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				st.close();
			}
		};
	}

}

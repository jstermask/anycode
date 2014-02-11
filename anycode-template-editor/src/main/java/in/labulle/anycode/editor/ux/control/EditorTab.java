package in.labulle.anycode.editor.ux.control;

import in.labulle.anycode.editor.context.IDirectiveContext;
import in.labulle.anycode.editor.core.ApiElement;
import in.labulle.anycode.editor.core.Directive;
import in.labulle.anycode.editor.core.Function;
import in.labulle.anycode.editor.ux.FormController;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditorTab extends Tab {
	private static final Logger LOG = LoggerFactory.getLogger(EditorTab.class);

	private IDirectiveContext directiveContext;

	@FXML
	private ListView<ApiElement> functionListView;

	@FXML
	private VBox form;
	
	@FXML
	private FormController formController;

	public EditorTab(IDirectiveContext ctx) {
		this.directiveContext = ctx;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"directive_tab.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
			init();
		} catch (IOException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Constructor", e);
			}
		}

	}

	private IDirectiveContext getDirectiveContext() {
		return directiveContext;
	}

	private void init() {
		loadTab();
		loadApiElements();
		selectNothing();
	}

	private void loadTab() {
		setOnClosed(getCloseEventHandler());
		setText(directiveContext.getFile().getName());
	}

	private void loadApiElements() {
		Directive d = getDirectiveContext().getDirective();
		functionListView
				.setCellFactory(new Callback<ListView<ApiElement>, ListCell<ApiElement>>() {

					@Override
					public ListCell<ApiElement> call(ListView<ApiElement> p) {

						ListCell<ApiElement> cell = new ListCell<ApiElement>() {

							@Override
							protected void updateItem(ApiElement t, boolean bln) {
								super.updateItem(t, bln);
								if (t != null) {
									String type = (t instanceof Function) ? "[F]"
											: "[M]";
									setText(type + " " + t.getName());
								}
							}

						};

						return cell;
					}
				});

		for (ApiElement elt : d.getElements()) {
			functionListView.getItems().add(elt);
		}
		functionListView.getSelectionModel().setSelectionMode(
				SelectionMode.SINGLE);
		functionListView.getSelectionModel().selectedItemProperty()
				.addListener(getSelectionListener());
	}

	private void selectNothing() {
		this.form.setVisible(false);
	}

	private EventHandler<Event> getCloseEventHandler() {
		return new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				getDirectiveContext().close();
				if (LOG.isDebugEnabled()) {
					LOG.debug("Tab closed");
				}
			}

		};
	}

	private ChangeListener<ApiElement> getSelectionListener() {
		return new ChangeListener<ApiElement>() {

			@Override
			public void changed(ObservableValue<? extends ApiElement> arg0,
					ApiElement arg1, ApiElement arg2) {
				if(arg2 != null) {
					formController.setElement(arg2);
					form.setVisible(true);
				}

			}

		};
	}

	


}

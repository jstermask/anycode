package in.labulle.anycode.editor.ux.control;

import in.labulle.anycode.editor.context.IDirectiveContext;
import in.labulle.anycode.editor.core.ApiElement;
import in.labulle.anycode.editor.core.Directive;

import java.io.IOException;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditorTab extends Tab {
	private static final Logger LOG = LoggerFactory.getLogger(EditorTab.class);

	private IDirectiveContext directiveContext;
	
	@FXML
	private ListView<String> functionListView;

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
	}
	
	private void loadTab() {
		setOnClosed(getCloseEventHandler());
		setText(directiveContext.getFile().getName());
	}
	
	private void loadApiElements() {
		Directive d = getDirectiveContext().getDirective();
		for(ApiElement elt : d.getElements()) {
			functionListView.getItems().add(elt.getName());
		}
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


}

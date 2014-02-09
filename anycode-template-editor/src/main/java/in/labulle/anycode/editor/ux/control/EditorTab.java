package in.labulle.anycode.editor.ux.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.labulle.anycode.editor.context.IDirectiveContext;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

public class EditorTab extends Tab {
	private static final Logger LOG = LoggerFactory.getLogger(EditorTab.class);
	
	private IDirectiveContext directiveContext;
	
	public EditorTab(IDirectiveContext ctx) {
		this.directiveContext = ctx;
		this.setText(ctx.getFile().getName());
		this.setOnClosed(getCloseEventHandler());
	}
	
	
	private IDirectiveContext getDirectiveContext() {
		return directiveContext;
	}
	
	
	private EventHandler<Event> getCloseEventHandler() {
		return new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				getDirectiveContext().close();
				if(LOG.isDebugEnabled()) {
					LOG.debug("Tab closed");
				}
			}
			
		};
	}
	

}

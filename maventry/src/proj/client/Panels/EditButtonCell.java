package proj.client.Panels;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

public class EditButtonCell extends AbstractCell<String> {

    @Override
    public void render(Context context, String value, SafeHtmlBuilder sb) {
        FlowPanel panel = new FlowPanel();
        Button editButton = new Button("Edit");
        panel.add(editButton);
        sb.appendHtmlConstant(panel.getElement().getString());
    }
}

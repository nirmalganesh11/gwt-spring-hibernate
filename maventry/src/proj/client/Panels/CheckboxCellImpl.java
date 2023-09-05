package proj.client.Panels;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.CheckBox;

public class CheckboxCellImpl extends AbstractCell<String> {
    @Override
    public void render(Context context, String value, SafeHtmlBuilder sb) {
        // Render a checkbox
        CheckBox checkBox = new CheckBox(value);
        //checkBox.setValue(value);
        sb.appendHtmlConstant(checkBox.toString());
    }
}


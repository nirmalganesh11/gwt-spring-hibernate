package proj.client.Panels;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class PasswordMaskCell extends AbstractCell<String> {

    @Override
    public void render(Context context, String value, SafeHtmlBuilder sb) {
        if (value != null) {
            StringBuilder maskedValue = new StringBuilder();
            for (int i = 0; i < value.length(); i++) {
                maskedValue.append("*");
            }
            sb.appendEscaped(maskedValue.toString());
        }
    }
}

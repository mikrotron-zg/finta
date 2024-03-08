package hr.mikrotron.finta;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;

@Route("")
public class MainLayout  extends AppLayout {
  public MainLayout() {
    addToNavbar(createHeader());
  }

  private Component createHeader() {
    Header header = new Header();
    header.addClassNames(BoxSizing.BORDER, Display.FLEX, FlexDirection.COLUMN, Width.FULL);

    Div layout = new Div();
    layout.addClassNames(Display.FLEX, AlignItems.CENTER, Padding.Horizontal.LARGE);

    H1 title = new H1("FinTA");
    title.addClassNames(Margin.Vertical.MEDIUM, Margin.End.AUTO, FontSize.LARGE);
    layout.add(title);

    Nav nav = new Nav();

    header.add(layout, nav);
    return header;
  }
}

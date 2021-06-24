package org.globsframework.gui.views;

import org.globsframework.gui.SelectionService;
import org.globsframework.gui.splits.ImageLocator;
import org.globsframework.gui.splits.color.ColorService;
import org.globsframework.gui.splits.layout.SingleComponentLayout;
import org.globsframework.gui.splits.ui.UIService;
import org.globsframework.gui.splits.utils.DummyImageLocator;
import org.globsframework.metamodel.DummyObject;
import org.globsframework.model.GlobChecker;
import org.globsframework.model.GlobRepository;
import org.globsframework.model.format.DescriptionService;
import org.globsframework.model.format.Formats;
import org.globsframework.model.format.utils.DefaultDescriptionService;
import org.globsframework.model.utils.GlobFieldComparator;
import org.globsframework.utils.directory.DefaultDirectory;
import org.globsframework.utils.directory.Directory;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class GuiTest {

  protected static final Directory directory = new DefaultDirectory();

  public static void main(String[] args) {

    directory.add(DescriptionService.class,
                  new DefaultDescriptionService(
                    new Formats(new SimpleDateFormat("yyyy/MM/dd"),
                                new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"),
                                new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US)),
                                "yes", "no")));
    directory.add(SelectionService.class, new SelectionService());
    directory.add(new ColorService());
    directory.add(new UIService());
    directory.add(ImageLocator.class, new DummyImageLocator());

    JFrame frame = new JFrame();
    GlobChecker checker = new GlobChecker();

    GlobRepository repository =
      checker.parse("<dummyObject id='1' name='name1' value='1.1'/>" +
                    "<dummyObject id='2' name='name2' value='2.2'/>");
    GlobTableView globTableView =
      GlobTableView.init(DummyObject.TYPE, repository, new GlobFieldComparator(DummyObject.ID), directory);
    globTableView.addColumn(DummyObject.NAME);
    globTableView.addColumn(DummyObject.ID);


    JPanel panel = new JPanel();
    panel.setLayout(new SingleComponentLayout(null));
    frame.setContentPane(panel);
    panel.add(globTableView.getComponent());
    frame.setSize(100, 100);
    frame.setVisible(true);
  }
}

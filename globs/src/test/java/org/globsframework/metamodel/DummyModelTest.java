package org.globsframework.metamodel;

import junit.framework.TestCase;
import org.globsframework.metamodel.links.Link;
import org.globsframework.metamodel.links.impl.DefaultMutableGlobLinkModel;
import org.globsframework.utils.TestUtils;

public class DummyModelTest extends TestCase {
  public void test() throws Exception {
    DummyModel.get(); // initialize all classes
     GlobLinkModel model = DummyModel.get().getLinkModel();
    Link[] expected = {
       DummyObjectWithRequiredLink.LINK,
       DummyObjectWithLinkFieldId.LINK,
       DummyObject.LINK,
       DummyObjectWithLinks.PARENT_LINK,
    };
    TestUtils.assertContained(model.getInboundLinks(DummyObject.TYPE), expected);
  }
}

package org.globsframework.remote;

import org.globsframework.metamodel.DummyObject;
import org.globsframework.metamodel.GlobModel;
import org.globsframework.metamodel.GlobType;
import org.globsframework.model.*;
import static org.globsframework.model.FieldValue.value;
import static org.globsframework.model.KeyBuilder.newKey;
import static org.junit.Assert.assertTrue;

import org.globsframework.remote.impl.DefaultCreateRequest;
import org.globsframework.remote.impl.DefaultDeleteRequest;
import org.globsframework.remote.impl.DefaultUpdateRequest;
import org.globsframework.sqlstreams.constraints.Constraints;
import org.globsframework.sqlstreams.drivers.jdbc.DbServicesTestCase;
import org.globsframework.xml.XmlGlobStreamReader;
import org.globsframework.utils.serialization.SerializedInputOutputFactory;
import org.junit.Test;

import java.util.Set;

public class RemoteExecutorTest extends DbServicesTestCase {

  @Test
  public void testUpdate() throws Exception {
    populate(sqlConnection,
             XmlGlobStreamReader.parse(
                "<dummyObject id='1' name='hello' value='1.1' present='true'/>" +
                                       "<dummyObject id='3' name='word' value='3.3' present='false'/>", directory.get(GlobModel.class)));
    GlobRepository globRepository = GlobRepositoryBuilder.init().add(sqlConnection.getQueryBuilder(DummyObject.TYPE)
      .selectAll().getQuery().executeAsGlobs()).get();

    globRepository.addChangeListener(new ChangeSetListener() {
      public void globsChanged(ChangeSet changeSet, GlobRepository globRepository) {
        SerializedRemoteAccess access = new SerializedRemoteAccess();
        byte[] bytes = access.apply(changeSet);
        RemoteExecutor executor = new RemoteExecutor(globModel, new RemoteExecutor.RequestBuilder() {
          public RemoteExecutor.UpdateRequest getUpdate(GlobType globType, FieldValues fieldValues) {
            return new DefaultUpdateRequest(sqlConnection, globType, Constraints.fieldsEqual(fieldValues));
          }

          public RemoteExecutor.CreateRequest getCreate(GlobType globType, FieldValues fieldValues) {
            return new DefaultCreateRequest(sqlConnection, globType, fieldValues, false);
          }

          public RemoteExecutor.DeleteRequest getDelete(GlobType globType, FieldValues valuesConstraint) {
            return new DefaultDeleteRequest(sqlConnection, globType, Constraints.fieldsEqual(valuesConstraint));
          }
        });
        executor.execute(SerializedInputOutputFactory.init(bytes));
      }

      public void globsReset(GlobRepository globRepository, Set<GlobType> changedTypes) {
      }
    });

    globRepository.startChangeSet();

    globRepository.create(DummyObject.TYPE,
                          value(DummyObject.ID, 2),
                          value(DummyObject.NAME, "a name"),
                          value(DummyObject.PRESENT, Boolean.TRUE));
    globRepository.delete(newKey(DummyObject.TYPE, 3));
    globRepository.update(newKey(DummyObject.TYPE, 1), DummyObject.VALUE, 2.2);
    globRepository.completeChangeSet();
    checkDb(newKey(DummyObject.TYPE, 1), DummyObject.VALUE, 2.2, sqlConnection);
    checkDb(newKey(DummyObject.TYPE, 2), DummyObject.NAME, "a name", sqlConnection);
    assertTrue(sqlConnection.getQueryBuilder(DummyObject.TYPE, Constraints.equal(DummyObject.ID, 3)).getQuery().executeAsGlobs().isEmpty());
  }
}

package org.globsframework.network;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.properties.Property;
import org.globsframework.model.Glob;
import org.globsframework.model.GlobList;

public interface NetworkService {

  void publish(Glob glob, GlobList globList);

  void registerListener(Client response, GlobType type);

  interface Client {
    void receive(ReplyOn replyOn, Glob glob, GlobList globList);
  }

  interface ReplyOn {
    void reply(Glob reply, GlobList globList);
  }
}

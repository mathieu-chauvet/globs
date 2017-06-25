package org.globsframework.streams.accessors;

import java.time.ZonedDateTime;

public interface DateAccessor extends Accessor {

  ZonedDateTime getDate();
}

package org.globsframework.utils.serialization;

import org.globsframework.utils.Strings;
import org.globsframework.utils.exceptions.IOFailure;

import java.util.Base64;


public class Encoder {
  private Encoder() {
  }

  public static String byteToString(byte[] cryptedBytes) {
    return Strings.removeNewLine(Base64.getEncoder().encodeToString(cryptedBytes));
  }

  public static byte[] stringToByte(String text) {
    try {
      return Base64.getDecoder().decode(text);
    }
    catch (Exception e) {
      throw new IOFailure(e);
    }
  }
}

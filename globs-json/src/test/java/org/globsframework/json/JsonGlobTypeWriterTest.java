package org.globsframework.json;

import org.junit.Test;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import java.io.StringWriter;
import java.util.Collections;

public class JsonGlobTypeWriterTest {

  public static final String EXPECTED = "" +
                                        "{" +
                                        "  'version':1," +
                                        "  'name':'jsonGlobType'," +
                                        "  'annotations':[]," +
                                        "  'fields':[" +
                                        "   {" +
                                        "    'name':'id'," +
                                        "    'type':'int'," +
                                        "    'annotations':[" +
                                        "      {" +
                                        "       'version':1," +
                                        "       'type':'KeyAnnotation'," +
                                        "       'fields':" +
                                        "         {'index':0}" +
                                        "      }," +
                                        "      {" +
                                        "       'version':1," +
                                        "       'type':'fieldNameAnnotation'," +
                                        "       'fields':" +
                                        "         {'name':'id'}" +
                                        "      }]" +
                                        "   }," +
                                        "   {" +
                                        "    'name':'name'," +
                                        "    'type':'string'," +
                                        "    'annotations':" +
                                        "     [" +
                                        "       {" +
                                        "        'version':1," +
                                        "        'type':'fieldNameAnnotation'," +
                                        "        'fields': {'name':'name'}" +
                                        "       }" +
                                        "     ]}," +
                                        "  {" +
                                        "   'name':'count'," +
                                        "   'type':'int'," +
                                        "   'annotations':" +
                                        "     [{" +
                                        "       'version':1," +
                                        "       'type':'fieldNameAnnotation'," +
                                        "       'fields':{'name':'count'}}]" +
                                        "  }," +
                                        "  {'name':'value'," +
                                        "   'type':'double'," +
                                        "   'annotations':" +
                                        "      [{" +
                                        "        'version':1," +
                                        "        'type':'fieldNameAnnotation'," +
                                        "        'fields':{'name':'value'}}" +
                                        "      ]" +
                                        "  }," +
                                        "  {'name':'isPresent'," +
                                        "   'type':'boolean'," +
                                        "   'annotations':" +
                                        "       [{" +
                                        "         'version':1," +
                                        "         'type':'fieldNameAnnotation'," +
                                        "         'fields':{'name':'isPresent'}}" +
                                        "       ]" +
                                        "   }" +
                                        "  ]" +
                                        "}";

  @Test
  public void writeType() throws Exception {
    JsonGeneratorFactory factory = Json.createGeneratorFactory(Collections.emptyMap());
    StringWriter writer = new StringWriter();
    JsonGenerator generator = factory.createGenerator(writer);
    JsonGlobTypeWriter jsonGlobTypeWriter =
      new JsonGlobTypeWriter(new GlobTypeToJsonName() {
      }, generator);

    jsonGlobTypeWriter.write(JsonGlobType.TYPE);
    generator.close();
    JsonTestUtils.checkSame(EXPECTED, writer.toString());
  }
}
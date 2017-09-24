package org.globsframework.json;

import org.globsframework.metamodel.Annotations;
import org.globsframework.metamodel.Field;
import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.fields.*;
import org.globsframework.model.Glob;

import javax.json.stream.JsonGenerator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class JsonGlobTypeWriter {
  final GlobTypeToJsonName globTypeToJsonName;
  private JsonGenerator jsonGenerator;
  private final JsonGlobWriter jsonGlobWriter;
  private final JsonWriterFieldVisitor fieldType;

  public JsonGlobTypeWriter(GlobTypeToJsonName globTypeToJsonName,
                            JsonGenerator jsonGenerator) {
    this.globTypeToJsonName = globTypeToJsonName;
    this.jsonGenerator = jsonGenerator;
    jsonGlobWriter = new JsonGlobWriter(this.globTypeToJsonName, jsonGenerator);
    fieldType = new JsonWriterFieldVisitor(jsonGenerator);
  }

  public void write(GlobType type) {
      jsonGenerator.writeStartObject();
      jsonGenerator.write(JsonGlobTypeReader.VERSION, 1);
      jsonGenerator.write(JsonGlobTypeReader.NAME, globTypeToJsonName.getName(type));
      writeAnnotations(jsonGenerator, jsonGlobWriter, type);

      jsonGenerator.writeStartArray(JsonGlobTypeReader.FIELDS);
      for (Field field : type.getFields()) {
        jsonGenerator.writeStartObject();
        jsonGenerator.write(JsonGlobTypeReader.FIELD_NAME, field.getName());
        field.safeVisit(fieldType);
        writeAnnotations(jsonGenerator, jsonGlobWriter, field);
        jsonGenerator.writeEnd(); // field
      }
    jsonGenerator.writeEnd(); //fields
    jsonGenerator.writeEnd(); //type
  }

  private void writeAnnotations(JsonGenerator generator, JsonGlobWriter jsonGlobWriter, Annotations annotations) {
    generator.writeStartArray(JsonGlobTypeReader.ANNOTATIONS);
    //order for test
    List<Glob> fieldGlobs = new ArrayList<>(annotations.listAnnotations());
    fieldGlobs.sort(Comparator.comparing(g -> g.getType().getName()));
    for (Glob glob : fieldGlobs) {
      jsonGlobWriter.write(glob);
    }
    generator.writeEnd(); // annotations
  }

  private static class JsonWriterFieldVisitor implements FieldVisitor {
    private JsonGenerator jsonGenerator;
    private static String TYPE_FIELD_NAME = JsonGlobTypeReader.FIELD_TYPE;

    public JsonWriterFieldVisitor(JsonGenerator jsonGenerator) {
      this.jsonGenerator = jsonGenerator;
    }

    public void visitInteger(IntegerField field) throws Exception {
      jsonGenerator.write(TYPE_FIELD_NAME, JsonGlobTypeReader.INT_TYPE);
    }

    public void visitDouble(DoubleField field) throws Exception {
      jsonGenerator.write(TYPE_FIELD_NAME, JsonGlobTypeReader.DOUBLE_TYPE);
    }

    public void visitString(StringField field) throws Exception {
      jsonGenerator.write(TYPE_FIELD_NAME, JsonGlobTypeReader.STRING_TYPE);
    }

    public void visitBoolean(BooleanField field) throws Exception {
      jsonGenerator.write(TYPE_FIELD_NAME, JsonGlobTypeReader.BOOLEAN_TYPE);
    }

    public void visitLong(LongField field) throws Exception {
      jsonGenerator.write(TYPE_FIELD_NAME, JsonGlobTypeReader.LONG_TYPE);
    }

    public void visitBlob(BlobField field) throws Exception {
      throw new RuntimeException("Blob not supported.");
    }
  }
}

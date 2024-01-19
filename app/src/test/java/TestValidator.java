import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TestValidator {
    Validator vSchema = new Validator();
    StringSchema schemaString = vSchema.string();
    NumberSchema schemaNumber = vSchema.number();
    MapSchema schemaMap = vSchema.map();
    Map<String, BaseSchema> schemas = new HashMap<>();

    @Test
    void testMapSchemaShapeOne() {
        schemas.put("name", vSchema.string().required());
        schemas.put("age", vSchema.number().positive());
        schemaMap.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertThat(schemaMap.isValid(human1)).isEqualTo(true);
    }
    @Test
    void testMapSchemaShapeTwo() {
        schemas.put("name", vSchema.string().required());
        schemas.put("gender", vSchema.string().required());
        schemas.put("age", vSchema.number().positive());
        schemaMap.shape(schemas);

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("gender", "male");
        human2.put("age", null);
        assertThat(schemaMap.isValid(human2)).isEqualTo(true);
    }

    @Test
    void testMapSchemaShapeThree() {
        schemas.put("name", vSchema.string().required());
        schemas.put("gender", vSchema.string().required());
        schemas.put("age", vSchema.number().positive());
        schemaMap.shape(schemas);

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "Maya");
        human3.put("gender", null);
        human3.put("age", null);
        assertThat(schemaMap.isValid(human3)).isEqualTo(false);
    }

    @Test
    void testMapSchemaShapeFour() {
        schemas.put("name", vSchema.string().required());
        schemas.put("age", vSchema.number().positive());
        schemaMap.shape(schemas);

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertThat(schemaMap.isValid(human4)).isEqualTo(false);
    }

    @Test
    void testMapSchemaNotRequired() {
        assertThat(schemaMap.isValid(null)).isEqualTo(true);
    }

    @Test
    void testMapSchemaRequired() {
        schemaMap.required();
        assertThat(schemaMap.isValid(null)).isEqualTo(false);
        assertThat(schemaMap.isValid(new HashMap())).isEqualTo(true);

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schemaMap.isValid(data)).isEqualTo(true);

        schemaMap.sizeof(2);
        assertThat(schemaMap.isValid(data)).isEqualTo(false);
        data.put("key2", "value2");
        assertThat(schemaMap.isValid(data)).isEqualTo(true);
    }

    @Test
    void testNumberSchemaNotRequired() {
        assertThat(schemaNumber.isValid(null)).isEqualTo(true);
        assertThat(schemaNumber.positive().isValid(null)).isEqualTo(true);
    }
    @Test
    void testNumberSchemaRequired() {
        schemaNumber.required();
        assertThat(schemaNumber.isValid(null)).isEqualTo(false);
        assertThat(schemaNumber.isValid("5")).isEqualTo(false);
        assertThat(schemaNumber.isValid(10)).isEqualTo(true);
        assertThat(schemaNumber.positive().isValid(-10)).isEqualTo(false);
        assertThat(schemaNumber.isValid(0)).isEqualTo(false);
    }
    @Test
    void testNumberSchemaRange() {
        schemaNumber.required().range(5, 10);
        assertThat(schemaNumber.isValid(5)).isEqualTo(true);
        assertThat(schemaNumber.isValid(10)).isEqualTo(true);
        assertThat(schemaNumber.isValid(4)).isEqualTo(false);
        assertThat(schemaNumber.isValid(11)).isEqualTo(false);
    }

    @Test
    void testStringSchemaNotRequired() {
        assertThat(schemaString.isValid("")).isEqualTo(true);
        assertThat(schemaString.isValid(null)).isEqualTo(true);
    }

    @Test
    void testStringSchemaRequired() {
        schemaString.required();
        assertThat(schemaString.isValid("")).isEqualTo(false);
        assertThat(schemaString.isValid(null)).isEqualTo(false);
        assertThat(schemaString.isValid(5)).isEqualTo(false);
        assertThat(schemaString.isValid("what does the fox say")).isEqualTo(true);
        assertThat(schemaString.isValid("hexlet")).isEqualTo(true);
    }

    @Test
    void testStringSchemaContains() {
        schemaString.required();
        assertThat(schemaString.contains("wh").isValid("what does the fox say"))
                .isEqualTo(true);
        assertThat(schemaString.contains("what").isValid("what does the fox say"))
                .isEqualTo(true);
        assertThat(schemaString.contains("whatthe").isValid("what does the fox say"))
                .isEqualTo(false);
        assertThat(schemaString.isValid("what does the fox say"))
                .isEqualTo(false);
    }

    @Test
    void testStringSchemaMinLength() {
        schemaString.required();
        assertThat(schemaString.minLength(4).isValid("hexlet"))
                .isEqualTo(true);
        assertThat(schemaString.minLength(21).isValid("what does the fox say"))
                .isEqualTo(true);
        assertThat(schemaString.minLength(50).isValid("hexlet"))
                .isEqualTo(false);
        assertThat(schemaString.isValid("what does the fox say"))
                .isEqualTo(false);
    }
}

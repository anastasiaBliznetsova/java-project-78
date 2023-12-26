import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class testValidator {
    Validator v = new Validator();
    StringSchema schema = v.string();

    @Test
    void testStringSchemaNotRequired() {
        assertThat(schema.isValid("")).isEqualTo(true);
        assertThat(schema.isValid(null)).isEqualTo(true);
    }

    @Test
    void testStringSchemaRequired() {
        schema.required();
        assertThat(schema.isValid("")).isEqualTo(false);
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid(5)).isEqualTo(false);
        assertThat(schema.isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.isValid("hexlet")).isEqualTo(true);
    }

    @Test
    void testStringSchemaContains() {
        schema.required();
        assertThat(schema.contains("wh").isValid("what does the fox say"))
                .isEqualTo(true);
        assertThat(schema.contains("what").isValid("what does the fox say"))
                .isEqualTo(true);
        assertThat(schema.contains("whatthe").isValid("what does the fox say"))
                .isEqualTo(false);
        assertThat(schema.isValid("what does the fox say"))
                .isEqualTo(false);
    }

    @Test
    void testStringSchemaMinLength() {
        schema.required();
        assertThat(schema.minLength(6).isValid("hexlet"))
                .isEqualTo(true);
        assertThat(schema.minLength(21).isValid("what does the fox say"))
                .isEqualTo(true);
        assertThat(schema.minLength(1).isValid("hexlet"))
                .isEqualTo(false);
        assertThat(schema.isValid("what does the fox say"))
                .isEqualTo(false);
    }

}

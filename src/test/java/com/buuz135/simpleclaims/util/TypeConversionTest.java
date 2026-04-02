package com.buuz135.simpleclaims.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class TypeConversionTest {

    // -------------------------------------------------------------------------
    // convert() – happy path for every built-in type
    // -------------------------------------------------------------------------

    @Test
    void convertInt() {
        assertEquals(42, TypeConversion.convert("int", "42"));
    }

    @Test
    void convertIntegerAlias() {
        assertEquals(-7, TypeConversion.convert("integer", "-7"));
    }

    @Test
    void convertIntegerFullyQualifiedAlias() {
        assertEquals(0, TypeConversion.convert("java.lang.integer", "0"));
    }

    @Test
    void convertLong() {
        assertEquals(9_999_999_999L, TypeConversion.convert("long", "9999999999"));
    }

    @Test
    void convertLongFullyQualified() {
        assertEquals(-1L, TypeConversion.convert("java.lang.long", "-1"));
    }

    @Test
    void convertShort() {
        assertEquals((short) 100, TypeConversion.convert("short", "100"));
    }

    @Test
    void convertShortFullyQualified() {
        assertEquals((short) -5, TypeConversion.convert("java.lang.short", "-5"));
    }

    @Test
    void convertByte() {
        assertEquals((byte) 127, TypeConversion.convert("byte", "127"));
    }

    @Test
    void convertByteFullyQualified() {
        assertEquals((byte) -128, TypeConversion.convert("java.lang.byte", "-128"));
    }

    @Test
    void convertDouble() {
        assertEquals(3.14, (double) TypeConversion.convert("double", "3.14"), 0.0001);
    }

    @Test
    void convertDoubleFullyQualified() {
        assertEquals(-0.5, (double) TypeConversion.convert("java.lang.double", "-0.5"), 0.0001);
    }

    @Test
    void convertFloat() {
        assertEquals(1.5f, (float) TypeConversion.convert("float", "1.5"), 0.0001f);
    }

    @Test
    void convertFloatFullyQualified() {
        assertEquals(-2.0f, (float) TypeConversion.convert("java.lang.float", "-2.0"), 0.0001f);
    }

    @Test
    void convertString() {
        assertEquals("hello world", TypeConversion.convert("string", "hello world"));
    }

    @Test
    void convertStringFullyQualified() {
        assertEquals("", TypeConversion.convert("java.lang.string", ""));
    }

    @Test
    void convertUUID() {
        UUID expected = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        assertEquals(expected, TypeConversion.convert("uuid", "550e8400-e29b-41d4-a716-446655440000"));
    }

    @Test
    void convertUUIDFullyQualified() {
        UUID expected = UUID.fromString("00000000-0000-0000-0000-000000000001");
        assertEquals(expected, TypeConversion.convert("java.util.uuid", "00000000-0000-0000-0000-000000000001"));
    }

    @Test
    void convertBigInteger() {
        assertEquals(new BigInteger("123456789012345678901234567890"),
                TypeConversion.convert("biginteger", "123456789012345678901234567890"));
    }

    @Test
    void convertBigIntegerFullyQualified() {
        assertEquals(BigInteger.ZERO, TypeConversion.convert("java.math.biginteger", "0"));
    }

    @Test
    void convertBigDecimal() {
        assertEquals(new BigDecimal("1234567890.123456789"),
                TypeConversion.convert("bigdecimal", "1234567890.123456789"));
    }

    @Test
    void convertBigDecimalFullyQualified() {
        assertEquals(BigDecimal.ZERO, TypeConversion.convert("java.math.bigdecimal", "0"));
    }

    // -------------------------------------------------------------------------
    // Boolean flexible parsing
    // -------------------------------------------------------------------------

    @ParameterizedTest
    @ValueSource(strings = {"true", "t", "yes", "y", "1", "TRUE", "True", "YES", "Yes", "T", "Y"})
    void convertBoolTruthyValues(String value) {
        assertEquals(Boolean.TRUE, TypeConversion.convert("bool", value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"false", "f", "no", "n", "0", "FALSE", "False", "NO", "No", "F", "N"})
    void convertBoolFalsyValues(String value) {
        assertEquals(Boolean.FALSE, TypeConversion.convert("bool", value));
    }

    @Test
    void convertBooleanAlias() {
        assertEquals(Boolean.TRUE, TypeConversion.convert("boolean", "true"));
    }

    @Test
    void convertBooleanFullyQualified() {
        assertEquals(Boolean.FALSE, TypeConversion.convert("java.lang.boolean", "false"));
    }

    // -------------------------------------------------------------------------
    // Type name normalisation (case-insensitive, trimmed)
    // -------------------------------------------------------------------------

    @ParameterizedTest
    @CsvSource({"INT,42", "Int,42", "INTEGER,42", "Integer,-1"})
    void convertIsCaseInsensitive(String typeName, String rawValue) {
        assertNotNull(TypeConversion.convert(typeName, rawValue));
    }

    @Test
    void convertTrimsWhitespaceInTypeName() {
        assertEquals(5, TypeConversion.convert("  int  ", "5"));
    }

    // -------------------------------------------------------------------------
    // convert() – error cases
    // -------------------------------------------------------------------------

    @Test
    void convertThrowsForUnknownType() {
        assertThrows(IllegalArgumentException.class,
                () -> TypeConversion.convert("unknowntype", "123"));
    }

    @Test
    void convertThrowsForInvalidIntValue() {
        assertThrows(IllegalArgumentException.class,
                () -> TypeConversion.convert("int", "notanumber"));
    }

    @Test
    void convertThrowsForInvalidUUID() {
        assertThrows(IllegalArgumentException.class,
                () -> TypeConversion.convert("uuid", "not-a-uuid"));
    }

    @Test
    void convertThrowsForNullType() {
        assertThrows(IllegalArgumentException.class,
                () -> TypeConversion.convert(null, "42"));
    }

    // -------------------------------------------------------------------------
    // tryConvert() – all outcomes
    // -------------------------------------------------------------------------

    @Test
    void tryConvertReturnsValueOnSuccess() {
        Optional<Object> result = TypeConversion.tryConvert("int", "99");
        assertTrue(result.isPresent());
        assertEquals(99, result.get());
    }

    @Test
    void tryConvertReturnsEmptyForNullType() {
        assertEquals(Optional.empty(), TypeConversion.tryConvert(null, "42"));
    }

    @Test
    void tryConvertReturnsEmptyForUnknownType() {
        assertEquals(Optional.empty(), TypeConversion.tryConvert("unknowntype", "42"));
    }

    @Test
    void tryConvertReturnsEmptyForInvalidValue() {
        assertEquals(Optional.empty(), TypeConversion.tryConvert("int", "notanumber"));
    }

    @Test
    void tryConvertReturnsEmptyForInvalidUUID() {
        assertEquals(Optional.empty(), TypeConversion.tryConvert("uuid", "bad-uuid"));
    }

    @Test
    void tryConvertStringNullValue() {
        // String pass-through returns null → Optional.ofNullable(null) → empty
        assertEquals(Optional.empty(), TypeConversion.tryConvert("string", null));
    }

    // -------------------------------------------------------------------------
    // register() / unregister()
    // -------------------------------------------------------------------------

    @Test
    void registerCustomType() {
        TypeConversion.register("mytype", s -> s.toUpperCase());
        assertEquals("HELLO", TypeConversion.convert("mytype", "hello"));
        TypeConversion.unregister("mytype");
    }

    @Test
    void registerNullTypeNameIsNoop() {
        // Should not throw; registry unchanged
        assertDoesNotThrow(() -> TypeConversion.register(null, s -> s));
    }

    @Test
    void registerNullParserIsNoop() {
        assertDoesNotThrow(() -> TypeConversion.register("nullparser", null));
        // Ensure the null-named type was not registered
        assertEquals(Optional.empty(), TypeConversion.tryConvert("nullparser", "x"));
    }

    @Test
    void unregisterExistingType() {
        TypeConversion.register("temptype", Integer::parseInt);
        TypeConversion.unregister("temptype");
        assertEquals(Optional.empty(), TypeConversion.tryConvert("temptype", "1"));
    }

    @Test
    void unregisterNullIsNoop() {
        assertDoesNotThrow(() -> TypeConversion.unregister(null));
    }

    @Test
    void registerOverwritesExistingParser() {
        // "string" already maps to pass-through; overwrite it
        Function<String, Object> original = s -> s;
        TypeConversion.register("string", s -> "overwritten");
        assertEquals("overwritten", TypeConversion.convert("string", "anything"));
        // Restore the original pass-through so other tests are not affected
        TypeConversion.register("string", original);
    }

    // -------------------------------------------------------------------------
    // getRegistryView() – immutable view
    // -------------------------------------------------------------------------

    @Test
    void getRegistryViewIsUnmodifiable() {
        var view = TypeConversion.getRegistryView();
        assertThrows(UnsupportedOperationException.class,
                () -> view.put("newtype", s -> s));
    }

    @Test
    void getRegistryViewContainsBuiltInTypes() {
        var view = TypeConversion.getRegistryView();
        assertTrue(view.containsKey("int"));
        assertTrue(view.containsKey("bool"));
        assertTrue(view.containsKey("string"));
        assertTrue(view.containsKey("uuid"));
        assertTrue(view.containsKey("bigdecimal"));
    }
}

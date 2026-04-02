package com.buuz135.simpleclaims.claim.party;

import com.buuz135.simpleclaims.util.TypeConversion;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PartyOverrideTest {

    // -------------------------------------------------------------------------
    // PartyOverride – constructors and accessors
    // -------------------------------------------------------------------------

    @Test
    void parametrisedConstructorStoresType() {
        var value = new PartyOverride.PartyOverrideValue("bool", true);
        var override = new PartyOverride(PartyOverrides.PARTY_PROTECTION_PVP, value);
        assertEquals(PartyOverrides.PARTY_PROTECTION_PVP, override.getType());
    }

    @Test
    void parametrisedConstructorStoresValue() {
        var value = new PartyOverride.PartyOverrideValue("integer", 50);
        var override = new PartyOverride(PartyOverrides.CLAIM_CHUNK_AMOUNT, value);
        assertSame(value, override.getValue());
    }

    @Test
    void defaultConstructorCreatesInstanceWithNullFields() {
        var override = new PartyOverride();
        assertNull(override.getType());
        assertNull(override.getValue());
    }

    @Test
    void setTypeUpdatesValue() {
        var override = new PartyOverride();
        override.setType("customtype");
        assertEquals("customtype", override.getType());
    }

    @Test
    void setValueUpdatesReference() {
        var override = new PartyOverride();
        var value = new PartyOverride.PartyOverrideValue("string", "test");
        override.setValue(value);
        assertSame(value, override.getValue());
    }

    @Test
    void toStringContainsType() {
        var override = new PartyOverride(PartyOverrides.PARTY_PROTECTION_BREAK_BLOCKS,
                new PartyOverride.PartyOverrideValue("bool", false));
        assertTrue(override.toString().contains(PartyOverrides.PARTY_PROTECTION_BREAK_BLOCKS));
    }

    // -------------------------------------------------------------------------
    // PartyOverrideValue – constructors
    // -------------------------------------------------------------------------

    @Test
    void stringConstructorStoresType() {
        var v = new PartyOverride.PartyOverrideValue("string", "hello");
        assertEquals("string", v.getType());
    }

    @Test
    void stringConstructorStoresValue() {
        var v = new PartyOverride.PartyOverrideValue("string", "hello");
        assertEquals("hello", v.getValue());
    }

    @Test
    void intConstructorConvertsToString() {
        var v = new PartyOverride.PartyOverrideValue("integer", 42);
        assertEquals("42", v.getValue());
    }

    @Test
    void boolConstructorConvertsToString() {
        var v = new PartyOverride.PartyOverrideValue("bool", true);
        assertEquals("true", v.getValue());
    }

    @Test
    void defaultConstructorHasNullFields() {
        var v = new PartyOverride.PartyOverrideValue();
        assertNull(v.getType());
        assertNull(v.getValue());
    }

    @Test
    void setTypeUpdates() {
        var v = new PartyOverride.PartyOverrideValue();
        v.setType("long");
        assertEquals("long", v.getType());
    }

    @Test
    void setValueUpdates() {
        var v = new PartyOverride.PartyOverrideValue();
        v.setValue("99");
        assertEquals("99", v.getValue());
    }

    // -------------------------------------------------------------------------
    // PartyOverrideValue – getTypedValue()
    // -------------------------------------------------------------------------

    @Test
    void getTypedValueReturnsInteger() {
        var v = new PartyOverride.PartyOverrideValue("integer", 25);
        assertEquals(25, v.getTypedValue());
    }

    @Test
    void getTypedValueReturnsBooleanTrue() {
        var v = new PartyOverride.PartyOverrideValue("bool", true);
        assertEquals(Boolean.TRUE, v.getTypedValue());
    }

    @Test
    void getTypedValueReturnsBooleanFalse() {
        var v = new PartyOverride.PartyOverrideValue("bool", false);
        assertEquals(Boolean.FALSE, v.getTypedValue());
    }

    @Test
    void getTypedValueReturnsString() {
        var v = new PartyOverride.PartyOverrideValue("string", "abc");
        assertEquals("abc", v.getTypedValue());
    }

    @Test
    void getTypedValueThrowsForUnknownType() {
        var v = new PartyOverride.PartyOverrideValue("unknowntype", "value");
        assertThrows(IllegalArgumentException.class, v::getTypedValue);
    }

    // -------------------------------------------------------------------------
    // PartyOverrideValue – tryGetTypedValue()
    // -------------------------------------------------------------------------

    @Test
    void tryGetTypedValueReturnsIntegerPresent() {
        var v = new PartyOverride.PartyOverrideValue("integer", 7);
        Optional<Object> result = v.tryGetTypedValue();
        assertTrue(result.isPresent());
        assertEquals(7, result.get());
    }

    @Test
    void tryGetTypedValueReturnsBooleanPresent() {
        var v = new PartyOverride.PartyOverrideValue("bool", true);
        Optional<Object> result = v.tryGetTypedValue();
        assertTrue(result.isPresent());
        assertEquals(Boolean.TRUE, result.get());
    }

    @Test
    void tryGetTypedValueReturnsEmptyForUnknownType() {
        var v = new PartyOverride.PartyOverrideValue("nosuchtype", "val");
        assertEquals(Optional.empty(), v.tryGetTypedValue());
    }

    @Test
    void tryGetTypedValueReturnsEmptyForInvalidValue() {
        var v = new PartyOverride.PartyOverrideValue("integer", "notanumber");
        assertEquals(Optional.empty(), v.tryGetTypedValue());
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    @Test
    void partyOverrideValueToStringContainsTypeAndValue() {
        var v = new PartyOverride.PartyOverrideValue("bool", true);
        String s = v.toString();
        assertTrue(s.contains("bool"));
        assertTrue(s.contains("true"));
    }
}

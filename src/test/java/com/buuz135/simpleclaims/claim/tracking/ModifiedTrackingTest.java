package com.buuz135.simpleclaims.claim.tracking;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ModifiedTrackingTest {

    // -------------------------------------------------------------------------
    // Parameterised constructor
    // -------------------------------------------------------------------------

    @Test
    void parametrisedConstructorStoresUUID() {
        UUID uuid = UUID.randomUUID();
        var tracking = new ModifiedTracking(uuid, "Alice", "2024-01-01T00:00:00");
        assertEquals(uuid, tracking.getUserUUID());
    }

    @Test
    void parametrisedConstructorStoresName() {
        var tracking = new ModifiedTracking(UUID.randomUUID(), "Bob", "2024-06-15T12:00:00");
        assertEquals("Bob", tracking.getUserName());
    }

    @Test
    void parametrisedConstructorStoresDate() {
        var tracking = new ModifiedTracking(UUID.randomUUID(), "Charlie", "2025-03-10T08:30:00");
        assertEquals("2025-03-10T08:30:00", tracking.getDate());
    }

    // -------------------------------------------------------------------------
    // Default constructor
    // -------------------------------------------------------------------------

    @Test
    void defaultConstructorHasNonNullUUID() {
        var tracking = new ModifiedTracking();
        assertNotNull(tracking.getUserUUID());
    }

    @Test
    void defaultConstructorHasDashPlaceholderName() {
        var tracking = new ModifiedTracking();
        assertEquals("-", tracking.getUserName());
    }

    @Test
    void defaultConstructorHasEmptyDate() {
        var tracking = new ModifiedTracking();
        assertEquals("", tracking.getDate());
    }

    @Test
    void defaultConstructorGeneratesDistinctUUIDs() {
        // Each default instance should get its own random UUID
        var a = new ModifiedTracking();
        var b = new ModifiedTracking();
        assertNotEquals(a.getUserUUID(), b.getUserUUID());
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    @Test
    void toStringContainsUserName() {
        var tracking = new ModifiedTracking(UUID.randomUUID(), "Dave", "2024-07-01");
        assertTrue(tracking.toString().contains("Dave"));
    }

    @Test
    void toStringContainsDate() {
        var tracking = new ModifiedTracking(UUID.randomUUID(), "Eve", "2024-07-01");
        assertTrue(tracking.toString().contains("2024-07-01"));
    }

    @Test
    void toStringContainsUUID() {
        UUID uuid = UUID.randomUUID();
        var tracking = new ModifiedTracking(uuid, "Frank", "");
        assertTrue(tracking.toString().contains(uuid.toString()));
    }
}

package com.buuz135.simpleclaims.claim.chunk;

import com.buuz135.simpleclaims.claim.tracking.ModifiedTracking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ChunkInfoTest {

    // -------------------------------------------------------------------------
    // formatCoordinates – static utility
    // -------------------------------------------------------------------------

    @ParameterizedTest
    @CsvSource({
        "0,  0,  '0:0'",
        "5,  3,  '5:3'",
        "-1, 10, '-1:10'",
        "99, -99, '99:-99'"
    })
    void formatCoordinates(int chunkX, int chunkZ, String expected) {
        assertEquals(expected, ChunkInfo.formatCoordinates(chunkX, chunkZ));
    }

    // -------------------------------------------------------------------------
    // Parameterised constructor
    // -------------------------------------------------------------------------

    @Test
    void parametrisedConstructorStoresPartyOwner() {
        UUID owner = UUID.randomUUID();
        var info = new ChunkInfo(owner, 3, 7);
        assertEquals(owner, info.getPartyOwner());
    }

    @Test
    void parametrisedConstructorStoresChunkX() {
        var info = new ChunkInfo(UUID.randomUUID(), 12, 0);
        assertEquals(12, info.getChunkX());
    }

    @Test
    void parametrisedConstructorStoresChunkZ() {
        var info = new ChunkInfo(UUID.randomUUID(), 0, -5);
        assertEquals(-5, info.getChunkZ());
    }

    @Test
    void parametrisedConstructorCreatesCreatedTracker() {
        var info = new ChunkInfo(UUID.randomUUID(), 0, 0);
        assertNotNull(info.getCreatedTracked());
    }

    // -------------------------------------------------------------------------
    // Default constructor
    // -------------------------------------------------------------------------

    @Test
    void defaultConstructorSetsChunkXToZero() {
        var info = new ChunkInfo();
        assertEquals(0, info.getChunkX());
    }

    @Test
    void defaultConstructorSetsChunkZToZero() {
        var info = new ChunkInfo();
        assertEquals(0, info.getChunkZ());
    }

    @Test
    void defaultConstructorHasNonNullPartyOwner() {
        var info = new ChunkInfo();
        assertNotNull(info.getPartyOwner());
    }

    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------

    @Test
    void setPartyOwnerUpdatesValue() {
        var info = new ChunkInfo();
        UUID newOwner = UUID.randomUUID();
        info.setPartyOwner(newOwner);
        assertEquals(newOwner, info.getPartyOwner());
    }

    @Test
    void setChunkXUpdatesValue() {
        var info = new ChunkInfo();
        info.setChunkX(42);
        assertEquals(42, info.getChunkX());
    }

    @Test
    void setChunkZUpdatesValue() {
        var info = new ChunkInfo();
        info.setChunkZ(-99);
        assertEquals(-99, info.getChunkZ());
    }

    @Test
    void setCreatedTrackedUpdatesValue() {
        var info = new ChunkInfo();
        var tracking = new ModifiedTracking(UUID.randomUUID(), "Tester", "2025-01-01");
        info.setCreatedTracked(tracking);
        assertSame(tracking, info.getCreatedTracked());
    }

    // -------------------------------------------------------------------------
    // getCoordinates – delegates to formatCoordinates
    // -------------------------------------------------------------------------

    @Test
    void getCoordinatesMatchesFormatCoordinates() {
        var info = new ChunkInfo(UUID.randomUUID(), 8, -3);
        assertEquals(ChunkInfo.formatCoordinates(8, -3), info.getCoordinates());
    }

    // -------------------------------------------------------------------------
    // DimensionStorage inner class
    // -------------------------------------------------------------------------

    @Test
    void dimensionStorageDefaultConstructorCreatesEmptyArray() {
        var storage = new ChunkInfo.DimensionStorage();
        assertNotNull(storage.getChunkInfoStorages());
        assertEquals(0, storage.getChunkInfoStorages().length);
    }

    @Test
    void dimensionStorageParametrisedConstructorStoresData() {
        var entries = new ChunkInfo.ChunkInfoStorage[]{
            new ChunkInfo.ChunkInfoStorage("overworld", new ChunkInfo[0])
        };
        var storage = new ChunkInfo.DimensionStorage(entries);
        assertArrayEquals(entries, storage.getChunkInfoStorages());
    }

    @Test
    void dimensionStorageSetterUpdatesValue() {
        var storage = new ChunkInfo.DimensionStorage();
        var entries = new ChunkInfo.ChunkInfoStorage[]{
            new ChunkInfo.ChunkInfoStorage("nether", new ChunkInfo[0])
        };
        storage.setChunkInfoStorages(entries);
        assertArrayEquals(entries, storage.getChunkInfoStorages());
    }

    // -------------------------------------------------------------------------
    // ChunkInfoStorage inner class
    // -------------------------------------------------------------------------

    @Test
    void chunkInfoStorageDefaultConstructorEmptyDimension() {
        var s = new ChunkInfo.ChunkInfoStorage();
        assertEquals("", s.getDimension());
    }

    @Test
    void chunkInfoStorageDefaultConstructorEmptyChunks() {
        var s = new ChunkInfo.ChunkInfoStorage();
        assertNotNull(s.getChunkInfos());
        assertEquals(0, s.getChunkInfos().length);
    }

    @Test
    void chunkInfoStorageParametrisedConstructorStoresDimension() {
        var s = new ChunkInfo.ChunkInfoStorage("overworld", new ChunkInfo[0]);
        assertEquals("overworld", s.getDimension());
    }

    @Test
    void chunkInfoStorageParametrisedConstructorStoresChunks() {
        var chunks = new ChunkInfo[]{new ChunkInfo(UUID.randomUUID(), 1, 2)};
        var s = new ChunkInfo.ChunkInfoStorage("overworld", chunks);
        assertArrayEquals(chunks, s.getChunkInfos());
    }

    @Test
    void chunkInfoStorageSetDimensionUpdatesValue() {
        var s = new ChunkInfo.ChunkInfoStorage();
        s.setDimension("nether");
        assertEquals("nether", s.getDimension());
    }

    @Test
    void chunkInfoStorageSetChunkInfosUpdatesValue() {
        var s = new ChunkInfo.ChunkInfoStorage();
        var chunks = new ChunkInfo[]{new ChunkInfo()};
        s.setChunkInfos(chunks);
        assertArrayEquals(chunks, s.getChunkInfos());
    }
}

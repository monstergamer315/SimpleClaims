package com.buuz135.simpleclaims.claim.party;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartyOverridesTest {

    @Test
    void claimChunkAmountKey() {
        assertEquals("simpleclaims.claim.amount", PartyOverrides.CLAIM_CHUNK_AMOUNT);
    }

    @Test
    void partyProtectionPlaceBlocksKey() {
        assertEquals("simpleclaims.party.protection.place_blocks", PartyOverrides.PARTY_PROTECTION_PLACE_BLOCKS);
    }

    @Test
    void partyProtectionBreakBlocksKey() {
        assertEquals("simpleclaims.party.protection.break_blocks", PartyOverrides.PARTY_PROTECTION_BREAK_BLOCKS);
    }

    @Test
    void partyProtectionInteractKey() {
        assertEquals("simpleclaims.party.protection.interact", PartyOverrides.PARTY_PROTECTION_INTERACT);
    }

    @Test
    void partyProtectionPvpKey() {
        assertEquals("simpleclaims.party.protection.pvp", PartyOverrides.PARTY_PROTECTION_PVP);
    }

    @Test
    void partyProtectionFriendlyFireKey() {
        assertEquals("simpleclaims.party.protection.friendly_fire", PartyOverrides.PARTY_PROTECTION_FRIENDLY_FIRE);
    }

    @Test
    void partyProtectionAllowEntryKey() {
        assertEquals("simpleclaims.party.protection.allow_entry", PartyOverrides.PARTY_PROTECTION_ALLOW_ENTRY);
    }

    @Test
    void partyProtectionInteractChestKey() {
        assertEquals("simpleclaims.party.protection.interact.chest", PartyOverrides.PARTY_PROTECTION_INTERACT_CHEST);
    }

    @Test
    void partyProtectionInteractDoorKey() {
        assertEquals("simpleclaims.party.protection.interact.door", PartyOverrides.PARTY_PROTECTION_INTERACT_DOOR);
    }

    @Test
    void partyProtectionInteractBenchKey() {
        assertEquals("simpleclaims.party.protection.interact.bench", PartyOverrides.PARTY_PROTECTION_INTERACT_BENCH);
    }

    @Test
    void partyProtectionInteractChairKey() {
        assertEquals("simpleclaims.party.protection.interact.chair", PartyOverrides.PARTY_PROTECTION_INTERACT_CHAIR);
    }

    @Test
    void partyProtectionInteractPortalKey() {
        assertEquals("simpleclaims.party.protection.interact.portal", PartyOverrides.PARTY_PROTECTION_INTERACT_PORTAL);
    }

    @Test
    void allKeysAreDistinct() {
        var keys = new String[]{
            PartyOverrides.CLAIM_CHUNK_AMOUNT,
            PartyOverrides.PARTY_PROTECTION_PLACE_BLOCKS,
            PartyOverrides.PARTY_PROTECTION_BREAK_BLOCKS,
            PartyOverrides.PARTY_PROTECTION_INTERACT,
            PartyOverrides.PARTY_PROTECTION_PVP,
            PartyOverrides.PARTY_PROTECTION_FRIENDLY_FIRE,
            PartyOverrides.PARTY_PROTECTION_ALLOW_ENTRY,
            PartyOverrides.PARTY_PROTECTION_INTERACT_CHEST,
            PartyOverrides.PARTY_PROTECTION_INTERACT_DOOR,
            PartyOverrides.PARTY_PROTECTION_INTERACT_BENCH,
            PartyOverrides.PARTY_PROTECTION_INTERACT_CHAIR,
            PartyOverrides.PARTY_PROTECTION_INTERACT_PORTAL
        };
        var unique = new java.util.HashSet<>(java.util.Arrays.asList(keys));
        assertEquals(keys.length, unique.size(), "All override key constants must be unique");
    }
}

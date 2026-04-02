package com.buuz135.simpleclaims.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleClaimsConfigTest {

    private final SimpleClaimsConfig config = new SimpleClaimsConfig();

    // -------------------------------------------------------------------------
    // Default integer values
    // -------------------------------------------------------------------------

    @Test
    void defaultPartyClaimsAmountIs25() {
        assertEquals(25, config.getDefaultPartyClaimsAmount());
    }

    @Test
    void maxAddChunkAmountIs100() {
        assertEquals(100, config.getMaxAddChunkAmount());
    }

    @Test
    void claimChunkGainInMinutesIsDisabledByDefault() {
        assertEquals(-1, config.getClaimChunkGainInMinutes());
    }

    @Test
    void maxPartyMembersIsUnlimitedByDefault() {
        assertEquals(-1, config.getMaxPartyMembers());
    }

    @Test
    void maxPartyAlliesIsUnlimitedByDefault() {
        assertEquals(-1, config.getMaxPartyAllies());
    }

    @Test
    void partyInactivityHoursIsDisabledByDefault() {
        assertEquals(-1, config.getPartyInactivityHours());
    }

    // -------------------------------------------------------------------------
    // Default boolean – protection off by default except allow-entry
    // -------------------------------------------------------------------------

    @Test
    void defaultPartyBlockPlaceIsDisabled() {
        assertFalse(config.isDefaultPartyBlockPlaceEnabled());
    }

    @Test
    void defaultPartyBlockBreakIsDisabled() {
        assertFalse(config.isDefaultPartyBlockBreakEnabled());
    }

    @Test
    void defaultPartyBlockInteractIsDisabled() {
        assertFalse(config.isDefaultPartyBlockInteractEnabled());
    }

    @Test
    void defaultPartyPVPIsDisabled() {
        assertFalse(config.isDefaultPartyPVPEnabled());
    }

    @Test
    void defaultPartyFriendlyFireIsDisabled() {
        assertFalse(config.isDefaultPartyFriendlyFireEnabled());
    }

    @Test
    void defaultPartyAllowEntryIsEnabled() {
        assertTrue(config.isDefaultPartyAllowEntry());
    }

    @Test
    void defaultPartyInteractChestIsDisabled() {
        assertFalse(config.isDefaultPartyInteractChest());
    }

    @Test
    void defaultPartyInteractDoorIsDisabled() {
        assertFalse(config.isDefaultPartyInteractDoor());
    }

    @Test
    void defaultPartyInteractBenchIsDisabled() {
        assertFalse(config.isDefaultPartyInteractBench());
    }

    @Test
    void defaultPartyInteractChairIsDisabled() {
        assertFalse(config.isDefaultPartyInteractChair());
    }

    @Test
    void defaultPartyInteractPortalIsDisabled() {
        assertFalse(config.isDefaultPartyInteractPortal());
    }

    // -------------------------------------------------------------------------
    // Default boolean – all override settings allowed by default
    // -------------------------------------------------------------------------

    @Test
    void allowPartyPVPSettingIsEnabledByDefault() {
        assertTrue(config.isAllowPartyPVPSetting());
    }

    @Test
    void allowPartyFriendlyFireSettingIsEnabledByDefault() {
        assertTrue(config.isAllowPartyFriendlyFireSetting());
    }

    @Test
    void allowPartyPlaceBlockSettingIsEnabledByDefault() {
        assertTrue(config.isAllowPartyPlaceBlockSetting());
    }

    @Test
    void allowPartyBreakBlockSettingIsEnabledByDefault() {
        assertTrue(config.isAllowPartyBreakBlockSetting());
    }

    @Test
    void allowPartyInteractBlockSettingIsEnabledByDefault() {
        assertTrue(config.isAllowPartyInteractBlockSetting());
    }

    @Test
    void allowPartyAllowEntrySettingIsEnabledByDefault() {
        assertTrue(config.isAllowPartyAllowEntrySetting());
    }

    @Test
    void allowPartyInteractChestSettingIsEnabledByDefault() {
        assertTrue(config.isAllowPartyInteractChestSetting());
    }

    @Test
    void allowPartyInteractDoorSettingIsEnabledByDefault() {
        assertTrue(config.isAllowPartyInteractDoorSetting());
    }

    @Test
    void allowPartyInteractBenchSettingIsEnabledByDefault() {
        assertTrue(config.isAllowPartyInteractBenchSetting());
    }

    @Test
    void allowPartyInteractChairSettingIsEnabledByDefault() {
        assertTrue(config.isAllowPartyInteractChairSetting());
    }

    @Test
    void allowPartyInteractPortalSettingIsEnabledByDefault() {
        assertTrue(config.isAllowPartyInteractPortalSetting());
    }

    // -------------------------------------------------------------------------
    // Default string and array values
    // -------------------------------------------------------------------------

    @Test
    void partyCommandAliasesAreNonEmpty() {
        assertNotNull(config.getPartyCommandAliases());
        assertTrue(config.getPartyCommandAliases().length > 0);
    }

    @Test
    void claimCommandAliasesAreNonEmpty() {
        assertNotNull(config.getClaimCommandAliases());
        assertTrue(config.getClaimCommandAliases().length > 0);
    }

    @Test
    void worldNameBlacklistIsEmptyByDefault() {
        assertNotNull(config.getWorldNameBlacklistForClaiming());
        assertEquals(0, config.getWorldNameBlacklistForClaiming().length);
    }

    @Test
    void fullWorldProtectionIsEmptyByDefault() {
        assertNotNull(config.getFullWorldProtection());
        assertEquals(0, config.getFullWorldProtection().length);
    }

    @Test
    void titleTopClaimTitleTextDefault() {
        assertEquals("Simple Claims", config.getTitleTopClaimTitleText());
    }

    @Test
    void wildernessNameDefault() {
        assertEquals("Wilderness", config.getWildernessName());
    }

    @Test
    void blocksThatIgnoreInteractRestrictionsDefault() {
        assertNotNull(config.getBlocksThatIgnoreInteractRestrictions());
        assertTrue(config.getBlocksThatIgnoreInteractRestrictions().length > 0);
    }

    // -------------------------------------------------------------------------
    // Misc boolean defaults
    // -------------------------------------------------------------------------

    @Test
    void enableAllyEntryTestingIsDisabledByDefault() {
        assertFalse(config.isEnableAlloyEntryTesting());
    }

    @Test
    void enableParticleBordersIsEnabledByDefault() {
        assertTrue(config.isEnableParticleBorders());
    }

    @Test
    void renderClaimNamesOnWorldMapIsDisabledByDefault() {
        assertFalse(config.isRenderClaimNamesOnWorldMap());
    }

    @Test
    void renderMapInClaimUIIsEnabledByDefault() {
        assertTrue(config.isRenderMapInClaimUI());
    }

    @Test
    void forceSimpleClaimsChunkWorldMapIsEnabledByDefault() {
        assertTrue(config.isForceSimpleClaimsChunkWorldMap());
    }

    @Test
    void creativeModeBypassProtectionIsDisabledByDefault() {
        assertFalse(config.isCreativeModeBypassProtection());
    }

    @Test
    void notifyPartyChatTogglingIsEnabledByDefault() {
        assertTrue(config.isNotifyPartyChatToggling());
    }
}

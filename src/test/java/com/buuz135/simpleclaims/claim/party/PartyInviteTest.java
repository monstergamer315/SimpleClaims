package com.buuz135.simpleclaims.claim.party;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PartyInviteTest {

    private final UUID recipient = UUID.randomUUID();
    private final UUID sender    = UUID.randomUUID();
    private final UUID party     = UUID.randomUUID();

    @Test
    void recipientIsAccessible() {
        var invite = new PartyInvite(recipient, sender, party);
        assertEquals(recipient, invite.recipient());
    }

    @Test
    void senderIsAccessible() {
        var invite = new PartyInvite(recipient, sender, party);
        assertEquals(sender, invite.sender());
    }

    @Test
    void partyIsAccessible() {
        var invite = new PartyInvite(recipient, sender, party);
        assertEquals(party, invite.party());
    }

    @Test
    void equalInvitesAreEqual() {
        var a = new PartyInvite(recipient, sender, party);
        var b = new PartyInvite(recipient, sender, party);
        assertEquals(a, b);
    }

    @Test
    void differentRecipientMakesInvitesUnequal() {
        var a = new PartyInvite(recipient, sender, party);
        var b = new PartyInvite(UUID.randomUUID(), sender, party);
        assertNotEquals(a, b);
    }

    @Test
    void differentSenderMakesInvitesUnequal() {
        var a = new PartyInvite(recipient, sender, party);
        var b = new PartyInvite(recipient, UUID.randomUUID(), party);
        assertNotEquals(a, b);
    }

    @Test
    void differentPartyMakesInvitesUnequal() {
        var a = new PartyInvite(recipient, sender, party);
        var b = new PartyInvite(recipient, sender, UUID.randomUUID());
        assertNotEquals(a, b);
    }

    @Test
    void hashCodeIsConsistentWithEquals() {
        var a = new PartyInvite(recipient, sender, party);
        var b = new PartyInvite(recipient, sender, party);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void toStringContainsComponents() {
        var invite = new PartyInvite(recipient, sender, party);
        String s = invite.toString();
        assertTrue(s.contains(recipient.toString()), "toString should include recipient UUID");
        assertTrue(s.contains(sender.toString()),    "toString should include sender UUID");
        assertTrue(s.contains(party.toString()),     "toString should include party UUID");
    }
}

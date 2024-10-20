package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void testPlayerName() {
        Player player = new Player("John Doe");
        assertEquals("John Doe", player.getName());
    }
}

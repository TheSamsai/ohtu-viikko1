/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sami
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
        
    };
    
    Statistics stats;
    
    public StatisticsTest() {
        stats = new Statistics(readerStub);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void findsPlayerByName() {
        assert(stats.search("Semenko") != null);
    }
    
    @Test
    public void returnsNullOnNonExistentName() {
        assert(stats.search("ÖÖÖÖÖÖÖÖÖÖÖ") == null);
    }
    
    @Test
    public void findsAllTeamMembers() {
        assert(stats.team("EDM").size() == 3);
    }
    
    @Test
    public void nonExistentTeamReturnsZero() {
        assert(stats.team("ÖÖÖ").size() == 0);
    }
    
    @Test
    public void topScorersInRightOrder() {
        int previousPoints = -1;
        
        for (Player p : stats.topScorers(4)) {
            if (previousPoints == -1) {
                previousPoints = p.getPoints();
            } else {
                if (previousPoints < p.getPoints()) {
                    // Discrepency detected
                    assert(false);
                } else {
                    previousPoints = p.getPoints();
                }
            }
        }
        
        // All are fine.
        assert(true);
    }
}

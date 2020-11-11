package pt.pa.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkManagerTest {
    private BookmarkManager manager;
    @BeforeEach
    void setUp() {
       manager= new BookmarkManager();
    }

    @org.junit.jupiter.api.Test
    void getTotalEntries() {
        assertEquals(0,manager.getTotalEntries());
        manager.addBookmarkFolder("bookmarks", "Redes Sociais");
        manager.addBookmarkFolder("bookmarks", "Diversos");
        assertEquals(2,manager.getTotalEntries());
        manager.addBookmarkEntry("diversos", "Gmail", "http://www.gmail.com");
        manager.addBookmarkEntry("diversos", "StackOverflow", "http://www.stackoverflow.com");
        assertEquals(4,manager.getTotalEntries());
    }

    @Test
    void getTotalFolders() {
        assertEquals(1,manager.getTotalFolders());
        manager.addBookmarkFolder("bookmarks", "Redes Sociais");
        manager.addBookmarkFolder("bookmarks", "Diversos");
        assertEquals(3,manager.getTotalFolders());
        manager.addBookmarkEntry("diversos", "Gmail", "http://www.gmail.com");
        manager.addBookmarkEntry("diversos", "StackOverflow", "http://www.stackoverflow.com");
        assertEquals(3,manager.getTotalFolders());
    }

    @Test
    void fullPathOf() {
        assertEquals("- Bookmarks",manager.fullPathOf("Bookmarks"));
        manager.addBookmarkFolder("bookmarks", "Redes Sociais");
        assertEquals("- Redes Sociais - Bookmarks",manager.fullPathOf("Redes Sociais"));
        manager.addBookmarkFolder("redes sociais", "Nova rede teste");
        assertEquals("- Nova rede teste - Redes Sociais - Bookmarks",manager.fullPathOf("Nova rede teste"));
    }


}
package pt.pa.model;

import javafx.geometry.Pos;
import pt.pa.adts.Position;
import pt.pa.adts.Tree;
import pt.pa.adts.TreeLinked;

import java.awt.print.Book;

public class BookmarkManager {
    private Tree<BookmarkEntry> bookmarks;

    public BookmarkManager() {
        BookmarkEntry bme = new BookmarkEntry("Bookmarks");
        bookmarks = new TreeLinked<>(bme);
    }

    private Position<BookmarkEntry> find(String key){
        Iterable<Position<BookmarkEntry>> tempList = bookmarks.positions();
        for(Position<BookmarkEntry> pos : tempList) {
            if(pos.element().getKey().equalsIgnoreCase(key))
                return pos;
        }
        return null;
    }

    private boolean exists(String key) {
        Iterable<BookmarkEntry> tempList = bookmarks.elements();
        for(BookmarkEntry bm : tempList) {
            if(bm.getKey().equalsIgnoreCase(key))
                return true;
        }
        return false;
    }

    /*
    private boolean exists(String key) {
        return find(key) != null;
    }
     */

    public void addBookmarkFolder(String keyParent, String keyFolder) throws BookmarkInvalidOperation {
        if(keyParent == null || keyFolder == null || !exists(keyParent) || exists(keyFolder))
            throw new BookmarkInvalidOperation("Invalid arguments");
        Position<BookmarkEntry> parent = find(keyParent);
        BookmarkEntry folder = new BookmarkEntry(keyFolder);
        bookmarks.insert(parent, folder);
    }

    public void addBookmarkEntry(String keyParent, String keyEntry, String url) throws BookmarkInvalidOperation {
        if(keyParent == null || keyEntry == null || url == null || !exists(keyParent) || exists(keyEntry))
            throw new BookmarkInvalidOperation("Invalid arguments");
        Position<BookmarkEntry> parent = find(keyParent);
        BookmarkEntry folder = new BookmarkEntry(keyEntry, url);
        bookmarks.insert(parent, folder);
    }

    public int getTotalEntries() {
        int total = bookmarks.size() - 1;
        return total;
    }

    public String getParentFolder(String keyEntry) {
        if(!exists(keyEntry) || !find(keyEntry).element().isFolder())
            throw new BookmarkInvalidOperation("Invalid arguments");
        Position<BookmarkEntry> parent = find(keyEntry);
        String folder = parent.element().getKey();
        return folder;
    }

    public Tree<BookmarkEntry> getBookmarkTree() {
        return bookmarks;
    }

    public int getTotalFolders() {
        int totalFolders = 0;
        Iterable<BookmarkEntry> list = bookmarks.elements();
        for(BookmarkEntry bme : list) {
            if(bme.isFolder())
                totalFolders++;
        }
        return totalFolders;
    }

    public String fullPathOf(String keyEntry) throws BookmarkInvalidOperation {
        if(find(keyEntry) == null)
            throw new BookmarkInvalidOperation("Invalid key entry");
        Position<BookmarkEntry> p = find(keyEntry);
        String path = "- " + keyEntry;
        while(bookmarks.parent(p) != null) {
            p = bookmarks.parent(p);
            path += " - " +  p.element().getKey();
        }
        return path;
    }
}

package myy803.springboot.OnlineBookStore.forms;

import jakarta.persistence.Column;

public class BookData {
    private String title;
    private String authors;
    private String category;

    public String title() {
        return title;
    }

    public void setBook_title(String book_title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

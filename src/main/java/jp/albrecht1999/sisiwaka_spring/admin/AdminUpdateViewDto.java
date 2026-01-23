package jp.albrecht1999.sisiwaka_spring.admin;

import java.time.LocalDate;

public class AdminUpdateViewDto {
    private final Long id;
    private final LocalDate date;
    private final String article;
    private final boolean valid;

    public AdminUpdateViewDto(Long id, LocalDate date, String article, boolean valid) {
        this.id = id;
        this.date = date;
        this.article = article;
        this.valid = valid;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getCreatedAt() {
        return date;
    }

    public String getArticle() {
        return article;
    }

    public boolean isValid() {
        return valid;
    }
}

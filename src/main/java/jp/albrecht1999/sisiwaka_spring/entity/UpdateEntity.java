package jp.albrecht1999.sisiwaka_spring.entity;

import java.time.LocalDate;

public class UpdateEntity {

    private Long id;
    private LocalDate createdAt;
    private String article;
    private boolean valid;

    public UpdateEntity(Long id, LocalDate createdAt, String article, boolean valid) {
        this.id = id;
        this.createdAt = createdAt;
        this.article = article;
        this.valid = valid;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getArticle() {
        return article;
    }

    public boolean isValid() {
        return valid;
    }
}

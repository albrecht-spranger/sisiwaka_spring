package jp.albrecht1999.sisiwaka_spring.entity;

import java.time.OffsetDateTime;

public class UpdateEntity {

    private Long id;
    private OffsetDateTime createdAt;
    private String article;
    private boolean valid;

    public UpdateEntity(Long id, OffsetDateTime createdAt, String article, boolean valid) {
        this.id = id;
        this.createdAt = createdAt;
        this.article = article;
        this.valid = valid;
    }

    public Long getId() {
        return id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getArticle() {
        return article;
    }

    public boolean isValid() {
        return valid;
    }
}

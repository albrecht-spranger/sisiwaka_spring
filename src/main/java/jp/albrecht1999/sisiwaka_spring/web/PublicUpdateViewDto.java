package jp.albrecht1999.sisiwaka_spring.web;

import java.time.LocalDate;

public class PublicUpdateViewDto {
    private final LocalDate date;
    private final String article;

    public PublicUpdateViewDto(LocalDate date, String article) {
        this.date = date;
        this.article = article;
    }

    public LocalDate getCreatedAt() {
        return date;
    }

    public String getArticle() {
        return article;
    }
}

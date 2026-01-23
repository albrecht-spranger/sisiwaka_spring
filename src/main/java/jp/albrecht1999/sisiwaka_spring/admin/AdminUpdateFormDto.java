package jp.albrecht1999.sisiwaka_spring.admin;

import java.time.LocalDate;

/**
 * フォームの1行分（編集・削除など）
 */
public class AdminUpdateFormDto {

    private Long id; // 既存行は必須。新規行はnullでもOKにするなら許容
    private LocalDate createdAt; // DBがdateならLocalDateが相性◎
    private String article;

    private Boolean valid; // checkbox未送信対策で Boolean 推奨（nullあり得る）
    private Boolean delete; // ★削除チェック（未送信だとnull）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    /**
     * nullをfalse扱いにするヘルパ（Service側で使うと楽）
     */
    public boolean isValidChecked() {
        return Boolean.TRUE.equals(valid);
    }

    public boolean isDeleteChecked() {
        return Boolean.TRUE.equals(delete);
    }
}

package jp.albrecht1999.sisiwaka_spring.admin;

public record UpdateRowDto(
        Long id,
        String createdAt,
        String article,
        boolean valid) {
}

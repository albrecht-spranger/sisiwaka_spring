package jp.albrecht1999.sisiwaka_spring.entity;

import java.time.OffsetDateTime;

public record ValidUpdateRow(
        OffsetDateTime createdAt,
        String article) {
}

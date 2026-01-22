package jp.albrecht1999.sisiwaka_spring.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.albrecht1999.sisiwaka_spring.repository.UpdatesRepository;
import jp.albrecht1999.sisiwaka_spring.entity.UpdateEntity;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UpdatesService {

    private final UpdatesRepository repo;
    private final ZoneId zone = ZoneId.of("Asia/Tokyo");
    private final DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public UpdatesService(UpdatesRepository repo) {
        this.repo = repo;
    }

    public List<UpdateRowDto> listAll() {
        return repo.findAll();
    }

    @Transactional
    public int saveAll(UpdatesForm form) {
        int n = 0;

        for (UpdateRowDto r : form.getRows()) {
            String createdAtStr = r.getCreatedAt() == null ? "" : r.getCreatedAt().trim();
            String article = r.getArticle() == null ? "" : r.getArticle();

            // 追加ボタンで増やした“空行”対策
            if (createdAtStr.isEmpty() && article.trim().isEmpty()) continue;

            OffsetDateTime createdAt;
            if (createdAtStr.isEmpty()) {
                createdAt = OffsetDateTime.now(zone);
            } else {
                LocalDateTime ldt = LocalDateTime.parse(createdAtStr, f);
                createdAt = ldt.atZone(zone).toOffsetDateTime();
            }

            repo.upsert(createdAt, article, r.isValid());
            n++;
        }

        return n;
    }
}

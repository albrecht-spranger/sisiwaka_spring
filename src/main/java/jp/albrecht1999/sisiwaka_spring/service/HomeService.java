package jp.albrecht1999.sisiwaka_spring.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class HomeService {

    private final JdbcTemplate jdbc;

    public HomeService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Map<String, Object>> latestUpdatesOrFallback() {
        try {
            String sql = """
                SELECT created_at, article
                  FROM public.updates
                 WHERE valid = true
                 ORDER BY created_at DESC
                 LIMIT 5
                """;
            return jdbc.queryForList(sql);
        } catch (Exception e) {
            // PHPの error_log 相当（まずはこれでOK）
            e.printStackTrace();

            Map<String, Object> fallback = new HashMap<>();
            fallback.put("created_at", LocalDateTime.now());
            fallback.put("article", "更新情報の取得に失敗しました。");
            return List.of(fallback);
        }
    }
}

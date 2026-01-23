package jp.albrecht1999.sisiwaka_spring.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.albrecht1999.sisiwaka_spring.entity.UpdateEntity;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public class UpdatesRepository {

	private final JdbcTemplate jdbcTemplate;

	public UpdatesRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// すべての更新情報を無条件ですべて取得
	public List<UpdateEntity> findAll() {
		String sql = """
				SELECT id, created_at, article, valid
				  FROM updates
				 ORDER BY created_at DESC, id DESC
				""";
		return jdbcTemplate.query(sql, (rs, rowNum) -> new UpdateEntity(
				rs.getLong("id"),
				rs.getObject("created_at", LocalDate.class),
				rs.getString("article"),
				rs.getBoolean("valid")));
	}

	public void upsert(OffsetDateTime createdAt, String article, boolean valid) {
		String sql = """
				INSERT INTO updates (created_at, article, valid)
				VALUES (?, ?, ?)
				ON CONFLICT (created_at)
				DO UPDATE SET article = EXCLUDED.article,
				              valid   = EXCLUDED.valid
				""";
		jdbcTemplate.update(sql, createdAt, article, valid);
	}

	// 有効な更新情報(最新5件)を取得
	public List<UpdateEntity> findValidTop5() {
		String sql = """
				SELECT created_at, article
				  FROM public.updates
				 WHERE valid = true
				 ORDER BY created_at DESC, id DESC
				 LIMIT 5
				""";
		return jdbcTemplate.query(sql, (rs, rowNum) -> new UpdateEntity(
				null,
				rs.getObject("created_at", LocalDate.class),
				rs.getString("article"),
				true));
	}
}
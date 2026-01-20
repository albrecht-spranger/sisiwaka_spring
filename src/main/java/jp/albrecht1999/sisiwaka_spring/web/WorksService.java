package jp.albrecht1999.sisiwaka_spring.web;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;
import java.sql.Timestamp;
import java.time.ZoneId;

@Service
public class WorksService {

	private final JdbcTemplate jdbcTemplate;

	public WorksService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// =========================
	// 作品一覧
	// =========================
	public List<Map<String, Object>> getAllProductsForList() {
		String sql = """
				   SELECT a.id, a.name, a.category, a.coloring, a.in_stock,
				          string_agg(DISTINCT at.techniques_slug, ',' ORDER BY at.techniques_slug) AS technique_slugs,
				          m.image_url AS thumbnail_url
				     FROM artworks AS a
				LEFT JOIN artwork_techniques AS at ON at.artwork_id = a.id
				LEFT JOIN (SELECT m.artwork_id, MIN(m.id) AS min_id
				             FROM artwork_media AS m
				            WHERE m.valid = true AND m.kind = 'image'
				            GROUP BY m.artwork_id) mm
				       ON mm.artwork_id = a.id
				LEFT JOIN artwork_media AS m ON m.id = mm.min_id
				    WHERE a.valid = true
				 GROUP BY a.id, a.name, a.category, a.coloring, a.in_stock, m.image_url
				 ORDER BY a.id DESC
				   """;

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		// PHPの explode 相当：technique_slugs -> techniques(List<String>)
		for (Map<String, Object> r : rows) {
			Object slugsObj = r.get("technique_slugs");
			String slugs = slugsObj == null ? "" : String.valueOf(slugsObj);
			List<String> techniques = slugs.isBlank()
					? List.of()
					: Arrays.asList(slugs.split(","));
			r.put("techniques", techniques);
		}
		return rows;
	}

	// =========================
	// 作品本体（詳細）
	// =========================
	public Map<String, Object> findById(int id) {

		String sql = """
				    SELECT a.*,
				           c.label_ja AS category_label,
				           clr.label_ja AS coloring_label
				      FROM artworks a
				 LEFT JOIN categories c ON a.category = c.slug
				 LEFT JOIN colorings clr ON a.coloring = clr.slug
				     WHERE a.id = ? AND a.valid = true
				""";

		Map<String, Object> work = jdbcTemplate.queryForMap(sql, id);
		Object ud = work.get("update_date");
		if (ud instanceof OffsetDateTime odt) {
			// すでに java.time なら、日本時間へ寄せる
			work.put("update_date", odt.atZoneSameInstant(ZoneId.of("Asia/Tokyo")).toOffsetDateTime());
		} else if (ud instanceof Timestamp ts) {
			// よくあるケース：Timestampで返ってくる
			work.put("update_date", ts.toInstant().atZone(ZoneId.of("Asia/Tokyo")).toOffsetDateTime());
		}

		return work;
	}

	// =========================
	// メディア
	// =========================
	public List<Map<String, Object>> findMediaByWorkId(int id) {

		String sql = """
				    SELECT image_url, video_url, alt_ja
				      FROM artwork_media
				     WHERE artwork_id = ? AND valid = true
				  ORDER BY sort_order, id
				""";

		return jdbcTemplate.queryForList(sql, id);
	}

	// =========================
	// 技法
	// =========================
	public List<String> findTechniquesByWorkId(int id) {

		String sql = """
				    SELECT t.label_ja
				      FROM artwork_techniques at
				      JOIN techniques t ON at.techniques_slug = t.slug
				     WHERE at.artwork_id = ?
				       AND at.valid = true
				       AND t.valid = true
				""";

		return jdbcTemplate.queryForList(sql, String.class, id);
	}

	public List<Map<String, Object>> getCategoriesForFilter() {
		String sql = """
				SELECT c.*
				  FROM categories AS c
				 WHERE c.slug IN (SELECT DISTINCT a.category FROM artworks AS a WHERE a.valid = true)
				""";
		return jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> getTechniquesForFilter() {
		String sql = "SELECT slug, label_ja FROM techniques WHERE valid = true ORDER BY sort_order ASC";
		return jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> getColoringsForFilter() {
		String sql = "SELECT slug, label_ja FROM colorings WHERE valid = true";
		return jdbcTemplate.queryForList(sql);
	}
}

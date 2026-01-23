package jp.albrecht1999.sisiwaka_spring.web;

import org.springframework.stereotype.Service;

import jp.albrecht1999.sisiwaka_spring.web.PublicUpdateViewDto;
import jp.albrecht1999.sisiwaka_spring.entity.UpdateEntity;
import jp.albrecht1999.sisiwaka_spring.entity.ValidUpdateRow;
import jp.albrecht1999.sisiwaka_spring.repository.UpdatesRepository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.*;

@Service
public class HomeService {

	private final UpdatesRepository updatesRepository;

	public HomeService(UpdatesRepository updatesRepository) {
		this.updatesRepository = updatesRepository;
	}

	/**
	 * トップページ表示用の新着情報を取得する
	 */
	public List<PublicUpdateViewDto> listForPublicTop5() {
		try {
			return updatesRepository.findValidTop5().stream()
					.map(e -> new PublicUpdateViewDto(
						e.getCreatedAt(),
						e.getArticle()
						))
					.toList();
		} catch (

		Exception e) {
			// ログ出力（まずはこれでOK）
			e.printStackTrace();

			// ③ フォールバック（必ず同じ型を返す）
			return List.of(
					new PublicUpdateViewDto(
							LocalDate.now(),
							"更新情報の取得に失敗しました。"));
		}
	}
}

<?php
// 作品詳細

declare(strict_types=1);
require_once __DIR__ . '/includes/db_connect.php';
require_once __DIR__ . '/includes/common_func.php';

// id バリデーション
$id = filter_input(INPUT_GET, 'id', FILTER_VALIDATE_INT);
if ($id === false || $id === null) {
	error_log("[detail.php] IDが指定されていません。");
	echo 'エラーが発生しました。';
	exit;
}

// DBから情報取得
try {
	$pdo = get_db_connection();

	// ====== 作品本体 ======
	$sql_artwork = "SELECT a.*, c.label_ja AS category, clr.label_ja AS coloring
		FROM artworks a
			LEFT JOIN categories c ON a.category = c.slug
			LEFT JOIN colorings clr ON a.coloring = clr.slug
		WHERE a.id = :id 
		AND a.valid = 1;
	";
	$stmt = $pdo->prepare($sql_artwork);
	$stmt->execute([':id' => $id]);
	$artwork = $stmt->fetch();
	if (!$artwork) {
		throw new RuntimeException('Artwork not found (ID=' . $id . ').');
	}

	// ====== 画像・動画（media） ======
	$sql_media = "SELECT image_url, video_url, alt_ja
        FROM artwork_media
        WHERE artwork_id = :id AND valid = 1
        ORDER BY sort_order ASC, id ASC
    ";
	$stmt_media = $pdo->prepare($sql_media);
	$stmt_media->execute([':id' => $id]);
	$media_rows = $stmt_media->fetchAll();

	// ===== 使われている技法一覧 =====
	$sql_techniques = "SELECT t.label_ja FROM artwork_techniques at JOIN techniques t
		ON at.techniques_slug = t.slug WHERE at.artwork_id = :id AND at.valid = 1 AND t.valid = 1
    ";
	$stmt_techniques = $pdo->prepare($sql_techniques);
	$stmt_techniques->execute([':id' => $id]);
	$techniques = $stmt_techniques->fetchAll(PDO::FETCH_COLUMN);
	// ⇒FETCH_COLUMN：このSQLだけ連想記憶ではなく、一列の値を返す
} catch (Throwable $e) {
	$log = sprintf(
		"[DB Error] %s\n  File: %s\n  Line: %d\n  Code: %s",
		$e->getMessage(),
		$e->getFile(),
		$e->getLine(),
		$e->getCode()
	);
	error_log($log);

	echo 'エラーが発生しました。';
	exit;
}
?>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>シシワカ陶苑｜<?= h($artwork['description_title']) ?></title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/detail.css">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link
		href="https://fonts.googleapis.com/css2?family=Hina+Mincho&family=Kaisei+Tokumin&family=M+PLUS+2:wght@100..900&family=Zen+Antique&display=swap"
		rel="stylesheet">

	<!-- Swiper -->
	<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
</head>

<body>
	<!-- =================================
		ヘッダ
	================================= -->
	<header>
		<a href="index.php" class="header_left_block">
			<img class="sisiwaka_touen_icon" src="./images/sisiwaka_touen_logo.jpg" alt="シシワカ陶苑ロゴ">
			<h1>
				シシワカ陶苑
			</h1>
		</a>
		<div class="header_right_block">
			<div class="minne_icon">
				<a href="https://minne.com/@sisiwaka10en" target="_blank">
					<img src="./images/minne_logo_vertical.png" alt="minne logo">
				</a>
			</div>
			<div class="instagram_icon">
				<a href="https://www.instagram.com/albrecht1999/" target="_blank">
					<img src="./images/Instagram_Glyph_Gradient.png" alt="Instagram Icon">
				</a>
			</div>
		</div>
	</header>

	<main>
		<!-- =================================
			一覧に戻る
		================================= -->
		<p class="back_to_works"><a href="works.php" class="link_style">一覧に戻る</a></p>

		<!-- =================================
			詳細表示
		================================= -->
		<section>
			<!-- flashメッセージ表示 -->
			<?php
			$error = get_flash('error');
			$success = get_flash('success');
			if ($error) {
				echo '<div class="flash_message">' . h($error) . '</div>';
			}
			if ($success) {
				echo '<div class="flash_message">' . h($success) . '</div>';
			}
			?>

			<!-- タイトル -->
			<div class="works_name_container">
				<h1 id="works_name"><?= h($artwork['description_title'], 'タイトル未定') ?></h1>
			</div>

			<!-- Swiperによるカルーセル(スライダー) -->
			<div class="gallerySwiperContainer">
				<div class="swiper">
					<div class="swiper-wrapper" id="swiper_slide_holder">
						<?php foreach ($media_rows as $media): ?>
							<div class="swiper-slide">
								<img loading="lazy" src="<?= h($media['image_url']) ?>" alt="<?= h($media['alt_ja']) ?>"
									<?php if ($media['video_url'] !== null): ?> data-type="video"
										data-video_src="<?= h($media['video_url']) ?>" <?php endif; ?> />
							</div>
						<?php endforeach; ?>
					</div>
				</div>
				<!-- ページネーション -->
				<div class="swiper-pagination"></div>
				<!-- 前後の矢印 -->
				<div class="swiper-button-prev"></div>
				<div class="swiper-button-next"></div>
			</div>

			<!-- 作品説明 -->
			<dl class="works_spec_container">
				<dt>銘</dt>
				<dd id="spec_name"><?= h($artwork['name'], '(銘)') ?></dd>
				<dt>用途</dt>
				<dd id="spec_category"><?= h($artwork['category'], '(用途)') ?></dd>
				<dt>説明</dt>
				<dd id="spec_description" class="pre_line"><?= h($artwork['description'], '(説明)') ?></dd>
				<dt>サイズ</dt>
				<dd id="spec_spec" class="pre_line"><?= h($artwork['spec'], '(サイズ)') ?></dd>
				<dt>技法</dt>
				<dd id="spec_techniques"><?= h($techniques ? implode(', ', $techniques) : '(技法)') ?></dd>
				<dt>色合い</dt>
				<dd id="spec_coloring"><?= h($artwork['coloring'], '(色合い)') ?></dd>
				<dt>粘土</dt>
				<dd id="spec_clay" class="pre_line"><?= h($artwork['clay'], '(粘土)') ?></dd>
				<dt>釉薬</dt>
				<dd id="spec_glaze" class="pre_line"><?= h($artwork['glaze'], '(釉掛け)') ?></dd>
				<dt>補足</dt>
				<dd id="spec_notes" class="pre_line"><?= h($artwork['notes'], '(補足)') ?></dd>
				<dt>完成日</dt>
				<dd id="spec_completion_date">
					<?php
					$completion_date = $artwork['completion_date'] ?? null;
					if ($completion_date) {
						$formatted_date = (new DateTime($completion_date))->format('Y/n/j');
						echo h($formatted_date);
					} else {
						echo '―';
					}
					?>
				</dd>
				<dt>Instagram</dt>
				<dd id="spec_instagram_url" class="instagram_icon">
					<?php if (!$artwork['instagram_url']): ?>
						―
					<?php else: ?>
						<a href="<?= h($artwork['instagram_url']) ?>" target="_blank" rel="noopener noreferrer">
							<img src="images/Instagram_Glyph_Gradient.png" loading="lazy" alt="Instagramへ">
						</a>
					<?php endif; ?>
				</dd>
				<dt>ご購入</dt>
				<dd id="spec_online_shop" class="minne_icon">
					<?php if ($artwork['in_stock'] === 0 || !$artwork['shop_url']): ?>
						(sold out)
					<?php else: ?>
						<a href="<?= h($artwork['shop_url']) ?>" target="_blank" rel="noopener noreferrer">
							<img src="images/minne_logo_vertical.png" loading="lazy" alt="オンラインショップへ">
						</a>
					<?php endif; ?>
				</dd>
				<dt></dt>
				<dd class="spec_update_date_style">
					(記事<a href="admin/edit.php?id=<?= h((string) $artwork['id']) ?>" id="edit_link">更新</a><span
						class="non_edit">更新</span>
					<span id="spec_update_date">
						<?php
						$update_date = $artwork['update_date'] ?? null;
						if ($update_date) {
							$formatted_date = (new DateTime($update_date))->format('Y/n/j');
							echo h($formatted_date);
						} else {
							echo '―';
						}
						?>
					</span>)
				</dd>
			</dl>
		</section>
	</main>

	<footer>
		<p>&copy; 2025 シシワカ陶苑</p>
	</footer>

	<!-- lightbox -->
	<!-- <div id="lightbox" aria-hidden="true"> -->
	<div id="lightbox">
		<div id="lightboxInner">
			<img id="lightboxImage" class="lightbox-media" src="./images/noimage.svg" alt="拡大画像">
			<video id="lightboxVideo" class="lightbox-media" preload="metadata" playsinline controls></video>
		</div>
	</div>

	<!-- script -->
	<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
	<script src="scripts/detail.js"></script>
</body>

</html>
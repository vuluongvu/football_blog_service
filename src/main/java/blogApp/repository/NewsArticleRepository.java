package blogApp.repository;

import blogApp.entity.NewsArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface NewsArticleRepository extends JpaRepository<NewsArticle,Long> {
    Optional<NewsArticle> findBySlug(String slug);
    Page<NewsArticle> findByTitleContainingIgnoreCase(String slug, Pageable pageable);
}

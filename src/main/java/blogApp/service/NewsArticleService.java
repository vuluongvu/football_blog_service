package blogApp.service;

import blogApp.dto.request.NewArticleCreationRequest;
import blogApp.dto.request.NewArticleUpdateRequest;
import blogApp.entity.NewsArticle;
import blogApp.repository.NewsArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
public class NewsArticleService {

    private NewsArticleRepository newsArticleRepository;

    // generate slug
    private String getSlug (String slug){
        return slug.trim().replaceAll(" ", "-").toLowerCase();
    }

    // find article by slug
    public NewsArticle findArticleBySlug(String slug) {
        return newsArticleRepository.findBySlug(slug).orElseThrow(() -> new RuntimeException("Article not found"));
    }

    // create article
    public NewsArticle registerArticle(NewArticleCreationRequest request) {

        String slug = getSlug(request.getTitle());

        NewsArticle article =  NewsArticle.builder()
                .title(request.getTitle())
                .slug(slug)
                .content(request.getContent())
                .team(request.getTeam())
                .league(request.getLeague())
                .author(request.getAuthor())
                .imageLink(request.getImageLink())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .tags(request.getTags())
                .build();
        return newsArticleRepository.save(article);
    }

    // update article
    public NewsArticle updateArticle(NewArticleUpdateRequest request, String slug) {
        var article = findArticleBySlug(slug);

        article.setContent(request.getContent());
        article.setTeam(request.getTeam());
        article.setLeague(request.getLeague());
        article.setAuthor(request.getAuthor());
        article.setImageLink(request.getImageLink());
        article.setUpdatedAt(LocalDateTime.now());
        article.setTags(request.getTags());
        return newsArticleRepository.save(article);
    }

    // delete article
    public void deleteArticle(String slug) {
        var article = findArticleBySlug(slug);
        newsArticleRepository.delete(article);
    }

    // searcning
    public Page<NewsArticle> searchNewsArticles(String searchData, Pageable pageable){
        return newsArticleRepository.findByTitleContainingIgnoreCase(searchData, pageable);
    }

    // pagination
    public Page<NewsArticle> getNewsArticles(Pageable pageable) {
        return newsArticleRepository.findAll(pageable);
    }

    // get all articles
    public List<NewsArticle> findAllArticles() {
        return newsArticleRepository.findAll();
    }
}

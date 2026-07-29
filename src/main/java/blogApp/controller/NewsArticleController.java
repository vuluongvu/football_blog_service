package blogApp.controller;

import blogApp.dto.request.NewArticleCreationRequest;
import blogApp.dto.request.NewArticleUpdateRequest;
import blogApp.dto.response.ApiResponse;
import blogApp.entity.NewsArticle;
import blogApp.service.NewsArticleService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/news-articles")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "https://football-blog-eta.vercel.app"})
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class NewsArticleController {

    private NewsArticleService newsArticleService;

    @PostMapping
    ApiResponse<NewsArticle> createArticle(@RequestBody NewArticleCreationRequest request) {
        return ApiResponse.<NewsArticle>builder()
                .message("created successfully")
                .statusCode(1001)
                .dataObject(newsArticleService.registerArticle(request))
                .build();
    }

    @PutMapping("/{slug}")
    ApiResponse<NewsArticle> updateArticle(@RequestBody NewArticleUpdateRequest request, @PathVariable String slug) {
        return ApiResponse.<NewsArticle>builder()
                .message("updated successfully")
                .statusCode(1002)
                .dataObject(newsArticleService.updateArticle(request, slug))
                .build();
    }

    @GetMapping("/{slug}")
    ApiResponse<NewsArticle> getArticle(@PathVariable String slug) {
        return ApiResponse.<NewsArticle>builder()
                .message("retrieved successfully")
                .statusCode(1003)
                .dataObject(newsArticleService.findArticleBySlug(slug))
                .build();
    }

    @DeleteMapping
    ApiResponse<Boolean> deleteArticle(@PathVariable String slug) {
        newsArticleService.deleteArticle(slug);
        return ApiResponse.<Boolean>builder()
                .message("deleted successfully")
                .statusCode(1005)
                .dataObject(true)
                .build();
    }

    // search articles
    @GetMapping("/search")
    ApiResponse<Page<NewsArticle>> searchArticles(
            @RequestParam String query,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ApiResponse.<Page<NewsArticle>>builder()
                .message("retrieved successfully")
                .statusCode(1006)
                .dataObject(newsArticleService.searchNewsArticles(query, pageable))
                .build();
    }

    // pagination
    @GetMapping
    public ApiResponse<Page<NewsArticle>> getArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size); // statics method để nhận 3 tham số (0, 3, Sort = theo tên)

        return ApiResponse.<Page<NewsArticle>>builder()
                .message("Successfully got video games")
                .statusCode(1002)
                .dataObject(newsArticleService.getNewsArticles(pageable))
                .build();
    }

}

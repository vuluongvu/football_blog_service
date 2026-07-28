package blogApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class NewsArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String slug;
    @Column(length = 10000)
    private String content;

    private String team;
    private String league;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String imageLink;
    private List<String> tags;
}

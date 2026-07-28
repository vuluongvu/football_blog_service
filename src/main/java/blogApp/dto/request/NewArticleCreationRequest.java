package blogApp.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewArticleCreationRequest {
    private String title;
    private String content;

    private String team;
    private String league;
    private String author;
    private String imageLink;
    private List<String> tags;
}

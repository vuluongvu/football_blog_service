package blogApp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@AllArgsConstructor
public class ApiResponse <T>{
    private String message;
    private long statusCode;
    private T dataObject;
}

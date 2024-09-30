package co.shop.api.dtos.imageDto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdateImageDto {
    private MultipartFile image;
    private String imageName;
    private Long productId;
}

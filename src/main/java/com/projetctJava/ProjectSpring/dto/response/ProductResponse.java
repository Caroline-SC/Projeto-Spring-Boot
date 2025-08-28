package com.projetctJava.ProjectSpring.dto.response;

import com.projetctJava.ProjectSpring.models.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= "id")
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;

    public ProductResponse(Product product){
        BeanUtils.copyProperties(product, this);
    }
    public static ProductResponse fromEntity(Product product){
        return new ProductResponse(product);
    }
}

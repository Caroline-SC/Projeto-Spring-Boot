package com.projetctJava.ProjectSpring.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCreateRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 5, max = 100, message = "Name must be between 2 and 100 characters long.")
    private String name;

    @NotBlank(message = "Description is required.")
    @Size(min = 5, max = 100, message = "Description must be between 10 and 100 characters long.")
    private String description;

    @NotNull(message = "Price is required.")
    @Positive(message = "The price must be positive.")
    private Double price;


}

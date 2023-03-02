package com.example.urlshortener.entity.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AccountUrlsResponseDto {
    @JsonProperty("urls")
    private List<UrlResponseDto> urls;
    @JsonProperty("total_pages")
    private Integer totalPages;
    @JsonProperty("current_page")
    private Integer currentPage;
    @JsonProperty("next_page")
    private String nextPage;
    @JsonProperty("previous_page")
    private String previousPage;
    @JsonProperty("is_last_page")
    private Boolean isLastPage;
}

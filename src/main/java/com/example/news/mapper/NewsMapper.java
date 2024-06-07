package com.example.news.mapper;

import com.example.news.model.News;
import com.example.news.web.model.list.NewsListResponse;
import com.example.news.web.model.single.NewsResponse;
import com.example.news.web.model.single.OneNewsResponse;
import com.example.news.web.model.upsert.UpsertNewsRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE/*,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS*/)
@DecoratedWith(NewsMapperDecorator.class)
public interface NewsMapper {

    //@Mapping(source = "news.category.categoryId", target = "categoryId")
    @Mapping(source = "news.category.shortDesc", target = "categoryShortDesc")
    @Mapping(source = "news.user.email", target = "userEmail")
    OneNewsResponse oneNewsToResponse(News news);

    @Mapping(source = "news.category.shortDesc", target = "categoryShortDesc")
    @Mapping(source = "news.user.email", target = "userEmail")
    NewsResponse newsToResponse(News news);

    News requestToNews(UpsertNewsRequest request);

    void requestToNews(Long newsId, UpsertNewsRequest request, @MappingTarget News news);

    default NewsListResponse newsListToNewsListResponse(List<News> newsList){
        NewsListResponse newsListResponse = new NewsListResponse();
        newsListResponse.setNewsList(newsList.stream()
                .map(this::newsToResponse)
                .toList());

        return newsListResponse;
    }

}

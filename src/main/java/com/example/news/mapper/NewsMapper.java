package com.example.news.mapper;

import com.example.news.model.News;
import com.example.news.web.model.list.NewsListResponse;
import com.example.news.web.model.single.NewsResponse;
import com.example.news.web.model.upsert.UpsertNewsRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
//@DecoratedWith(NewsMapperDelegate.class)
public interface NewsMapper {

    NewsResponse newsToResponse(News news);

    News requestToNews(UpsertNewsRequest request);

    News requestToNews(Long newsId, UpsertNewsRequest request);

    default NewsListResponse newsListToNewsListResponse(List<News> newsList){
        NewsListResponse newsListResponse = new NewsListResponse();
        newsListResponse.setNewsResponseList(newsList.stream()
                .map(this::newsToResponse)
                .toList());

        return newsListResponse;
    }

}

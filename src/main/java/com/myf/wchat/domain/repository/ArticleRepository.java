package com.myf.wchat.domain.repository;

import com.myf.wchat.domain.messageDomain.response.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,String> {
}

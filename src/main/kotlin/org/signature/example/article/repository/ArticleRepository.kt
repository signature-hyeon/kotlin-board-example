package org.signature.example.article.repository

import org.signature.example.article.entity.ArticleEntity

interface ArticleRepository {

    fun save(article: ArticleEntity): ArticleEntity
    fun findById(articleId: Long): ArticleEntity?

}

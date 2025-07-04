package org.signature.example.article.dto.response

import org.signature.example.article.entity.ArticleEntity
import java.time.LocalDateTime

data class ArticleRegisterResponse(
    val articleId: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
) {
    constructor(article: ArticleEntity) : this(
        articleId = article.savedId(),
        title = article.title,
        content = article.content,
        createdAt = article.createdAt
    )
}

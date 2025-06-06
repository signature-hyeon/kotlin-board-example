package org.signature.example.article.dto.response

import org.signature.example.article.entity.ArticleEntity
import java.time.LocalDateTime

data class ArticleUpdateResponse (
    val title: String,
    val content: String,
    val userId: Long,
    val updatedAt: LocalDateTime,
){
    constructor(article: ArticleEntity) : this(
        article.title,
        article.content,
        article.userId,
        article.updatedAt
    )
}

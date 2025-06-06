package org.signature.example.article.dto.response

import java.time.LocalDateTime

data class ArticleQueryResponse(
    val id: Long,
    val userId: Long,
    val title: String,
    val content: String,
    val updatedAt: LocalDateTime
) {

}

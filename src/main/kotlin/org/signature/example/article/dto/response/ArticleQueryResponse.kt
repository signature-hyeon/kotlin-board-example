package org.signature.example.article.dto.response

data class ArticleQueryResponse(
    val id: Long,
    val userId: Long,
    val title: String,
    val content: String,
) {

}

package org.signature.example.article.dto.request

data class ArticleUpdateRequest (
    val title: String,
    val content: String,
    val userId: Long,
){

}

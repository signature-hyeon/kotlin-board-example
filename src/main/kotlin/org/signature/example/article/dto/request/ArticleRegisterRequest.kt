package org.signature.example.article.dto.request

data class ArticleRegisterRequest (
    val userId: Long,
    val title: String,
    val content: String,
){

}

package org.signature.example.article.service

import org.signature.example.article.dto.request.ArticleRegisterRequest
import org.signature.example.article.dto.response.ArticleQueryResponse
import org.signature.example.article.dto.response.ArticleRegisterResponse
import org.signature.example.article.entity.ArticleEntity
import org.signature.example.article.repository.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val articleRepository: ArticleRepository
) {
    fun register(request: ArticleRegisterRequest): ArticleRegisterResponse {
        val article = articleRepository.save(
            ArticleEntity(
                userId = request.userId,
                title = request.title,
                content = request.content
            )
        )
        return ArticleRegisterResponse(article.savedId())
    }

    fun getArticle(articleId: Long): ArticleQueryResponse {
        val article = articleRepository.findById(articleId) ?: throw IllegalArgumentException("게시글이 존재하지 않습니다.")
        return ArticleQueryResponse(
            id = article.savedId(),
            userId = article.userId,
            title = article.title,
            content = article.content
        )
    }
}

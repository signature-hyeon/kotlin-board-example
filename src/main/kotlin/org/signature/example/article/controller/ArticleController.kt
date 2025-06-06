package org.signature.example.article.controller

import org.signature.example.article.dto.request.ArticleRegisterRequest
import org.signature.example.article.dto.response.ArticleQueryResponse
import org.signature.example.article.dto.response.ArticleRegisterResponse
import org.signature.example.article.service.ArticleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/articles")
class ArticleController (
    private val articleService: ArticleService
) {
    @PostMapping
    fun register(@RequestBody request: ArticleRegisterRequest): ArticleRegisterResponse {
        return articleService.register(request)
    }

    @GetMapping("/{articleId}")
    fun getArticle(@PathVariable articleId: Long): ArticleQueryResponse {
        return articleService.getArticle(articleId)
    }
}


package org.signature.example.article.controller

import org.signature.example.article.dto.request.ArticleRegisterRequest
import org.signature.example.article.dto.request.ArticleUpdateRequest
import org.signature.example.article.dto.response.ArticleQueryResponse
import org.signature.example.article.dto.response.ArticleRegisterResponse
import org.signature.example.article.dto.response.ArticleUpdateResponse
import org.signature.example.article.service.ArticleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/articles")
class ArticleController(
    private val articleService: ArticleService
) {
    @PostMapping
    fun register(@RequestBody request: ArticleRegisterRequest): ArticleRegisterResponse {
        return articleService.registerArticle(request)
    }

    @GetMapping("/{articleId}")
    fun getArticle(@PathVariable articleId: Long): ArticleQueryResponse {
        return articleService.getArticle(articleId)
    }

    @PatchMapping("/{articleId}")
    fun updateArticle(@PathVariable articleId: Long,
                      @RequestBody request: ArticleUpdateRequest) : ArticleUpdateResponse {
        return articleService.updateArticle(articleId, request)
    }
}


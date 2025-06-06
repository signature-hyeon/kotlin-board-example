package org.signature.example.article.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.signature.example.article.dto.request.ArticleRegisterRequest
import org.signature.example.article.entity.ArticleEntity
import org.signature.example.article.repository.ArticleMapRepository
import org.signature.example.article.repository.ArticleRepository

class ArticleServiceTest {

    @Test
    fun `게시글을 저장하면 ID 값을 잘 반환한다`() {
        val sut = ArticleService(ArticleMapRepository())
        val request = ArticleRegisterRequest(
            userId = 1,
            title = "hello",
            content = "kotlin"
        )

        val result = sut.register(request)

        assertNotNull(result.articleId)
    }

    @Test
    fun `게시글을 조회하면 게시글이 정상적으로 반환한다`() {
        val sut = ArticleService(StubRepository())
        val saved = sut.register(
            ArticleRegisterRequest(
                userId = 1,
                title = "hello",
                content = "kotlin"
            )
        )

        val result = sut.getArticle(saved.articleId)

        assertNotNull(result)
    }

    @Test
    fun `ID로 게시글을 조회 했을 때 결과가 없다면 예외를 반환한다`() {
        val sut = ArticleService(StubRepository())

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            sut.getArticle(2L)
        }
    }
}


class StubRepository : ArticleRepository {
    private val store = mutableMapOf<Long, ArticleEntity>()
    private var articleId = 1L

    override fun save(article: ArticleEntity): ArticleEntity {
        val savedArticle = ArticleEntity(
            id = articleId++,
            userId = 1L,
            title = "hello",
            content = "kotlin"
        )
        store[savedArticle.savedId()] = savedArticle
        return savedArticle
    }

    override fun findById(articleId: Long): ArticleEntity? {
        return store[articleId]
    }
}

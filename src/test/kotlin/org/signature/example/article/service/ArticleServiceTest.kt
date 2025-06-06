package org.signature.example.article.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.signature.example.article.dto.request.ArticleUpdateRequest
import org.signature.example.article.entity.ArticleEntity
import org.signature.example.article.fixture.ArticleFixture
import org.signature.example.article.repository.ArticleMapRepository
import org.signature.example.article.repository.ArticleRepository

class ArticleServiceTest {

    @Test
    fun `게시글을 등록 시 새로운 ID 가 생성되어 반환된다`() {
        val sut = ArticleService(StubRepository())
        val request = ArticleFixture.generateRegisterRequest()

        val result = sut.registerArticle(request)

        assertTrue(result.articleId > 0)
    }

    @Test
    fun `게시글을 조회하면 게시글이 정상적으로 반환한다`() {
        val sut = ArticleService(StubRepository())
        val saved = sut.registerArticle(ArticleFixture.generateRegisterRequest())

        val result = sut.getArticle(saved.articleId)

        assertEquals(saved.articleId, result.id)
        assertEquals(saved.title, result.title)
        assertEquals(saved.content, result.content)
    }

    @Test
    fun `ID로 게시글을 조회 했을 때 결과가 없다면 예외가 발생한다`() {
        val sut = ArticleService(StubRepository())

        val exception = assertThrows<IllegalArgumentException> {
            sut.getArticle(2L)
        }

        assertEquals("게시글이 존재하지 않습니다.", exception.message)
    }

    @Test
    fun `게시글을 수정하면 정상적으로 업데이트된다`() {
        val sut = ArticleService(StubRepository())
        val saveRequest = ArticleFixture.generateRegisterRequest()
        val saved = sut.registerArticle(saveRequest)
        val updateRequest = ArticleUpdateRequest(
            title = "hello updated",
            content = "kotlin updated",
            userId = saveRequest.userId
        )

        val result = sut.updateArticle(saved.articleId, updateRequest)

        assertEquals(updateRequest.title, result.title)
        assertEquals(updateRequest.content, result.content)
        assertEquals(updateRequest.userId, result.userId)
    }

    @Test
    fun `존재하지 않은 게시물인 경우 수정하면 예외가 발생한다`() {
        val sut = ArticleService(StubRepository())

        val exception = assertThrows<IllegalArgumentException> {
            sut.getArticle(1)
        }

        assertEquals("게시글이 존재하지 않습니다.", exception.message)
    }

    @Test
    fun `작성자가 아닌 경우 게시글을 수정하면 예외가 발생한다`() {
        val sut = ArticleService(ArticleMapRepository())
        val saveRequest = ArticleFixture.generateRegisterRequest()
        val saved = sut.registerArticle(saveRequest)
        val updateRequest = ArticleUpdateRequest(
            title = "hello updated",
            content = "kotlin updated",
            userId = saveRequest.userId + 1L
        )

        val exception = assertThrows<IllegalArgumentException> {
            sut.updateArticle(saved.articleId, updateRequest)
        }

        assertEquals("게시글 수정 권한이 없습니다.", exception.message)
    }
}

class StubRepository : ArticleRepository {
    private val store = mutableMapOf<Long, ArticleEntity>()
    private var articleId = 1L

    override fun save(article: ArticleEntity): ArticleEntity {
        val saved = ArticleEntity(
            id = articleId++,
            userId = article.userId,
            title = article.title,
            content = article.content,
            createdAt = article.createdAt,
            updatedAt = article.updatedAt
        )
        store[saved.savedId()] = saved
        return saved
    }

    override fun findById(articleId: Long): ArticleEntity? {
        return store[articleId]
    }
}

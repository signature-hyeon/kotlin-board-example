package org.signature.example.article.repository

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.signature.example.article.fixture.ArticleFixture

class ArticleMapRepositoryTest {

    private lateinit var sut: ArticleMapRepository

    @BeforeEach
    fun setUp() {
        sut = ArticleMapRepository()
    }

    @Test
    fun `처음 게시물을 저장하면 ID를 잘 반환한다`() {
        val article = ArticleFixture.generate()

        val saved = sut.save(article)

        Assertions.assertNotNull(saved.id)
    }

    @Test
    fun `이미 존재하는 게시물을 저장하면 갱신된 값을 반환한다`() {
        val article = ArticleFixture.generate(id = 1L)
        val updatedArticle = ArticleFixture.generate(id = 1L)
        sut.save(article)

        val updated = sut.save(updatedArticle)

        Assertions.assertEquals(updated.content, updatedArticle.content)
    }

    @Test
    fun `ID에 해당하는 게시물이 존재하면 정상적으로 조회된다`() {
        val request = ArticleFixture.generate()
        sut.save(request)

        val article = sut.findById(request.savedId())

        Assertions.assertEquals(article!!.savedId(), request.id)
    }
}

package org.signature.example.article.repository

import org.signature.example.article.entity.ArticleEntity
import org.springframework.stereotype.Repository
import java.util.concurrent.atomic.AtomicLong

@Repository
class ArticleMapRepository : ArticleRepository {

    private val store = mutableMapOf<Long, ArticleEntity>()
    private val articleId = AtomicLong(0)

    override fun save(article: ArticleEntity): ArticleEntity {
        val entity = ArticleEntity(
            id = if (article.isNew()) articleId.incrementAndGet() else article.id,
            userId = article.userId,
            title = article.title,
            content = article.content
        )
        store[entity.savedId()] = entity
        return entity
    }

    override fun findById(articleId: Long): ArticleEntity? = store[articleId]
}

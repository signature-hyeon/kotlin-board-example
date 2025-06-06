package org.signature.example.article.fixture

import org.signature.example.article.dto.request.ArticleRegisterRequest
import org.signature.example.article.entity.ArticleEntity
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

object ArticleFixture {

    fun generate(
        id: Long = Random.nextLong(1, 1000),
        userId: Long = Random.nextLong(1, 1000),
        title: String = UUID.randomUUID().toString(),
        content: String = UUID.randomUUID().toString(),
        createdAt: LocalDateTime = LocalDateTime.now(),
        updatedAt: LocalDateTime = LocalDateTime.now(),
    ) = ArticleEntity(
        id = id,
        userId = userId,
        title = title,
        content = content,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )

    fun generateRegisterRequest(
        userId: Long = Random.nextLong(1, 1000),
        title: String = UUID.randomUUID().toString(),
        content: String = UUID.randomUUID().toString(),
    ) = ArticleRegisterRequest(
        userId = userId,
        title = title,
        content = content
    )
}

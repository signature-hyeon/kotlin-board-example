package org.signature.example.article.fixture

import org.signature.example.article.entity.ArticleEntity
import java.util.*
import kotlin.random.Random

object ArticleFixture {

    fun generate(
        id: Long = Random.nextLong(1, 1000),
        userId: Long = Random.nextLong(1, 1000),
        title: String = UUID.randomUUID().toString(),
        content: String = UUID.randomUUID().toString()
    ) = ArticleEntity(
        id = id,
        userId = userId,
        title = title,
        content = content
    )
}

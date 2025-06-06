package org.signature.example.article.entity

import java.time.LocalDateTime

class ArticleEntity(
    var id: Long? = null,
    var userId: Long,
    var title: String,
    var content: String,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
) {
    constructor(userId: Long, title: String, content: String) : this(
        userId = userId,
        title = title,
        content = content,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )

    fun updateArticle(title: String, content: String) {
        this.title = title
        this.content = content
        this.updatedAt = LocalDateTime.now()
    }

    fun isNew(): Boolean {
        return id == null;
    }

    fun savedId(): Long {
        return id?: throw NullPointerException("null 이면 안되는데!")
    }

    fun checkUser(userId: Long) {
        check(this.userId == userId) { throw IllegalArgumentException("게시글 수정 권한이 없습니다.") }
    }
}

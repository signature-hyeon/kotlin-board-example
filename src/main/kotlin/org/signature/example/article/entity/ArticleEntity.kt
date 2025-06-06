package org.signature.example.article.entity

class ArticleEntity(
    var id: Long? = null,
    var userId: Long,
    var title: String,
    var content: String,
) {
    fun isNew(): Boolean {
        return id == null;
    }

    fun savedId(): Long {
        return id?: throw NullPointerException("null 이면 안되는데!")
    }
}

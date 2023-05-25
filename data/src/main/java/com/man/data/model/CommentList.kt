package com.man.data.model

/**
 *
 * Created by Lukmanul Hakim on  22/05/23
 * devs.lukman@gmail.com
 */

data class CommentList(
    val videoId: String?,
    val totalComment: Int,
    val comments: List<Comment>,
    val isCommentPrivate: Boolean
) {
    data class Comment(
        val commentBy: UserModel,
        val comment: String?,
        val createdAt: String,
        val totalLike: Long,
        val totalDisLike: Long,
        val threadCount: Int,
        val thread: List<Comment>
    )
}

data class CommentText(
    val comment: String
)
package com.tien.service;

import com.tien.models.Comment;

public interface CommentService {

	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;
	
	public Comment likeComment(Integer commentId, Integer userId) throws Exception;
	
	public Comment findCommentById(Integer commentId) throws Exception;
	
}

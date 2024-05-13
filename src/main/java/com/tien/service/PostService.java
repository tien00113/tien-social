package com.tien.service;

import java.util.List;

import com.tien.models.Post;

public interface PostService {
	
	Post createNewPost(Post post,Integer userId) throws Exception;
	
	String deletePost(Integer postId, Integer userId) throws Exception;
	
	List<Post> findPostByUserId(Integer userId);
	
	Post findPostById(Integer postId) throws Exception;
	
	List<Post> findAllPost();
	
	Post savedPost(Integer postId, Integer userId) throws Exception;
	
	Post likePost(Integer postid, Integer userId) throws Exception;
	
	void deleteCommentFromPost(Integer postId, Integer commentId) throws Exception;
}

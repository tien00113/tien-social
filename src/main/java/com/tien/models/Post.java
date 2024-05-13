package com.tien.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String caption;

	private String video;

	private String image;

	@ManyToOne
	private User user;

	@ManyToMany
	private List<User> liked = new ArrayList<>();

	private LocalDateTime craetedAt;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "post_id")
	private List<Comment> comments = new ArrayList<>();

	public Post() {

	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<User> getLiked() {
		return liked;
	}

	public void setLiked(List<User> liked) {
		this.liked = liked;
	}



	public Post(Integer id, String caption, String video, String image, User user, List<User> liked,
			LocalDateTime craetedAt, List<Comment> comments) {
		super();
		this.id = id;
		this.caption = caption;
		this.video = video;
		this.image = image;
		this.user = user;
		this.liked = liked;
		this.craetedAt = craetedAt;
		this.comments = comments;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCraetedAt() {
		return craetedAt;
	}

	public void setCraetedAt(LocalDateTime craetedAt) {
		this.craetedAt = craetedAt;
	}

}

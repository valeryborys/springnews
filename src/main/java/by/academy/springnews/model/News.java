package by.academy.springnews.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table (name="news")
public class News implements Serializable{

	private static final long serialVersionUID = 4437049130519600326L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="date")
	private Timestamp datetime;
	
	@Column(name="brief")
	private String brief;
	
	@Column(name="content")
	private String content;
	
	
	
	public News() {
	}
	
	
	
	public News(String title, Timestamp datetime, String brief, String content) {
		super();
		this.title = title;
		this.datetime = datetime;
		this.brief = brief;
		this.content = content;
	}



	public News(int id, String title, String brief, String content, Timestamp datetime) {
		this.id = id;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.datetime = datetime;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getDatetime() {
		return datetime;
	}
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((datetime == null) ? 0 : datetime.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		if (brief == null) {
			if (other.brief != null)
				return false;
		} else if (!brief.equals(other.brief))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (datetime == null) {
			if (other.datetime != null)
				return false;
		} else if (!datetime.equals(other.datetime))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", brief=" + brief + ", datetime=" + datetime + "]";
	}
	
	
	

}

package ru.satcit.kolpak.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * User: Aleksei
 * Date: 25.01.16 17:29
 */
@Entity
@Table(name = "file")
public class File implements HaveId {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private long id;

  @Column
  private String name;

  @Column(name = "path")
  private String path;

  @Column(name = "created_date")
  private Date createdDate;

  @Override
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
}

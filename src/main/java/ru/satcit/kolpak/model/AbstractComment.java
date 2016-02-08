package ru.satcit.kolpak.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * User: Aleksei
 * Date: 26.01.16 13:23
 */
@MappedSuperclass
public class AbstractComment implements Comment, Comparable<AbstractComment>, HaveId {
  @Id
  @GeneratedValue
  @Column(name="id")
  private long id;

  @Column(name="text")
  private String text;

  @Column(name="date")
  private Date date;

  @Override
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public int compareTo(AbstractComment o) {
    return -1 * getDate().compareTo(o.getDate());
  }
}

package ru.satcit.kolpak.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Aleksei
 * Date: 26.01.16 13:23
 */
@MappedSuperclass
public class AbstractComment implements Comment, Comparable<AbstractComment> {
  private static final String DATE_PATTERN = "dd-MM-yyyy HH:mm";

  @Id
  @GeneratedValue
  @Column(name="id")
  private long id;

  @Column(name="text")
  private String text;

  @Column(name="date")
  private Date date;

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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public String getFormattedDate() {
    return new SimpleDateFormat(DATE_PATTERN).format(getDate());
  }

  @Override
  public int compareTo(AbstractComment o) {
    return -1 * getDate().compareTo(o.getDate());
  }
}

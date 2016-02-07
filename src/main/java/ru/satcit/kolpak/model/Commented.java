package ru.satcit.kolpak.model;

import java.util.List;

/**
 * User: Aleksei
 * Date: 25.01.16 15:44
 */
public interface Commented {
  List<? extends Comment> getComments();
}

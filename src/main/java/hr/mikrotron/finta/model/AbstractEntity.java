package hr.mikrotron.finta.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Used as a base class for database entities
 */
@MappedSuperclass
public abstract class AbstractEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  public boolean persisted() {
    return id != null;
  }

  @Override
  public int hashCode() {
    if (id != null) {
      return id.hashCode();
    }
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof AbstractEntity)) {
      return false; // null or other class
    }
    AbstractEntity other = (AbstractEntity) obj;

    if (id != null) {
      return id.equals(other.id);
    }
    return super.equals(other);
  }
}

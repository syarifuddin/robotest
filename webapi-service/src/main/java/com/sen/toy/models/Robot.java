package com.sen.toy.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.joda.time.LocalDate;

@Entity
@Table(name = "robots")
public class Robot {

    @Id
    @GeneratedValue
    private long id;


    @NotNull
    @Column(name = "name")
    @Size(min = 1)
    private String name;


    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Robot)) return false;
        Robot that = (Robot) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Robot{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}

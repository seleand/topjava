package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */

@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal meal WHERE meal.id=:id AND meal.user=:user"),
        @NamedQuery(name = Meal.GET, query = "SELECT meal FROM Meal meal WHERE meal.id=:id AND meal.user=:user"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT meal FROM Meal meal WHERE meal.user=:user ORDER BY meal.dateTime desc"),
        @NamedQuery(name = Meal.ALL_BETWEEN, query = "SELECT meal FROM Meal meal WHERE meal.user=:user AND meal.dateTime>=:startdatetime AND meal.dateTime<=:enddatetime ORDER BY meal.dateTime desc"),
})

@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")})
public class Meal extends BaseEntity {

    public static final String DELETE = "Meal.DELETE";
    public static final String ALL_SORTED = "Meal.ALL_SORTED";
    public static final String ALL_BETWEEN = "Meal.ALL_BETWEEN";
    public static final String GET = "Meal.GET";

    @Column(name = "date_time", nullable = false)
    @NotEmpty
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotEmpty
    private String description;

    @Column(name = "calories", nullable = false)
    @NotEmpty
    @Size(min = 10, max = 5000)
//    @Digits(fraction = 0, integer = 4)
    private int calories;

//    @CollectionTable(name = "users", joinColumns = @JoinColumn(name = "id"))
//    @Column(name = "name")
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}

package ru.ad.lab6.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.util.Objects;
import lombok.Data;


@Entity
@Data
@Table(name = "bicycle")
public class Bicycle {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "model")
  @Size(min = 3, max = 30, message = "len of model name is not between 3 and 30")
  private String model;

  @Column(name = "brand")
  @Size(min = 3, max = 30, message = "len of brand name is not between 3 and 30")
  private String brand;

  @Column(name = "condition")
  @Size(min = 3, max = 30, message = "len of name of condition is not between 3 and 30")
  private String condition;

  @Column(name = "speeds_count")
  @Min(value = 1, message = "there are no such bicycles!")
  @Max(value = 10, message = "there are no such bicycles!")
  private int speedsCount;

  @Column(name = "price")
  @Min(value = 1, message = "It's not a charity, it's a store!")
  private float price;

}

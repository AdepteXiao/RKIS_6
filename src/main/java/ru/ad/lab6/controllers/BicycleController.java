package ru.ad.lab6.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ad.lab6.models.Bicycle;
import ru.ad.lab6.services.BicycleService;

@Controller
@RequestMapping("/bicycles")
public class BicycleController {
  private final BicycleService bicycleService;


  @Autowired
  public BicycleController(BicycleService bicycleService) {
    this.bicycleService = bicycleService;
  }


  @GetMapping()
  public String index(@RequestParam(name = "price", required = false) Float price, Model model) {
    if (price != null) {
      model.addAttribute("bicycles", bicycleService.findByPrice(price));
    } else {
      model.addAttribute("bicycles", bicycleService.findAll());
    }
    return "bicycles/index";
  }


  @GetMapping("/{id}")
  public String show(@PathVariable("id") int id, Model model) {
    model.addAttribute("bicycle", bicycleService.findOne(id));
    return "bicycles/show";
  }


  @GetMapping("/{id}/edit")
  public String edit(@PathVariable("id") int id, Model model) {
    model.addAttribute("bicycle", bicycleService.findOne(id));
    return "bicycles/edit";
  }


  @GetMapping("/new")
  public String newInstrument(@ModelAttribute("bicycle") Bicycle bicycle) {
    return "bicycles/new";
  }


  @PostMapping()
  public String create(
      @ModelAttribute("bicycle") @Valid Bicycle bicycle,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      return "bicycles/new";
    }

    bicycleService.save(bicycle);
    return "redirect:/bicycles";
  }


  @PatchMapping("/{id}")
  public String update(
      @ModelAttribute("bicycle") @Valid Bicycle bicycle,
      BindingResult bindingResult,
      @PathVariable("id") int id
  ) {
    if (bindingResult.hasErrors()) {
      return "bicycles/edit";
    }
    bicycleService.update(id, bicycle);
    return "redirect:/bicycles";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    bicycleService.delete(id);
    return "redirect:/bicycles";
  }
}

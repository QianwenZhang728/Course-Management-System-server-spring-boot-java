package com.example.myapp.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.myapp.models.Widget;
import com.example.myapp.services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin(origins = "*")
public class WidgetController {
  @Autowired
  WidgetService service;

  @GetMapping("/hello")
  public String sayHello() {
    return "Hello World!!";
  }

  @GetMapping("/api/widgets")
  public List<Widget> findAllWidgets() {
    return service.findAllWidgets();
  }

  @GetMapping("/api/topics/{topicId}/widgets")
  public List<Widget> findWidgetsForTopic(
      @PathVariable("topicId") String topicId) {
    return service.findWidgetsForTopic(topicId);
  }

  @GetMapping("/api/widgets/{wid}")
  public Widget findWidgetById(
      @PathVariable("wid") Integer widgetId) {
    return service.findWidgetById(widgetId);
  }

  @PostMapping("/api/topics/{topicId}/widgets")
  public Widget createWidget(
      @PathVariable("topicId") String topicId,
      @RequestBody Widget widget) {
    return service.createWidget(widget, topicId);
  }

  @PutMapping("/api/widgets/{wid}")
  public Widget updateWidget(
      @PathVariable("wid") Integer widgetId,
      @RequestBody Widget newWidget) {
    return service.updateWidget(widgetId, newWidget);
  }

//  @PutMapping("/api/widgets")
//  public Integer updateWidget(
//      @RequestBody List<Widget> newWidgets) {
//    return service.updateAll(newWidgets);
//  }

  @DeleteMapping("/api/widgets/{wid}")
  public void deleteWidget(
        @PathVariable("wid") Integer widgetId) {
    service.deleteWidget(widgetId);
  }

}

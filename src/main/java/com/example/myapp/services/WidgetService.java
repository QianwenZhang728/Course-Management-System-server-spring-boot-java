package com.example.myapp.services;

import com.example.myapp.models.Widget;
import com.example.myapp.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WidgetService {
  List<Widget> widgets =  new ArrayList<Widget>();

  @Autowired
  WidgetRepository widgetRepository;

  public List<Widget> findAllWidgets() {
    return (List<Widget>) widgetRepository.findAll();
  }

  public Widget findWidgetById(Integer widgetId) {
    return widgetRepository.findById(widgetId).get();
//    for(Widget w: widgets) {
//      if (w.getId().equals(widgetId)) {
//        return w;
//      }
//    }
//    return null;
  }

  public List<Widget> findWidgetsForTopic(String tid) {
    List<Widget> ws = widgetRepository.findWidgetsForTopic(tid);
    Collections.sort(ws);
    return ws;
//    List<Widget> ws = new ArrayList<Widget>();
//    for (Widget w: widgets) {
//      if (w.getTopicId().equals(tid)) {
//        ws.add(w);
//      }
//    }
//    Collections.sort(ws);
//    for( Widget w : ws) {
//      System.out.println("name = " + w.toString() + ", order = " + w.getWidgetOrder());
//    }
//
//    return ws;
  }

  public Widget createWidget(Widget widget, String topicId) {
    widget.setTopicId(topicId);
    widget.setWidgetOrder(widgetRepository.findWidgetsForTopic(topicId).size());
    return widgetRepository.save(widget);
//    widget.setId(123);
//    widget.setTopicId(topicId);
//    widget.setWidgetOrder(this.widgets.stream().filter(w -> w.getTopicId().equals(topicId)).collect(Collectors.toList()).size());
//    widgets.add(widget);
//    return widget;
  }

  public Widget updateWidget(Integer widgetId, Widget newWidget) {
    Optional widgetO = widgetRepository.findById(widgetId);
    if(widgetO.isPresent()) {
      Widget widget = (Widget) widgetO.get();
      widget.setName(newWidget.getName());
      widget.setType(newWidget.getType());
      widget.setSize(newWidget.getSize());
      widget.setText(newWidget.getText());
      widget.setWidgetOrder(newWidget.getWidgetOrder());
      widget.setUrl(newWidget.getUrl());
      widget.setStyle(newWidget.getStyle());
      return widgetRepository.save(widget);
    } else {
      return null;
    }

//    for (Widget w: widgets) {
//      if(w.getId().equals(widgetId)) {
//        w.setName(newWidget.getName());
//        w.setType(newWidget.getType());
//        w.setSize(newWidget.getSize());
//        w.setText(newWidget.getText());
//        w.setWidgetOrder(newWidget.getWidgetOrder());
//        return 1;
//      }
//    }
//    return 0;
  }

  public void deleteWidget(Integer widgetId) {
    widgetRepository.deleteById(widgetId);
//    for (Widget w: widgets) {
//      if(w.getId().equals(widgetId)) {
//        widgets.remove(w);
//        return 1;
//      }
//    }
//    return 0;
  }

//  public Integer updateAll(List<Widget> newWidgets) {
//    this.widgets = newWidgets;
//    return 1;
//  }
}

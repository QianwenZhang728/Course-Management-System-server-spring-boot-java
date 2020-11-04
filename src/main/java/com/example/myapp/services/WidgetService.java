package com.example.myapp.services;

import com.example.myapp.models.Widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class WidgetService {
  List<Widget> widgets =  new ArrayList<Widget>();

  public List<Widget> findAllWidgets() {
    return widgets;
  }

  public Widget findWidgetById(String widgetId) {
    for(Widget w: widgets) {
      if (w.getId().equals(widgetId)) {
        return w;
      }
    }
    return null;
  }

  public List<Widget> findWidgetsForTopic(String tid) {
    List<Widget> ws = new ArrayList<Widget>();
    for (Widget w: widgets) {
      if (w.getTopicId().equals(tid)) {
        ws.add(w);
      }
    }
    Collections.sort(ws);
    for( Widget w : ws) {
      System.out.println("name = " + w.toString() + ", order = " + w.getWidgetOrder());
    }

    return ws;
  }

  public Widget createWidget(Widget widget, String topicId) {
    widget.setId(UUID.randomUUID().toString());
    widget.setTopicId(topicId);
    widget.setWidgetOrder(this.widgets.stream().filter(w -> w.getTopicId().equals(topicId)).collect(Collectors.toList()).size());
    widgets.add(widget);
    return widget;
  }

  public Integer updateWidget(String widgetId, Widget newWidget) {
    for (Widget w: widgets) {
      if(w.getId().equals(widgetId)) {
        w.setName(newWidget.getName());
        w.setType(newWidget.getType());
        w.setSize(newWidget.getSize());
        w.setText(newWidget.getText());
        w.setWidgetOrder(newWidget.getWidgetOrder());
        return 1;
      }
    }
    return 0;
  }

  public Integer deleteWidget(String widgetId) {
    for (Widget w: widgets) {
      if(w.getId().equals(widgetId)) {
        widgets.remove(w);
        return 1;
      }
    }
    return 0;
  }

//  public Integer updateAll(List<Widget> newWidgets) {
//    this.widgets = newWidgets;
//    return 1;
//  }
}

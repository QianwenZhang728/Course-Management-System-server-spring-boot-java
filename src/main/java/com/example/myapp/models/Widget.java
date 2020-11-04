package com.example.myapp.models;

public class Widget implements Comparable<Widget> {
  private String id;
  private String name;
  private String type;
  private String topicId;
  private Integer widgetOrder;
  private String text;
  private String url;
  private Integer size;
  private Integer width;
  private Integer height;
  private String cssClass;
  private String style;
  private String value;

  public Widget() {
    this.size = 1;
    this.text = "";
  }

  public Widget(String id, String name, String type) {
    this.id = id;
    this.name = name;
    this.type = type;
  }

  public Widget(String id, String name, String type, String topicId) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.topicId = topicId;
  }

  public String getId() {
    return id;
  }

  public String getTopicId() {
    return topicId;
  }

  public void setTopicId(String topicId) {
    this.topicId = topicId;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return this.text;
  }

  public Integer getSize() {
    return this.size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public void setWidgetOrder(Integer order) {
    this.widgetOrder = order;
  }

  public Integer getWidgetOrder() {
    return this.widgetOrder;
  }

  public int compareTo(Widget w) {
    if (this.widgetOrder > w.getWidgetOrder()) {
      return 1;
    } else {
      return -1;
    }
  }

  public String toString() {
    return this.getName();
  }

}

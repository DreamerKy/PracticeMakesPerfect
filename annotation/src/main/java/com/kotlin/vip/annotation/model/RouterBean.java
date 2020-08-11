package com.kotlin.vip.annotation.model;

import javax.lang.model.element.Element;

/**
 * Created by likaiyu on 2019/12/8.
 * 路由路径path的最终实体封装类
 */
public class RouterBean {
    public enum Type {
        ACTIVITY,
        CALL
    }

    private Type type;//枚举类型
    private Element element;//类节点
    private Class<?> clazz;//注解使用的类对象
    private String path;//路由地址
    private String group;//路由组

    private RouterBean(Builder builder) {
        this.type = builder.type;
        this.element = builder.element;
        this.clazz = builder.clazz;
        this.path = builder.path;
        this.group = builder.group;
    }

    private RouterBean(Type type, Class<?> clazz, String path, String group) {
        this.type = type;
        this.clazz = clazz;
        this.path = path;
        this.group = group;
    }

    public static RouterBean create(Type type, Class<?> clazz, String path, String group) {
        return new RouterBean(type, clazz, path, group);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public static class Builder {
        private Type type;
        private Element element;
        private Class<?> clazz;
        private String path;
        private String group;

        public Builder setType(Type type) {
            this.type = type;
            return this;
        }

        public Builder setElement(Element element) {
            this.element = element;
            return this;
        }

        public Builder setClazz(Class<?> clazz) {
            this.clazz = clazz;
            return this;
        }

        public Builder setPath(String path) {
            this.path = path;
            return this;
        }

        public Builder setGroup(String group) {
            this.group = group;
            return this;
        }

        public RouterBean build() {
            if (path == null || path.length() == 0) {
                throw new IllegalArgumentException("path必填项为空");
            }
            return new RouterBean(this);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "type=" + type +
                    ", element=" + element +
                    ", clazz=" + clazz +
                    ", path='" + path + '\'' +
                    ", group='" + group + '\'' +
                    '}';
        }
    }


}

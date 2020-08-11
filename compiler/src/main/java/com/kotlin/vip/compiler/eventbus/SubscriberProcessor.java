package com.kotlin.vip.compiler.eventbus;

import com.google.auto.service.AutoService;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by likaiyu on 2020/1/1.
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes({EventBusConstants.SUBSCRIBE_ANNOTATION_TYPES})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedOptions({EventBusConstants.PACKAGE_NAME,EventBusConstants.CLASS_NAME})
public class SubscriberProcessor extends AbstractProcessor {
    //操作Element工具类（类，函数，属性都是ELement）
    private Element elementUtils;
    private Types typeUtils;
    private Messager messager;
    private Filer filer;
    //APT包名
    private String packageName;
    //APT类名
    private String className;
    private final Map<TypeElement, List<ExecutableElement>> methodByClass = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
//        elementUtils = (Element) processingEnvironment.getElementUtils();
//        typeUtils = (Types) processingEnvironment.getTypeUtils();
//        messager = processingEnvironment.getMessager();
//        filer = processingEnvironment.getFiler();











    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }
}

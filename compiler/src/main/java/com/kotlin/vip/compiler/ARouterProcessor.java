package com.kotlin.vip.compiler;

import com.google.auto.service.AutoService;
import com.kotlin.vip.annotation.ARouter;
import com.kotlin.vip.annotation.model.RouterBean;
import com.kotlin.vip.compiler.utils.Constants;
import com.kotlin.vip.compiler.utils.EmptyUtils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.WildcardTypeName;

import java.io.IOException;
import java.util.ArrayList;
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
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

//AutoService固定写法，加个注解即可
//通过auto-service中的@AutoService可以自动生成AutoService注解处理器用来注册
//用来生成META-INF/services/javax.annotation.processing.Processor文件
@AutoService(Processor.class)
//允许支持的注解类型，让注解处理器处理
@SupportedAnnotationTypes("com.kotlin.vip.annotation.ARouter")
//指定JDK编译版本
@SupportedSourceVersion(SourceVersion.RELEASE_7)
//注解处理器接收的参数
@SupportedOptions("content")
public class ARouterProcessor extends AbstractProcessor {
    //操作Element工具类（类、函数、属性都是element）
    private Elements elementUtils;
    //type（类信息）工具类
    private Types typeUtils;
    //用来报告错误，警告和其他提示信息
    private Messager messager;
    //用来生成类、资源
    private Filer filer;

    //子模块名，如app/order/personal,需要拼接类名时用到
    private String moduleName;
    //包名，用于存放Apt生成的类文件
    private String packageNameForAPT;

    //临时map存储，用来存放路由组Group对应的详细Path，生成路由类文件时遍历
    //key:组名"app",value:"app"组的路由路径"Arouter$$Path$$app.class"
    private Map<String, List<RouterBean>> tempPathMap = new HashMap<>();

    //临时map存储，用来存放路由Group信息。生成路由组类文件时遍历
    //key:组名"app",value:类名"Arouter$$Path$$app.class"
    private Map<String, String> tempGroupMap = new HashMap<>();

    /**
     * 进行初始化操作，通过该方法的参数可以获取一些有用的工具类
     *
     * @param processingEnvironment
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementUtils = processingEnvironment.getElementUtils();
        typeUtils = processingEnvironment.getTypeUtils();
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();

        //获取build.gradle传过来的参数
        //通过ProcessingEnvironment获取对应参数
        Map<String, String> option = processingEnvironment.getOptions();
        if (!EmptyUtils.isEmpty(option)) {
            moduleName = option.get(Constants.MODULE_NAME);
            packageNameForAPT = option.get(Constants.APT_PACKAGE);
            messager.printMessage(Diagnostic.Kind.NOTE, "moduleName >>> " + moduleName);
            messager.printMessage(Diagnostic.Kind.NOTE, "packageNameForAPT >>> " + packageNameForAPT);
        }

        if (EmptyUtils.isEmpty(moduleName) || EmptyUtils.isEmpty(packageNameForAPT)) {
            throw new RuntimeException("注解处理器需要的参数moduleName或者packageName为空，请在对应build.gradle进行配置");
        }

//        String content = processingEnvironment.getOptions().get("content");
//        messager.printMessage(Diagnostic.Kind.NOTE, content);

    }

    /**
     * 相当于main 函数，开始处理注解
     * 注解处理器的核心方法，处理具体的注解，生成Java文件
     *
     * @param set
     * @param roundEnvironment
     * @return true 表示后续处理器不会再处理
     */

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (set.isEmpty()) return false;
        //获取所有带ARouter注解的类节点
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ARouter.class);
//        for (Element element : elements) {
            /*//通过类节点获取包节点
            String packageName = elementUtils.getPackageOf(element).getQualifiedName().toString();
            //获取简单类名
            String className = element.getSimpleName().toString();
            messager.printMessage(Diagnostic.Kind.NOTE, "备注解的类：" + className);
            //最终生成类文件名
            String finalClassName = className + "$$ARouter";
            //创建一个新的源文件（Class）,并返回一个对象以允许写入它
            try {
                JavaFileObject sourceFile = null;
                sourceFile = filer.createSourceFile(packageName + "." + finalClassName);
                //定义Writer对象，开始写入
                Writer writer = sourceFile.openWriter();
                // 设置包名
                writer.write("package " + packageName + ";\n");

                writer.write("public class " + finalClassName + " {\n");

                writer.write("public static Class<?> findTargetClass(String path) {\n");

                // 获取类之上@ARouter注解的path值
                ARouter aRouter = element.getAnnotation(ARouter.class);

                writer.write("if (path.equals(\"" + aRouter.path() + "\")) {\n");

                writer.write("return " + className + ".class;\n}\n");

                writer.write("return null;\n");

                writer.write("}\n}");

                // 最后结束别忘了
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }*/

           /* //javapoet简单写法
            String packageName = elementUtils.getPackageOf(element).getQualifiedName().toString();
            String className = element.getSimpleName().toString();
            messager.printMessage(Diagnostic.Kind.NOTE, "被注解的类有：" + className);
            String finalClassName = className + "$$ARouter";

            try {
                ARouter aRouter = element.getAnnotation(ARouter.class);
                //构建方法体
                MethodSpec method = MethodSpec.methodBuilder("findTargetClass")
                        .addModifiers(Modifier.PUBLIC,Modifier.STATIC)
                        .returns(Class.class)//返回class
                        .addParameter(String.class,"path")
                        .addStatement("return path.equals($S) ? $T.class : null",
                                aRouter.path(), ClassName.get((TypeElement) element))
                                .build();
                //构建类
                TypeSpec type = TypeSpec.classBuilder(finalClassName)
                        .addModifiers(Modifier.PUBLIC)
                        .addMethod(method)
                        .build();
                //在指定的包名下生成Java类文件
                JavaFile javaFile = JavaFile.builder(packageName,type)
                        .build();
                javaFile.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        // }
        //javapoet高级写法

        if (!EmptyUtils.isEmpty(elements)) {
            try {
                parseElements(elements);
                return true;
            } catch (IOException e) {
                messager.printMessage(Diagnostic.Kind.NOTE,"出异常了 >>> "+ e.toString());
                e.printStackTrace();
            }
        }
        //必须写返回值，表示处理@ARouter注解完成
        return true;
    }


    //解析所有被@ARouter注解的类元素集合
    private void parseElements(Set<? extends Element> elements) throws IOException {
        //通过Elemen工具类，获取Activity的类型
        TypeElement activityType = elementUtils.getTypeElement(Constants.ACTIVITY);
        //显示类信息（获取被注解节点，类节点）
        TypeMirror activityMirror = activityType.asType();


        for (Element element : elements) {
            TypeMirror elementMirror = element.asType();

            messager.printMessage(Diagnostic.Kind.NOTE, "遍历元素信息：" + elementMirror.toString());

            //获取每个类上的@ARouter注解中的注解值
            ARouter aRouter = element.getAnnotation(ARouter.class);

            //路由详细信息，最终实体封装类
            RouterBean bean = new RouterBean.Builder()
                    .setGroup(aRouter.group())
                    .setPath(aRouter.path())
                    .setElement(element)
                    .build();

            //高级判断：ARouter注解仅能用在类之上，并且规定的Activity
            if (typeUtils.isSubtype(elementMirror, activityMirror)) {
                bean.setType(RouterBean.Type.ACTIVITY);
            } else {
                throw new RuntimeException("@ARouter注解目前仅限用于Activity类之上");
            }
            //赋值临时map存储，用原来存放路由组Group对应的详细Path类对象
            valueOfPathMap(bean);
        }

        // 获取ARouterLoadGroup、ARouterLoadPath类型（生成类文件需要实现的接口）
        TypeElement groupLoadType = elementUtils.getTypeElement("com.kotlin.vip.arouterapi.ARouterLoadGroup"); // 组接口
        TypeElement pathLoadType = elementUtils.getTypeElement(Constants.AROUTE_PATH); // 路径接口

        messager.printMessage(Diagnostic.Kind.NOTE, "activityType：" + activityType);
        messager.printMessage(Diagnostic.Kind.NOTE, "groupLoadType：" + groupLoadType);
        messager.printMessage(Diagnostic.Kind.NOTE, "pathLoadType：" + pathLoadType);

        //第一步：生成路由组Group对应的详细Path类文件，如：ARouter$$Path$$app
        createPathFile(pathLoadType);

        //第二步：生成路由组Group类文件（没有第一步，取不到类文件），如：ARouter$$Group$$app
        createGroupFile(groupLoadType, pathLoadType);

    }

    private void createPathFile(TypeElement pathLoadType) throws IOException {
        if (EmptyUtils.isEmpty(tempPathMap)) return;
        TypeName methodReturns = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ClassName.get(RouterBean.class)
        );

        //遍历分组，每个分组创建一个路径类文件 如：ARouter$$Path$$app
        for (Map.Entry<String, List<RouterBean>> entry : tempPathMap.entrySet()) {
            //方法配置：public Map<String,RouterBean> loadPath(){}
            MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(Constants.PATH_METHOD_NAME)
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC)
                    .returns(methodReturns);
            methodBuilder.addStatement("$T<$T, $T> $N = new $T<>()",
                    ClassName.get(Map.class),
                    ClassName.get(String.class),
                    ClassName.get(RouterBean.class),
                    Constants.PATH_PARAMETER_NAME,
                    HashMap.class);
            //一个分组有若干个详细路径信息
            List<RouterBean> pathList = entry.getValue();
            for (RouterBean routerBean : pathList) {
                methodBuilder.addStatement(
                        "$N.put($S, $T.create($T.$L,$T.class,$S,$S))",
                        Constants.PATH_PARAMETER_NAME,
                        routerBean.getPath(),
                        ClassName.get(RouterBean.class),
                        ClassName.get(RouterBean.Type.class),
                        routerBean.getType(),
                        ClassName.get((TypeElement) routerBean.getElement()),
                        routerBean.getPath(),
                        routerBean.getGroup()
                );
            }

            //遍历之后 返回pathMap;
            methodBuilder.addStatement("return $N", Constants.PATH_PARAMETER_NAME);

            //最终生成类的文件名
            String finalClassName = Constants.PATH_FILE_NAME + entry.getKey();
            messager.printMessage(Diagnostic.Kind.NOTE, "APT生成路由Path类文件：" +
                    packageNameForAPT + "." + finalClassName);

            //生成类文件：ARouter$$Path$$app
            JavaFile.builder(packageNameForAPT,
                    TypeSpec.classBuilder(finalClassName)
                    .addSuperinterface(ClassName.get(pathLoadType))
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(methodBuilder.build())
                    .build())
                    .build()
                    .writeTo(filer);
            tempGroupMap.put(entry.getKey(),finalClassName);
        }


    }

    private void createGroupFile(TypeElement groupLoadType, TypeElement pathLoadType) throws IOException {
        if(EmptyUtils.isEmpty(tempGroupMap) || EmptyUtils.isEmpty(tempPathMap)){
            return;
        }

        //Map<String, Class<? extends ARouterLoadPath>>
        //方法返回值
        TypeName methodReturns = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ParameterizedTypeName.get(ClassName.get(Class.class),
                        WildcardTypeName.subtypeOf(ClassName.get(pathLoadType)))
        );
        //方法配置
        MethodSpec.Builder  methodBuilder = MethodSpec.methodBuilder(Constants.GROUP_METHOD_NAME)
                .addAnnotation(Override.class)//@Override
                .addModifiers(Modifier.PUBLIC)//public
                .returns(methodReturns);//返回值

        //遍历之前：Map<String,Class<?extends ARouterLoadPath>> groupMap = new HashMap<>()
        methodBuilder.addStatement("$T<$T, $T> $N = new $T<>()",
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ParameterizedTypeName.get(ClassName.get(Class.class),
                        WildcardTypeName.subtypeOf(ClassName.get(pathLoadType))),
                Constants.GROUP_PARAMETER_NAME,
                HashMap.class);

        //方法内容配置
        for (Map.Entry<String, String> entry : tempGroupMap.entrySet()) {
            //groupMap.put("app", ARouter$$Path$$app.class);
            methodBuilder.addStatement("$N.put($S, $T.class)",
                    Constants.GROUP_PARAMETER_NAME,
                    entry.getKey(),
                    ClassName.get(packageNameForAPT, entry.getValue()));
        }

        //返回Map
        methodBuilder.addStatement("return $N",Constants.GROUP_PARAMETER_NAME);

        //最终生成的类文件名
        String finalClassName = Constants.GROUP_FILE_NAME + moduleName;

        //生成类文件ARouter$$Group$$app
        JavaFile.builder(packageNameForAPT,
                TypeSpec.classBuilder(finalClassName)
                .addSuperinterface(ClassName.get(groupLoadType))
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodBuilder.build())
                .build())
                .build()
                .writeTo(filer);
    }

    /**
     * 赋值临时map存储，用来存放路由组Group对应的详细path类对象,生成路由路径文件时遍历
     *
     * @param bean 路由详细信息，最终实体封装类
     */
    private void valueOfPathMap(RouterBean bean) {
        if (checkRouterPath(bean)) {
            messager.printMessage(Diagnostic.Kind.NOTE, "RouterBean >>> " + bean.toString());
            //开始赋值Map
            List<RouterBean> routerBeans = tempPathMap.get(bean.getGroup());
            if (EmptyUtils.isEmpty(routerBeans)) {
                routerBeans = new ArrayList<>();
                routerBeans.add(bean);
                tempPathMap.put(bean.getGroup(), routerBeans);
            } else {
                routerBeans.add(bean);
            }
        } else {
            messager.printMessage(Diagnostic.Kind.ERROR, "@ARouter注解未按规范配置，如：/app/MainActivity");
        }


    }

    private boolean checkRouterPath(RouterBean bean) {
        String group = bean.getGroup();
        String path = bean.getPath();
        if (EmptyUtils.isEmpty(path) || !path.startsWith("/")) {
            messager.printMessage(Diagnostic.Kind.ERROR, "@ARouter注解未按规范配置，如：/app/MainActivity");
            return false;
        }

        if (path.lastIndexOf("/") == 0) {
            messager.printMessage(Diagnostic.Kind.ERROR, "@ARouter注解未按规范配置，如：/app/MainActivity");
            return false;
        }

        String finalGroup = path.substring(1, path.indexOf("/", 1));

        if (!EmptyUtils.isEmpty(group) && !group.equals(moduleName)) {
            messager.printMessage(Diagnostic.Kind.ERROR, "@ARouter注解中的group值必须和子模块名一致！");
            return false;
        } else {
            bean.setGroup(finalGroup);
        }
        return true;
    }
}

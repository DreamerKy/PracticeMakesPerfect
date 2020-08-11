package com.example.dreams.launchertask;

import android.os.Build;
import android.util.ArraySet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import androidx.annotation.RequiresApi;

/**
 * Created by likaiyu on 2020/2/18.
 */
class TaskSortUtil {

    //高优先级task
    private static List<Task> sNewTasksHigh = new ArrayList<>();

    /**
     * 任务的有向无环图的拓扑排序
     *
     * @param originTasks
     * @param mClassAllTasks
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static synchronized List<Task> getSortResult(List<Task> originTasks, List<Class<? extends Task>> mClassAllTasks) {

        long makeTime = System.currentTimeMillis();
        Set<Integer> dependSet = new ArraySet<>();
        Graph graph = new Graph(originTasks.size());
        for (int i = 0; i < originTasks.size(); i++) {
            Task task = originTasks.get(i);
            if (task.ismIsSend() || task.dependsOn() == null || task.dependsOn().size() == 0) {
                continue;
            }
            for (Class cls : task.dependsOn()) {
                int indexOfDepend = getIndexOfTask(originTasks, mClassAllTasks, cls);
                if(indexOfDepend<0) {
                    throw new IllegalStateException(task.getClass().getSimpleName() +
                            "depends on" + cls.getSimpleName() + "can not be found in task list ");
                }
                dependSet.add(indexOfDepend);
                graph.addEdge(indexOfDepend,i);
            }
        }

        List<Integer> indexList = graph.topologicalSort();
        List<Task> newTasksAll = getResultTasks(originTasks,dependSet,indexList);

        return newTasksAll;
    }

    private static List<Task> getResultTasks(List<Task> originTasks, Set<Integer> dependSet, List<Integer> indexList) {
        List<Task> newTasksAll = new ArrayList<>(originTasks.size());
        //被别人依赖的
        List<Task> newTasksDepended = new ArrayList<>();
        //没有依赖的
        List<Task> newTasksWithOutDepend = new ArrayList<>();
        //需要提升自己优先级的，先执行
        List<Task> newTasksRunAsSoon = new ArrayList<>();

        //顺序：被别人依赖的--->需要提升优先级的--->需要被等待的--->没有依赖的
        for(int index : indexList){
            if(dependSet.contains(index)){
                newTasksDepended.add(originTasks.get(index));
            }else{
                Task task = originTasks.get(index);
                if(task.needRunAsSoon()){
                   newTasksRunAsSoon.add(task);
                }else{
                    newTasksWithOutDepend.add(task);
                }
            }
        }

        sNewTasksHigh.addAll(newTasksDepended);
        sNewTasksHigh.addAll(newTasksRunAsSoon);
        newTasksAll.addAll(sNewTasksHigh);
        newTasksAll.addAll(newTasksWithOutDepend);
        return newTasksAll;
    }

    private static int getIndexOfTask(List<Task> originTasks, List<Class<? extends Task>> mClassAllTasks, Class cls) {
        int index = mClassAllTasks.indexOf(cls);
        if (index >= 0) {
            return index;
        }
        for (int i = 0; i < originTasks.size(); i++) {
            if (cls.getSimpleName().equals(originTasks.get(i).getClass().getSimpleName())) {
                return i;
            }
        }
        return index;
    }
}

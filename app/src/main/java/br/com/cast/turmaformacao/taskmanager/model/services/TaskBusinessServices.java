package br.com.cast.turmaformacao.taskmanager.model.services;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.http.TaskService;
import br.com.cast.turmaformacao.taskmanager.model.persistence.LabelRepository;
import br.com.cast.turmaformacao.taskmanager.model.persistence.TaskRepository;


public final class TaskBusinessServices {

    private TaskBusinessServices() {
        super();
    }

    public static List<Task> findAll() {
        List<Task> list = TaskRepository.getAll();

        for(Task t: list){
            t.setLabel(LabelRepository.getId(t.getLabel().getId()));
        }

        return list;
    }

    public static void save(Task task) {
        TaskRepository.save(task);
    }

    public static void syncronized(){
        List<Task> webtasks = TaskService.getTaskWebId();

        for(Task task: webtasks){

            Long id = TaskRepository.getIdByWebId(task.getWeb_id());

            task.setId(id == null ? null : id);

            save(task);
        }
    }

    public static void delete(Task task){
        TaskRepository.delete(task.getId());
    }

}

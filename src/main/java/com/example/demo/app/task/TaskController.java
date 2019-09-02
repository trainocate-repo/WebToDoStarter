package com.example.demo.app.task;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;

/**
 * ToDoアプリ
 */
@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    /**
     * タスクの一覧を表示します
     * @param taskForm
     * @param model
     * @return resources/templates下のHTMLファイル名
     */
    @GetMapping
    public String task(TaskForm taskForm, Model model) {
    	
    	//新規登録か更新かを判断する仕掛け
        
        //Taskのリストを取得する
        
        model.addAttribute("list", "");
        model.addAttribute("title", "タスク一覧");

        return "task/index";
    }

    /**
     * タスクデータを一件挿入
     * @param taskForm
     * @param result
     * @param model
     * @param principal
     * @return
     */
    @PostMapping("/insert")
    public String insert(
    	@Valid @ModelAttribute TaskForm taskForm,
        BindingResult result,
        Model model) {
    	
    	//削除してください
    	Task task = null;
    	
    	//TaskFormのデータをTaskに格納
        
        if (!result.hasErrors()) {
        	
        	//一件挿入後リダイレクト
        	
            return "";
        } else {
            taskForm.setNewTask(true);
            model.addAttribute("taskForm", taskForm);
            List<Task> list = taskService.findAll();
            model.addAttribute("list", list);
            model.addAttribute("title", "タスク一覧（バリデーション）");
            return "task/index";
        }
    }

    /**
     * 一件タスクデータを取得し、フォーム内に表示
     * @param taskForm
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String showUpdate(
    	TaskForm taskForm,
        @PathVariable int id,
        Model model) {

    	//Taskを取得(Optionalでラップ)
        
        //TaskFormへの詰め直し
        
        //TaskFormがnullでなければ中身を取り出し
		
        model.addAttribute("taskForm", "");
        List<Task> list = taskService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("taskId", id);
        model.addAttribute("title", "更新用フォーム");

        return "task/index";
    }
    
    /**
     * タスクidを取得し、一件のデータ更新
     * @param taskForm
     * @param result
     * @param id
     * @param model
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/update")
    public String update(
    	@Valid @ModelAttribute TaskForm taskForm,
    	BindingResult result,
    	@RequestParam("taskId") int taskId,
    	Model model,
    	RedirectAttributes redirectAttributes) {
        
    	//TaskFormのデータをTaskに格納
    	
        if (!result.hasErrors()) {
        	
        	//更新処理、フラッシュスコープの使用、リダイレクト（個々の編集ページ）
        	
            return "" ;
        } else {
            model.addAttribute("taskForm", taskForm);
            model.addAttribute("title", "タスク一覧");
            return "task/index";
        }
        
        
    }

    /**
     * タスクidを取得し、一件のデータ削除
     * @param id
     * @param model
     * @return
     */
    @PostMapping("/delete")
    public String delete(
    	@RequestParam("taskId") String id,
    	Model model) {
    	
    	//タスクを一件削除しリダイレクト
    	
        return "";
    }

    /**
     * TaskFormのデータをTaskに入れて返す
     * @param taskForm
     * @param taskId 新規登録の場合は0を指定
     * @return
     */
    private Task makeTask(TaskForm taskForm, int taskId) {
        Task task = new Task();
        if(taskId != 0) {
        	task.setId(taskId);
        }
        task.setUserId(1);
        task.setTypeId(taskForm.getTypeId());
        task.setTitle(taskForm.getTitle());
        task.setDetail(taskForm.getDetail());
        task.setDeadline(taskForm.getDeadline());
        return task;
    }

    /**
     * TaskのデータをTaskFormに入れて返す
     * @param task
     * @return
     */
    private TaskForm makeTaskForm(Task task) {
    	
        TaskForm taskForm = new TaskForm();

        taskForm.setTypeId(task.getTypeId());
        taskForm.setTitle(task.getTitle());
        taskForm.setDetail(task.getDetail());
        taskForm.setDeadline(task.getDeadline());
        taskForm.setNewTask(false);
        
        return taskForm;
    }

}
package com.example.demo.app.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Profile;
import com.example.demo.repository.ProfileRepository;

/**
 * プロフィール表示・編集・削除
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileRepository rep;

	@Autowired
	private ProfileService service;

	@Autowired
	public ProfileController(ProfileRepository rep) {
		this.rep =rep;
	}

	/**
	 * プロフィール一覧を表示する
	 * @param form
	 * @param model
	 * @return
	 */
    @GetMapping
    public String getProfile(@ModelAttribute ProfileForm form, Model model) {

    	model.addAttribute("profiles", rep.findAll());
    	model.addAttribute("count", rep.count());

        return "profile/list";
    }

    /**
     * 新規登録画面に遷移する
     * @param profile
     * @param model
     * @return
     */
    @GetMapping("/input")
    public String getInput(@ModelAttribute Profile profile, Model model) {

        return "profile/Input";
    }


    /**
     * 新規登録する
     * @param profile
     * @param model
     * @return
     */
    @PostMapping("/input")
    public String postInput(@ModelAttribute  Profile profile, Model model) {

    	//新規登録
    	service.save(profile);

    	model.addAttribute("profiles", rep.findAll());
    	model.addAttribute("count", rep.count());

    	return "profile/list";
    }

    /**
     *各IDのプロフィール詳細に飛ぶ
     * @param form
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public String getDetail(@ModelAttribute  ProfileForm form, Model model, @PathVariable("id")Integer id) {

    	//プロフィールを取得する
    	Profile profile = service.getProfile(id);

    	model.addAttribute("profile", profile);

    	return "profile/detail";
    }

    /**
     *編集画面に遷移する
     * @param form
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/edit/{id}")
    public String getEdit(@ModelAttribute ProfileForm form, Model model, @PathVariable("id")Integer id) {

    	//プロフィールを取得する
    	Profile profile = service.getProfile(id);

    	model.addAttribute("profile", profile);

        return "profile/edit";
    }

    /**
     * 編集を保存する
     * @param profile
     * @param model
     * @return
     */
    @PostMapping("/edit")
    public String postEdit(@ModelAttribute  Profile profile, Model model) {

    	rep.saveAndFlush(profile);

    	model.addAttribute("profiles", rep.findAll());
    	model.addAttribute("count", rep.count());

    	return "profile/list";
    }

    /**
     * 削除画面に遷移する
     * @param form
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String getDeleteId(@ModelAttribute ProfileForm form, Model model, @PathVariable("id")Integer id) {

    	//プロフィールを取得する
    	Profile profile = service.getProfile(id);

    	model.addAttribute("profile", profile);

        return "profile/delete";
    }

    /**
     * 削除を確定する
     * @param profile
     * @param model
     * @return
     */
    @PostMapping("/delete")
    public String postDelete(@ModelAttribute  Profile profile, Model model) {

    	rep.delete(profile);

    	model.addAttribute("profiles", rep.findAll());
    	model.addAttribute("count", rep.count());

    	return "profile/list";
    }

}
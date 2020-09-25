package com.example.demo.app.profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.model.Profile;
import com.example.demo.domain.model.ProfileForm;
import com.example.demo.domain.model.ProfileRepository;
import com.example.demo.service.ProfileService;

/**
 * ToDoアプリ
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileRepository rep;

	@Autowired
	public ProfileController(ProfileRepository rep) {
		this.rep =rep;
	}

	@Autowired
	private ProfileService profileService;

	Map<Integer, Profile> profileMap = new HashMap<>();
	List<Integer>profileList = new ArrayList<>();

	int profileId ;

	OptionalInt profileNewId ;

    @GetMapping
    public String getProfile(@ModelAttribute ProfileForm form, Model model) {

    	for(Profile profiles : rep.findAll()) {
    		profileMap.put(profiles.getId(), profiles);
    		profileList.add(profiles.getId());
    	}

    	profileNewId = profileList.stream().mapToInt(v -> v+1).max()  ;

    	model.addAttribute("success", "サクセス");

    	model.addAttribute("profiles", rep.findAll());
    	model.addAttribute("count", rep.count());

        return "profile/list";
    }

    @GetMapping("/input")
    public String getInput(@ModelAttribute Profile profile, Model model) {

        return "profile/Input";
    }


    @PostMapping("/input")
    public String postInput(@ModelAttribute  Profile profile, Model model) {

    	profile.setId(profileNewId.getAsInt());
    	rep.saveAndFlush(profile);

    	for(Profile profiles : rep.findAll()) {
    		profileMap.put(profiles.getId(), profiles);
    		profileList.add(profiles.getId());
    	}

    	profileNewId = profileList.stream().mapToInt(v -> v+1).max()  ;

    	model.addAttribute("profiles", rep.findAll());
    	model.addAttribute("count", rep.count());

    	return "profile/list";
    }

    @GetMapping("/detail/{id}")
    public String getDetail(@ModelAttribute  ProfileForm form, Model model, @PathVariable("id")Integer id) {

    	model.addAttribute("detail", profileMap.get(id));

    	return "profile/detail";
    }

    @GetMapping("/edit/{id}")
    public String getEdit(@ModelAttribute ProfileForm form, Model model, @PathVariable("id")Integer id) {

    	model.addAttribute("edit", profileMap.get(id));
    	profileId = id;

        return "profile/edit";
    }

    @PostMapping("/edit")
    public String postEdit(@ModelAttribute  Profile profile, Model model) {

    	profile.setId(profileId);
    	rep.saveAndFlush(profile);

    	model.addAttribute("profiles", rep.findAll());
    	model.addAttribute("count", rep.count());

    	return "profile/list";
    }

    @GetMapping("/delete/{id}")
    public String getDeleteId(@ModelAttribute ProfileForm form, Model model, @PathVariable("id")Integer id) {

    	model.addAttribute("detail", profileMap.get(id));
    	profileId = id;

        return "profile/delete";
    }

    @GetMapping("/delete")
    public String getDelete(@ModelAttribute  Profile profile, Model model) {

    	profile.setId(profileId);
    	rep.delete(profile);

    	model.addAttribute("profiles", rep.findAll());
    	model.addAttribute("count", rep.count());

    	return "profile/list";
    }

}
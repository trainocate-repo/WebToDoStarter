package com.example.demo.app.profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Profile;
import com.example.demo.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository rep;

	/**
	 * 新規登録
	 */
	@Override
	public void save(Profile profile) {

    	OptionalInt profileNewId;
    	List <Integer> profileList =new ArrayList<>();

    	//DBから最後のIDを取得し、1を加算する
    	rep.findAll().forEach(p -> profileList.add(p.getId()));
    	profileNewId = profileList.stream().mapToInt(v -> v+1).max()  ;

    	profile.setId(profileNewId.getAsInt());
    	rep.saveAndFlush(profile);

	}

	/**
	 *プロフィール詳細の取得
	 */
	@Override
	public Profile getProfile(Integer id) {

    	Map<Integer, Profile> profileMap = new HashMap<>();
    	rep.findAll().forEach(p -> profileMap.put(p.getId(), p));

       	//FIXME マップに入れずに検索結果を格納したい
    	// rep.findById(id).stream().collect(Collectors.toList()));

    	return profileMap.get(id);
	}



}

package com.example.demo.app.profile;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Profile;

@Service
public interface ProfileService {

	/**
	 * 新規登録
	 * @param profile
	 */
	void save(Profile profile);

	/**
	 * プロフィール詳細を取得
	 * @param id
	 * @return
	 */
	Profile getProfile(Integer id);




}

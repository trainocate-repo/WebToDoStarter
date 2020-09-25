package com.example.demo.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Profile implements Serializable{

	@Id
	//@GeneratedValue
	private Integer id =0;

	/**
	 * name 名前
	 */
	private String name;

	/**
	 * appealingPoint アピールポイント
	 */
	private String appealingPoint;

	/**
	 * duties 職務
	 */
	private String duties;

	/**
	 * challenge 将来取り組みたいこと
	 */
	private String challenge;

	/**
	 * workHistory 職歴
	 */
	private String workHistory;

	/**
	 * skil スキル
	 */
	private String skill;

	/**
	 * hobby 趣味
	 */
	private String hobby;

	/**
	 *idの取得
	 *
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 *idの設定
	 *
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 *nameの取得
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 *nameの設定
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *appealingPointの取得
	 *
	 * @return appealingPoint
	 */
	public String getAppealingPoint() {
		return appealingPoint;
	}

	/**
	 *appealingPointの設定
	 *
	 * @param appealingPoint
	 */
	public void setAppealingPoint(String appealingPoint) {
		this.appealingPoint = appealingPoint;
	}

	/**
	 *dutiesの取得
	 *
	 * @return duties
	 */
	public String getDuties() {
		return duties;
	}

	/**
	 *dutiesの設定
	 *
	 * @param duties
	 */
	public void setDuties(String duties) {
		this.duties = duties;
	}

	/**
	 *challengeの取得
	 *
	 * @return challenge
	 */
	public String getChallenge() {
		return challenge;
	}

	/**
	 *challengeの設定
	 *
	 * @param challenge
	 */
	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

	/**
	 *workHistoryの取得
	 *
	 * @return workHistory
	 */
	public String getWorkHistory() {
		return workHistory;
	}

	/**
	 *workHistoryの設定
	 *
	 * @param workHistory
	 */
	public void setWorkHistory(String workHistory) {
		this.workHistory = workHistory;
	}

	/**
	 *skillの取得
	 *
	 * @return skill
	 */
	public String getSkill() {
		return skill;
	}

	/**
	 *skillの設定
	 *
	 * @param skill
	 */
	public void setSkill(String skill) {
		this.skill = skill;
	}

	/**
	 *hobbyの取得
	 *
	 * @return hobby
	 */
	public String getHobby() {
		return hobby;
	}

	/**
	 *hobbyの設定
	 *
	 * @param hobby
	 */
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}




}
